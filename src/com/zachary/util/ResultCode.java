package com.zachary.util;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @desc 返回h5页面数据格式{"errcode":"-1","errmsg":"系统繁忙"}
 * @author zhangcheng
 * @since crm-wx-core-1.0
 */
public class ResultCode<T> implements Serializable{

	private ResultCode(String code, String errmsg, T retval, boolean isSuccess) {
		errcode = code;
		success = isSuccess;
		this.errmsg = errmsg;
		this.retval = retval;
	}

	/*public boolean equals(Object another) {
		if (another == null || !(another instanceof ResultCode))
			return false;
		else
			return errcode == ((ResultCode<?>) another).errcode;
	}*/

	/**
	 * 返回失败数据, 不包含对象
	 * @param code
	 * @param errmsg
	 * @return
	 */
	public static <T> ResultCode<T> getFailure(String code, String errmsg) {
		return new ResultCode<T>(StringUtils.isEmpty(code) ? CODE_FAIL : code,
				errmsg, null, false);
	}

	/**
	 * 返回失败数据, 包含对象
	 * @param code
	 * @param errmsg
	 * @param retval
	 * @return
	 */
	public static <T> ResultCode<T> getFailureReturn(String code, String errmsg,
			T retval) {
		return new ResultCode<T>(StringUtils.isEmpty(code) ? CODE_FAIL : code,
				errmsg, retval, false);
	}
	
	/**
	 * 返回失败数据, 不包含对象
	 * @param code
	 * @param e 异常信息
	 * @return
	 */
	public static <T> ResultCode<T> getFailureException(String code, Exception e){
		if(e instanceof Exception){
			return getFailure(code, e.getMessage());
		}
		return getFailure(code, "系统繁忙，请稍后再试！");
	}
	
	/**
	 * 返回成功数据, 不包含对象
	 * @param code
	 * @param errmsg
	 * @return
	 */
	public static <T> ResultCode<T> getSuccess(String code, String errmsg) {
		return new ResultCode<T>(StringUtils.isEmpty(code) ? CODE_SUCCESS
				: code, errmsg, null, true);
	}

	/**
	 * 返回成功数据, 包含对象
	 * @param code
	 * @param errmsg
	 * @param retval
	 * @return
	 */
	public static <T> ResultCode<T> getSuccessReturn(String code,
			String errmsg, T retval) {
		return new ResultCode<T>(StringUtils.isEmpty(code) ? CODE_SUCCESS
				: code, errmsg, retval, true);
	}

	public static String gsonToString(Object obj) {
		return gson.toJson(obj);
	}

	/**
	 * @return success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @return retval
	 */
	public Object getRetval() {
		return retval;
	}

	/**
	 * @return errmsg
	 */
	public String getErrmsg() {
		return errmsg;
	}

	/**
	 * @return errcode
	 */
	public String getErrcode() {
		return errcode;
	}

	private static final long serialVersionUID = -4859637902882356019L;
	public static final String CODE_SUCCESS = "0";
	public static final String CODE_FAIL = "-1";
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final ResultCode<String> SUCCESS = new ResultCode(
			CODE_SUCCESS, "ok", null, true);
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final ResultCode<String> FAIL = new ResultCode(CODE_FAIL,
			"error", null, false);
	private String errcode;
	private String errmsg;
	private T retval;
	@JSONField(serialize = false)
	private boolean success;
	private static Gson gson = new GsonBuilder()
			.setDateFormat("yyyy-MM-dd HH:mm:ss").disableHtmlEscaping()
			.setPrettyPrinting().create();
	
}
