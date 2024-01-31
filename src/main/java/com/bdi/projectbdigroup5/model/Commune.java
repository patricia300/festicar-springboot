package com.bdi.projectbdigroup5.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
