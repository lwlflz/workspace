package com.build.cloud.modules.bs.controller;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.build.cloud.core.base.controller.AbstractController;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.modules.bs.entity.BsMtrKindArchiveEntity;
import com.build.cloud.modules.bs.service.IBsMtrKindArchiveService;

import cn.hutool.core.util.StrUtil;
/**
 * @className BsMtrKindArchiveController
 * @descripion 材料分类档案controller
 * @author WangJun
 * @date 2018年4月10日下午7:34:00
 */
@RestController
@RequestMapping(value = "/bs/mtrkind")
public class BsMtrKindArchiveController extends AbstractController {
	@Autowired
	private IBsMtrKindArchiveService bsMtrKindArchiveService;
//	@Autowired
//	private IBsMtrArchiveService bsMtrArchiveService;
	/*
	 * 树形结构
	 */
	@GetMapping("/v1/list")
	@RequiresPermissions("bs:mtrkind:list")
	public Result getTypeTree(@RequestParam(required = false) String status) {
//		List<BsMtrKindArchiveEntity> treeList = redisUtils.get("MTRKINDTREE", List.class);
//		if (CollectionUtils.isEmpty(treeList)) {
			EntityWrapper<BsMtrKindArchiveEntity> wrapper = new EntityWrapper<BsMtrKindArchiveEntity>();
			if (StrUtil.isNotEmpty(status)) {
				wrapper.eq("status", "0");
			}
			List<BsMtrKindArchiveEntity> treeList = bsMtrKindArchiveService.selectList(wrapper);
//			redisUtils.set("MTRKINDTREE", treeList, RedisUtils.DEFAULT_EXPIRE);
//		}
		
		return Result.ok().putList(treeList);
	}
	/*
	 * 信息
	 */
	@GetMapping("/v1/info")
	@RequiresPermissions("bs:mtrkind:info")
	public Result info(String id) {
		BsMtrKindArchiveEntity mtrKindArchive = new BsMtrKindArchiveEntity();
		if (StrUtil.isNotBlank(id)) {
			mtrKindArchive = bsMtrKindArchiveService.selectById(id);
		}
		return Result.ok().putEntity(mtrKindArchive);
	}
//	/*
//	 * 保存
//	 */
//	@RequestMapping("/v1/save")
//	@RequiresPermissions("bs:mtrkind:save")
//	public Result save(@RequestBody BsMtrKindArchiveEntity mtrKindArchive) {
//		ValidatorUtils.validateEntity(mtrKindArchive);
//		mtrKindArchive.setPinyinCode(PinyinUtil.getAllFirstLetter(mtrKindArchive.getMtrKindName()));
//		EntityWrapper<BsMtrKindArchiveEntity> wrapper = new EntityWrapper<BsMtrKindArchiveEntity>();
//		wrapper.eq("mtr_kind_name", mtrKindArchive.getMtrKindName());
//		wrapper.or().eq("mtr_kind_code", mtrKindArchive.getMtrKindCode());
//		int count = bsMtrKindArchiveService.selectCount(wrapper);
//		if (count != 0) {
//			return Result.error("编码不能重复,保存失败");
//		}
//		bsMtrKindArchiveService.insert(mtrKindArchive);
//		this.getTypeTree(null);
//		return Result.ok();
//	}
//	/*
//	 * 删除
//	 */
//	@RequestMapping("/v1/delete")
//	@RequiresPermissions("bs:mtrkind:delete")
//	public Result delete(@RequestBody String id) {
//		id = getDelId(id);
//		if (StrUtil.isBlank(id)) {
//			return Result.error("ID不能为空");
//		}
//		// 判断是否有材料档案引用
//		EntityWrapper<BsMtrArchiveEntity> wrapper = new EntityWrapper<BsMtrArchiveEntity>();
//		wrapper.in("mtr_kind_id", id);
//		List<BsMtrArchiveEntity> list = bsMtrArchiveService.selectList(wrapper);
//		// 判断是否有子级分类
//		EntityWrapper<BsMtrKindArchiveEntity> wrapper2 = new EntityWrapper<BsMtrKindArchiveEntity>();
//		wrapper2.in("parent_id", id);
//		List<BsMtrKindArchiveEntity> list2 = bsMtrKindArchiveService.selectList(wrapper2);
//		if (!CollectionUtil.isEmpty(list) || !CollectionUtil.isEmpty(list2)) {
//			return Result.error("删除失败，数据已被引用");
//		}
//		bsMtrKindArchiveService.deleteById(id);
//		this.getTypeTree(null);
//		return Result.ok();
//	}
//	/*
//	 * 更新
//	 */
//	@RequestMapping("/v1/update")
//	@RequiresPermissions("bs:mtrkind:update")
//	public Result update(@RequestBody BsMtrKindArchiveEntity mtrKindArchive) {
//		ValidatorUtils.validateEntity(mtrKindArchive);
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("parent_id", mtrKindArchive.getId());
//		map.put("status", "0");
//		if (bsMtrKindArchiveService.selectByMap(map).size() > 0) {
//			return Result.error("请先封存相关子集");
//		}
//		bsMtrKindArchiveService.updateById(mtrKindArchive);
//		this.getTypeTree(null);
//		return Result.ok();
//	}
}
