package com.ynon.coupons.beans;

public class ErrorBean {

	private int errNum;
	private String errMsg;
	private boolean errorName;

	public ErrorBean(int errNum, String errMsg, boolean errorName) {
		super();
		this.errNum = errNum;
		this.errMsg = errMsg;
		this.errorName = errorName;
	}

	public ErrorBean() {}

	public int getErrNum() {
		return errNum;
	}

	public void setErrNum(int errNum) {
		this.errNum = errNum;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public boolean getErrorName() {
		return errorName;
	}

	public void setErrorName(boolean errorName) {
		this.errorName = errorName;
	}

	@Override
	public String toString() {
		return "ErrorBean [errNum=" + errNum + ", errMsg=" + errMsg + ", errorName=" + errorName + "]";
	}



}
