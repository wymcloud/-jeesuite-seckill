/**
 * 
 */
package com.jeesuite.seckill.exception;

/**
 * @description <br>
 * @author <a href="mailto:vakinge@gmail.com">vakin</a>
 * @date 2016年10月25日
 */
public class UserNoExistException extends DemoBaseException {

	private static final long serialVersionUID = 1L;

	public UserNoExistException() {
		super(1001,"用户不存在!!");
	}

}
