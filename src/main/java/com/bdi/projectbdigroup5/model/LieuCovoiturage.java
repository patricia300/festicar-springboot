package com.bdi.projectbdigroup5.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

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
    @JsonIgnoreProperties("lieuCovoiturages")
    private Commune commune;

    @OneToMany(mappedBy = "lieuCovoiturage", fetch = FetchType.LAZY)
    private List<PointPassageCovoiturage> pointPassageCovoiturages;
}
