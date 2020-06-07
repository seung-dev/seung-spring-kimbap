package seung.spring.kimbap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;
import seung.spring.boot.conf.web.util.SRequest;
import seung.spring.boot.conf.web.util.SResponse;
import seung.spring.kimbap.util.SKimbapError;

@Slf4j
@ControllerAdvice
public class SKimbapAdvice {

    @ExceptionHandler({
        Exception.class
    })
    public ResponseEntity<SResponse> constraintViolationException(Exception exception, SRequest sRequest) {
        log.error("((ERROR))", exception);
        SKimbapError sError = SKimbapError.matchError(exception.getClass());
        return ResponseEntity
                .status(sError.httpStatus())
                .body(SResponse
                        .builder()
                        .data(sRequest.getData())
                        .error_code(sError.errorCode())
                        .error_message(exception.getMessage())
                        .build()
                        )
                ;
    }
    
}
