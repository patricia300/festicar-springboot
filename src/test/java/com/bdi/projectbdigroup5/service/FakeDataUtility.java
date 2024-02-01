package com.bdi.projectbdigroup5.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.bdi.projectbdigroup5.model.Commune;
import com.bdi.projectbdigroup5.model.Covoitureur;
import com.bdi.projectbdigroup5.model.Departement;
import com.bdi.projectbdigroup5.model.DomainePrincipal;
import com.bdi.projectbdigroup5.model.Festival;
import com.bdi.projectbdigroup5.model.Festivalier;
import com.bdi.projectbdigroup5.model.LieuCovoiturage;
import com.bdi.projectbdigroup5.model.OffreCovoiturage;
import com.bdi.projectbdigroup5.model.Organisateur;
import com.bdi.projectbdigroup5.model.Region;
import com.bdi.projectbdigroup5.model.SousDomaine;
import com.bdi.projectbdigroup5.model.TypeLieuCovoiturage;

public class FakeDataUtility {

    @Autowired
    private UtilisateurFaker  utilisateurFaker;

    public Festivalier getFestivalier(){
        return utilisateurFaker.createFakeFestivalier();
    }

    public Organisateur getOrganisateur(){
        return utilisateurFaker.createFakeOrganisateur();
    }

    public static Festival getFestival(){
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
        festival.setDateDebut(new Date("13/06/2024"));
        festival.setDateFin(new Date("16/06/2024"));
        festival.setNom("¡ RIO LOCO ! GARONNE, LE FESTIVAL");
        festival.setNombrePass(40);
        festival.setTarifPass(Float.parseFloat("9"));
        festival.setSiteWeb("www.rio-loco.org");

        return festival;
    }

    public static LieuCovoiturage getLieuCovoiturageA(){

        Region region = new Region();
        region.setNom("Nouvelle-Aquitaine");
        
        Departement departement=new Departement();
        departement.setRegion(region);
        departement.setNumero("86");
        departement.setNom("Vienne");

        Commune commune = new Commune();
        commune.setDepartement(departement);
        commune.setCodeInsee("86194");
        commune.setCodePostal("86000");
        commune.setLatitude(Float.parseFloat("46.5839207726"));
        commune.setLongitude(Float.parseFloat("0.359947653003"));
        commune.setNom("POITIERS");        

        LieuCovoiturage lieuCovoiturage = new LieuCovoiturage();
        
        lieuCovoiturage.setAdresse("A10 Sortie 29 Poitiers Nord");
        lieuCovoiturage.setLatitude(Float.parseFloat("46.620867"));
        lieuCovoiturage.setLongitude(Float.parseFloat("0.343598"));
        lieuCovoiturage.setNom("Parking de covoiturage de Poitiers Nord");
        lieuCovoiturage.setType(TypeLieuCovoiturage.AIRE_COVOITURAGE);


        return lieuCovoiturage;
    }

    public static LieuCovoiturage getLieuCovoiturageB(){

        Region region = new Region();
        region.setNom("Pays de la Loire");
        
        Departement departement=new Departement();
        departement.setRegion(region);
        departement.setNumero("49");
        departement.setNom("Maine-et-Loire");

        Commune commune = new Commune();
        commune.setDepartement(departement);
        commune.setCodeInsee("49248");
        commune.setCodePostal("49520");
        commune.setLatitude(Float.parseFloat("47.7629344972"));
        commune.setLongitude(Float.parseFloat("-1.19300189799"));
        commune.setNom("LA PREVIERE");        

        LieuCovoiturage lieuCovoiturage = new LieuCovoiturage();
        
        lieuCovoiturage.setAdresse("giratoire D775-D771 (Pouancé)");
        lieuCovoiturage.setLatitude(Float.parseFloat("47.746999"));
        lieuCovoiturage.setLongitude(Float.parseFloat("-1.157561"));
        lieuCovoiturage.setNom("Aire de covoiturage de La Pidaie");
        lieuCovoiturage.setType(TypeLieuCovoiturage.AIRE_COVOITURAGE);


        return lieuCovoiturage;
    }

    public OffreCovoiturage getOffreCovoiturage(){
        OffreCovoiturage offreCovoiturage = new OffreCovoiturage();
        Covoitureur covoitureur = utilisateurFaker.createFakeCovoitureur();

        offreCovoiturage.setCovoitureur(covoitureur);
        offreCovoiturage.setDateOffre(new Date("13/06/2024"));
        offreCovoiturage.setModeleVoiture("null");
        offreCovoiturage.setNombrePlaces(3);

        return offreCovoiturage;
    }
    
}
