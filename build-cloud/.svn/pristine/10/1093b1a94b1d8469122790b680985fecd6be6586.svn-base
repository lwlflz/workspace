package com.build.cloud.modules.punch.service;
import java.util.List;

import com.build.cloud.common.dbutils.DatabaseHelper;
import com.build.cloud.modules.punch.entity.DevDeviceEntity;
import com.build.cloud.modules.punch.entity.DevEmployeeEntity;
import com.build.cloud.modules.punch.entity.ProAttendUserEntity;
import com.build.cloud.modules.punch.entity.StatCardEntity;
public class AttendService {
	private AttendService() {
	}
	private static class AttendServiceInstance {
		public static AttendService SERVICE = new AttendService();
	}
	public static final AttendService getService() {
		return AttendServiceInstance.SERVICE;
	}
	/**
	 * @Title: getAttendUser @Description: 查询考勤机人员 @param @return 设定文件 @return
	 *         List<ProAttendUserEntity> 返回类型 @throws
	 */
	public List<ProAttendUserEntity> getAttendUser() {
		StringBuilder sql = new StringBuilder(50);
		sql.append("SELECT");
		sql.append(" u.ng_id AS ngId, ");
		sql.append(" u.sz_employ_id AS szEmployId,");
		sql.append(" u.sz_name AS szName,");
		sql.append(" u.sz_card_id AS szCardId,");
		sql.append(" u.sz_card AS szCard,");
		sql.append(" u.ts_create AS tsCreate");
		sql.append(" FROM");
		sql.append(" sys_user AS u");
		sql.append(" WHERE");
		sql.append(" u.nt_user_state = ? ");
		Object[] params = { 1 };
		return DatabaseHelper.queryEntityList(ProAttendUserEntity.class, sql.toString(), params);
	}
	/**
	 * @Title: getDevice @Description: TODO(这里用一句话描述这个方法的作用) @param @return 设定文件 @return
	 * List<DevDeviceEntity> 返回类型 @throws
	 */
	public List<DevDeviceEntity> getDevice() {
		StringBuilder sql = new StringBuilder(30);
		sql.append("SELECT ");
		sql.append(" dev.ng_id AS ngId, ");
		sql.append(" dev.sz_name AS szName, ");
		sql.append(" dev.sz_type AS szType, ");
		sql.append(" dev.st_dev_class AS stDevClass,");
		sql.append(" dev.sz_ip_addr AS szIpAddr, ");
		sql.append(" dev.sz_dev_pwd AS szDevPwd, ");
		sql.append(" dev.sz_mask AS szMask, ");
		sql.append(" dev.sz_gateway AS szGateway, ");
		sql.append(" dev.sz_mac AS szMac,");
		sql.append(" dev.sz_serial AS szSerial, ");
		sql.append(" dev.ts_create AS tsCreate ");
		sql.append("FROM ");
		sql.append(" dev_device AS dev ");
		sql.append("WHERE ");
		sql.append(" dev.nt_state = ? ");
		Object[] params = { 1 };
		return DatabaseHelper.queryEntityList(DevDeviceEntity.class, sql.toString(), params);
	}
	public List<DevEmployeeEntity> getDevEmp() {
		StringBuilder sql = new StringBuilder(20);
		sql.append("SELECT ");
		sql.append(" de.ng_id AS ngId, ");
		sql.append(" de.ng_dev_id AS ngDevId, ");
		sql.append(" de.sz_employ_id AS szEmployId, ");
		sql.append(" de.ts_create AS tsCreate ");
		sql.append("FROM ");
		sql.append(" dev_employee AS de ");
		Object[] params = {};
		return DatabaseHelper.queryEntityList(DevEmployeeEntity.class, sql.toString(), params);
	}
	public List<StatCardEntity> getStatCard() {
		StringBuilder sql = new StringBuilder(50);
		sql.append("SELECT ");
		sql.append(" stc.ng_id AS ngId,");
		sql.append(" stc.ng_user_id AS ngUserId,");
		sql.append(" stc.ng_dev_id AS ngDevId,");
		sql.append(" stc.ts_card AS tsCard,");
		sql.append(" stc.st_card_type AS cardType,");
		sql.append(" stc.sz_user_name AS szUserName,");
		sql.append(" stc.sz_employ_id AS szEmployId,");
		sql.append(" stc.sz_dev_name AS szDevName,");
		sql.append(" stc.sz_dev_place AS szDevPlace ");
		sql.append("FROM ");
		sql.append(" stat_card AS stc ");
		sql.append(" WHERE to_days(stc.ts_card) = to_days(now())");
		Object[] params = {};
		return DatabaseHelper.queryEntityList(StatCardEntity.class, sql.toString(), params);
	}
}
