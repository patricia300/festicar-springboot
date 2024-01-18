package com.bdi.projectbdigroup5.model;

import jakarta.persistence.*;
import java.util.List;

import lombok.Data;

@Data
public class Covoitureur extends Utilisateur {

    private String numeroTelephone;

    @OneToMany(mappedBy = "covoitureur", fetch = FetchType.LAZY)
    private List<OffreCovoiturage> offreCovoiturages;
}
