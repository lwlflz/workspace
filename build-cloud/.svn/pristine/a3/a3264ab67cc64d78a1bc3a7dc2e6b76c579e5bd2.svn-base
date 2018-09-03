package com.build.cloud.modules.productplan.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hutool.core.map.MapUtil;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.beust.jcommander.internal.Maps;
import com.build.cloud.core.base.controller.AbstractController;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.modules.bs.entity.BsCodeEntity;
import com.build.cloud.modules.bs.service.IBsProjectService;
import com.build.cloud.modules.productplan.dto.ProPlanDetail;
import com.build.cloud.modules.productplan.dto.ProPlanInfo;
import com.build.cloud.modules.productplan.service.IProPlanDetailService;
import com.build.cloud.modules.productplan.service.IProPlanInfoService;
import com.build.cloud.modules.sys.entity.SysUserEntity;
import com.build.cloud.modules.sys.shiro.ShiroUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;

/**
 * <p>
 * 前端控制器
 * </p>
 * 
 * @author liangsen
 * @since 2018-04-23
 */
@Controller
@RequestMapping("/pp/proPlanInfo")
public class ProPlanInfoController extends AbstractController {
	@Autowired
	private IProPlanInfoService proPlanInfoService;

	@Autowired
	private IProPlanDetailService proPlanDetailService;
	
	@Autowired
	private IBsProjectService bsProjectService;

	/**
	 * mpp文件导入返回json
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	// @PostMapping("/v1/upload")
	// @ResponseBody
	// public Result uploadFile(HttpServletRequest request, HttpServletResponse
	// response) {
	// try {
	// MultipartHttpServletRequest fileRequest =
	// (MultipartHttpServletRequest)request;
	// // MultipartFile file = fileRequest.getFile("file");
	// MultipartFile file = null;
	// Map<String, MultipartFile> pMap = fileRequest.getFileMap();
	// for (String keyStr : pMap.keySet()) {
	// file = pMap.get(keyStr);
	// }
	// List<ProPlanDetail> list = MpxjUtil.readFile(file.getInputStream());
	// return new Result().putList(list);
	// } catch (Exception e) {
	// logger.error("文件导入失败", e);
	// return Result.error("文件导入失败："+e.getMessage());
	// }
	// }

	/**
	 * 保存生产任务和详情
	 * 
	 * @param info
	 * @return
	 */
	@PostMapping("/v1/save")
	@ResponseBody
	public Result insertProPlan(@RequestBody ProPlanInfo info) {
		try {
			proPlanInfoService.insertProPlan(info);
			return Result.ok("保存成功");
		} catch (Exception e) {
			logger.error("保存失败", e);
			return Result.error("保存失败：" + e.getMessage());
		}
	}

	/**
	 * 修改生产任务和详情
	 * 
	 * @param info
	 * @return
	 */
	@PostMapping("/v1/update")
	@ResponseBody
	public Result update(@RequestBody ProPlanInfo info) {
		try {
			proPlanInfoService.updateProPlan(info);
			return Result.ok("修改成功");
		} catch (Exception e) {
			logger.error("修改失败", e);
			return Result.error("修改失败：" + e.getMessage());
		}
	}

	/**
	 * 根据版本号查询生产计划(无版本号则返回最新版本)
	 * 
	 * @param info
	 * @return
	 */
	@GetMapping("/v1/selectProPlanByVersion")
	@ResponseBody
	public Result selectProPlanByVersion(ProPlanInfo info) {
		try {
			// 为了判断当前用户是否集团管理员(非集团管理员才能新增生产计划)
			SysUserEntity user = ShiroUtils.getUserEntity();
			String mgrType = user.getMgrType();
			ProPlanInfo ppi = new ProPlanInfo();
			if (Objects.equal(mgrType, "2")) {
				ppi.setAllowOpered(false);
			}

			if (StringUtils.isEmpty(info.getProjectId()) && StringUtils.isEmpty(info.getId())) {
				return new Result().put(ppi);
			}

			ProPlanInfo res = proPlanInfoService.selectProPlanByVersion(info);
			if (res == null) {
				return new Result().put(ppi);
			}
			
			return new Result().put(res);
		} catch (Exception e) {
			logger.error("查询异常", e);
			return Result.error("查询异常：" + e.getMessage());
		}
	}

	/**
	 * 根据项目id查询已完成合同拆分的生产计划详情
	 */
	@GetMapping("/v1/selectDetail")
	@ResponseBody
	public Result selectDetailByProjectId(ProPlanInfo info) {
		try {
			if (StringUtils.isEmpty(info.getProjectId())) {
				return Result.error("项目ID不能为空");
			}

			List<ProPlanDetail> resList = proPlanInfoService.selectDetail(info);
			return Result.ok().put(resList);
		} catch (Exception e) {
			logger.error("查询异常", e);
			return Result.error("查询异常：" + e.getMessage());
		}
	}

	/**
	 * 根据项目id查询生产计划版本列表
	 * 
	 * @param info
	 * @return
	 */
	@GetMapping("/v1/selectVersionListByProjecId")
	@ResponseBody
	public Result selectVersionListByProjecId(ProPlanInfo info) {
		try {
			if (StringUtils.isEmpty(info.getProjectId())) {
				return Result.error("项目ID不能为空");
			}

			List<Integer> versionList = Lists.newArrayList();

			EntityWrapper<ProPlanInfo> ew = new EntityWrapper<ProPlanInfo>();
			ew.setEntity(info);
			ew.orderBy("version", false);
			List<ProPlanInfo> resList = proPlanInfoService.selectList(ew);
			for (ProPlanInfo proPlanInfo : resList) {
				versionList.add(proPlanInfo.getVersion());
			}
			return new Result().put(versionList);
		} catch (Exception e) {
			logger.error("查询异常", e);
			return Result.error("查询异常：" + e.getMessage());
		}
	}

	/**
	 * 
	 * @param info
	 * @return
	 */
	@PostMapping("/v1/actSubmit")
	@ResponseBody
	public Result submit(@RequestBody ProPlanInfo info) {
		try {
			proPlanInfoService.submit(info);
			return Result.ok();
		} catch (Exception e) {
			logger.error("提交审核异常", e);
			return Result.error("提交审核异常：" + e.getMessage());
		}
	}

	/**
	 * 
	 * @param info
	 * @return
	 */
	@PostMapping("/v1/actCheck")
	@ResponseBody
	public Result check(@RequestBody ProPlanInfo info) {
		try {
			proPlanInfoService.check(info);
			return Result.ok();
		} catch (Exception e) {
			logger.error("审核异常", e);
			return Result.error("审核异常：" + e.getMessage());
		}
	}

	/**
	 * 
	 * @param info
	 * @return
	 */
	@PostMapping("/v1/actReject")
	@ResponseBody
	public Result reject(@RequestBody ProPlanInfo info) {
		try {
			proPlanInfoService.reject(info);
			return Result.ok();
		} catch (Exception e) {
			logger.error("驳回异常", e);
			return Result.error("驳回异常：" + e.getMessage());
		}
	}

	/**
	 * 
	 * @param info
	 * @return
	 */
	@PostMapping("/v1/actEndReturn")
	@ResponseBody
	public Result endReturn(@RequestBody ProPlanInfo info) {
		try {
			EntityWrapper<ProPlanDetail> detailEw = new EntityWrapper<>();
			detailEw.eq("pro_id", info.getId());
			detailEw.isNotNull("consplit_id");
			detailEw.ne("consplit_id", "");
			Integer count = proPlanDetailService.selectCount(detailEw);
			if (count > 0) {
				return Result.error("生产计划已经完成合同拆分,无法弃审！");
			}

			proPlanInfoService.endReturn(info);
			return Result.ok();
		} catch (Exception e) {
			logger.error("弃审异常", e);
			return Result.error("弃审异常：" + e.getMessage());
		}
	}

	@GetMapping("/v1/listProCode")
	@ResponseBody
	public Result listProCode() {
		try {
			EntityWrapper<BsCodeEntity> subEw = new EntityWrapper<BsCodeEntity>();
			subEw.eq("code_type", "sub_item");
			subEw.or();
			subEw.eq("code_type", "branch");
			List<BsCodeEntity> subItemList = bsCodeService.selectList(subEw);

			EntityWrapper<BsCodeEntity> branchEw = new EntityWrapper<BsCodeEntity>();
			branchEw.eq("code_type", "branch");
			List<BsCodeEntity> branchList = bsCodeService.selectList(branchEw);

			EntityWrapper<BsCodeEntity> layeredEw = new EntityWrapper<BsCodeEntity>();
			layeredEw.eq("code_type", "layered");
			List<BsCodeEntity> layeredList = bsCodeService.selectList(layeredEw);

			Map<String, List<BsCodeEntity>> map = Maps.newHashMap();
			map.put("sub_item", subItemList);
			map.put("branch", branchList);
			map.put("layered", layeredList);

			// if(Objects.equals(entity.getCodeType(),"sub_item")){
			// EntityWrapper<BsCodeEntity> ew = new EntityWrapper<BsCodeEntity>();
			// ew.eq("code_type", entity.getCodeType());
			// ew.or();
			// ew.eq("code_type", "branch");
			// resList = bsCodeService.selectList(ew);
			// }else{
			// EntityWrapper<BsCodeEntity> ew = new EntityWrapper<BsCodeEntity>();
			// ew.eq("code_type", entity.getCodeType());
			// resList = bsCodeService.selectList(ew);
			// }

			return Result.ok().put(map);
		} catch (Exception e) {
			logger.error("查询失败", e);
			return Result.error("查询失败:" + e);
		}
	}

	/*
	 * 生产计划详情树形结构(用料部位) 2018-07-11 sdp
	 */
	@GetMapping("/v1/dateillist")
	@RequiresPermissions("bs:mtrkind:list")
	@ResponseBody
	public Result getPlanDetailTree(@RequestParam String projectId) {
		try {
			List<ProPlanDetail> treeList = proPlanDetailService.queryPlanDetailList(projectId);
			return Result.ok().putList(treeList);
		} catch (Exception e) {
			logger.error("查询生产计划详情树形结构失败", e);
			return Result.error("查询生产计划详情树形结构失败:" + e);
		}
	}
}
