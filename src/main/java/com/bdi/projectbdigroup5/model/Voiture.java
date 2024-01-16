package com.bdi.projectbdigroup5.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "voitures")
public class Voiture {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String immatriculation;

    private String marque;

    private String modele;
    
    private String couleur;
}
