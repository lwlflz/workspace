package com.build.cloud.modules.bs.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hutool.core.util.StrUtil;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.beust.jcommander.internal.Lists;
import com.build.cloud.core.base.controller.AbstractController;
import com.build.cloud.core.base.controller.Result;
import com.build.cloud.modules.bs.entity.BsCodeEntity;
import com.build.cloud.modules.bs.service.IBsCodeService;
import com.build.cloud.modules.sys.bean.DictBean;
import com.build.cloud.modules.sys.util.DictUtils;

/**
 * <p>
 *   前端控制器
 * </p>
 * @author liangsen
 * @since 2018-05-16
 */
@Controller
@RequestMapping("/bs/bsCode")
public class BsCodeController extends AbstractController{
    
    @Autowired private IBsCodeService bsCodeService;
    
    /**
	 * 获取编码类型
	 */
	@GetMapping("/v1/getCodeType")
	@ResponseBody
	public Result getWorkType() {
		List<DictBean> codeTypeList = DictUtils.getDictList("code_type");
		return Result.ok().putList(codeTypeList);
	}
    
//    @PostMapping("/v1/save")
//    @ResponseBody
//    public Result save(@RequestBody BsCodeListEntity entity) {
//    	try{
//    		if(StringUtils.isEmpty(entity.getCodeType())){
//    			return Result.error("编码类型不能为空");
//    		}
//    		
//    		bsCodeService.saveBsCode(entity);
//    		return Result.ok("保存成功");
//    	}catch(Exception e){
//    		logger.error("保存失败", e);
//    		return Result.error("保存失败:"+e);
//    	}
//    }
//    
//    @GetMapping("/v1/delete")
//    @ResponseBody
//    public Result delete(BsCodeEntity entity) {
//    	try{
//    		String res = bsCodeService.deleteJudge(entity);
//    		if(res==null){
//    			return Result.ok("验证通过,允许删除");
//    		}else{
//    			return Result.error(res);
//    		}
//    	}catch(Exception e){
//    		logger.error("删除失败", e);
//    		return Result.error("删除失败:"+e);
//    	}
//    }.
    
    @GetMapping("/v1/list")
    @ResponseBody
    public Result list(BsCodeEntity entity) {
    	try{
    		if(StrUtil.isBlank(entity.getCodeType())){
    			return Result.error("codeType不能为空");
    		}
    		
    		List<BsCodeEntity> resList = Lists.newArrayList();
    		if(Objects.equals(entity.getCodeType(),"sub_item")){
    			EntityWrapper<BsCodeEntity> ew = new EntityWrapper<BsCodeEntity>();
        		ew.eq("code_type", entity.getCodeType());
        		ew.or();
        		ew.eq("code_type", "branch");
        		resList = bsCodeService.selectList(ew);
    		}else{
    			EntityWrapper<BsCodeEntity> ew = new EntityWrapper<BsCodeEntity>();
        		ew.eq("code_type", entity.getCodeType());
        		resList = bsCodeService.selectList(ew);
    		}
    		
    		return Result.ok().put(resList) ;
    	}catch(Exception e){
    		logger.error("查询失败", e);
    		return Result.error("查询失败:"+e);
    	}
    }
}
