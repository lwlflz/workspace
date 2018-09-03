package com.build.cloud.core.base.controller;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.WebUtils;

import com.alibaba.fastjson.JSON;
import com.build.cloud.common.utils.HttpRequestUtils;
import com.build.cloud.common.utils.RedisUtils;
import com.build.cloud.common.utils.cache.RedisKeys;
import com.build.cloud.core.constant.DictConstant;
import com.build.cloud.modules.bs.bean.CodeBean;
import com.build.cloud.modules.bs.service.IBsCodeService;
import com.build.cloud.modules.sys.entity.SysUserEntity;
import com.build.cloud.modules.sys.shiro.ShiroUtils;
/**
 * @ClassName: AbstractController
 * @Description: Controller公共组件
 * @author: liutao
 * @date: 2018年3月16日 下午1:56:33
 */
public abstract class AbstractController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	protected RedisUtils redisUtils;
	@Autowired
	protected IBsCodeService bsCodeService;
	protected SysUserEntity getUser() {
		return ShiroUtils.getUserEntity();
	}
	/**
	 * 
	 * @Title: getUserId   
	 * @Description: 获取当前用户ID 
	 * @param @return    设定文件   
	 * @return String    返回类型   
	 * @throws
	 */
	protected String getUserId() {
		return getUser().getId();
	}
	protected String getDeptId() {
		return getUser().getId();
	}
	protected String getCorpCode() {
		return getUser().getCorpCode();
	}
	/**
	 * 
	 * @Title: getCurrentCompanyId   
	 * @Description: 获取当前用户选择的公司ID
	 * @param @param userId
	 * @param @return    设定文件   
	 * @return String    返回类型   
	 * @throws
	 */
	protected String getCurrentCompanyId() {
		return redisUtils.get(RedisKeys.getDefCom(getUserId()));
	}
	
	protected String getJson(String text, String key) {
		return JSON.parseObject(text).getString(key);
	}
	
	protected String getDelId(String id) {
		return getJson(id, "id");
	}
	
	protected Map<String, Object> getParams(){
		return WebUtils.getParametersStartingWith(HttpRequestUtils.getRequest(), null);
	}
	
	protected List<CodeBean> getType(){
		return bsCodeService.getTeamType(DictConstant.WORK_KIND);
	}
	
	protected List<CodeBean> getTeamType(){
		return bsCodeService.getTeamType(DictConstant.WORK_TYPE);
	}
}
