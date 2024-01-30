package com.bdi.projectbdigroup5.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@AllArgsConstructor
@Getter
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Nombre de pass festival insuffisant")
public class NombrePassFestivalInsuffisantException extends RuntimeException{
    private final Long idFestival;
    private final int nbPassDisponible;
}
