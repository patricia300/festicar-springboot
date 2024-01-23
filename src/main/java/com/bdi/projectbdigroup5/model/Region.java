package com.bdi.projectbdigroup5.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "regions")
public class Region {
    @Id
    @Column(unique = true, nullable = false)
    private String nom;
}
