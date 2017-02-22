/**
 * 
 */
package com.jeesuite.seckill.dao;

import tk.mybatis.mapper.common.ExampleMapper;

/**
 * @description <br>
 * @author <a href="mailto:vakinge@gmail.com">vakin</a>
 * @date 2016年11月17日
 */
public interface BaseMapper<T> extends tk.mybatis.mapper.common.BaseMapper<T>,ExampleMapper<T>{

}
