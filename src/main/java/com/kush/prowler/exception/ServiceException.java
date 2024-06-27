package com.kush.prowler.exception;

import com.kush.prowler.model.AppStatusCode;
import lombok.Getter;

/**
 * @author kush
 */
@Getter
public class ServiceException extends RuntimeException {
    private final AppStatusCode statusCode;
    private final String[] args;

    public ServiceException(AppStatusCode statusCode, String[] args){
        this.statusCode = statusCode;
        this.args = args;
    }

    public static ServiceException of(AppStatusCode appStatusCode) {
        return new ServiceException(appStatusCode, new String[]{});
    }

    public static ServiceException of(AppStatusCode appStatusCode, String message) {
        return new ServiceException(appStatusCode, new String[]{message});
    }
    public static ServiceException of(AppStatusCode appStatusCode, String... message) {
        return new ServiceException(appStatusCode, message);
    }

}
