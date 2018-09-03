package com.build.cloud.modules.bs.entity;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.build.cloud.core.entity.DataEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author liangsen
 * @since 2018-05-16
 */
@TableName("bs_code")
public class BsCodeEntity extends DataEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 类型
     */
	@TableField("code_type")
	private String codeType;
    /**
     * 编码
     */
	@TableField("code_num")
	private String codeNum;
    /**
     * 名称
     */
	@TableField("code_name")
	private String codeName;
	/**
     * 父ID
     */
    @TableField("parent_id")
    private String parentId;
    /**
     * 所有父ID
     */
    @TableField("parent_ids")
    private String parentIds;

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getCodeNum() {
		return codeNum;
	}

	public void setCodeNum(String codeNum) {
		this.codeNum = codeNum;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
}
