package com.bdi.projectbdigroup5.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.time.Instant;
import java.util.Date;

@Data
@Entity
@Table(name="utilisateurs")
public abstract class Utilisateur {
    @Id
    private String email;

    private String nom;

    private String prenom;

    private Date dateNaissance;

    private String urlPhoto;

    @CreationTimestamp
    private Instant dateCreation;

}
