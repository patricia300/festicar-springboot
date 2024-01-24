package com.bdi.projectbdigroup5.dto;

import com.bdi.projectbdigroup5.model.Commune;
import com.bdi.projectbdigroup5.model.OffreCovoiturage;
import com.bdi.projectbdigroup5.model.Organisateur;
import com.bdi.projectbdigroup5.model.SousDomaine;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Builder
public class FestivalResponseDto {
    private final Long id;

    private String nom;

    private Date dateDebut;

    private String siteWeb;

    private Float tarifPass;

    private Date dateFin;

    private int nombrePass;

    private String nomCommune;

    private String nomSousDomaine;

    private String nomDomainePrincipal;

    private String nomOrganisateur;

    private List<OffreCovoiturage> offreCovoiturages;
}
