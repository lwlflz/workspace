package com.build.cloud.modules.bs.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.build.cloud.common.exception.BusinessException;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.validator.ValidatorUtils;
import com.build.cloud.core.base.controller.AbstractController;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.modules.bs.bean.CodeBean;
import com.build.cloud.modules.bs.entity.BsTeamEntity;
import com.build.cloud.modules.bs.service.IBsTeamService;
import com.build.cloud.modules.productplan.dto.ProRota;
import com.build.cloud.modules.productplan.service.IProRotaService;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
/**
 * @className BsTeamController
 * @descripion 班组档案
 * @author huangchao
 * @date 2018年4月12日下午7:36:28
 */
@RestController
@RequestMapping("/bs/team")
public class BsTeamController extends AbstractController {
	@Autowired
	private IBsTeamService bsTeamService;
	@Autowired
	private IProRotaService proRotaService;
	/**
	 * 班组档案 --列表分页
	 */
	@GetMapping("/v1/list")
	@RequiresPermissions("bs:team:list")
	public Result list(@RequestParam Map<String, Object> params) {
		params.put("companyId", getCurrentCompanyId());
		PageUtils page = bsTeamService.queryPage(params);
		return Result.ok().put("result", page);
	}
	/**
	 * 班组档案 --查询班组分类
	 */
	@GetMapping("/v1/teamtype")
	public Result findTeamType() {
		List<CodeBean> list = super.getTeamType();
		return Result.ok().putList(list);
	}
	/**
	 * 班组档案 --信息
	 */
	@GetMapping("/v1/info")
	@RequiresPermissions("bs:team:info")
	public Result info(String id) {
		if (StrUtil.isBlank(id)) {
			return Result.error("ID不能为空");
		}
		BsTeamEntity entity = bsTeamService.selectById(id);
		return Result.ok().putEntity(entity);
	}
	/**
	 * 班组档案 --保存
	 */
	@RequestMapping("/v1/save")
	@RequiresPermissions("bs:team:save")
	public Result save(@RequestBody BsTeamEntity entity) {
		ValidatorUtils.validateEntity(entity);
		checkData(entity, 1);// 参数校验
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("team_code", entity.getTeamCode());
		map.put("team_name", entity.getTeamName());
		List<BsTeamEntity> teamList = bsTeamService.selectByMap(map);
		// 校验数据是否已经存在，防止循环插入
		if (!CollectionUtil.isEmpty(teamList)) {
			return Result.error("记录已经存在请检查后重试");
		}
		entity.setCompanyId(getCurrentCompanyId());
		bsTeamService.insert(entity);
		return Result.ok();
	}
	/**
	 * 班组档案 -- 删除
	 */
	@RequestMapping("/v1/delete")
	@RequiresPermissions("bs:team:delete")
	public Result delete(@RequestBody String id) {
		id = getDelId(id);
		if (StrUtil.isBlank(id)) {
			return Result.error("ID不能为空");
		}

		
		int num = proRotaService.selectCount(new EntityWrapper<ProRota>().eq("team_id", id));
		if(num > 0) {
			return Result.error("该数据已经关联项目花名册数据，请检查 ");
		}
		bsTeamService.deleteById(id);
		return Result.ok();
	}
	/**
	 * 班组档案 -- 修改
	 */
	@RequestMapping("/v1/update")
	@RequiresPermissions("bs:team:update")
	public Result update(@RequestBody BsTeamEntity entity) {
		ValidatorUtils.validateEntity(entity);
		checkData(entity, 2);// 参数校验
		EntityWrapper<BsTeamEntity> wrapper = new EntityWrapper<BsTeamEntity>();
		wrapper.eq("team_code", entity.getTeamCode());
		wrapper.eq("team_name", entity.getTeamName());
		wrapper.eq("team_leader_id", entity.getTeamLeaderId());
		wrapper.notIn("id", entity.getId());
		int count = bsTeamService.selectCount(wrapper);
		if (count > 0) {
			return Result.error("当前班组已有对应记录");
		}
		bsTeamService.updateAllColumnById(entity);
		return Result.ok();
	}
	
	/**
	 * 根据项目id查询班组信息或者
	 * @param params
	 * @return
	 */
	@GetMapping("/v1/teamList")
	@RequiresPermissions("bs:team:list")
	public Result getTeam(@RequestParam Map<String, Object> params) {
		params.put("companyId", getCurrentCompanyId());
		return new Result().put("result", bsTeamService.queryTeam(params));
	}
	/**
	 * 参数校验
	 * @param entity 班组档案实体对象
	 * @param mark 标识 1新增 2修改
	 */
	private void checkData(BsTeamEntity entity, int mark) {
		if (mark == 2 && StrUtil.isBlank(entity.getId())) {
			throw new BusinessException("ID不能为空");
		}
		if (StrUtil.isBlank(entity.getTeamType())) {
			throw new BusinessException("班组类型不能为空");
		}
		if (StrUtil.isBlank(entity.getTeamLeader())) {
			throw new BusinessException("班组负责人不能为空");
		}
		if (StrUtil.isBlank(entity.getTeamIdnum())) {
			throw new BusinessException("班组负责人身份证不能为空");
		}
//		if (StrUtil.isBlank(entity.getPhone())) {
//			throw new BusinessException("班组负责人电话不能为空");
//	     	}
		if (StrUtil.isBlank(entity.getBelong())) {
			throw new BusinessException("所属地址不能为空");
		}
	}
}
