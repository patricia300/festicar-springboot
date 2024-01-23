package com.bdi.projectbdigroup5.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "festivals")
public class Festival {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private Date dateDebut;

    private String siteWeb;

    private Float tarifPass;

    private Date dateFin;

    @ManyToOne
    @JoinColumn(name = "code_insee_commune", nullable = false)
    private Commune commune;

    @ManyToOne
    @JoinColumn(name = "nom_domaine_principal", nullable = false)
    private DomainePrincipal domainePrincipal;

    @ManyToOne
    @JoinColumn(name = "nom_sous_domaine")
    private SousDomaine sousDomaine;

    @ManyToOne
    @JoinColumn(name = "email_organisateur")
    private Organisateur organisateur;

    @OneToMany(mappedBy = "festival", fetch = FetchType.LAZY)
    private List<OffreCovoiturage> offreCovoiturages;

}
