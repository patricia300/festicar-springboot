package com.bdi.projectbdigroup5.model;

import java.util.List;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Data
@Entity
@Table(name="paniers")
public class Panier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private Instant dateCreation;

    @UpdateTimestamp
    private Instant dateModification;

    private StatutPanier statut;

    @ManyToOne
    @JoinColumn(name = "email_festivalier", nullable = false)
    private Festivalier festivalier;

    @OneToMany(mappedBy = "panier", fetch = FetchType.LAZY)
    private List<Article> articles;
}
