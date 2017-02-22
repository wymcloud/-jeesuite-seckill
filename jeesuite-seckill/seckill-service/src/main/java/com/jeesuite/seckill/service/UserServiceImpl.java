/**
 * 
 */
package com.jeesuite.seckill.service;

import java.util.Map;

import com.jeesuite.kafka.spring.TopicConsumerSpringProvider;
import com.jeesuite.seckill.api.IUserService;
import com.jeesuite.seckill.dao.entity.UserEntity;
import com.jeesuite.seckill.dao.mapper.UserEntityMapper;
import com.jeesuite.seckill.dto.User;
import com.jeesuite.seckill.exception.UserNoExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.jeesuite.cache.command.RedisHashMap;
import com.jeesuite.cache.command.RedisString;
import com.jeesuite.common.util.BeanCopyUtils;
import com.jeesuite.common2.lock.DistributeLockTemplate;
import com.jeesuite.common2.lock.LockCaller;
import com.jeesuite.common2.lock.MultiRetryLock;
import com.jeesuite.filesystem.spring.FSProviderSpringFacade;
import com.jeesuite.kafka.message.DefaultMessage;
import com.jeesuite.kafka.spring.TopicProducerSpringProvider;

/**
 * @description <br>
 * @author <a href="mailto:vakinge@gmail.com">vakin</a>
 * @date 2016年10月25日
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired(required = false)
	private UserEntityMapper userMapper;
	
	@Autowired(required = false)
	private TopicProducerSpringProvider kafkaProducer;

	@Autowired
	private TransactionTemplate transactionTemplate;
	
	@Qualifier("qiniuFSProvider")
	private FSProviderSpringFacade provider;

	@Override
	public User getUser(Integer userId) throws UserNoExistException {
		UserEntity entity = userMapper.selectByPrimaryKey(userId);
		if(entity == null)throw new UserNoExistException();
		return BeanCopyUtils.copy(entity, User.class);
	}


	@Override
	public void updateUser(User user) {
		//分布式锁
		MultiRetryLock lock = new MultiRetryLock("updateuser_"+user.getId());
		lock.lock();
		try {			
			final UserEntity entity = BeanCopyUtils.copy(user, UserEntity.class);
			transactionTemplate.execute(new TransactionCallback<Void>() {
				
				@Override
				public Void doInTransaction(TransactionStatus status) {
					userMapper.updateByPrimaryKeySelective(entity);
					return null;
				}
			});
		} finally {
			lock.unlock();
		}
		
		//另外一种锁使用方法
		DistributeLockTemplate.execute("lockkey-0001", new LockCaller<Void>() {
			@Override
			public Void onHolder() {
				//获得锁干点啥？
				return null;
			}

			@Override
			public Void onWait() {
				// 没获得锁干点啥？
				return null;
			}
		}, 10000);
	}

	@Override
	@Transactional
	public void removeUser(final Integer userId) throws UserNoExistException {
		UserEntity entity = userMapper.selectByPrimaryKey(userId);
		if(entity == null)throw new UserNoExistException();
		userMapper.deleteByPrimaryKey(userId);
	}




	@Override
	public void kafkaProducerTest() {
		kafkaProducer.publish("demo-topic", new DefaultMessage("hello"));
	}


	@Override
	public void cacheTest() {
		RedisString redisString = new RedisString("key1");
		
		if(redisString.exists()){			
			String value = redisString.get();
			System.out.println(value);
			redisString.remove();
		}
		
		RedisHashMap redisHashMap = new RedisHashMap("key2");
		
		redisHashMap.containsKey("field1");
		redisHashMap.set("field1", "111");
		
		Map<String, User> users = redisHashMap.getAll();
		
	}

	/*@Override
	public String upload(byte[] data, String fileName) {
		return null;
	}*/


	@Override
	public String upload(byte[] data, String fileName) {
		String url = provider.upload(null, fileName, data, null);
		return url;
	}

}
