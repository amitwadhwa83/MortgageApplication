package com.mortagage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Could not find mortgage rate for calculation period")
public class RateNotFoundException extends GenericException {
    public RateNotFoundException(String message) {
        super(message);
    }

    public RateNotFoundException(int period) {
        super("Period:" + period);
    }
}
