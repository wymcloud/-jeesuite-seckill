/**
 * 
 */
package com.jeesuite.seckill.startup;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jeesuite.spring.InstanceFactory;
import com.jeesuite.spring.SpringInstanceProvider;

/**
 * @description <br>
 * @author <a href="mailto:vakinge@gmail.com">vakin</a>
 * @date 2016年10月21日
 */
public class AppServer {

	private final static Logger logger = LoggerFactory.getLogger(AppServer.class);

	private static final String RUNING_FILENAME = "runing";

	private static File confDir;
	private static File logDir;

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// 定义配置项
		// PropertySourcesPlaceholderConfigurer configurer = new
		// PropertySourcesPlaceholderConfigurer();
		
		//初始化本地日志输出目录
		confDir = new File(Thread.currentThread().getContextClassLoader().getResource("").getPath());
		final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/root.xml");

		logger.info("启动app-server....");

		// 初始化spring 工厂
		InstanceFactory.setInstanceProvider(new SpringInstanceProvider(context));

		System.out.println("==============================");
		System.out.println("|---------服务启动成功----------|");
		System.out.println("==============================");

		// 注册服务关闭钩子
		addShutdownHook(context);
		// Thread.currentThread().join();

	}

	/**
	 * 配合脚本stop，确保context.close()执行后在kill进程
	 * 
	 * @throws IOException
	 */
	private static void addShutdownHook(ClassPathXmlApplicationContext context) throws Exception {
		context.registerShutdownHook();
		
		if (confDir.getName().contains("conf") == false) {
			return;
		}
		if(System.getProperty("os.name").toLowerCase().startsWith("win")){  
		  return ; 
		}  
		logger.info("logDir:{}", logDir.getAbsolutePath());

		File runingFile = new File(logDir, RUNING_FILENAME);
		// 防止启动脚本阻塞，未及时创建runing文件
		try {
			Thread.sleep(60 * 1000);
		} catch (Exception e) {
		}
		while (true) {
			// runing文件被脚本stop命令删除
			if (!runingFile.exists()) {
				logger.info("收到脚本关闭请求。。。");
				try {
					context.close();
				} catch (Exception e) {
					logger.error("context关闭失败", e);
				}
				boolean ok = new File(logDir, "stoping").createNewFile();
				if (ok) {
					logger.info("context 关闭ok");
				}
				break;
			}
			try {
				Thread.sleep(3000);
			} catch (Exception e) {
			}
		}
	}

}
