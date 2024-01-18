package com.bdi.projectbdigroup5.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "departements")
public class Departement {
    @Id
    @Column(unique = true, nullable = false)
    private String numero;

    @Column(nullable = false)
    private String nom;

    @ManyToOne
    @JoinColumn(name = "nom_region", nullable = false)
    private Region region;

    @OneToMany(mappedBy = "departement", nullable = false, fetch = FetchType.LAZY)
    private List<Commune> communes;

}
