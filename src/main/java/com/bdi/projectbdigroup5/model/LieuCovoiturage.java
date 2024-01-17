package com.bdi.projectbdigroup5.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

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

    private TypeLieuCovoiturage type;

    @ManyToOne
    @JoinColumn(name = "code_insee_commune", nullable = false)
    private Commune commune;

    @OneToMany(mappedBy = "lieuCovoiturage", fetch = FetchType.LAZY)
    private List<PointPassageCovoiturage> pointPassageCovoiturages;
}
