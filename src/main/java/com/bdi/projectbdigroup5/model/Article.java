package com.bdi.projectbdigroup5.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "articles")
public class Article {
    /* Todo: Juste pour enlever l'erreur pour l'identifiant, à enlever plus tard */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantite;

}
