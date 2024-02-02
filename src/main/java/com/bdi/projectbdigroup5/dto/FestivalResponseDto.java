package com.bdi.projectbdigroup5.dto;

import com.bdi.projectbdigroup5.model.Article;
import com.bdi.projectbdigroup5.model.Festival;
import com.bdi.projectbdigroup5.model.OffreCovoiturage;
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

    private String codePostalCommune;

    private String nomCommune;

    private String nomSousDomaine;

    private String nomDomainePrincipal;

    private String nomOrganisateur;

    private List<OffreCovoiturageFestivalDto> offreCovoiturages;

    public static FestivalResponseDto createFestivalResponseDtoFromArticle(Article article)
    {
        Festival f = article.getPointPassageCovoiturage().getOffreCovoiturage().getFestival();
        OffreCovoiturage o = article.getPointPassageCovoiturage().getOffreCovoiturage();
        OffreCovoiturageFestivalDto offreCovoiturageFestivalDto = OffreCovoiturageFestivalDto.builder()
                .id(o.getId())
                .dateOffre(o.getDateOffre())
                .heureArrive(o.getHeureArrive())
                .heureDepart(o.getHeureDepart())
                .pointPassageCovoiturages(List.of(article.getPointPassageCovoiturage()))
                .covoitureur(o.getCovoitureur())
                .modeleVoiture(o.getModeleVoiture())
                .nombrePlaces(o.getNombrePlaces())
                .build();

        return FestivalResponseDto.builder()
                .id(f.getId())
                .nom(f.getNom())
                .nombrePass(f.getNombrePass())
                .siteWeb(f.getSiteWeb())
                .codePostalCommune(f.getCommune().getCodePostal())
                .nomCommune(f.getCommune().getNom())
                .offreCovoiturages(List.of(offreCovoiturageFestivalDto))
                .tarifPass(f.getTarifPass())
                .nomOrganisateur(f.getOrganisateur().getNom() + " " + f.getOrganisateur().getPrenom())
                .nomSousDomaine(f.getSousDomaine().getNom())
                .nomDomainePrincipal(f.getSousDomaine().getDomainePrincipal().getNom())
                .dateDebut(f.getDateDebut())
                .dateFin(f.getDateFin())
                .build();
    }

    public static FestivalResponseDto createFestivalResponseDtoFromFestival(Festival f, List<OffreCovoiturageFestivalDto> offreCovoiturageFestivalDtos) {

        return FestivalResponseDto.builder()
                .id(f.getId())
                .nom(f.getNom())
                .nombrePass(f.getNombrePass())
                .siteWeb(f.getSiteWeb())
                .codePostalCommune(f.getCommune().getCodePostal())
                .nomCommune(f.getCommune().getNom())
                .offreCovoiturages(offreCovoiturageFestivalDtos)
                .tarifPass(f.getTarifPass())
                .nomOrganisateur(f.getOrganisateur().getNom() + " " + f.getOrganisateur().getPrenom())
                .nomSousDomaine(f.getSousDomaine().getNom())
                .nomDomainePrincipal(f.getSousDomaine().getDomainePrincipal().getNom())
                .dateDebut(f.getDateDebut())
                .dateFin(f.getDateFin())
                .build();
    }

    public static List<OffreCovoiturageFestivalDto> createOffreCovoiturageFestivalDtos(List<OffreCovoiturage> offreCovoiturages){
        return offreCovoiturages.stream().map(o -> OffreCovoiturageFestivalDto.builder()
                        .id(o.getId())
                        .dateOffre(o.getDateOffre())
                        .heureArrive(o.getHeureArrive())
                        .heureDepart(o.getHeureDepart())
                        .pointPassageCovoiturages(o.getPointPassageCovoiturages())
                        .covoitureur(o.getCovoitureur())
                        .modeleVoiture(o.getModeleVoiture())
                        .nombrePlaces(o.getNombrePlaces())
                        .build())
                .toList();
    }
}
