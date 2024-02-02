package com.bdi.projectbdigroup5.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class DomainePrincipal {
    @Id
    private String nom;

    @OneToMany(mappedBy = "domainePrincipal", fetch = FetchType.EAGER)
    List<SousDomaine> sousDomaines;
}
