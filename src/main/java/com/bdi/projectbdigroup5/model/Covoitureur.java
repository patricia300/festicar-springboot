package com.bdi.projectbdigroup5.model;

import jakarta.persistence.*;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Covoitureur extends Utilisateur {
    @Column(nullable = false)
    private String numeroTelephone;

    @OneToMany(mappedBy = "covoitureur", fetch = FetchType.LAZY)
    private List<OffreCovoiturage> offreCovoiturages;
}
