package com.bdi.projectbdigroup5.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Ressource not found")
@AllArgsConstructor
public class FestivalierNotFoundException extends RuntimeException {
    private final String message;
}
