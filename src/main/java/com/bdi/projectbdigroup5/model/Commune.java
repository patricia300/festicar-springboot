package com.bdi.projectbdigroup5.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "communes")
public class Commune {
    @Id
    private String codeInsee;

    private String codePostal;

    private String nom;

    private float latitude;

    private float longitude;

    @ManyToOne
    @JoinColumn(name = "numero_departement", nullable = false)
    private Departement departement;
}
