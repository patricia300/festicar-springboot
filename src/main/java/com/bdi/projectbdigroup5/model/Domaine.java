package com.bdi.projectbdigroup5.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter @Setter
public abstract class Domaine {
    @Id
    private String nom;
}
