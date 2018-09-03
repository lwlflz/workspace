/**
 * 
 */
package com.build.cloud.modules.bs.controller;

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
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.common.validator.ValidatorUtils;
import com.build.cloud.core.base.controller.AbstractController;
import com.build.cloud.modules.bs.entity.BsMtrArchiveEntity;
import com.build.cloud.modules.bs.entity.BsMtrKindArchiveEntity;
import com.build.cloud.modules.bs.service.IBsMtrArchiveService;
import com.build.cloud.modules.bs.service.IBsMtrKindArchiveService;

import cn.hutool.core.util.PinyinUtil;
import cn.hutool.core.util.StrUtil;

/**
 * @className BsMtrArchiveController
 * @descripion 材料档案控制层
 * @author WangJun
 * @date 2018年4月12日上午9:21:44
 */
@RestController
@RequestMapping("/bs/mtrarch")
public class BsMtrArchiveController extends AbstractController{
	
	@Autowired
	private IBsMtrArchiveService bsMtrArchiveService;
	
	@Autowired
	private IBsMtrKindArchiveService bsMtrKindArchiveService;
	/*
	 * 列表
	 */
	@GetMapping("/v1/list")
	@RequiresPermissions("bs:mtrarch:list")
	public Result list(@RequestParam Map<String, Object> params) {
		for (String key : params.keySet()) {
            //map.keySet()返回的是所有key的值
			 String value = (String)params.get(key);
			 String replaceAll = value.replaceAll(" ", "");
			 params.put(key, replaceAll);
        }
		PageUtils page = bsMtrArchiveService.queryPage(params);
		return Result.ok().put(page);
	}
	/*
	 * 信息
	 */
	@GetMapping("/v1/info")
	@RequiresPermissions("bs:mtrarch:info")
	public Result info(String id) {
		BsMtrArchiveEntity mtrArchive = null;
		if(StrUtil.isNotBlank(id)) {
			mtrArchive = bsMtrArchiveService.selectById(id);
		}		
		return Result.ok().putEntity(mtrArchive);
	}
	/*
	 * 保存
	 */
	@PostMapping("/v1/save")
	@RequiresPermissions("bs:mtrarch:save")
	public Result save(@RequestBody BsMtrArchiveEntity mtrArchive) {
		ValidatorUtils.validateEntity(mtrArchive);
		BsMtrKindArchiveEntity mtrKind = bsMtrKindArchiveService.selectById(mtrArchive.getMtrKindId());
		if(mtrKind == null) {
			return Result.error("材料分类编码不存在，请确认后重新添加");
		}
		EntityWrapper<BsMtrArchiveEntity> wrapper = new EntityWrapper<BsMtrArchiveEntity>();
		wrapper.eq("mtr_code", mtrArchive.getMtrCode());
		int count = bsMtrArchiveService.selectCount(wrapper);
		if(count !=0) {
			return Result.error("数据重复，新增失败");		
		}
		bsMtrArchiveService.insert(mtrArchive);
		return Result.ok();
			
	}
	/*
	 * 删除
	 */
	@PostMapping("/v1/delete")
	@RequiresPermissions("bs:mtrarch:delete")
	public Result delete(@RequestBody String id) {
		id = getDelId(id);
		if(StrUtil.isBlank(id)){
			return Result.error("ID不能为空");
		}
		bsMtrArchiveService.deleteById(id);
		return Result.ok();
	}
	/*
	 * 更新
	 */
	@PostMapping("/v1/update")
	@RequiresPermissions("bs:mtrarch:update")
	public Result update(@RequestBody BsMtrArchiveEntity mtrArchive) {
		ValidatorUtils.validateEntity(mtrArchive);
		mtrArchive.setPinyinCode(PinyinUtil.getAllFirstLetter(mtrArchive.getMtrName()));
		EntityWrapper<BsMtrArchiveEntity> wrapper = new EntityWrapper<BsMtrArchiveEntity>();
		wrapper.eq("mtr_code", mtrArchive.getMtrCode());
		wrapper.ne("id", mtrArchive.getId());
		int count = bsMtrArchiveService.selectCount(wrapper);
		if(count !=0) {		
			return Result.error(500,"数据已重复，更新失败");
		}
		bsMtrArchiveService.updateById(mtrArchive);
		return Result.ok();			
	}

}
