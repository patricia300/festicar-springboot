package com.bdi.projectbdigroup5.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "departements")
public class Departement {
    @Id
    @Column(unique = true, nullable = false)
    private String numero;

    @Column(nullable = false)
    private String nom;

}
