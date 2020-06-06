package seung.spring.kimbap.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SKimbapException extends Exception {

    private static final long serialVersionUID = 5189823797488674551L;
    
    public SKimbapException(Throwable e) {
        super(e);
    }
    
    public SKimbapException(String message) {
        super(message);
    }
    
    public SKimbapException(String message, Throwable e) {
        super(message, e);
    }
    
}
