package com.bdi.projectbdigroup5.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@AllArgsConstructor
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Aucun place ou pass disponible")
public class QuantiteZeroException extends RuntimeException{
    private Long id;
    private String classType;
}
