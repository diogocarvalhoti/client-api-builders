package io.platformbuilders.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class ResourceNoContentException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceNoContentException() {
        super();
    }

}
