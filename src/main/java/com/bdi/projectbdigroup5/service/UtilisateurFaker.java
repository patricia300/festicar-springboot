package com.bdi.projectbdigroup5.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;
import java.util.Locale;

import com.bdi.projectbdigroup5.model.Covoitureur;
import com.bdi.projectbdigroup5.model.Festivalier;
import com.bdi.projectbdigroup5.model.Organisateur;


@Service
@AllArgsConstructor
public class UtilisateurFaker {

    public static final String DATE_PATTERN = "dd-MM-yyyy";
    public static final String DATE_SOURCE_MIN = "01-01-1970";
    public static final String DATE_SOURCE_MAX = "01-01-2010";
    FestivalierService festivalierService;
    OrganisateurService organisateurService;
    CovoitureurService covoitureurService;

    
    public Iterable<Festivalier> createFakeFestivaliers(int nombreFestivaliers){
        ArrayList<Festivalier> festivaliers = new ArrayList<>();
        
        int compteur = nombreFestivaliers;
        while(compteur>0){
            festivaliers.add(createFakeFestivalier());
            compteur--;
        }

        festivaliers=(ArrayList<Festivalier>)festivalierService.createFestivaliers(festivaliers);
        return festivaliers;
    }

    public Iterable<Organisateur> createFakeOrganisateurs(int nombreOrganisateur){
        ArrayList<Organisateur> organisateurs=new ArrayList<>();

        int compteur = nombreOrganisateur;
        while (compteur>=0) {
            organisateurs.add(createFakeOrganisateur());
            compteur--;
        }

        organisateurs= (ArrayList<Organisateur>)organisateurService.createOrganisateurs(organisateurs);

        return organisateurs;
    }

    public Iterable<Covoitureur> createFakeCovoitureurs(int nombreCovoitureur){
        ArrayList<Covoitureur> covoitureurs= new ArrayList<>();

        int compteur = nombreCovoitureur;
        while (compteur>=0) {
            covoitureurs.add(createFakeCovoitureur());
            compteur--;
        }

        covoitureurs=(ArrayList<Covoitureur>)covoitureurService.createCovoitureurs(covoitureurs);

        return covoitureurs;
    }

    public Covoitureur createFakeCovoitureur(){
        Covoitureur covoitureur = new Covoitureur();

        Faker faker = new Faker(new Locale("fr"));

        SimpleDateFormat formater = new SimpleDateFormat(DATE_PATTERN, Locale.FRANCE);
        covoitureur.setNom(faker.name().lastName());
        covoitureur.setPrenom(faker.name().firstName());
        covoitureur.setEmail(faker.internet().emailAddress());
        covoitureur.setUrlPhoto(faker.avatar().image());
        covoitureur.setNumeroTelephone(faker.phoneNumber().cellPhone());
        try{
            covoitureur.setDateNaissance(faker.date().between(formater.parse(
                    DATE_SOURCE_MIN), formater.parse(DATE_SOURCE_MAX)));
        }catch(ParseException e){
            System.out.println(e.getMessage());
        }

        return covoitureur;
    }

    public Organisateur createFakeOrganisateur(){
        Organisateur organisateur = new Organisateur();
        Faker faker = new Faker(new Locale("fr"));

        SimpleDateFormat formater = new SimpleDateFormat(DATE_PATTERN, Locale.FRANCE);
        organisateur.setNom(faker.name().lastName());
        organisateur.setPrenom(faker.name().firstName());
        organisateur.setEmail(faker.internet().emailAddress());
        organisateur.setUrlPhoto(faker.avatar().image());
        try{
            organisateur.setDateNaissance(faker.date().between(formater.parse(DATE_SOURCE_MIN), formater.parse(DATE_SOURCE_MAX)));
        }catch(ParseException e){
            System.out.println(e.getMessage());
        }
        return organisateur;
    }

    public Festivalier createFakeFestivalier(){
        Faker faker=new Faker(new Locale("fr"));
        Address address = faker.address();

        Festivalier festivalier = new Festivalier();
        SimpleDateFormat formater = new SimpleDateFormat(DATE_PATTERN, Locale.FRANCE);
        festivalier.setNom(faker.name().lastName());
        festivalier.setPrenom(faker.name().firstName());
        festivalier.setEmail(faker.internet().emailAddress());
        festivalier.setUrlPhoto(faker.avatar().image());
        festivalier.setAdresse(address.fullAddress());
        festivalier.setCodePostal(address.zipCode());
        festivalier.setVille(address.city());
        try{
            festivalier.setDateNaissance(faker.date().between(formater.parse(DATE_SOURCE_MIN), formater.parse(DATE_SOURCE_MAX)));
        }catch(ParseException e){
            System.out.println(e.getMessage());
        }
        return festivalier;
    }

}
