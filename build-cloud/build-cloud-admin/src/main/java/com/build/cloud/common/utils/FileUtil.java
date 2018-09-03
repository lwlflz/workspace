package com.build.cloud.common.utils;
import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import sun.misc.BASE64Decoder;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.build.cloud.common.constant.Constant;
public final class FileUtil extends FileUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);
	public static String SEPARATOR = File.separator;
	private FileUtil() {
	}
	/**
	 * 判断文件夹是否存在
	 * @param file
	 * @author 刘滔
	 * @see
	 */
	public static void judeDirExists(File file) {
		if (file.exists()) {
			if (file.isDirectory()) {
				LOGGER.info("dir exists");
			} else {
				LOGGER.info("the same name file exists, can not create dir");
			}
		} else {
			LOGGER.info("dir not exists, create it ...");
			file.mkdirs();
		}
	}
	public static File newFile(String filePath) {
		File file = new File(filePath);
		// 创建文件
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}
	/**
	 * 将MultipartFile转换成File
	 * @param file
	 * @return
	 */
	public static File convert(MultipartFile file) {
		judeDirExists(new File(Constant.getPicturePath()));
		String filePath = Constant.getPicturePath() + SEPARATOR + 
				DateUtils.dateToStamp() + "."+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
		File convFile = new File(filePath);
		try {
			copyInputStreamToFile(file.getInputStream(), convFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return convFile;
	}
	
	public static MultipartFile base64ToMultipart(String base64) {
	    try {
	        String[] baseStrs = base64.split(",");

	        BASE64Decoder decoder = new BASE64Decoder();
	        byte[] b = new byte[0];
	        b = decoder.decodeBuffer(baseStrs[1]);

	        for(int i = 0; i < b.length; ++i) {
	            if (b[i] < 0) {
	                b[i] += 256;
	            }
	        }

	        return new BASE64DecodedMultipartFile(b, baseStrs[0]);
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	public static String getMimeType(String fileUrl)
		throws java.io.IOException {
		FileNameMap fileNameMap = URLConnection.getFileNameMap();
		String type = fileNameMap.getContentTypeFor(fileUrl);
		return type;
	}
	// 创建图片类型数组
	private static final String IMG[] =
		{
			"bmp", "jpg", "jpeg", "png", "tiff", "gif", "pcx", "tga", "exif", "fpx", "svg", "psd", "cdr", "pcd", "dxf",
			"ufo", "eps", "ai", "raw", "wmf" };
	// 创建文档类型数组
	private static final String DOCUMENT[] =
		{ "txt", "doc", "docx", "xls", "htm", "html", "jsp", "rtf", "wpd", "pdf", "ppt" };
	// 创建视频类型数组
	private static final String VIDEO[] =
		{ "mp4", "avi", "mov", "wmv", "asf", "navi", "3gp", "mkv", "f4v", "rmvb", "webm" };
	// 创建音乐类型数组
	private static final String MUSIC[] =
		{ "mp3", "wma", "wav", "mod", "ra", "cd", "md", "asf", "aac", "vqf", "ape", "mid", "ogg", "m4a", "vqf" };
	public static String getFileType(String fileName) {
		if (fileName == null) {
			fileName = "文件名为空！";
			return fileName;
		} else {
			// 获取文件后缀名并转化为写，用于后续比较
			String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();
			for (String img : IMG) {
				if (img.equals(fileType)) {
					return "image";
				}
			}
			for (String document : DOCUMENT) {
				if (document.equals(fileType)) {
					return "file";
				}
			}
			for (String video : VIDEO) {
				if (video.equals(fileType)) {
					return "media";
				}
			}
			for (String music : MUSIC) {
				if (music.equals(fileType)) {
					return "music";
				}
			}
		}
		return "other";
	}
}
