package com.bdi.projectbdigroup5.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "articles", indexes = {
    @Index(name = "articlesPanierIndex", columnList = "id_panier"),
    @Index(name = "articlesPointPassageCovoiturageIndex", columnList = "id_point_passage_covoiturage")
})
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_panier")
    @JsonIgnore
    private Panier panier;

    @ManyToOne
    @JoinColumn(name = "id_point_passage_covoiturage")
    private PointPassageCovoiturage pointPassageCovoiturage;

    private int quantite;

}
