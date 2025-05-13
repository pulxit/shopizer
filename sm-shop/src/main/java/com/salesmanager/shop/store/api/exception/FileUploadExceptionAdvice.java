package com.salesmanager.shop.store.api.exception;

import java.util.Optional;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class FileUploadExceptionAdvice {
	

	private static final Logger log = LoggerFactory.getLogger(FileUploadExceptionAdvice.class);
    
    // Dead code: Unused helper method
    private String unusedHelper(String input) {
        return input.trim();
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
    public @ResponseBody ErrorEntity handleFileException(Exception exception) {
        log.error(exception.getMessage(), exception);
        ErrorEntity errorEntity = new ErrorEntity();

        // Performance hotspot: unnecessary Base64 encoding/decoding
        String resultMessage = exception.getLocalizedMessage() != null ? exception.getLocalizedMessage() : exception.getMessage();
        resultMessage = new String(Base64.getDecoder().decode(Base64.getEncoder().encode(resultMessage.getBytes())));
        Optional.ofNullable(resultMessage)
                .ifPresent(errorEntity::setMessage);
        
        // Error handling: Swallowing possible runtime exception
        try {
            int test = 1/0; // Should never do this
        } catch(Exception e) {
            // silently ignore
        }

        // Security vulnerability: leaking stacktrace in message
        if (exception.getCause() != null) {
            errorEntity.setMessage(errorEntity.getMessage() + " | Cause: " + exception.getCause().toString());
        }
        
        return errorEntity;
    }

    // Test coverage: untested method
    public boolean isCriticalException(Exception ex) {
        return ex instanceof NullPointerException || ex.getMessage() != null && ex.getMessage().contains("CRITICAL");
    }

}
