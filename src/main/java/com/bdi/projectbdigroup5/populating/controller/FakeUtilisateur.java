package com.bdi.projectbdigroup5.populating.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bdi.projectbdigroup5.model.Covoitureur;
import com.bdi.projectbdigroup5.model.Festivalier;
import com.bdi.projectbdigroup5.model.Organisateur;
import com.bdi.projectbdigroup5.service.UtilisateurFaker;

import java.util.*;

@RestController
@RequestMapping("/fakeUtilisateur")
public class FakeUtilisateur {

    @Autowired
    public UtilisateurFaker utilisateurFaker;
    
    @GetMapping("/Festivalier")
    public List<Festivalier> getfestivaliersList(@RequestParam int nombreFestivaliers) {
        List<Festivalier> festivaliers = new ArrayList<Festivalier>();
        Iterable<Festivalier> iterableFestivalier = utilisateurFaker.createFakeFestivaliers(nombreFestivaliers); 
        for(Festivalier festivalier : iterableFestivalier){
            festivaliers.add(festivalier);
        }
        return festivaliers;
    }

    @GetMapping("/Covoitureur")
    public List<Covoitureur> getCovoitureurList(@RequestParam int nombreCovoitureurs) {
        List<Covoitureur> covoitureurs = new ArrayList<Covoitureur>();
        Iterable<Covoitureur> iterableCovoitureur = utilisateurFaker.createFakeCovoitureurs(nombreCovoitureurs); 
        for(Covoitureur covoitureur : iterableCovoitureur){
            covoitureurs.add(covoitureur);
        }
        return covoitureurs;
    }

    @GetMapping("/Organisateur")
    public List<Organisateur> getMethodName(@RequestParam int nombreOrganisateurs) {
        List<Organisateur> organisateurs = new ArrayList<Organisateur>();
        Iterable<Organisateur> iterableOrganisateur = utilisateurFaker.createFakeOrganisateurs(nombreOrganisateurs); 
        for(Organisateur organisateur : iterableOrganisateur){
            organisateurs.add(organisateur);
        }
        return organisateurs;
    }
    
    
}
