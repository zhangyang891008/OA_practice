package com.example;

public class RespStat {
	
	public RespStat() {
	}
	
	public RespStat(int code, String msg, String exception) {
		this.code = code;
		this.msg = msg;
		this.exception = exception;
	}
	
	private int code;
	private String msg;
	private String exception;
	
	public static RespStat buildRespStat(int code, String msg, String exception) {
		return new RespStat(code, msg, exception);
	}
	
	public static RespStat buildRespStat(int code, String msg) {
		return new RespStat(code, msg, "");
	}
	
 
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	
}
