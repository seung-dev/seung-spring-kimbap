package seung.spring.kimbap;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;

import seung.java.kimchi.exception.SCastException;

public enum SKimbapError {

	Success(null, HttpStatus.OK, "0000")
	, Exception(Exception.class, HttpStatus.INTERNAL_SERVER_ERROR, "E999")
	, SCastException(SCastException.class, HttpStatus.INTERNAL_SERVER_ERROR, "E001")
	, EmptyResultDataAccessException(EmptyResultDataAccessException.class, HttpStatus.INTERNAL_SERVER_ERROR, "E002")
	, DuplicateKeyException(DuplicateKeyException.class, HttpStatus.INTERNAL_SERVER_ERROR, "E002")
	, ConstraintViolationException(ConstraintViolationException.class, HttpStatus.BAD_REQUEST, "E003")
	, MissingServletRequestParameterException(MissingServletRequestParameterException.class, HttpStatus.BAD_REQUEST, "E004")
	;
	
	private final Class<?> exception;
	private final HttpStatus httpStatus;
	private final String errorCode;
	
	private SKimbapError(Class<?> exception, HttpStatus httpStatus, String errorCode) {
		this.httpStatus = httpStatus;
		this.exception = exception;
		this.errorCode = errorCode;
	}
	
	public static SKimbapError matchError(Class<?> exception) {
		for(SKimbapError sError : SKimbapError.values()) {
			if(exception.equals(sError.exception())) {
				return sError;
			}
		}
		return SKimbapError.Exception;
	}
	
	public int httpStatus() {
		return this.httpStatus.value();
	}
	
	public String errorCode() {
		return this.errorCode;
	}
	
	public Class<?> exception() {
		return this.exception;
	}
	
}
