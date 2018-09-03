package com.build.cloud.common.exception;

/**
 * 
 * <p>BusinessException.java</p> 
 * <p>自定义异常</p>
 * <Detail Description>
 * @author LiuTao
 * @since 2017年6月1日 上午1:00:01
 */
public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
    private String message;
	private int code = 500;
    
    public BusinessException(String message) {
		super(message);
		this.message = message;
	}
	
	public BusinessException(String message, Throwable e) {
		super(message, e);
		this.message = message;
	}
	
	public BusinessException(String message, int code) {
		super(message);
		this.message = message;
		this.code = code;
	}
	
	public BusinessException(String message, int code, Throwable e) {
		super(message, e);
		this.message = message;
		this.code = code;
	}

	public String getmessage() {
		return message;
	}

	public void setmessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	
}
