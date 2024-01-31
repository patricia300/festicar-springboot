package com.bdi.projectbdigroup5.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Festivalier extends Utilisateur {
    private String adresse;

    private String complementAdresse;

    private String ville;

    private String codePostal;
}
