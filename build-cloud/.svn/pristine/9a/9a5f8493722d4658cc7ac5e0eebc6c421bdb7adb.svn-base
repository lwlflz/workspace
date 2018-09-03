package com.build.cloud.common.utils;
import java.io.PrintWriter;
import java.io.StringWriter;
/**
 * <p>
 * PrintStackTrace.java
 * </p>
 * <p>
 * <Method Simple Comment>
 * </p>
 * <Detail Description>
 * @author 刘滔
 * @since 2017年6月14日 下午3:24:32
 */
public class PrintStackTrace {
	/**
	 * 
	 */
	public PrintStackTrace() {
		// TODO Auto-generated constructor stub
	}
	public static String getErrorInfoFromException(Throwable e) {
		try {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			return "\r\n" + sw.toString() + "\r\n";
		} catch (Exception e2) {
			return "bad getErrorInfoFromException";
		}
	}
}
