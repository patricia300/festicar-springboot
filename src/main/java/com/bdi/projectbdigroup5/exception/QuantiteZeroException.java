package com.bdi.projectbdigroup5.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@AllArgsConstructor
@Getter
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Aucun place ou pass disponible")
public class QuantiteZeroException extends RuntimeException{
    private final Long id;
    private final String classType;
}
