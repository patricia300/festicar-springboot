package com.bdi.projectbdigroup5.model;

import jakarta.persistence.*;
import lombok.Data;
import oracle.sql.DATE;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.beans.JavaBean;
import java.time.Instant;

@Data
@Entity
@Table(name="paniers")
public class Panier {
    @Id
    @Column(unique = true)
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
}
