package com.bdi.projectbdigroup5.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "festivals")
public class Festival {
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    /* Todo: à verifier si type "Date" marche avec JPA*/
    private Date dateDebut;

    private String siteWeb;

    private Float tarifPass;

    private Date dateFin;

}
