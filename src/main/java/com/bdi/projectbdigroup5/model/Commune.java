package com.bdi.projectbdigroup5.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "communes")
public class Commune {
    @Id
    @Column(unique = true, nullable = false)
    private String codeInsee;

    private String codePostal;

    private String nom;

    private float latitude;

    private float longitude;

    @ManyToOne
    @JoinColumn(name = "numero_departement", nullable = false)
    private Departement departement;

    @OneToMany(mappedBy = "commune", nullable = false, fetch = FetchType.LAZY)
    private List<Festival> festivals;

    @OneToMany(mappedBy = "commune", nullable = false, fetch = FetchType.LAZY)
    private List<LieuCovoiturage> lieuCovoiturages;
}
