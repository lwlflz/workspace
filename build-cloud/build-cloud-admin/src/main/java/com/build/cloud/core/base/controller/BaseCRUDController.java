package com.build.cloud.core.base.controller;
import java.io.Serializable;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.build.cloud.core.base.controller.Result;
import com.build.cloud.common.utils.StringUtil;
import com.build.cloud.core.base.service.ICommonService;
import com.build.cloud.core.entity.AbstractEntity;
public abstract class BaseCRUDController<Entity extends AbstractEntity<ID>, ID extends Serializable>
		extends BaseBeanController<Entity> {
	protected ICommonService<Entity> commonService;
	/**
	 * 设置基础service
	 * @param baseService
	 */
	@Autowired
	public void setCommonService(ICommonService<Entity> commonService) {
		this.commonService = commonService;
	}
	public Entity get(ID id) {
		if (!StringUtil.isNullOrEmpty(id)) {
			return commonService.selectById(id);
		} else {
			return newModel();
		}
	}
	@PostMapping(value = "{id}/delete")
	@ResponseBody
	public Result delete(ID id) {
		try {
			commonService.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			Result.error("删除失败");
		}
		return Result.ok("删除成功");
	}
	@RequestMapping(value = "batch/delete", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Result batchDelete(@RequestParam(value = "ids", required = false) ID[] ids) {
		try {
			commonService.deleteBatchIds(Arrays.asList(ids));
		} catch (Exception e) {
			e.printStackTrace();
			Result.error("删除失败");
		}
		return Result.ok("删除成功");
	}
}
