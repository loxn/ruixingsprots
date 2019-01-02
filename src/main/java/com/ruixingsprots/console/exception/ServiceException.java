/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.ruixingsprots.console.exception;

/**
 * Created by v_luoxin on 2018-12-31 22:06
 */
public class ServiceException extends RuntimeException {
    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
