package org.epik.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public final class EpikNotFoundException extends RuntimeException {
    public EpikNotFoundException(final String message) {
        super(message);
    }
}
