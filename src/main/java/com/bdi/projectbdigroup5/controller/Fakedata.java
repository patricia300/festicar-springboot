package com.bdi.projectbdigroup5.controller;


import org.springframework.web.bind.annotation.RestController;

import com.bdi.projectbdigroup5.model.Covoitureur;
import com.bdi.projectbdigroup5.model.Festival;
import com.bdi.projectbdigroup5.model.OffreCovoiturage;
import com.bdi.projectbdigroup5.service.FestivalService;
import com.bdi.projectbdigroup5.service.OffreCovoiturageService;
import com.github.javafaker.Faker;
import com.bdi.projectbdigroup5.service.CovoitureurService;

import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/fakedata")
public class Fakedata {

    @Autowired 
    private FestivalService festivalService;
    
    @Autowired
    private CovoitureurService covoitureurService;

    @Autowired
    private OffreCovoiturageService offreCovoiturageService;

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
    
}
