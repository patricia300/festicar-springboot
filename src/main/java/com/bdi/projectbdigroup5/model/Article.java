package com.bdi.projectbdigroup5.model;


import jakarta.persistence.*;

public class Article {

    @ManyToOne
    @JoinColumn(name = "id_paniers")
    private Panier panier;

    @ManyToOne
    @JoinColumn(name = "id_point_passage_covoiturages")
    private PointPassageCovoiturage pointPassageCovoiturage;

    private int quantite;

}
