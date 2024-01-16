package com.bdi.projectbdigroup5.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "festivaliers")
public class Festivalier extends Utilisateur {
    private String adresse;

    private String complementAdresse;

    private String ville;

    private String codePostal;
}
