package com.bdi.projectbdigroup5.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name="offre_covoiturages")
public class OffreCovoiturage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateOffre;

    private int nombrePlaces;

    private String modeleVoiture;

    private Date heureDepart;

    private Date heureArrive;
}
