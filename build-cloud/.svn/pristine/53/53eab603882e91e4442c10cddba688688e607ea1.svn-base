package com.build.cloud.modules.fastdfs.service;
import java.io.File;
import java.util.Map;

import com.sunsine.fastdfs.FastDfsInfo;
import com.sunsine.fastdfs.exception.FastDFSException;
/**
 * @ClassName: IFileService
 * @Description: 文件管理
 * @author: liutao
 * @date: 2018年3月2日 下午2:06:43
 */
public interface IFileService {
	public FastDfsInfo upload(String filePath) throws FastDFSException;
	public FastDfsInfo upload(String filePath, String suffix) throws FastDFSException;
	public FastDfsInfo upload(String filePath, String suffix, Map<String, String> values) throws FastDFSException;
	
	public void deleteFile(FastDfsInfo dfs) throws FastDFSException;
	public void deleteFile(String groupName, String remoteFileName) throws FastDFSException;
	
	public byte[] downloadFile(FastDfsInfo dfs) throws FastDFSException;
	public byte[] downloadFile(String groupName, String remoteFileName) throws FastDFSException;
}
