package com.bdi.projectbdigroup5.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.bdi.projectbdigroup5.model.Commune;
import com.bdi.projectbdigroup5.model.Departement;
import com.bdi.projectbdigroup5.model.DomainePrincipal;
import com.bdi.projectbdigroup5.model.Festival;
import com.bdi.projectbdigroup5.model.Festivalier;
import com.bdi.projectbdigroup5.model.Organisateur;
import com.bdi.projectbdigroup5.model.Region;
import com.bdi.projectbdigroup5.model.SousDomaine;

public class FakeDataUtility {

    @Autowired
    private UtilisateurFaker  utilisateurFaker;

    public Festivalier getFestivalier(){
        return utilisateurFaker.createFakeFestivalier();
    }

    public Organisateur getOrganisateur(){
        return utilisateurFaker.createFakeOrganisateur();
    }

    public Festival getFestival(){
        Region region = new Region();
        region.setNom("Occitanie");
        
        Departement departement=new Departement();
        departement.setRegion(region);
        departement.setNumero("31");
        departement.setNom("Haute-Garonne");

        Commune commune = new Commune();
        commune.setDepartement(departement);
        commune.setCodeInsee("31555");
        commune.setCodePostal("31300");
        commune.setLatitude(Float.parseFloat("43.5963814303"));
        commune.setLongitude(Float.parseFloat("1.43167293364"));
        commune.setNom("TOULOUSE");

        DomainePrincipal domainePrincipal =new DomainePrincipal();
        domainePrincipal.setNom("Musiques actuelles");
        
        SousDomaine sousDomaine = new SousDomaine();
        sousDomaine.setDomainePrincipal(domainePrincipal);
        sousDomaine.setNom("Musiques traditionnelles et du monde");


        Festival festival = new Festival();
        festival.setCommune(commune);
        festival.setSousDomaine(sousDomaine);
        festival.setOrganisateur(getOrganisateur());
        festival.setDateDebut(new Date("13/06/2024"));
        festival.setDateFin(new Date("16/06/2024"));

    }
    
}
