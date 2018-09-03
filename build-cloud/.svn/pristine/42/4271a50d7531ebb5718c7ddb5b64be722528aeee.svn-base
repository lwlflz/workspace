package com.build.cloud.common.dbutils;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.build.cloud.common.utils.ReflectionUtils;
/**
 * 使用 QueryRunner 提供其具体的实现
 * @param <T>: 子类需传入的泛型类型.
 */
public class JdbcImpl<T> {
	public Class<T> type;
	public JdbcImpl() {
		type = ReflectionUtils.getSuperGenericType(getClass());
	}
	public void batch(Connection connection, String sql, Object[]... params)
		throws SQLException {
	}
	public <E> E getForValue(Connection connection, String sql, Object... params)
		throws SQLException {
		return null;
	}
	public List<T> getForList(Connection connection, String sql, Object... params)
		throws SQLException {
		return DBUtil.queryBeanList(type, null, connection, sql, params);
	}
	public List<Map<String, Object>> queryMapList(Connection conn, String sql, Object... params) {
		return DBUtil.queryMapList(conn, sql, params);
	}
	public T get(Connection connection, String sql, Object... params)
		throws SQLException {
		return DBUtil.queryBean(type, null, connection, sql, params);
	}
	public void update(Connection connection, String sql, Object... params)
		throws SQLException {
	}
}