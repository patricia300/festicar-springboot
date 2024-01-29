package com.bdi.projectbdigroup5.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_panier")
    @JsonIgnoreProperties("articles")
    private Panier panier;

    @ManyToOne
    @JoinColumn(name = "id_point_passage_covoiturage")
    private PointPassageCovoiturage pointPassageCovoiturage;

    private int quantite;

}
