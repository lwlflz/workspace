package com.build.cloud.core.entity;
/**
 * @ClassName: TableInfo
 * @Description: 动态表信息
 * @author: liutao
 * @date: 2018年3月21日 下午3:05:54
 */
public class TableInfo {
	public static final String TAB_FIELDS = "fields";
	public static final String TAB_PKNAME = "pkName";
	public static final String TAB_TABLENAME = "tableName";
	public static final String TAB_ID = "id";
	private String fieldName;
	private Object fieldValue;
	public TableInfo(String fieldName, Object fieldValue) {
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public Object getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(Object fieldValue) {
		this.fieldValue = fieldValue;
	}
}
