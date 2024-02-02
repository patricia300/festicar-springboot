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
import java.util.logging.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
import com.bdi.projectbdigroup5.model.Panier;
import com.bdi.projectbdigroup5.model.PointPassageCovoiturage;
import com.bdi.projectbdigroup5.model.Region;
import com.bdi.projectbdigroup5.model.SousDomaine;
import com.bdi.projectbdigroup5.model.StatutPanier;
import com.bdi.projectbdigroup5.model.TypeLieuCovoiturage;
import com.github.javafaker.Address;
import com.github.javafaker.Faker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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
    private LieuCovoiturageService lieuCovoiturageService;

    @Autowired
    private PointPassageCovoiturageService pointPassageCovoiturageService;

    @Autowired
    private FestivalierService festivalierService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private DepartementService departementService;

    @Autowired
    private OrganisateurService organisateurService;

    @Autowired
    private CovoitureurService covoitureurService;

    @Autowired
    private CommuneService communeService;

    @Autowired
    private DomainePrincipalService domainePrincipalService;

    @Autowired
    private SousDomaineService sousDomaineService;

    private static Logger log = Logger.getLogger("ConcurrencyTest.class");

    public Festivalier getFestivalier() {
        Faker faker = new Faker(new Locale("fr"));
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
        try {
            festivalier
                    .setDateNaissance(faker.date().between(formater.parse("01-01-1970"), formater.parse("01-01-2010")));
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }

        return festivalierService.createFestivalier(festivalier);
    }

    public Covoitureur getCovoitureur() {
        Covoitureur covoitureur = new Covoitureur();

        Faker faker = new Faker(new Locale("fr"));

        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy", Locale.FRANCE);
        covoitureur.setNom(faker.name().lastName());
        covoitureur.setPrenom(faker.name().firstName());
        covoitureur.setEmail(faker.internet().emailAddress());
        covoitureur.setUrlPhoto(faker.avatar().image());
        covoitureur.setNumeroTelephone(faker.phoneNumber().cellPhone());
        try {
            covoitureur
                    .setDateNaissance(faker.date().between(formater.parse("01-01-1970"), formater.parse("01-01-2010")));
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }

        return covoitureurService.createCovoitureur(covoitureur);
    }

    public Organisateur getOrganisateur() {
        Organisateur organisateur = new Organisateur();
        Faker faker = new Faker(new Locale("fr"));

        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy", Locale.FRANCE);
        organisateur.setNom(faker.name().lastName());
        organisateur.setPrenom(faker.name().firstName());
        organisateur.setEmail(faker.internet().emailAddress());
        organisateur.setUrlPhoto(faker.avatar().image());
        try {
            organisateur
                    .setDateNaissance(faker.date().between(formater.parse("01-01-1970"), formater.parse("01-01-2010")));
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return organisateurService.createOrganisateur(organisateur);
    }

    @Test
    public void testConcurrentPanierPayment() {
        log.info("I'm starting");

        // festival
        Region regionFestival = new Region();
        regionFestival.setNom("Occitanie");
        regionFestival = regionService.createRegion(regionFestival);

        Departement departementFestival = new Departement();
        departementFestival.setRegion(regionFestival);
        departementFestival.setNumero("31");
        departementFestival.setNom("Haute-Garonne");
        departementFestival = departementService.createDepartement(departementFestival);

        Commune communeFestival = new Commune();
        communeFestival.setDepartement(departementFestival);
        communeFestival.setCodeInsee("31555");
        communeFestival.setCodePostal("31300");
        communeFestival.setLatitude(Float.parseFloat("43.5963814303"));
        communeFestival.setLongitude(Float.parseFloat("1.43167293364"));
        communeFestival.setNom("TOULOUSE");
        communeFestival = communeService.createCommune(communeFestival);

        DomainePrincipal domainePrincipal = new DomainePrincipal();
        domainePrincipal.setNom("Musiques actuelles");
        domainePrincipal = domainePrincipalService.createDomainePrincipal(domainePrincipal);

        SousDomaine sousDomaine = new SousDomaine();
        sousDomaine.setDomainePrincipal(domainePrincipal);
        sousDomaine.setNom("Musiques traditionnelles et du monde");
        sousDomaine = sousDomaineService.createSousDomaine(sousDomaine);

        Festival festival = new Festival();

        festival.setCommune(communeFestival);
        festival.setSousDomaine(sousDomaine);
        festival.setDateDebut(new Date("13/06/2024"));
        festival.setDateFin(new Date("16/06/2024"));
        festival.setNom("¡ RIO LOCO ! GARONNE, LE FESTIVAL");
        festival.setNombrePass(40);
        festival.setTarifPass(Float.parseFloat("9"));
        festival.setSiteWeb("www.rio-loco.org");
        festival.setOrganisateur(getOrganisateur());

        festival = festivalService.createFestival(festival);

        // lieuCovoiturage A

        Region regionLieuCovoiturageA = new Region();
        regionLieuCovoiturageA.setNom("Nouvelle-Aquitaine");
        regionLieuCovoiturageA = regionService.createRegion(regionLieuCovoiturageA);

        Departement departementLieuCovoiturageA = new Departement();
        departementLieuCovoiturageA.setRegion(regionLieuCovoiturageA);
        departementLieuCovoiturageA.setNumero("86");
        departementLieuCovoiturageA.setNom("Vienne");
        departementLieuCovoiturageA = departementService.createDepartement(departementLieuCovoiturageA);

        Commune communeLieuCovoiturageA = new Commune();
        communeLieuCovoiturageA.setDepartement(departementLieuCovoiturageA);
        communeLieuCovoiturageA.setCodeInsee("86194");
        communeLieuCovoiturageA.setCodePostal("86000");
        communeLieuCovoiturageA.setLatitude(Float.parseFloat("46.5839207726"));
        communeLieuCovoiturageA.setLongitude(Float.parseFloat("0.359947653003"));
        communeLieuCovoiturageA.setNom("POITIERS");
        communeLieuCovoiturageA = communeService.createCommune(communeLieuCovoiturageA);

        LieuCovoiturage lieuCovoiturageA = new LieuCovoiturage();

        lieuCovoiturageA.setCommune(communeLieuCovoiturageA);
        lieuCovoiturageA.setAdresse("A10 Sortie 29 Poitiers Nord");
        lieuCovoiturageA.setLatitude(Float.parseFloat("46.620867"));
        lieuCovoiturageA.setLongitude(Float.parseFloat("0.343598"));
        lieuCovoiturageA.setNom("Parking de covoiturage de Poitiers Nord");
        lieuCovoiturageA.setType(TypeLieuCovoiturage.AIRE_COVOITURAGE);

        lieuCovoiturageA = lieuCovoiturageService.createLieuCovoiturage(lieuCovoiturageA);

        // lieuCovoiturage B

        Region regionLieuCovoiturageB = new Region();
        regionLieuCovoiturageB.setNom("Pays de la Loire");
        regionLieuCovoiturageB = regionService.createRegion(regionLieuCovoiturageB);

        Departement departementLieuCovoiturageB = new Departement();
        departementLieuCovoiturageB.setRegion(regionLieuCovoiturageB);
        departementLieuCovoiturageB.setNumero("49");
        departementLieuCovoiturageB.setNom("Maine-et-Loire");
        departementLieuCovoiturageB = departementService.createDepartement(departementLieuCovoiturageB);

        Commune communeLieuCovoiturageB = new Commune();
        communeLieuCovoiturageB.setDepartement(departementLieuCovoiturageB);
        communeLieuCovoiturageB.setCodeInsee("49248");
        communeLieuCovoiturageB.setCodePostal("49520");
        communeLieuCovoiturageB.setLatitude(Float.parseFloat("47.7629344972"));
        communeLieuCovoiturageB.setLongitude(Float.parseFloat("-1.19300189799"));
        communeLieuCovoiturageB.setNom("LA PREVIERE");
        communeLieuCovoiturageB = communeService.createCommune(communeLieuCovoiturageB);

        LieuCovoiturage lieuCovoiturageB = new LieuCovoiturage();

        lieuCovoiturageB.setCommune(communeLieuCovoiturageB);
        lieuCovoiturageB.setAdresse("giratoire D775-D771 (Pouancé)");
        lieuCovoiturageB.setLatitude(Float.parseFloat("47.746999"));
        lieuCovoiturageB.setLongitude(Float.parseFloat("-1.157561"));
        lieuCovoiturageB.setNom("Aire de covoiturage de La Pidaie");
        lieuCovoiturageB.setType(TypeLieuCovoiturage.AIRE_COVOITURAGE);

        lieuCovoiturageB = lieuCovoiturageService.createLieuCovoiturage(lieuCovoiturageB);

        // offreCovoiturage

        OffreCovoiturage offreCovoiturage = new OffreCovoiturage();
        Covoitureur covoitureur = getCovoitureur();

        offreCovoiturage.setCovoitureur(covoitureur);
        offreCovoiturage.setDateOffre(new Date("13/06/2024"));
        offreCovoiturage.setModeleVoiture("2020,Land Rover,Range Rover Velar");
        offreCovoiturage.setNombrePlaces(3);
        offreCovoiturage.setFestival(festival);

        offreCovoiturage = offreCovoiturageService.createOffreCovoiturage(offreCovoiturage);

        List<PointPassageCovoiturage> pointPassages = new ArrayList<>();

        PointPassageCovoiturage pointPassageCovoiturageA = new PointPassageCovoiturage();
        pointPassageCovoiturageA.setLieuCovoiturage(lieuCovoiturageA);
        pointPassageCovoiturageA.setPrix(Float.parseFloat("35"));
        pointPassageCovoiturageA.setDifferenceHeurePassage(2);
        pointPassageCovoiturageA.setOffreCovoiturage(offreCovoiturage);

        pointPassages.add(pointPassageCovoiturageService.createPointPassageCovoiturage(pointPassageCovoiturageA));

        PointPassageCovoiturage pointPassageCovoiturageB = new PointPassageCovoiturage();
        pointPassageCovoiturageB.setLieuCovoiturage(lieuCovoiturageB);
        pointPassageCovoiturageB.setPrix(Float.parseFloat("20"));
        pointPassageCovoiturageB.setDifferenceHeurePassage(1);
        pointPassageCovoiturageB.setOffreCovoiturage(offreCovoiturage);

        pointPassages.add(pointPassageCovoiturageService.createPointPassageCovoiturage(pointPassageCovoiturageB));

        List<PanierResponseDto> paniers = new ArrayList<>();

        ArticleRequestBodyDto article;
        List<ArticleRequestBodyDto> articles = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            Festivalier festivalier = getFestivalier();

            article = new ArticleRequestBodyDto(pointPassages.get(i % 2).getId(), 1);
            articles.add(article);
            PanierRequestBodyDto panier = new PanierRequestBodyDto(festivalier.getEmail(), articles);
            PanierResponseDto panierResponse = panierService.savePanierFestivalier(panier);

            paniers.add(panierResponse);
            articles.clear();
        }

        int nombrePass = festival.getNombrePass();
        int nombrePlaces = offreCovoiturage.getNombrePlaces();

        log.info("nombre de place est " + nombrePlaces);
        log.info("nombre de pass est " + nombrePass);

        List<Callable<String>> callableTasks = new ArrayList<>();
        List<Panier> panierValides = new ArrayList<>();

        paniers.forEach(p -> {
            callableTasks.add(() -> {
                try {

                    PanierResponseDto panier = panierService.updatePanierStatusToPayed(p.getPanier().getId());
                    if (panier.getPanier().getStatut() == StatutPanier.PAYER) {
                        panierValides.add(panier.getPanier());
                    }
                    return "PanierA enregistré";

                } catch (NombrePassFestivalInsuffisantException | NombrePlaceCovoiturageInsuffisantException e) {
                    fail("Nombre de place insufisant : " + e.getMessage());
                    log.warning("Nombre de place insufisant : " + e.getMessage());
                    return "PanierA non enregistré : Nombre de place insufisant";
                } catch (Exception e) {
                    fail("Erreur non attendue : " + e.getMessage());
                    log.warning("Erreur non attendue : " + e.getMessage());
                    return "PanierA non enregistré : erreur non attentdue";
                }
            });
        });

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        try {
            executorService.invokeAll(callableTasks);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            log.warning(e.getMessage());
        }

        executorService.shutdown();
        // try {
        // if (!executorService.awaitTermination(5000, TimeUnit.MILLISECONDS)) {
        // executorService.shutdownNow(); }
        // } catch (InterruptedException e) {
        // executorService.shutdownNow();
        // }

        int nombrePanierValide = panierValides.size();
        int nombrePassActuel = festivalService.findById(festival.getId()).getNombrePass();
        int nombrePlacesActuel = offreCovoiturageService.findByID(offreCovoiturage.getId()).getNombrePlaces();

        assertEquals(nombrePass - nombrePanierValide, nombrePassActuel,
                "Le nombre de pass disponible doit etre mis à jour");
        assertEquals(nombrePlaces - nombrePanierValide, nombrePlacesActuel,
                "Le nombre de place disponible doit etre mis a jour");
    }

}
