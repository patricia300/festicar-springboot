package com.bdi.projectbdigroup5.model;

import jakarta.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Festivalier extends Utilisateur {
    private String adresse;

    private String complementAdresse;

    private String ville;

    private String codePostal;
}
