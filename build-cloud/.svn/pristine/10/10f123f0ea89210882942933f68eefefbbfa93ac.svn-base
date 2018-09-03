package com.build.cloud.modules.mat.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.build.cloud.common.utils.PageUtils;
import com.build.cloud.core.base.controller.AbstractController;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.modules.mat.service.IMatPurchaseStockService;

/**
 * <p>Title: MatPurchaseStockController</p>  
 * <p>Description: </p>  
 * @author WangJun
 * @date 2018年7月12日 下午2:07:31
 */
@RestController
@RequestMapping("/mat/ps")
public class MatPurchaseStockController extends AbstractController{
	@Autowired
	private IMatPurchaseStockService matPurchaseStockService;
	
	@GetMapping("/v1/list")
	public Result list(@RequestParam Map<String,Object> params) {
		String companyId = getCurrentCompanyId();
		params.put("companyId", companyId);
		PageUtils queryPageList = matPurchaseStockService.queryPageList(params);
		
		return Result.ok().put(queryPageList);
	}
}
