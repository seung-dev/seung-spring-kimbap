package seung.spring.kimbap.exception;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;

import seung.java.kimchi.exception.SKimchiException;

public enum SKimbapError {

    Success(null, HttpStatus.OK, "0000")
    , Exception(Exception.class, HttpStatus.INTERNAL_SERVER_ERROR, "E999")
    , SKimchiException(SKimchiException.class, HttpStatus.INTERNAL_SERVER_ERROR, "E901")
    , SKimbapException(SKimbapException.class, HttpStatus.INTERNAL_SERVER_ERROR, "E902")
    , EmptyResultDataAccessException(EmptyResultDataAccessException.class, HttpStatus.INTERNAL_SERVER_ERROR, "E911")
    , DuplicateKeyException(DuplicateKeyException.class, HttpStatus.INTERNAL_SERVER_ERROR, "E912")
    , ConstraintViolationException(ConstraintViolationException.class, HttpStatus.BAD_REQUEST, "E401")
    , MissingServletRequestParameterException(MissingServletRequestParameterException.class, HttpStatus.BAD_REQUEST, "E402")
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
