/**
 * 
 */
package com.jeesuite.seckill.api;

import com.jeesuite.seckill.dto.User;
import com.jeesuite.seckill.exception.UserNoExistException;

/**
 * @description <br>
 * @author <a href="mailto:vakinge@gmail.com">vakin</a>
 * @date 2016年10月25日
 */
public interface IUserService {

	User getUser(Integer userId) throws UserNoExistException;
	
	void updateUser(User user);
	
	void removeUser(Integer userId) throws UserNoExistException;
	
	void kafkaProducerTest();
	
	void cacheTest();
	
	String upload(byte[] data,String fileName);
}
