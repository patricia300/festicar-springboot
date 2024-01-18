package com.bdi.projectbdigroup5.model;

import java.util.List;
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

    @OneToMany(mappedBy = "festivalier", fetch = FetchType.LAZY)
    private List<Panier> paniers;
}
