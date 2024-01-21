package com.bdi.projectbdigroup5.model;

import jakarta.persistence.*;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class Domaine {
    @Id
    private String nom;
}
