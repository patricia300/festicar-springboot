package com.bdi.projectbdigroup5.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "communes")
public class Commune {
    @Id
    @Column(unique = true, nullable = false)
    private String codeInsee;

    private String codePostal;

    private String nom;

    private float latitude;

    private float longitude;
}
