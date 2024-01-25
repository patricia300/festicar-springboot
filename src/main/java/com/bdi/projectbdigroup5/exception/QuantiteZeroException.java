package com.bdi.projectbdigroup5.exception;

import lombok.AllArgsConstructor;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@AllArgsConstructor
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Aucun place disponible")
public class QuantiteZeroException extends RuntimeException{
    private Long id;
}
