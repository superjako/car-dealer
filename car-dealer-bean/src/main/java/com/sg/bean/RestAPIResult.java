package com.sg.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.sg.constant.SystemConstant;

/**
 * REST API接口统一响应接口实体
 */
public class RestAPIResult<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	// 返回代码，1表示成功，其它的都有对应问题
	private int respCode = 1;

	// 如果code!=1,错误信息
	private String respMsg = "成功！";

	@SuppressWarnings("unchecked")
	// respCode为1时返回结果
	private T respData = (T) new Object();

	// 附加信息
	private Map<String, Object> respMap = new HashMap<String, Object>();

	public int getRespCode() {
		return respCode;
	}

	public void setRespCode(int respCode) {
		this.respCode = respCode;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

	public T getRespData() {
		return respData;
	}

	public void setRespData(T respData) {
		this.respData = respData;
	}

	public Map<String, Object> getRespMap() {
		return respMap;
	}

	public void setRespMap(Map<String, Object> respMap) {
		this.respMap = respMap;
	}

	@SuppressWarnings("unchecked")
	public RestAPIResult(String errorMsg) {
		this.respMsg = errorMsg;
		this.respCode = SystemConstant.Code.error;
		this.respData = (T) new Object();
		this.respMap = new HashMap<String, Object>();
	}

	@SuppressWarnings("unchecked")
	public RestAPIResult() {
		this.respData = (T) new Object();
		this.respMap = new HashMap<String, Object>();
	}

	public void success(T object) {
		this.respCode = SystemConstant.Code.success;
		this.respMsg = SystemConstant.Message.success;
		this.respData = object;
		this.respMap = new HashMap<String, Object>();
	}

	@SuppressWarnings("unchecked")
	public void error() {
		this.respCode = SystemConstant.Code.error;
		this.respMsg = SystemConstant.Message.error;
		this.respData = (T) new Object();
		this.respMap = new HashMap<String, Object>();
	}

	@SuppressWarnings("unchecked")
	public void error(String message) {
		this.respCode = SystemConstant.Code.error;
		this.respMsg = message;
		this.respData = (T) new Object();
		this.respMap = new HashMap<String, Object>();
	}

}
