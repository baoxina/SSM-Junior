package com.baoxina.web.exception;

/**
 * 系统自定义异常类，针对预期的异常，需要在程序中抛出此类的异常
 * @author baoxina
 *
 */
public class YiLiveException extends Exception{

	private static final long serialVersionUID = 5700284586711380735L;
	//异常信息
	private String message;
	public YiLiveException(String message) {
		super(message);
		this.setMessage(message);
	}
	public String getMessage() {
		return this.message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
