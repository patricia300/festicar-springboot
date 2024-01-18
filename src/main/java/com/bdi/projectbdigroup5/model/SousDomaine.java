package com.bdi.projectbdigroup5.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class SousDomaine extends Domaine {

    @OneToMany(mappedBy = "sousDomaine", fetch = FetchType.LAZY)
    private List<Festival> festivals;
}
