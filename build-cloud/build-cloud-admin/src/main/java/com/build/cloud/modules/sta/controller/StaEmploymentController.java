package com.build.cloud.modules.sta.controller;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beust.jcommander.internal.Maps;
import com.build.cloud.common.utils.DateUtils;
import com.build.cloud.common.utils.cache.RedisKeys;
import com.build.cloud.core.base.controller.AbstractController;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.modules.sta.StaEmploymentBean;
import com.build.cloud.modules.sta.service.IStaEmploymentService;
import com.google.common.collect.Lists;
import com.sunsine.common.util.MathUtil;
import com.sunsine.common.util.StringUtil;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
@RestController
@RequestMapping("/sta/work")
public class StaEmploymentController extends AbstractController {
	@Autowired
	private IStaEmploymentService staEmploymentService;
	@GetMapping("/v1/syncWorkerRecord")
	public Result syncWorkerRecord() {
		staEmploymentService.syncWorkerRecord();
		return Result.ok("用工统计同步成功！");
	}
	@GetMapping("/v1/getStaEmployment")
	public Result getStaEmployment(@RequestParam Map<String, Object> params) {
		if (CollectionUtil.isEmpty(params)) {
			params = Maps.newHashMap();
			params.put("time", DateUtil.date());
		}
		String companyId = redisUtils.get(RedisKeys.getDefCom(getUserId()));
		params.put("companyId", companyId);
		String t = MapUtil.getStr(params, "time");
		if (StringUtil.isNull(t)) {
			params.put("time", DateUtil.date());
		}
		Date time = DateUtil.parse(MapUtil.getStr(params, "time"));
		List<String> mDay = DateUtils.getAllDaysMonthByDate(time);
		List<StaEmploymentBean> list = staEmploymentService.selectStaEmployment(params);
		List<Map<String, Object>> rList = Lists.newArrayList();
//		List<Map<String, Object>> totalDailyList = staEmploymentService.selectByDay(params);
//		Double totalDaily = 0D; // 记录不同班组同一天合计工时
		for (StaEmploymentBean entity : list) {
			Map<String, Object> map = Maps.newHashMap();
			map.put("teamId", entity.getTeamId());
			map.put("teamName", entity.getTeamName());
			map.put("teamTypeName", entity.getTeamTypeName());
			map.put("totalWorkHours", entity.getTotalWorkHours());
			int[] staDays = StrUtil.splitToInt(entity.getStaDay(), ",");
			String[] workHours = StrUtil.split(entity.getWorkHours(), ",");
			int len = staDays.length;
			List<StaEmploymentBean> nList = Lists.newArrayList();
			int i = 0;
			Double teamTotal = 0D; // 同一班组当月总工时
			l: for (String day : mDay) {
				StaEmploymentBean bean = new StaEmploymentBean();
//				for (Map<String, Object> td : totalDailyList) {
//					int month = MapUtil.getInt(td, "sta_day");
//					int dd = MapUtil.getInt(td, "sta_day");
//					if (i < len && staDays[i] == dd) {
//						Double totalDaily = MapUtil.getDouble(td, "totalDaily");
//						map.put("totalDaily", totalDaily);
//					}
//				}
				if (i < len && staDays[i] == Convert.toInt(day)) {
					BeanUtil.copyProperties(entity, bean);
					bean.setStaDay(staDays[i] + "");
					bean.setWorkHours(workHours[i]);
					teamTotal = MathUtil.add(teamTotal, Convert.toDouble(workHours[i]));
					i++;
				} else {
					bean.setWorkHours("0");
					bean.setTeamName(entity.getTeamName());
					bean.setTeamTypeName(entity.getTeamTypeName());
				}
				bean.setStaDay(day);
				nList.add(bean);
				continue l;
			}
			map.put("teamTotal", teamTotal);
			map.put("list", nList);
			rList.add(map);
		}
		return Result.ok().putList(rList);
	}
}
