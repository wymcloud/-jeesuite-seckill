/**
 * 
 */
package com.jeesuite.seckill.interceptor;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.jeesuite.seckill.exception.DemoBaseException;
import com.jeesuite.mybatis.plugin.cache.CacheHandler;

@Aspect
@Service
@Order(0)
public class ServiceInterceptor{
	
	protected static final Logger logger = LoggerFactory.getLogger(ServiceInterceptor.class);

	//定义拦截切面
	@Pointcut("execution(* com.jeesuite.demo.service..*.*(..))")  
    public void pointcut(){}

	 @Around("pointcut()") 
	 public Object around(ProceedingJoinPoint pjp) throws Throwable{
		 Method method = null;
		 try {
			method = ((MethodSignature)pjp.getSignature()).getMethod();
			return pjp.proceed();
		} catch (Exception e) {
			//回滚自动缓存
			CacheHandler.rollbackCache();
			if(e instanceof DemoBaseException)throw e;
			
			
			logger.error(method.getName(),e);
			throw new RuntimeException(e);
		}
	 }  

}
