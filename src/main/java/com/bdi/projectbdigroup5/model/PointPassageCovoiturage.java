package com.bdi.projectbdigroup5.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="point_passage_covoiturages")
public class PointPassageCovoiturage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int differenceHeurePassage;

    private float prix;

    @ManyToOne
    @JoinColumn(name = "id_lieu_covoiturage", nullable = false)
    private LieuCovoiturage lieuCovoiturage;

    @ManyToOne
    @JoinColumn(name = "id_offre_covoiturage", nullable = false)
    private OffreCovoiturage offreCovoiturage;

    @OneToMany(mappedBy = "pointPassageCovoiturage", fetch = FetchType.LAZY)
    private List<Article> articles;
}
