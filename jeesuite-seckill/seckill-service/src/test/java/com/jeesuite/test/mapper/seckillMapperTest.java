/**
 * 
 */
package com.jeesuite.test.mapper;

import com.jeesuite.cache.command.RedisObject;
import com.jeesuite.common.util.BeanCopyUtils;
import com.jeesuite.seckill.dao.cache.SeckillCache;
import com.jeesuite.seckill.dao.entity.SeckillEntity;
import com.jeesuite.seckill.dao.mapper.SeckillEntityMapper;
import com.jeesuite.seckill.dto.Seckill;
import com.jeesuite.spring.InstanceFactory;
import com.jeesuite.spring.SpringInstanceProvider;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test-root.xml"})
public class seckillMapperTest implements ApplicationContextAware{

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Resource
	private SeckillEntityMapper seckillMapper;

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		InstanceFactory.setInstanceProvider(new SpringInstanceProvider(arg0));
	}

    @Test
    public void queryById() throws Exception {
        long seckillId=1000;
        SeckillEntity seckill=seckillMapper.queryById(seckillId);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }

    @Test
    public void queryAll() throws Exception {

        List<SeckillEntity> seckills=seckillMapper.queryAll(0,100);
        for (SeckillEntity seckill : seckills)
        {
            System.out.println(seckill);
        }
    }

	@Test
	public void reduceNumber() throws Exception {

		long seckillId=1000;
		Date date=new Date();
		int updateCount=seckillMapper.reduceNumber(seckillId,date);
		System.out.println(updateCount);

	}
    @Test
    public void testSeckillCache() throws Exception {

     /* RedisObject redisObject = new RedisObject("seckill");*/
         /* redisObject.set(new Seckill(1001, 2000));
        Object seckill = redisObject.get();
        redisObject.getTtl();
        redisObject.exists();
        redisObject.setExpire(300);
        redisObject.remove();*/
        /*int id = 1001;
        SeckillCache sc = new SeckillCache();
        SeckillEntity seckill = sc.getSeckill(id);
        if(seckill == null){
            seckill = seckillMapper.queryById(id);
            if(seckill!= null){
                String result = sc.putSeckill(seckill);
                System.out.println(result);
                seckill = sc.getSeckill(id);
                System.out.println(seckill);
            }
        }*/
        }

    }
