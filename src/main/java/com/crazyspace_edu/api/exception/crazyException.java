package com.crazyspace_edu.api.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class crazyException extends RuntimeException {
    public final Map<String, String> validation = new HashMap<>();

    public abstract int getStatusCode();

    public crazyException() {
    }

    public crazyException(String message) {
        super(message);
    }

    public crazyException(String message, Throwable cause) {
        super(message, cause);
    }

    public crazyException(Throwable cause) {
        super(cause);
    }

    public crazyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
