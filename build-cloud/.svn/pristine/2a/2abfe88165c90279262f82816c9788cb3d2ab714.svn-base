package com.build.cloud.common.aspect;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.build.cloud.common.annotation.DataFilter;
import com.build.cloud.common.constant.Constant;
import com.build.cloud.common.exception.BusinessException;
import com.build.cloud.modules.sys.entity.SysUserEntity;
import com.build.cloud.modules.sys.service.ISysDeptService;
import com.build.cloud.modules.sys.service.ISysRoleCompanyService;
import com.build.cloud.modules.sys.service.ISysRoleDeptService;
import com.build.cloud.modules.sys.service.ISysUserRoleService;
import com.build.cloud.modules.sys.shiro.ShiroUtils;
import com.google.common.collect.Sets;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
/**
 * @ClassName: DataFilterAspect
 * @Description: 数据过滤，切面处理类
 * @author: liutao
 * @date: 2018年3月16日 下午12:22:35
 */
@Aspect
@Component
public class DataFilterAspect {
	@Autowired
	private ISysDeptService sysDeptService;
	@Autowired
	private ISysUserRoleService sysUserRoleService;
	@Autowired
	private ISysRoleDeptService sysRoleDeptService;
	@Autowired
	private ISysRoleCompanyService sysRoleCompanyService;
	@Pointcut("@annotation(com.build.cloud.common.annotation.DataFilter)")
	public void dataFilterCut() {
	}
	@SuppressWarnings("unchecked")
	@Before("dataFilterCut()")
	public void dataFilter(JoinPoint point)
		throws Throwable {
		Object params = point.getArgs()[0];
		if (params != null && params instanceof Map) {
//			SysUserEntity user = ShiroUtils.getUserEntity();
//			// 如果不是超级管理员，则进行数据过滤
//			if (!StrUtil.equals(user.getId(), Constant.SUPER_ADMIN)) {
//				Map<String, String> map = (Map<String, String>)params;
//				map.put(Constant.SQL_FILTER, getSQLFilter(user, point));
//			}
			return;
		}
		throw new BusinessException("数据权限接口，只能是Map类型参数，且不能为NULL");
	}
	/**
	 * 获取数据过滤的SQL
	 */
	private String getSQLFilter(SysUserEntity user, JoinPoint point) {
		MethodSignature signature = (MethodSignature)point.getSignature();
		DataFilter dataFilter = signature.getMethod().getAnnotation(DataFilter.class);
		// 获取表的别名
		String tableAlias = dataFilter.tableAlias();
		if (StrUtil.isNotBlank(tableAlias)) {
			tableAlias += ".";
		}
		// 用户角色对应的公司及部门ID列表
		List<String> roleIdList = sysUserRoleService.queryRoleIdList(user.getId());
		// 用户公司ID列表
		Set<String> companyIdList = Sets.newHashSet();
		if (user.getDeptId() != null) {
			companyIdList.add(user.getCompanyId());
			if (CollectionUtil.isNotEmpty(roleIdList)) {
				List<String> userCompanyIdList =
					sysRoleCompanyService.queryCompanyIdList(roleIdList.toArray(new String[roleIdList.size()]));
				companyIdList.addAll(userCompanyIdList);
			}
		}
		// 用户部门ID列表
		Set<String> deptIdList = Sets.newHashSet();
		if (user.getDeptId() != null) {
			deptIdList.add(user.getDeptId());
			if (CollectionUtil.isNotEmpty(roleIdList)) {
				List<String> userDeptIdList =
					sysRoleDeptService.queryDeptIdList(roleIdList.toArray(new String[roleIdList.size()]));
				deptIdList.addAll(userDeptIdList);
			}
		}
		// 用户子部门ID列表
		if (dataFilter.subDept()) {
			List<String> subDeptIdList = sysDeptService.getSubDeptIdList(user.getDeptId());
			deptIdList.addAll(subDeptIdList);
		}
		StringBuilder sqlFilter = new StringBuilder();
		sqlFilter.append(" (");
		sqlFilter.append(tableAlias).append(dataFilter.deptId()).append(" in(");
		sqlFilter.append(StrUtil.join(",", deptIdList));
		sqlFilter.append(")");
		// 没有本部门数据权限，也能查询本人数据
		if (dataFilter.user()) {
			sqlFilter.append(" or ").append(tableAlias).append(dataFilter.userId());
			sqlFilter.append("=").append(user.getId());
		}
		sqlFilter.append(")");
		return sqlFilter.toString();
	}
}
