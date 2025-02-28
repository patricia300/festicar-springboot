package com.bdi.projectbdigroup5.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Ressource not found")
@AllArgsConstructor
@Getter
public class NotFoundException extends RuntimeException {
    private final String message;


}
