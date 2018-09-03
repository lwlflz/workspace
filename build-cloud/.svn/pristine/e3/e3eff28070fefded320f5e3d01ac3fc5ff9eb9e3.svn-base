package com.build.cloud.modules.mat.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.core.base.controller.AbstractController;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.modules.mat.entity.MatPurchasePlanSumEntity;
import com.build.cloud.modules.mat.entity.MatPurchasePlanSumListEntity;
import com.build.cloud.modules.mat.service.IMatPurchasePlanSumlistService;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;

@RestController
@RequestMapping("/mat/purchaseplansumlist")
public class MatPurchasePlanSumlistController extends AbstractController{

	@Autowired
	private IMatPurchasePlanSumlistService purchasePlanSumlistService;
	/**
	 * 月度采购计划汇总清单--列表分页
	 */
	@GetMapping("/v1/pageList")
	public Result pageList(@RequestParam Map<String, Object> params){
		PageUtils page = purchasePlanSumlistService.queryPage(params);
		return Result.ok().put("result",page);
	}
	
	/**
	 * 月度采购计划汇总清单--数据列表（无分页）
	 */
	@GetMapping("/v1/list")
	public Result list(){
		List<MatPurchasePlanSumListEntity> list = purchasePlanSumlistService.selectList(null);
		return Result.ok().putList(list);
	}
	
	/**
	 * 月度采购计划汇总清单 --详情
	 */
	@GetMapping("/v1/info")
	public Result info(String id){
		if(StrUtil.isBlank(id)){
			return Result.error("ID不能为空");
		}
		MatPurchasePlanSumListEntity entity = purchasePlanSumlistService.selectById(id);
		return Result.ok().putEntity(entity);
	}
	
	/**
	 * 月度采购计划汇总清单--保存
	 */
	@PostMapping("/v1/save")
	@Transactional
	public Result save(@RequestBody MatPurchasePlanSumListEntity entity){
		purchasePlanSumlistService.insert(entity);
		return Result.ok().putObject("id", entity.getId());
	}
	
	/**
	 * 月度采购计划汇总清单--删除
	 */
	@PostMapping("/v1/delete")
	public Result delete(@RequestBody String id) {
		id = getDelId(id);
		if(StrUtil.isBlank(id)){
			return Result.error("ID不能为空");
		}
		purchasePlanSumlistService.deleteById(id);
		return Result.ok();
	}
	/**
	 * 月度采购计划汇总清单--修改
	 */
	@PostMapping("/v1/update")
	@Transactional
	public Result update(@RequestBody MatPurchasePlanSumListEntity entity) {
		if(entity == null) {
			return Result.error("请选择数据进行操作");
		}
		purchasePlanSumlistService.updateAllColumnById(entity);
		return Result.ok();
	}
}
