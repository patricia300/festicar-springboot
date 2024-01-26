package com.bdi.projectbdigroup5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "id_festival", nullable = false)
    @JsonIgnore
    private Festival festival;

    @ManyToOne
    @JoinColumn(name = "email_covoitureur", nullable = false)
    private Covoitureur covoitureur;

    @OneToMany(mappedBy = "offreCovoiturage", fetch = FetchType.LAZY)
    private List<PointPassageCovoiturage> pointPassageCovoiturages;
}
