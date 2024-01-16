package com.bdi.projectbdigroup5.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "lieu_covoiturages")
public class LieuCovoiturage {
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String adresse;

    private String latitude;

    private String longitude;

    /* Todo: à revoir */
    private TypeLieuCovoiturage type;
}
