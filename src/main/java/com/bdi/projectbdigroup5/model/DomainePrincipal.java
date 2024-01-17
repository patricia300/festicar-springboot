package com.bdi.projectbdigroup5.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class DomainePrincipal extends Domaine {
    @OneToMany(mappedBy = "domainePrincipal", fetch = FetchType.LAZY)
    private List<Festival> festivals;
}
