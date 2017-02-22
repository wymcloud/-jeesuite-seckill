package com.jeesuite.test.mapper;

import com.jeesuite.seckill.dao.entity.SuccessKilledEntity;
import com.jeesuite.seckill.dao.mapper.SuccessKilledEntityMapper;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test-root.xml"})
public class SuccessKilledMapperTest implements ApplicationContextAware{

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Resource
    private SuccessKilledEntityMapper successKilledMapper;

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		InstanceFactory.setInstanceProvider(new SpringInstanceProvider(arg0));
	}

    @Test
    public void insertSuccessKilled() throws Exception {

        long seckillId=1000L;
        long userPhone=13476191877L;
        int insertCount=successKilledMapper.insertSuccessKilled(seckillId,userPhone);
        System.out.println("insertCount="+insertCount);
    }

    @Test
    public void queryByIdWithSeckill() throws Exception {
        long seckillId=1000L;
        long userPhone=13476191877L;
        SuccessKilledEntity successKilled=successKilledMapper.queryByIdWithSeckill(seckillId,userPhone);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());


    }
}
