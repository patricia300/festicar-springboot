package com.bdi.projectbdigroup5.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "lieu_covoiturages")
public class LieuCovoiturage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String adresse;

    private float latitude;

    private float longitude;

    @Enumerated(EnumType.STRING)
    private TypeLieuCovoiturage type;

    @ManyToOne
    @JoinColumn(name = "code_insee_commune", nullable = false)
    private Commune commune;
}
