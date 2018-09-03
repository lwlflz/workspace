package com.build.cloud.modules.sys.controller;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.build.cloud.common.annotation.SysLog;
import com.build.cloud.common.constant.CacheConstant;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.core.base.controller.AbstractController;
import com.build.cloud.modules.sys.entity.SysPostEntity;
import com.build.cloud.modules.sys.service.ISysPostService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
/**
 * @ClassName: SysPostController
 * @Description: 员工岗位表
 * @author: liutao
 * @date: 2018年3月31日 上午9:32:17
 */
@RestController
@RequestMapping("/sys/post")
public class SysPostController extends AbstractController {
	@Autowired
	private ISysPostService sysPostService;
	
	/**
	 * 列表
	 */
	@GetMapping("/v1/list")
	@RequiresPermissions("sys:post:list")
	public Result list(@RequestParam Map<String, Object> params) {
		PageUtils page = sysPostService.queryPage(params);
		return Result.ok().put(page);
	}
	
	/**
	 * 
	 * @Title: all   
	 * @Description: 获取所有岗位
	 * @param @return    设定文件   
	 * @return Result    返回类型   
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("/v1/all")
	public Result all() {
		List<Map<String, Object>> result = (List<Map<String, Object>>)redisUtils.get(CacheConstant.SYS_POST_KEY, List.class);
		if (CollectionUtil.isEmpty(result)) {
			result = Lists.newArrayList();
			EntityWrapper<SysPostEntity> wrapper = new EntityWrapper<SysPostEntity>();
			wrapper.eq("status", "0");
			wrapper.orderBy("post_sort IS NULL ASC");
			List<SysPostEntity> list = sysPostService.selectList(wrapper);
			for (SysPostEntity entity : list) {
				Map<String, Object> map = Maps.newLinkedHashMap();
				map.put("id", entity.getId());
				map.put("postName", entity.getPostName());
				result.add(map);
			}
			redisUtils.set(CacheConstant.SYS_POST_KEY, result, CacheConstant.EXPIRED_MONTH);
		}
		
		return Result.ok().putList(result);
	}
	
	/**
	 * 信息
	 */
	@RequestMapping("/v1/info")
	@RequiresPermissions("sys:post:info")
	public Result info(@RequestBody String id) {
		JSONObject obj = JSON.parseObject(id);
		id = obj.getString("id");
		if(StrUtil.isBlank(id)){
			return Result.error("ID不能为空");
		}
		SysPostEntity sysPost = sysPostService.selectById(id);
		return Result.ok().putEntity(sysPost);
	}
	/**
	 * 保存
	 */
	@SysLog("保存公司岗位")
	@PostMapping("/v1/save")
	@RequiresPermissions("sys:post:save")
	public Result save(@RequestBody SysPostEntity sysPost) {
		sysPostService.insert(sysPost);
		redisUtils.delete(CacheConstant.SYS_POST_KEY);
		return Result.ok();
	}
	/**
	 * 修改
	 */
	@PostMapping("/v1/update")
	@RequiresPermissions("sys:post:update")
	public Result update(@RequestBody SysPostEntity sysPost) {
		sysPostService.updateById(sysPost);
		redisUtils.delete(CacheConstant.SYS_POST_KEY);
		return Result.ok();
	}
	/**
	 * 删除
	 */
	@PostMapping("/v1/delete")
	@RequiresPermissions("sys:post:delete")
	public Result delete(@RequestBody String id) {
		id = getDelId(id);
		if(StrUtil.isEmpty(id)){
			return Result.error("ID不能为空");
		}
		sysPostService.deleteById(id);
		redisUtils.delete(CacheConstant.SYS_POST_KEY);
		return Result.ok();
	}
}
