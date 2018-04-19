package com.gdkyit.core.exceptions;

public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String error;

    public ServiceException(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }



}
