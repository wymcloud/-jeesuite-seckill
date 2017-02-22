/**
 * 
 */
package com.jeesuite.seckill.exception;

/**
 * @description <br>
 * @author <a href="mailto:vakinge@gmail.com">vakin</a>
 * @date 2016年10月25日
 */
public class DemoBaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private int code;
	
	public DemoBaseException() {
		super();
	}

	public DemoBaseException(int code,String message) {
		super(message);
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	

}
