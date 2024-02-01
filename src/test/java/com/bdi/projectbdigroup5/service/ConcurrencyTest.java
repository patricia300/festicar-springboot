package com.bdi.projectbdigroup5.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.bdi.projectbdigroup5.ProjectBdiGroup5Application;
import com.bdi.projectbdigroup5.dto.ArticleRequestBodyDto;
import com.bdi.projectbdigroup5.dto.PanierRequestBodyDto;
import com.bdi.projectbdigroup5.dto.PanierResponseDto;
import com.bdi.projectbdigroup5.exception.NombrePassFestivalInsuffisantException;
import com.bdi.projectbdigroup5.exception.NombrePlaceCovoiturageInsuffisantException;
import com.bdi.projectbdigroup5.model.Commune;
import com.bdi.projectbdigroup5.model.Covoitureur;
import com.bdi.projectbdigroup5.model.Departement;
import com.bdi.projectbdigroup5.model.DomainePrincipal;
import com.bdi.projectbdigroup5.model.Festival;
import com.bdi.projectbdigroup5.model.Festivalier;
import com.bdi.projectbdigroup5.model.LieuCovoiturage;
import com.bdi.projectbdigroup5.model.OffreCovoiturage;
import com.bdi.projectbdigroup5.model.Organisateur;
import com.bdi.projectbdigroup5.model.PointPassageCovoiturage;
import com.bdi.projectbdigroup5.model.Region;
import com.bdi.projectbdigroup5.model.SousDomaine;
import com.bdi.projectbdigroup5.model.TypeLieuCovoiturage;
import com.github.javafaker.Address;
import com.github.javafaker.Faker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


import lombok.AllArgsConstructor;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConcurrencyTest {
    
    @Autowired
    private PanierService panierService;

    @Autowired
    private FestivalService festivalService;

    @Autowired
    private OffreCovoiturageService offreCovoiturageService;
    
    @Autowired
    private ArticleService articleService;

    @Autowired
    private LieuCovoiturageService lieuCovoiturageService;

    @Autowired
    private PointPassageCovoiturageService  pointPassageCovoiturageService;

    @Autowired
    private FestivalierService festivalierService;


    public Festivalier getFestivalier(){
        Faker faker=new Faker(new Locale("fr"));
        Address address = faker.address();

        Festivalier festivalier = new Festivalier();
        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy", Locale.FRANCE);
        festivalier.setNom(faker.name().lastName());
        festivalier.setPrenom(faker.name().firstName());
        festivalier.setEmail(faker.internet().emailAddress());
        festivalier.setUrlPhoto(faker.avatar().image());
        festivalier.setAdresse(address.fullAddress());
        festivalier.setCodePostal(address.zipCode());
        festivalier.setVille(address.city());
        try{
            festivalier.setDateNaissance(faker.date().between(formater.parse("01-01-1970"), formater.parse("01-01-2010")));
        }catch(ParseException e){
            System.out.println(e.getMessage());
        }
        return festivalier;
    }

    public Covoitureur getCovoitureur(){
        Covoitureur covoitureur = new Covoitureur();

        Faker faker = new Faker(new Locale("fr"));

        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy", Locale.FRANCE);
        covoitureur.setNom(faker.name().lastName());
        covoitureur.setPrenom(faker.name().firstName());
        covoitureur.setEmail(faker.internet().emailAddress());
        covoitureur.setUrlPhoto(faker.avatar().image());
        covoitureur.setNumeroTelephone(faker.phoneNumber().cellPhone());
        try{
            covoitureur.setDateNaissance(faker.date().between(formater.parse("01-01-1970"), formater.parse("01-01-2010")));
        }catch(ParseException e){
            System.out.println(e.getMessage());
        }

        return covoitureur;
    }

    public Organisateur getOrganisateur(){
        Organisateur organisateur = new Organisateur();
        Faker faker = new Faker(new Locale("fr"));

        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy", Locale.FRANCE);
        organisateur.setNom(faker.name().lastName());
        organisateur.setPrenom(faker.name().firstName());
        organisateur.setEmail(faker.internet().emailAddress());
        organisateur.setUrlPhoto(faker.avatar().image());
        try{
            organisateur.setDateNaissance(faker.date().between(formater.parse("01-01-1970"), formater.parse("01-01-2010")));
        }catch(ParseException e){
            System.out.println(e.getMessage());
        }
        return organisateur;
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
        festival.setDateDebut(new Date("13/06/2024"));
        festival.setDateFin(new Date("16/06/2024"));
        festival.setNom("¡ RIO LOCO ! GARONNE, LE FESTIVAL");
        festival.setNombrePass(40);
        festival.setTarifPass(Float.parseFloat("9"));
        festival.setSiteWeb("www.rio-loco.org");
        festival.setOrganisateur(getOrganisateur());

        return festiva;
    }

    public  LieuCovoiturage getLieuCovoiturageA(){

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


        return lieuCovoiturageService.createLieuCovoiturage(lieuCovoiturage);
    }

    public  LieuCovoiturage getLieuCovoiturageB(){

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


        return lieuCovoiturageService.createLieuCovoiturage(lieuCovoiturage);
    }

    public PointPassageCovoiturage getPointPassageCovoiturageA(){
        PointPassageCovoiturage pointPassageCovoiturageA = new PointPassageCovoiturage();
        pointPassageCovoiturageA.setLieuCovoiturage(getLieuCovoiturageA());
        pointPassageCovoiturageA.setPrix(Float.parseFloat("35"));
        pointPassageCovoiturageA.setDifferenceHeurePassage(2);

        return pointPassageCovoiturageA;
    }

    public PointPassageCovoiturage getPointPassageCovoiturageB(){
        PointPassageCovoiturage pointPassageCovoiturageB = new PointPassageCovoiturage();
        pointPassageCovoiturageB.setLieuCovoiturage(getLieuCovoiturageB());
        pointPassageCovoiturageB.setPrix(Float.parseFloat("20"));
        pointPassageCovoiturageB.setDifferenceHeurePassage(1);

        return pointPassageCovoiturageB;
    }

    public OffreCovoiturage getOffreCovoiturage(){

        OffreCovoiturage offreCovoiturage = new OffreCovoiturage();
        Covoitureur covoitureur = getCovoitureur();

        offreCovoiturage.setCovoitureur(covoitureur);
        offreCovoiturage.setDateOffre(new Date("13/06/2024"));
        offreCovoiturage.setModeleVoiture("2020,Land Rover,Range Rover Velar");
        offreCovoiturage.setNombrePlaces(3);
        offreCovoiturage.setFestival(getFestival());

        PointPassageCovoiturage pointPassageCovoiturageA = getPointPassageCovoiturageA();
        pointPassageCovoiturageA.setOffreCovoiturage(offreCovoiturage);
        pointPassageCovoiturageService.createPointPassageCovoiturage(pointPassageCovoiturageA);

        PointPassageCovoiturage pointPassageCovoiturageB = getPointPassageCovoiturageB();
        pointPassageCovoiturageA.setOffreCovoiturage(offreCovoiturage);
        pointPassageCovoiturageService.createPointPassageCovoiturage(pointPassageCovoiturageB);

        offreCovoiturageService.createOffreCovoiturage(offreCovoiturage);

        return offreCovoiturage;
    }
    
    public PanierResponseDto getPanierA(){
        
        
        Festivalier festivalier = getFestivalier();
        festivalierService.createFestivalier(festivalier);

        OffreCovoiturage offreCovoiturage = getOffreCovoiturage();
        
        ArticleRequestBodyDto article = new ArticleRequestBodyDto(offreCovoiturage.getPointPassageCovoiturages().get(0).getId(),1);
        
        List<ArticleRequestBodyDto> articles = new ArrayList<>();
        articles.add(article);

        PanierRequestBodyDto panier = new PanierRequestBodyDto(festivalier.getEmail(),articles);
        PanierResponseDto panierResponse = panierService.savePanierFestivalier(panier);
        
        return panierResponse;
    }

    public PanierResponseDto getPanierB(){
        
        Festivalier festivalier = getFestivalier();
        festivalierService.createFestivalier(festivalier);

        OffreCovoiturage offreCovoiturage = getOffreCovoiturage();
        
        ArticleRequestBodyDto article = new ArticleRequestBodyDto(offreCovoiturage.getPointPassageCovoiturages().get(1).getId(),1);
        
        List<ArticleRequestBodyDto> articles = new ArrayList<>();
        articles.add(article);

        PanierRequestBodyDto panier = new PanierRequestBodyDto(festivalier.getEmail(),articles);
        
        PanierResponseDto panierResponse = panierService.savePanierFestivalier(panier);
        
        return panierResponse;
    }

    public PanierResponseDto getPanierC(){
        
        
        Festivalier festivalier = getFestivalier();
        festivalierService.createFestivalier(festivalier);

        OffreCovoiturage offreCovoiturage = getOffreCovoiturage();
        
        ArticleRequestBodyDto article = new ArticleRequestBodyDto(offreCovoiturage.getPointPassageCovoiturages().get(0).getId(),1);
        
        List<ArticleRequestBodyDto> articles = new ArrayList<>();
        articles.add(article);

        PanierRequestBodyDto panier = new PanierRequestBodyDto(festivalier.getEmail(),articles);
        
        PanierResponseDto panierResponse = panierService.savePanierFestivalier(panier);
        
        return panierResponse;
    }

    public PanierResponseDto getPanierD(){
        
        
        Festivalier festivalier = getFestivalier();
        festivalierService.createFestivalier(festivalier);

        OffreCovoiturage offreCovoiturage = getOffreCovoiturage();
        
        ArticleRequestBodyDto article = new ArticleRequestBodyDto(offreCovoiturage.getPointPassageCovoiturages().get(1).getId(),1);
        
        List<ArticleRequestBodyDto> articles = new ArrayList<>();
        articles.add(article);

        PanierRequestBodyDto panier = new PanierRequestBodyDto(festivalier.getEmail(),articles);
        
        PanierResponseDto panierResponse = panierService.savePanierFestivalier(panier);
        
        return panierResponse;
    }


    @Test
    public void testConcurrentPanierPayment(){




        PanierResponseDto panierA = getPanierA();
        PanierResponseDto panierB = getPanierB();
        PanierResponseDto panierC = getPanierC();
        PanierResponseDto panierD = getPanierD();

        Festival festival = festivalService.findById(panierA.getArticles().get(0).getFestival().getId());

        OffreCovoiturage offreCovoiturage = articleService.findByID(panierA.getArticles().get(0).getId()).getPointPassageCovoiturage().getOffreCovoiturage();

        int nombrePass = festival.getNombrePass();
        int nombrePlaces = offreCovoiturage.getNombrePlaces();

        Callable<String> savepanierA = () ->{
            try{
                panierService.updatePanierStatusToPayed(panierA.getPanier().getId());
                return "PanierA enregistré";

            }catch(NombrePassFestivalInsuffisantException | NombrePlaceCovoiturageInsuffisantException e){
                fail("Nombre de place insufisant : " + e.getMessage());
                return "PanierA non enregistré : Nombre de place insufisant";
            }catch(Exception e){
                fail("Erreur non attendue : " + e.getMessage());
                return "PanierA non enregistré : erreur non attentdue";
            }
        };

        Callable<String> savepanierB = () ->{
            try{
                panierService.updatePanierStatusToPayed(panierB.getPanier().getId());
                return "PanierA enregistré";

            }catch(NombrePassFestivalInsuffisantException | NombrePlaceCovoiturageInsuffisantException e){
                fail("Nombre de place insufisant : " + e.getMessage());
                return "PanierA non enregistré : Nombre de place insufisant";
            }catch(Exception e){
                fail("Erreur non attendue : " + e.getMessage());
                return "PanierA non enregistré : erreur non attentdue";
            }
        };

        Callable<String> savepanierC = () ->{
            try{
                panierService.updatePanierStatusToPayed(panierC.getPanier().getId());
                return "PanierA enregistré";

            }catch(NombrePassFestivalInsuffisantException | NombrePlaceCovoiturageInsuffisantException e){
                fail("Nombre de place insufisant : " + e.getMessage());
                return "PanierA non enregistré : Nombre de place insufisant";
            }catch(Exception e){
                fail("Erreur non attendue : " + e.getMessage());
                return "PanierA non enregistré : erreur non attentdue";
            }
        };

        Callable<String> savepanierD = () ->{
            try{
                panierService.updatePanierStatusToPayed(panierD.getPanier().getId());
                return "PanierA enregistré";

            }catch(NombrePassFestivalInsuffisantException | NombrePlaceCovoiturageInsuffisantException e){
                fail("Nombre de place insufisant : " + e.getMessage());
                return "PanierA non enregistré : Nombre de place insufisant";
            }catch(Exception e){
                fail("Erreur non attendue : " + e.getMessage());
                return "PanierA non enregistré : erreur non attentdue";
            }
        };
        
        List<Callable<String>> callableTasks = new ArrayList<>();

        callableTasks.add(savepanierD);
        callableTasks.add(savepanierC);
        callableTasks.add(savepanierB);
        callableTasks.add(savepanierA);

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        try{
            executorService.invokeAll(callableTasks);
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }

        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(5000, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow(); } 
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }


        festival = festivalService.findById(panierA.getArticles().get(0).getFestival().getId());

        offreCovoiturage = articleService.findByID(panierA.getArticles().get(0).getId()).getPointPassageCovoiturage().getOffreCovoiturage();

        assertEquals(nombrePass-nombrePlaces, festival.getNombrePass(), "Le nombre de pass disponible doit etre mis à jour");
        assertEquals(0, offreCovoiturage.getNombrePlaces(), "Le nombre de place disponible doit etre mis a jour");
    }
    
}
