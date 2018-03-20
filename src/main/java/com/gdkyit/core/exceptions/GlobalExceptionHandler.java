package com.gdkyit.core.exceptions;

import com.gdkyit.core.domain.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = { Exception.class,RuntimeException.class })
    public Result handleGenericException(Exception ex) {
        if (log.isDebugEnabled()) {
            log.debug("handling exception..." + ex.getMessage());
        }
        Result result =  Result.genFailResult(ex.getMessage());
        return result;
    }
    @ExceptionHandler(value = { ServiceException.class })
    public Result handleServiceException(ServiceException ex) {
        if (log.isDebugEnabled()) {
            log.debug("handling exception..." + ex.getError());
        }
        Result result =  Result.genServiceFailResult(ex.getError());
        return result;
    }
}