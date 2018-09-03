package com.build.cloud.modules.bs.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.build.cloud.core.base.controller.AbstractController;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.modules.bs.entity.BsBussinessTypeEntity;
import com.build.cloud.modules.bs.service.IBsBussinessTypeService;
/**
 * Title: BsBussinessTypeController Description: 客商分类
 * @author 鲁四围
 * @date 2018年4月13日
 */
@RestController
@RequestMapping("bs/busType")
public class BsBussinessTypeController extends AbstractController {
	@Autowired
	private IBsBussinessTypeService bsBussinessTypeService;
//	@Autowired
//	private IBsBussinessService bsBussinessService;
	/**
	 * 列表
	 */
	@RequestMapping("/v1/list")
	// @RequiresPermissions("bs:busType:list")
	public Result list(@RequestParam Map<String, Object> params) {
		// params.put("status", "0");
		// PageUtils page = bsBussinessTypeService.queryPage(params);
		return Result.ok().putList(getList());
	}
	@RequestMapping("/v1/getBusTypeList")
	public Result getBusTypeList() {
		return Result.ok().put(getList());
	}
	private List<BsBussinessTypeEntity> getList() {
		EntityWrapper<BsBussinessTypeEntity> ew = new EntityWrapper<BsBussinessTypeEntity>();
		ew.eq("status", "0");
		return bsBussinessTypeService.selectList(ew);
	}
}
