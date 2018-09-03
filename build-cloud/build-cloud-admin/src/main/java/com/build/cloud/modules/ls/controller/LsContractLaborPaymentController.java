package com.build.cloud.modules.ls.controller;

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
import com.build.cloud.modules.ls.entity.LsContractLaborPaymentEntity;
import com.build.cloud.modules.ls.service.ILsContractLaborPaymentService;

import cn.hutool.core.util.StrUtil;

@RestController
@RequestMapping("/ls/contractlaborpayment")
public class LsContractLaborPaymentController extends AbstractController{
	
	@Autowired
	private ILsContractLaborPaymentService lsContractLaborPaymentService;
	/**
	 * 劳务分包合同付款协议--列表分页
	 */
	@GetMapping("/v1/pageList")
	public Result pageList(@RequestParam Map<String, Object> params){
		String conId = (String)params.get("conId");
		if(StrUtil.isBlank(conId)) {
			return Result.error("劳务分包合同id不能为空");
		}
		PageUtils page = lsContractLaborPaymentService.queryPage(params);
		return Result.ok().put("result",page);
	}
	
	/**
	 * 劳务分包合同付款协议--数据列表（无分页）
	 */
	@GetMapping("/v1/list")
	public Result list(@RequestParam Map<String, Object> params){
		String conId = (String)params.get("conId");
		if(StrUtil.isBlank(conId)) {
			return Result.error("劳务分包合同id不能为空");
		}
		List<LsContractLaborPaymentEntity> list = lsContractLaborPaymentService.selectList(new EntityWrapper<LsContractLaborPaymentEntity>().eq("con_id", conId));
		return Result.ok().putList(list);
	}
	
	/**
	 * 劳务分包合同付款协议 --详情
	 */
	@GetMapping("/v1/info")
	public Result info(String id){
		if(StrUtil.isBlank(id)){
			return Result.error("ID不能为空");
		}
		LsContractLaborPaymentEntity entity = lsContractLaborPaymentService.selectById(id);
		return Result.ok().putEntity(entity);
	}
	
	/**
	 * 劳务分包合同付款协议--保存
	 */
	@PostMapping("/v1/save")
	@Transactional
	public Result save(@RequestBody LsContractLaborPaymentEntity entity){
		if(entity != null && StrUtil.isBlank(entity.getConId())) {
			return Result.error("合同ID不能为空");
		}
		lsContractLaborPaymentService.insert(entity);
		return Result.ok().putObject("id", entity.getId());
	}
	
	/**
	 * 劳务分包合同付款协议 --删除
	 */
	@PostMapping("/v1/delete")
	public Result delete(@RequestBody String id) {
		id = getDelId(id);
		if(StrUtil.isBlank(id)){
			return Result.error("ID不能为空");
		}
		lsContractLaborPaymentService.deleteById(id);
		return Result.ok();
	}
	/**
	 * 劳务分包合同付款协议--修改
	 */
	@PostMapping("/v1/update")
	@Transactional
	public Result update(@RequestBody LsContractLaborPaymentEntity entity) {
		if(entity == null) {
			return Result.error("请选择数据进行操作");
		}
		lsContractLaborPaymentService.updateAllColumnById(entity);
		return Result.ok();
	}
}
