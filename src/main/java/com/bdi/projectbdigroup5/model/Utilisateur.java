package com.bdi.projectbdigroup5.model;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.time.Instant;
import java.util.Date;

@Data
@MappedSuperclass
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
