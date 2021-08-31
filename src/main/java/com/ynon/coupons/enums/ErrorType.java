package com.ynon.coupons.enums;

public enum ErrorType {

	GENERAL_ERROR(601,"General error",true),
	INVALID_USER(602, "Username already exist",false),
	INVALID_COMPANY(603, "Company already exist",false),
	COMPANY_MISMATCH(604,"Null Company",false),
	COMPANY_NAME_SHORT(605,"Company name is too short",false),
	DELETE_COMPANY_FAILED(606,"Delete company was failed",true),
	COSTUMER_EXIST(607,"Costumer details already exist",false),
	INVALID_COSTUMER(608,"Invalid costumer",false),
	PURCHASE_FAILED(609,"Purchase Failed",true),
	PURCHASE_DELETE_FAILED(610,"Delete purchase from DB failed",false), 
	INVALID_PURCHASE(611,"Invalid purchase",false), 
	NULL(612,"Missing information",false), 
	USER_SHORT_PASSWORD(613,"Short password",false),
	USER_EMPTY_PASSWORD(614,"Empty password",false), 
	USER_LONG_PASSWORD(615,"Long password",false), 
	USER_INVALID_EMAIL(616,"Invalid Email",false), 
	USER_ALREADY_EXIST(617,"User already exist",false),
	USER_INVALID(618,"User is not exist",false), 
	CONNECTION_ERROR(619,"Connection error",true), 
	COUPON_INVALID_PRICE(620,"Invalid coupon price",true),
	INVALID_COMPANY_ID(621,"Company is invalid",false), 
	PURCHASE_FAILED_COUPON_EXPIRED(622,"expired coupon is trying to be purchase",true),
	PURCHASE_FAILED_COUPON_OUT_OF_STOKE(623,"coupon out of stock",false),
	COUPON_EXIST(624,"Coupon add failed - coupon already exist",true),
	COUPON_MAXPRICE_PRICE(625,"Get coupon by max price failed",false),
	COUPON_BY_CATEGORY(626,"Get coupons by category failed",true),
	COUPON_AMOUNT_INVALID(627,"Lowly amount",true), 
	USER_LOGIN_FAILED(628,"Login Failed",false), 
	USER_CREATE_ERR(629,"Create User Failed",false),
	WRONG_USERNAME_OR_PASSWORD(630,"Wrong user name or password", false), 
	PURCHASE_FAILED_DOUBLE_PURCHASE(631,"can not purchase the same coupon twice",false), 
	COUPON_TITLE_IS_ALREADY_EXISTS(632,"Coupon title is already exists, choose a different title",false),
	ID_NOT_EXIST(633,"Id Not Exist",false),
	IMAGE_UPLOAD_FAILED(634,"IMAGE UPLOAD FAILED",false),
	IMAGE_ID_NOT_FOUND(634,"IMAGE_ID_NOT_FOUND",false);




	private int errorNumber;
	private String errorMessage;
	private boolean isPrintStackTrace;

	private ErrorType(int errorNumber, String errorMassage, boolean stackTracePrint) {
		this.errorNumber = errorNumber;
		this.errorMessage = errorMassage;
		this.isPrintStackTrace = stackTracePrint;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

	public int getErrorNum() {
		return this.errorNumber;
	}

	public boolean isPrintStackTrace() {
		return isPrintStackTrace;
	}





}

