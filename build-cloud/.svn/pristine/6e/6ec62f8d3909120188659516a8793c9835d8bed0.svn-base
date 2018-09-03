package com.build.cloud.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * 
 * @ClassName: SysFileEntityEntity   
 * @Description: 文件实体表
 * @author: liutao
 * @date: 2018年4月3日 上午11:36:54
 */
@TableName("sys_file_entity")
public class SysFileEntityEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 文件编号
	 */
	@TableId(value = "file_id", type = IdType.UUID)
	private String fileId;
	/**
	 * 文件MD5
	 */
	private String fileMd5;
	/**
	 * 文件相对路径
	 */
	private String filePath;
	/**
	 * 文件内容类型
	 */
	private String fileContentType;
	/**
	 * 文件后缀扩展名
	 */
	private String fileExtension;
	/**
	 * 文件大小(单位B)
	 */
	private long fileSize;

	/**
	 * 设置：文件编号
	 */
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	/**
	 * 获取：文件编号
	 */
	public String getFileId() {
		return fileId;
	}
	/**
	 * 设置：文件MD5
	 */
	public void setFileMd5(String fileMd5) {
		this.fileMd5 = fileMd5;
	}
	/**
	 * 获取：文件MD5
	 */
	public String getFileMd5() {
		return fileMd5;
	}
	/**
	 * 设置：文件相对路径
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	/**
	 * 获取：文件相对路径
	 */
	public String getFilePath() {
		return filePath;
	}
	/**
	 * 设置：文件内容类型
	 */
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	/**
	 * 获取：文件内容类型
	 */
	public String getFileContentType() {
		return fileContentType;
	}
	/**
	 * 设置：文件后缀扩展名
	 */
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	/**
	 * 获取：文件后缀扩展名
	 */
	public String getFileExtension() {
		return fileExtension;
	}
	/**
	 * 设置：文件大小(单位B)
	 */
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	/**
	 * 获取：文件大小(单位B)
	 */
	public long getFileSize() {
		return fileSize;
	}
}
