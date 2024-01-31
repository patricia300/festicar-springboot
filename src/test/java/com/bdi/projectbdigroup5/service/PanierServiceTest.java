package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.dto.ArticleRequestBodyDto;
import com.bdi.projectbdigroup5.dto.PanierRequestBodyDto;
import com.bdi.projectbdigroup5.dto.PanierResponseDto;
import com.bdi.projectbdigroup5.model.*;
import com.bdi.projectbdigroup5.populating.controller.Fakedata;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PanierServiceTest {

    private Festivalier festivalier;
    private Commune commune;
    private Departement departement;
    private Region region;
    private Festival festival;
    private List<OffreCovoiturage> offreCovoiturages;
    private List<PointPassageCovoiturage> pointPassageCovoiturages;
    Covoitureur covoitureur;
    Organisateur organisateur;
    private Fakedata fakedata;
    private UtilisateurFaker utilisateurFaker;

    @InjectMocks
    private PanierService panierService;

    @BeforeEach
    public void init()
    {
        this.festivalier.setEmail("mohamed.rey@gmail.com");
        this.festivalier.setNom("Jane");
        this.festivalier.setPrenom("Maria");
        this.festivalier.setDateNaissance(new Date());
        this.festivalier.setAdresse("38 Rue Teissere");
        this.festivalier.setVille("Grenoble");
        this.festivalier.setCodePostal("38100");

        this.covoitureur = this.utilisateurFaker.createFakeCovoitureur();
        this.organisateur = this.utilisateurFaker.createFakeOrganisateur();

        this.offreCovoiturages = (List<OffreCovoiturage>) fakedata.getOffreCovoiturage(5);

        this.region.setNom("Centre-Val de Loire");

        this.departement.setNumero("37");
        this.departement.setNom("Indre-et-Loire");
        this.departement.setRegion(this.region);

        this.commune.setCodeInsee("37003");
        this.commune.setCodePostal("37400");
        this.commune.setNom("Amboise");
        this.commune.setLatitude(47.391724F);
        this.commune.setLongitude(1.00024F);

        DomainePrincipal domainePrincipal = new DomainePrincipal();
        domainePrincipal.setNom("Musiques actuelles");

        SousDomaine sousDomaine = new SousDomaine();
        sousDomaine.setNom("Musiques traditionnelles et du monde");
        sousDomaine.setDomainePrincipal(domainePrincipal);

        this.festival.setOffreCovoiturages(this.offreCovoiturages);
        this.festival.setOrganisateur(organisateur);
        this.festival.setCommune(commune);
        this.festival.setSousDomaine(sousDomaine);
        this.festival.setId(1776L);
        this.festival.setSiteWeb("www.les-grimaldines.com");
        this.festival.setNombrePass(84);
        this.festival.setTarifPass(12.0F);
        this.festival.setNom("LES GRIMALDINES");
        this.festival.setDateDebut(new Date("2024-07-17T22:00:00.000+00:00"));
        this.festival.setDateFin(new Date("2024-08-07T22:00:00.000+00:00"));


    }

    @Test
    void getCurrentPanier() {

        Long pointPassageId = this.offreCovoiturages.get(0).getPointPassageCovoiturages().get(0).getId();
        Long pointpassageId2 = this.offreCovoiturages.get(1).getPointPassageCovoiturages().get(1).getId();
        ArticleRequestBodyDto articleRequestBodyDto = ArticleRequestBodyDto.builder()
                .idPointPassage(pointPassageId)
                .quantite(1)
                .build();

        ArticleRequestBodyDto articleRequestBodyDto2 = ArticleRequestBodyDto.builder()
                .idPointPassage(pointpassageId2)
                .quantite(1)
                .build();

        PanierRequestBodyDto requestBodyDto = PanierRequestBodyDto.builder()
                .emailFestivalier(this.festivalier.getEmail())
                .articles(List.of(articleRequestBodyDto2, articleRequestBodyDto))
                .build();

        PanierResponseDto panierResponseDto = this.panierService.savePanierFestivalier(requestBodyDto);

        assertNotNull(panierResponseDto);

    }
}