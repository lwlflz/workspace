package com.build.cloud.modules.fastdfs.service.impl;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.csource.common.MyException;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.build.cloud.modules.fastdfs.service.IFileService;
import com.google.common.collect.Maps;
import com.sunsine.fastdfs.FastDFSTemplate;
import com.sunsine.fastdfs.FastDfsInfo;
import com.sunsine.fastdfs.exception.FastDFSException;

import cn.hutool.core.io.FileUtil;
/**
 * @ClassName: FileServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author: liutao
 * @date: 2018年3月2日 下午2:13:03
 */
@Service
public class FileServiceImpl implements IFileService {
	private static final Logger LOGGER = LoggerFactory.getLogger(FileServiceImpl.class);
	@Autowired
	private FastDFSTemplate dfsTemplate;
	private ExecutorService cachedThreadPool = Executors.newFixedThreadPool(100);
	private final long awaitTime = 5 * 1000;

	@Override
	public FastDfsInfo upload(String filePath)
		throws FastDFSException {
		String suffix = FileUtil.extName(filePath);
		return upload(filePath, suffix);
	}
	@Override
	public FastDfsInfo upload(String filePath, String suffix)
		throws FastDFSException {
		FastDfsInfo rv = null;
		File file = new File(filePath);
		try (FileInputStream fis = new FileInputStream(file);) {
			byte[] b = new byte[fis.available()];
			fis.read(b);
			rv = dfsTemplate.upload(b, suffix, Maps.newHashMap());
			return rv;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rv;
//		return upload(filePath, suffix, Maps.newHashMap());
	}
	@Override
	public FastDfsInfo upload(String filePath, String suffix, Map<String, String> values)
		throws FastDFSException {
		Callable<FastDfsInfo> runnable = () -> {
			File file = new File(filePath);
			try (FileInputStream fis = new FileInputStream(file);) {
				byte[] b = new byte[fis.available()];
				fis.read(b);
				FastDfsInfo rv = dfsTemplate.upload(b, suffix, values);
				return rv;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		};
		FastDfsInfo rv = null;
		Future<?> future = cachedThreadPool.submit(runnable);
		try {
			rv = (FastDfsInfo)future.get();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 close(cachedThreadPool);
		return rv;
	}
	@Override
	public void deleteFile(FastDfsInfo dfs)
		throws FastDFSException {
		dfsTemplate.deleteFile(dfs);
	}
	@Override
	public void deleteFile(String groupName, String remoteFileName)
		throws FastDFSException {
		dfsTemplate.deleteFile(groupName, remoteFileName);
	} 
	@Override
	public byte[] downloadFile(FastDfsInfo dfs)
		throws FastDFSException {
		return dfsTemplate.loadFile(dfs);
	}
	@Override
	public byte[] downloadFile(String groupName, String remoteFileName)
		throws FastDFSException {
		return dfsTemplate.loadFile(groupName, remoteFileName);
	}
	private void close(ExecutorService service) {
		try {
			service.shutdown();
			// (所有的任务都结束的时候，返回TRUE)
			if (!service.awaitTermination(awaitTime, TimeUnit.MILLISECONDS)) {
				// 超时的时候向线程池中所有的线程发出中断(interrupted)。
				service.shutdownNow();
			}
		} catch (InterruptedException e) {
			// awaitTermination方法被中断的时候也中止线程池中全部的线程的执行。
			LOGGER.error("awaitTermination interrupted: " + e);
			service.shutdownNow();
		}
	}
}
