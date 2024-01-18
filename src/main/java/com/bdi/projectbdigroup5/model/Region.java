package com.bdi.projectbdigroup5.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "regions")
public class Region {
    @Id
    @Column(unique = true, nullable = false)
    private String nom;

    @OneToMany(mappedBy = "region", nullable = false, fetch = FetchType.LAZY)
    private List<Departement> departements;
}
