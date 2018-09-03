package com.build.cloud.common.handler;
import java.util.Date;

import org.apache.ibatis.reflection.MetaObject;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.build.cloud.common.constant.Constant;
import com.build.cloud.common.constant.DataBaseFieldConstant;
import com.build.cloud.modules.sys.shiro.ShiroUtils;
/**
 * @ClassName: BaseMetaObjectHandler
 * @Description: 填充创建时间，创建人，更新时间，更新人
 * @author: liutao
 * @date: 2018年3月17日 下午4:07:27
 */
public class BaseMetaObjectHandler extends MetaObjectHandler {
	/**
	 * 新增
	 */
	@Override
	public void insertFill(MetaObject metaObject) {
		// 创建用户
		Object createBy = getFieldValByName(DataBaseFieldConstant.CREATE_BY, metaObject);
		if (createBy == null) {
			setFieldValByName(DataBaseFieldConstant.CREATE_BY, ShiroUtils.getLoginName(), metaObject);
		}
		// 创建时间
		Object createDate = getFieldValByName(DataBaseFieldConstant.CREATE_TIME, metaObject);
		if (createDate == null) {
			setFieldValByName(DataBaseFieldConstant.CREATE_TIME, new Date(), metaObject);
		}
		setFieldValByName(DataBaseFieldConstant.UPDATE_BY, ShiroUtils.getLoginName(), metaObject);
		setFieldValByName(DataBaseFieldConstant.UPDATE_TIME, new Date(), metaObject);
		// 删除标记
//		Object valid = getFieldValByName(DataBaseFieldConstant.VALID, metaObject);
//		if (valid == null) {
			setFieldValByName(DataBaseFieldConstant.VALID, Constant.DEL_FLAG_NORMAL, metaObject);
//		}
	}
	@Override
	public void updateFill(MetaObject metaObject) {
		
		// 更新用户
//		Object updateBy = getFieldValByName(DataBaseFieldConstant.UPDATE_BY, metaObject);
//		if (updateBy == null) {
			setFieldValByName(DataBaseFieldConstant.UPDATE_BY, ShiroUtils.getLoginName(), metaObject);
//		}
		// 更新用户
//		Object updateDate = getFieldValByName(DataBaseFieldConstant.UPDATE_TIME, metaObject);
//		if (updateDate == null) {
			setFieldValByName(DataBaseFieldConstant.UPDATE_TIME, new Date(), metaObject);
//		}
	}
}