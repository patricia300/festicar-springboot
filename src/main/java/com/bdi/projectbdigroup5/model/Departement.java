package com.bdi.projectbdigroup5.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "departements")
public class Departement {
    @Id
    private String numero;

    @Column(nullable = false)
    private String nom;

    @ManyToOne
    @JoinColumn(name = "nom_region", nullable = false)
    @JsonIgnoreProperties("departements")
    private Region region;

    @OneToMany(mappedBy = "departement", fetch = FetchType.LAZY)
    private List<Commune> communes;

}
