package com.bdi.projectbdigroup5.model;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class Domaine {
    @Id
    private String nom;
}
