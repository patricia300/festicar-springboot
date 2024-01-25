package com.bdi.projectbdigroup5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "point_passage_covoiturages")
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
    @JsonIgnore
    private OffreCovoiturage offreCovoiturage;
}
