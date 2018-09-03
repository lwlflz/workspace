package com.build.cloud.modules.sys.controller;
import java.util.Arrays;
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

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.common.utils.cache.RedisKeys;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.common.validator.ValidatorUtils;
import com.build.cloud.core.base.controller.AbstractController;
import com.build.cloud.modules.sys.bean.DictBean;
import com.build.cloud.modules.sys.entity.SysDictEntity;
import com.build.cloud.modules.sys.service.ISysDictService;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
/**
 * @ClassName: SysDictController
 * @Description: 数据字典
 * @author: liutao
 * @date: 2018年3月16日 下午2:47:42
 */
@RestController
@RequestMapping("/sys/dict")
public class SysDictController extends AbstractController {
	@Autowired
	private ISysDictService sysDictService;
	/**
	 * 列表
	 */
	@GetMapping("/v1/list")
//	@RequiresPermissions("sys:dict:list")
	public Result list(@RequestParam Map<String, Object> params) {
//		PageUtils page = sysDictService.queryPage(params);
		List<SysDictEntity> list = redisUtils.get(RedisKeys.getDictKey("all"), List.class);
		if (CollectionUtil.isEmpty(list)) {
			list = sysDictService.selectList(new EntityWrapper<SysDictEntity>());
			redisUtils.set(RedisKeys.getDictKey("all"), list);
		}
		return Result.ok().putList(list);//.put(page);
	}
	@GetMapping("/v1/all")
	public Result all() {
		List<SysDictEntity> list = sysDictService.selectList(new EntityWrapper<SysDictEntity>());
		return Result.ok().putList(list);
	}
	/**
	 * 信息
	 */
	@GetMapping("/v1/info")
	@RequiresPermissions("sys:dict:info")
	public Result info(String id) {
		SysDictEntity dict = sysDictService.selectById(id);
		return Result.ok().putEntity(dict);
	}
	/**
	 * 保存
	 */
	@PostMapping("/v1/save")
	@RequiresPermissions("sys:dict:save")
	public Result save(@RequestBody SysDictEntity dict) {
		// 校验类型
		ValidatorUtils.validateEntity(dict);
		sysDictService.insert(dict);
		return Result.ok();
	}
	/**
	 * 修改
	 */
	@PostMapping("/v1/update")
	@RequiresPermissions("sys:dict:update")
	public Result update(@RequestBody SysDictEntity dict) {
		// 校验类型
		ValidatorUtils.validateEntity(dict);
		sysDictService.updateById(dict);
		return Result.ok();
	}
	/**
	 * 删除
	 */
	@PostMapping("/v1/delete")
	@RequiresPermissions("sys:dict:delete")
	public Result delete(@RequestBody String id) {
		id = getDelId(id);
		if(StrUtil.isBlank(id)){
			return Result.error("ID不能为空");
		}
		sysDictService.deleteBatchIds(Arrays.asList(id));
		return Result.ok();
	}
}
