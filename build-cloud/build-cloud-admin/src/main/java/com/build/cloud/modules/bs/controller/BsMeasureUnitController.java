/**
 * 
 */
package com.build.cloud.modules.bs.controller;

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
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.common.validator.ValidatorUtils;
import com.build.cloud.core.base.controller.AbstractController;
import com.build.cloud.modules.bs.entity.BsMeasureUnitEntity;
import com.build.cloud.modules.bs.service.IBsMeasureUnitService;

import cn.hutool.core.util.StrUtil;

/**
 * @className BsMeasureUnitController
 * @descripion 计量单位控制层
 * @author WangJun
 * @date 2018年4月11日下午4:22:48
 */
@RestController
@RequestMapping(value="/bs/meunit")
public class BsMeasureUnitController extends AbstractController{
	@Autowired
	private IBsMeasureUnitService bsMeasureUnitService;
	/*
	 * 列表
	 */
	@GetMapping("/v1/list")
	@RequiresPermissions("bs:measureunit:list")
	public Result list(@RequestParam Map<String, Object> params) {
		PageUtils page = bsMeasureUnitService.queryPage(params);
		return Result.ok().put("result", page);
	}
	
	/**
	 * 获取计量单位全部数据
	 * @return
	 */
	@GetMapping("/v1/meunitlist")
	@RequiresPermissions("bs:measureunit:list")
	public Result meunitlist() {
		List<BsMeasureUnitEntity> list = bsMeasureUnitService.selectList(null);
		return Result.ok().putList(list);
	}
	
	/*
	 * 信息
	 */
	@GetMapping("/v1/info")
	@RequiresPermissions("bs:measureunit:info")
	public Result info(String id) {
		BsMeasureUnitEntity bsMeasureUnit = null ;
		if(StrUtil.isNotBlank(id)) {
			bsMeasureUnit = bsMeasureUnitService.selectById(id);
		}
		return Result.ok().putEntity(bsMeasureUnit);
	}
	/*
	 * 保存
	 */
	@RequestMapping("/v1/save")
	@RequiresPermissions("bs:measureunit:save")
	public Result save(@RequestBody BsMeasureUnitEntity bsMeasureUnit) {
		ValidatorUtils.validateEntity(bsMeasureUnit);
		EntityWrapper<BsMeasureUnitEntity> wrapper = new EntityWrapper<BsMeasureUnitEntity>();
		wrapper.eq("measure_code", bsMeasureUnit.getMeasureCode());
		int count = bsMeasureUnitService.selectCount(wrapper);
		if(count != 0) {
			return Result.error(500,"数据已重复，新增失败");			
		}
		bsMeasureUnitService.insert(bsMeasureUnit);
		return Result.ok();
		
		
	}
	/*
	 * 删除
	 */
	@RequestMapping("/v1/delete")
	@RequiresPermissions("bs:measureunit:delete")
	public Result delete(@RequestBody String id) {
		id = getDelId(id);
		if(StrUtil.isBlank(id)){
			return Result.error("ID不能为空");
		}
		bsMeasureUnitService.deleteById(id);
		return Result.ok();
	}
	/*
	 * 更新
	 */
	@RequestMapping("/v1/update")
	@RequiresPermissions("bs:measureunit:update")
	public Result update(@RequestBody BsMeasureUnitEntity bsMeasureUnit) {
		ValidatorUtils.validateEntity(bsMeasureUnit);
		EntityWrapper<BsMeasureUnitEntity> wrapper = new EntityWrapper<BsMeasureUnitEntity>();
		wrapper.eq("measure_code", bsMeasureUnit.getMeasureCode());
		wrapper.ne("id", bsMeasureUnit.getId());
		int count = bsMeasureUnitService.selectCount(wrapper);
		if(count != 0) {
			return Result.error(500,"数据已重复，新增失败");	
		}
		bsMeasureUnitService.updateById(bsMeasureUnit);
		return Result.ok();
	}
}
