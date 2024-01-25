package com.bdi.projectbdigroup5.populating.controller;


import org.springframework.web.bind.annotation.RestController;

import com.bdi.projectbdigroup5.model.Article;
import com.bdi.projectbdigroup5.model.Covoitureur;
import com.bdi.projectbdigroup5.model.Festival;
import com.bdi.projectbdigroup5.model.OffreCovoiturage;
import com.bdi.projectbdigroup5.model.Panier;
import com.bdi.projectbdigroup5.model.PointPassageCovoiturage;
import com.bdi.projectbdigroup5.model.StatutPanier;
import com.bdi.projectbdigroup5.repository.LieuCovoiturageRepository;
import com.bdi.projectbdigroup5.service.FestivalService;
import com.bdi.projectbdigroup5.service.FestivalierService;
import com.bdi.projectbdigroup5.service.LieuCovoiturageService;
import com.bdi.projectbdigroup5.service.OffreCovoiturageService;
import com.bdi.projectbdigroup5.service.PanierService;
import com.bdi.projectbdigroup5.service.PointPassageCovoiturageService;
import com.github.javafaker.Faker;
import com.bdi.projectbdigroup5.service.ArticleService;
import com.bdi.projectbdigroup5.service.CovoitureurService;

import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/fakedata")
public class Fakedata {

    @Autowired 
    private FestivalService festivalService;

    @Autowired
    private FestivalierService festivalierService;
    
    @Autowired
    private CovoitureurService covoitureurService;

    @Autowired
    private OffreCovoiturageService offreCovoiturageService;

    @Autowired
    private LieuCovoiturageService lieuCovoiturageService;

    @Autowired
    private PointPassageCovoiturageService pointPassageCovoiturageService;

    @Autowired
    private PanierService panierservice;

    @Autowired
    private ArticleService articleService;

    @GetMapping("/offreCovoiturage")
    public Iterable<OffreCovoiturage> getOffreCovoiturage(@RequestParam int nombreOffreCovoiturage) {

        
        List<OffreCovoiturage> offreCovoiturages = new ArrayList<OffreCovoiturage>();
        
        for( int i=1; i<=nombreOffreCovoiturage; i++ ){
            Festival festival  = festivalService.festivalAleatoire();
            Covoitureur covoitureur = covoitureurService.covoitureurAleatoir();
            LocalDate festivalLocalDate = festival.getDateDebut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); 
            Date offreCovoiturageDate = Date.from(festivalLocalDate.minusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
            Calendar calendar =Calendar.getInstance();
            calendar.setTime(offreCovoiturageDate);
            calendar.add(calendar.HOUR_OF_DAY,(int)Math.floor(Math.random() * 13 + 8));
            Date offreCovoiturageHeureDepart= calendar.getTime();
            OffreCovoiturage offreCovoiturage = new OffreCovoiturage();
            offreCovoiturage.setCovoitureur(covoitureur);
            offreCovoiturage.setFestival(festival);
            offreCovoiturage.setDateOffre(offreCovoiturageDate);
            offreCovoiturage.setHeureDepart(offreCovoiturageHeureDepart);
            offreCovoiturage.setNombrePlaces((int)Math.floor(Math.random() * 3 + 1));
            offreCovoiturage.setModeleVoiture( "2021,Honda,CR-V Hybrid");

            offreCovoiturages.add(offreCovoiturageService.createOffreCovoiturage(offreCovoiturage));
        }

        return offreCovoiturages;
    }

    @GetMapping("/pointPassage")
    public Iterable<PointPassageCovoiturage> getpointPassageCovoiturage() {
        
        List<PointPassageCovoiturage> pointPassageCovoiturages=new ArrayList<PointPassageCovoiturage>();

        Iterable<OffreCovoiturage> offreCovoiturages = offreCovoiturageService.getAllOffreCovoiturage();
        Iterator<OffreCovoiturage> iterator = offreCovoiturages.iterator();

        int nombreStopMax= 5;
        int nombreStopMin=2;

        float prixPassageMax = 100;
        float prixPassageMin = 10;
        
        OffreCovoiturage offreCovoiturage;
        PointPassageCovoiturage pointPassageCovoiturage;

        int  nombreStop;
        int diffHeurePassage;
        float prixPassage;

        while (iterator.hasNext()) {
            offreCovoiturage = iterator.next();
            nombreStop =(int) Math.floor(Math.random()*(nombreStopMax-nombreStopMin+1)+nombreStopMin);
            diffHeurePassage =(int) Math.floor(Math.random()*5);

            prixPassage = (float) Math.floor(Math.random()*(prixPassageMax-prixPassageMin+1)+prixPassageMin);

            Calendar calendar =Calendar.getInstance();
            calendar.setTime(offreCovoiturage.getHeureDepart());
            
            for(int i=0; i<=nombreStop;i++){

                calendar.add(Calendar.HOUR_OF_DAY, diffHeurePassage);
                pointPassageCovoiturage = new PointPassageCovoiturage();
                pointPassageCovoiturage.setLieuCovoiturage(lieuCovoiturageService.lieuCovoiturageAleatoire());
                pointPassageCovoiturage.setDifferenceHeurePassage(diffHeurePassage);
                pointPassageCovoiturage.setOffreCovoiturage(offreCovoiturage);
                pointPassageCovoiturage.setPrix(prixPassage);
    
                diffHeurePassage =diffHeurePassage+(int) Math.floor(Math.random()*5);
                prixPassage=(float) Math.floor(Math.random()*(prixPassage-prixPassageMin+1)+prixPassageMin);

                pointPassageCovoiturages.add(pointPassageCovoiturage);
            }

            calendar.add(Calendar.HOUR_OF_DAY, diffHeurePassage);

            offreCovoiturage.setHeureArrive(calendar.getTime());

            offreCovoiturageService.updateOffreCovoiturage(offreCovoiturage);
        }
        
        return pointPassageCovoiturageService.createPointPassageCovoiturages(pointPassageCovoiturages);
    }

    @GetMapping("/panier")
    public Iterable<Panier> getPanier(@RequestParam int nombrePanier) {
        List<Panier> paniers = new ArrayList<Panier>();
        List<Article> articlesPaniers = new ArrayList<Article>();
        StatutPanier statutPaniers [] = StatutPanier.values();
        int nombreArticles;
        int nombreArticlesMax=2;
        int nombreArticleMin=1;
        int i =0;
        int nombrePlacesRestantes;
        Panier panier;
        Article article;
        PointPassageCovoiturage pointPassageCovoiturage;
        Random prng = new Random();

        while (i<=nombrePanier) {
            panier = new Panier();
            nombreArticles=(int) Math.floor(Math.random() *  (nombreArticlesMax-nombreArticleMin+1) +nombreArticleMin);
            panier.setFestivalier(festivalierService.festivalierAleatoire());
            panier.setStatut(statutPaniers[prng.nextInt(statutPaniers.length)]);

            for(int j=1;j<=nombreArticles; j++){
                article = new Article();
                article.setPanier(panier);
                article.setQuantite((int) Math.floor(Math.random() *  (nombreArticlesMax-nombreArticleMin+1) +nombreArticleMin));

                pointPassageCovoiturage = pointPassageCovoiturageService.pointPassageCovoiturageAleatoire();

                while (pointPassageCovoiturage.getOffreCovoiturage().getNombrePlaces()< article.getQuantite()) {
                    pointPassageCovoiturage = pointPassageCovoiturageService.pointPassageCovoiturageAleatoire();
                }

                nombrePlacesRestantes = pointPassageCovoiturage.getOffreCovoiturage().getNombrePlaces();
                pointPassageCovoiturage.getOffreCovoiturage().setNombrePlaces(nombrePlacesRestantes-article.getQuantite());

                article.setPointPassageCovoiturage(pointPassageCovoiturage);
                articlesPaniers.add(article);
            }

            panier.setArticles(articlesPaniers);
            paniers.add(panier);

           
            i++;
        }
        
        panierservice.createPaniers(paniers);
        articleService.createArticles(articlesPaniers);

        return  panierservice.findAll();

    }
    
}
