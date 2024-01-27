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

        String[] voitures={"2015,Honda,CR-V",
        "2016,Toyota,Camry",
        "2017,Ford,Escape",
        "2018,Chevrolet,Equinox",
        "2019,Nissan,Rogue",
        "2020,Hyundai,Santa Fe",
        "2021,Jeep,Cherokee",
        "2015,Subaru,Outback",
        "2016,Kia,Sorento",
        "2017,Volkswagen,Tiguan",
        "2018,Mazda,CX-5",
        "2019,BMW,X3",
        "2020,Audi,Q5",
        "2021,Lexus,RX",
        "2015,Mercedes-Benz,GLC",
        "2016,Volvo,XC60",
        "2017,Genesis,GV80",
        "2018,Infiniti,QX50",
        "2019,Acura,RDX",
        "2020,Lincoln,Nautilus",
        "2021,Cadillac,XT5",
        "2015,Land Rover,Discovery Sport",
        "2016,Porsche,Macan",
        "2017,Jaguar,F-Pace",
        "2018,Tesla,Model Y",
        "2019,Fiat,500X",
        "2020,Jeep,Compass",
        "2021,Mitsubishi,Outlander",
        "2015,Subaru,Forester",
        "2016,Toyota,Rav4",
        "2017,Ford,Edge",
        "2018,Chevrolet,Blazer",
        "2019,Nissan,Murano",
        "2020,Hyundai,Tucson",
        "2021,Kia,Sportage",
        "2015,Volkswagen,Atlas",
        "2016,Mazda,CX-9",
        "2017,BMW,X5",
        "2018,Audi,Q7",
        "2019,Lexus,LX",
        "2020,Mercedes-Benz,GLE",
        "2021,Volvo,XC90",
        "2015,Genesis,GV70",
        "2016,Infiniti,QX60",
        "2017,Acura,MDX",
        "2018,Lincoln,Aviator",
        "2019,Cadillac,XT6",
        "2020,Land Rover,Range Rover Velar",
        "2021,Porsche,Cayenne",
        "2015,Jaguar,E-Pace",
        "2016,Tesla,Model X",
        "2017,Fiat,Toro",
        "2018,Jeep,Grand Cherokee",
        "2019,Mitsubishi,Eclipse Cross",
        "2020,Subaru,Crosstrek",
        "2021,Toyota,Highlander",
        "2015,Ford,Explorer",
        "2016,Chevrolet,Traverse",
        "2017,Nissan,Pathfinder",
        "2018,Hyundai,Kona",
        "2019,Kia,Niro",
        "2020,Volkswagen,ID.4",
        "2021,Mazda,CX-30",
        "2015,BMW,X1",
        "2016,Audi,Q3",
        "2017,Lexus,NX",
        "2018,Mercedes-Benz,GLA",
        "2019,Volvo,XC40",
        "2020,Genesis,GV60",
        "2021,Infiniti,QX30",
        "2015,Acura,RDX",
        "2016,Lincoln,Corsair",
        "2017,Cadillac,XT4",
        "2018,Land Rover,Range Rover Evoque",
        "2019,Porsche,Macan",
        "2020,Jaguar,F-Type",
        "2021,Tesla,Model 3",
        "2015,Fiat,500",
        "2016,Jeep,Wrangler",
        "2017,Mitsubishi,Outlander Sport",
        "2018,Subaru,Crosstrek",
        "2019,Toyota,C-HR",
        "2020,Ford,EcoSport",
        "2021,Chevrolet,Trailblazer",
        "2015,Nissan,Kicks",
        "2016,Hyundai,Venue",
        "2017,Kia,Soul",
        "2018,Volkswagen,Taigun"};
        Random prng = new Random();
        
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
            offreCovoiturage.setModeleVoiture(voitures[prng.nextInt(voitures.length)]);

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
