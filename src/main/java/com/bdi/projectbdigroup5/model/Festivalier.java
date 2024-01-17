package com.bdi.projectbdigroup5.model;

import lombok.Data;

@Data
public class Festivalier extends Utilisateur {
    private String adresse;

    private String complementAdresse;

    private String ville;

    private String codePostal;
}
