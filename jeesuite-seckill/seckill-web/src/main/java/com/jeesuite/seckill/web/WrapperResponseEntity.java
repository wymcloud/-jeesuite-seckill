package com.jeesuite.seckill.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


public class WrapperResponseEntity {

	// 状态
	private String code;

	// 返回信息
	private String msg;

	// 响应数据
	@JsonInclude(Include.NON_NULL)
	private Object data;
	
	
	public WrapperResponseEntity(){};


	
	/**
	 * 构造函数
	 * 
	 * @param errorCode
	 * @param msg
	 */
	public WrapperResponseEntity(String errorCode, String msg,Object data) {
		this.code = errorCode;
		this.msg = msg;
		this.data = data;
	}

	/**
	 * 获取数据
	 * 
	 * @return
	 */
	public Object getData() {
		return data;
	}

	/**
	 * 获取状态
	 * 
	 * @return
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * 获取信息
	 * 
	 * @return
	 */
	public String getMsg() {
		return this.msg;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "RestResponse [getData()=" + getData() + ", getCode()=" + getCode() + ", getMsg()=" + getMsg() + "]";
	}
}
