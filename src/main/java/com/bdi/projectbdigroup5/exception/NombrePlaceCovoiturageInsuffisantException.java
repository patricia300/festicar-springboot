package com.bdi.projectbdigroup5.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@AllArgsConstructor
@Getter
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Nombre de place offre covoiturage insuffisant")
public class NombrePlaceCovoiturageInsuffisantException extends RuntimeException {
    private final Long idOffreCovoiturage;
    private final int nbPlaceDisponible;
}
