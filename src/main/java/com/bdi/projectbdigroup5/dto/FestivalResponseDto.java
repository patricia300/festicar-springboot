package com.bdi.projectbdigroup5.dto;

import lombok.Builder;
import lombok.Getter;

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

    private List<OffreCovoiturageFestivalDto> offreCovoiturages;
}
