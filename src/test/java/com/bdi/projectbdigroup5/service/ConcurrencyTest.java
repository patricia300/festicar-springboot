package com.bdi.projectbdigroup5.service;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConcurrencyTest {
    
    @Autowired
    private PanierService panierService;
    @Autowired
    private OffreCovoiturageService offreCovoiturageService;
    @Autowired
    private PointPassageCovoiturageService  pointPassageCovoiturageServiceService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private OrganisateurService organisateurService;
    @Autowired
    private FestivalierService festivalierService;
    
}
