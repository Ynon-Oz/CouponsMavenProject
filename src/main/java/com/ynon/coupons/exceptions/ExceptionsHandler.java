package com.ynon.coupons.exceptions;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ynon.coupons.beans.ErrorBean;
import com.ynon.coupons.enums.ErrorType;

// Exception handler class
@RestControllerAdvice
public class ExceptionsHandler {

	//	Response - Object in Spring
	@ExceptionHandler
	@ResponseBody
	// Variable name is throwable in order to remember that it handles Exception and Error
	public ErrorBean toResponse(Throwable throwable, HttpServletResponse response) {

		//	ErrorBean errorBean;
		if(throwable instanceof ApplicationException) {
			ApplicationException appException = (ApplicationException) throwable;

			ErrorType errorType = appException.getErrorType(); 
			int errorNumber = errorType.getErrorNum();
			String errorMessage = errorType.getErrorMessage();
			Boolean errorPrintToLog = errorType.isPrintStackTrace();
			//			String errorName = errorType.name();
			if(errorType.isPrintStackTrace()) {
				appException.printStackTrace();
			}

			response.setStatus(errorType.getErrorNum());
			ErrorBean errorBean = new ErrorBean(errorNumber, errorMessage, errorPrintToLog); 

			return errorBean;
		}

		response.setStatus(500);
		String errorMessage = throwable.getMessage();
		ErrorBean errorBean = new ErrorBean(600, errorMessage, true);
		throwable.printStackTrace();

		return errorBean;
	}

}



