package com.bdi.projectbdigroup5.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class SousDomaine {
    @Id
    private String nom;

    @ManyToOne()
    @JoinColumn(name="domaine_principal", nullable = false)
    @JsonIgnoreProperties("sousDomaines")
    private DomainePrincipal domainePrincipal;
}