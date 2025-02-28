package com.bdi.projectbdigroup5.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Data
@Entity
@Table(name="paniers", indexes = {
    @Index(name = "paniersFestivalierIndex", columnList = "email_festivalier" )   
})
public class Panier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private Instant dateCreation;

    @UpdateTimestamp
    private Instant dateModification;

    @Enumerated(EnumType.STRING)
    private StatutPanier statut = StatutPanier.EN_COURS;

    @ManyToOne
    @JoinColumn(name = "email_festivalier", nullable = false)
    @JsonIgnore
    private Festivalier festivalier;

    @OneToMany(mappedBy = "panier", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Article> articles;
}
