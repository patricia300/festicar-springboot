package com.bdi.projectbdigroup5.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonIgnoreProperties("communes")
    private Departement departement;
}
