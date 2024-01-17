package com.bdi.projectbdigroup5.model;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class Domaine {
    @Id
    @Column(unique = true, nullable = false)
    private String nom;
}
