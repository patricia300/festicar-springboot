package com.bdi.projectbdigroup5.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_panier")
    @JsonIgnore
    @ToString.Exclude
    private Panier panier;

    @ManyToOne
    @JoinColumn(name = "id_point_passage_covoiturage")
    private PointPassageCovoiturage pointPassageCovoiturage;

    private int quantite;

}
