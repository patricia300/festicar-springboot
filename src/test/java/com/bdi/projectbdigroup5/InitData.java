package com.bdi.projectbdigroup5;

import com.bdi.projectbdigroup5.model.*;
import com.bdi.projectbdigroup5.repository.*;
import com.github.javafaker.Faker;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Locale;


@Service
@AllArgsConstructor
public class InitData {
    private FestivalierRepository festivalierRepository;
    private PointPassageCovoiturageRepository pointPassageCovoiturageRepository;
    LieuCovoiturageRepository lieuCovoiturageRepository;
    CommuneRepository communeRepository;
    DepartementRepository departementRepository;
    CovoitureurRepository covoitureurRepository;
    OrganisateurRepository organisateurRepository;
    OffreCovoiturageRepository offreCovoiturageRepository;
    RegionRepository regionRepository;
    DomainePrincipalRepository domainePrincipalRepository;
    SousDomaineRepository sousDomaineRepository;
    FestivalRepository festivalRepository;
    ArticleRepository articleRepository;
    PanierRepository panierRepository;

    public Festivalier createFestivalierTest(String email){
        Faker faker = initFaker();
        Festivalier festivalier = new Festivalier();
        festivalier.setEmail(email);
        festivalier.setNom(faker.name().firstName());
        festivalier.setPrenom(faker.name().lastName());
        festivalier.setDateNaissance(new Date("2000/01/05"));
        festivalier.setAdresse(faker.address().streetAddress());
        festivalier.setVille(faker.address().cityName());
        festivalier.setCodePostal(faker.address().zipCode());
        return festivalierRepository.save(festivalier);
    }

    public Covoitureur createCovoitureurTest() {
        Faker faker = initFaker();
        Covoitureur  covoitureur = new Covoitureur();
        covoitureur.setEmail(faker.internet().emailAddress());
        covoitureur.setNom(faker.name().firstName());
        covoitureur.setPrenom(faker.name().lastName());
        covoitureur.setDateNaissance(new Date("2000/01/05"));
        covoitureur.setNumeroTelephone(faker.phoneNumber().cellPhone());
        return covoitureurRepository.save(covoitureur);
    }

    public Organisateur createOrganisateurTest(){
        Faker faker= initFaker();
        Organisateur organisateur = new Organisateur();
        organisateur.setEmail(faker.internet().emailAddress());
        organisateur.setNom(faker.name().firstName());
        organisateur.setPrenom(faker.name().lastName());
        organisateur.setDateNaissance(new Date("2000/01/05"));

        return organisateurRepository.save(organisateur);
    }

    public Region createRegionTest(){
        Region region = new Region();
        region.setNom("Centre-Val de Loire");
        return regionRepository.save(region);
    }

    public Departement createDepartementTest(){
        Region region = createRegionTest();
        Departement departement = new Departement();
        departement.setNumero("37");
        departement.setNom("Indre-et-Loire");
        departement.setRegion(region);
        return departementRepository.save(departement);
    }

    public Commune createCommuneTest(){
        Faker faker = initFaker();
        Commune commune = new Commune();
        Departement departement = createDepartementTest();
        commune.setCodeInsee(String.valueOf(faker.number().numberBetween(37000,37999)));
        commune.setCodePostal(faker.address().zipCode());
        commune.setNom(faker.address().cityName());
        commune.setLatitude(65.688642F);
        commune.setLongitude(5.00024F);
        commune.setDepartement(departement);
        return communeRepository.save(commune);
    }

    public LieuCovoiturage createLieuCovoiturageTest(){
            Faker faker = initFaker();
            LieuCovoiturage lieuCovoiturage = new LieuCovoiturage();
            lieuCovoiturage.setId(faker.random().nextLong());
            lieuCovoiturage.setLongitude(47.391724F);
            lieuCovoiturage.setLongitude(1.00024F);
            lieuCovoiturage.setType(TypeLieuCovoiturage.AIRE_COVOITURAGE);
            lieuCovoiturage.setAdresse(faker.address().streetAddress());
            lieuCovoiturage.setNom(faker.address().cityName());
            lieuCovoiturage.setCommune(createCommuneTest());
            return  lieuCovoiturageRepository.save(lieuCovoiturage);
    }

    public PointPassageCovoiturage createPointPassageCovoiturageTest(Long id){
        Faker faker = initFaker();
        PointPassageCovoiturage pointPassageCovoiturage = new PointPassageCovoiturage();
        pointPassageCovoiturage.setId(id);
        pointPassageCovoiturage.setDifferenceHeurePassage(faker.number().numberBetween(1,4));
        pointPassageCovoiturage.setLieuCovoiturage(createLieuCovoiturageTest());
        pointPassageCovoiturage.setPrix(Float.parseFloat(String.valueOf(faker.number().numberBetween(10, 30))));
        pointPassageCovoiturage.setOffreCovoiturage(createOffreCovoiturageTest());
        return  pointPassageCovoiturageRepository.save(pointPassageCovoiturage);
    }

    public PointPassageCovoiturage createPointPassageCovoiturageTest(Long id, int qteOffreCovoiturage){
        Faker faker = initFaker();
        PointPassageCovoiturage pointPassageCovoiturage = new PointPassageCovoiturage();
        pointPassageCovoiturage.setId(id);
        pointPassageCovoiturage.setDifferenceHeurePassage(faker.number().numberBetween(1,4));
        pointPassageCovoiturage.setLieuCovoiturage(createLieuCovoiturageTest());
        pointPassageCovoiturage.setPrix(Float.parseFloat(String.valueOf(faker.number().numberBetween(10, 30))));
        pointPassageCovoiturage.setOffreCovoiturage(createOffreCovoiturageTest(qteOffreCovoiturage));
        return  pointPassageCovoiturageRepository.save(pointPassageCovoiturage);
    }


    public OffreCovoiturage createOffreCovoiturageTest() {
        Faker faker = initFaker();
        OffreCovoiturage offreCovoiturage = new OffreCovoiturage();
        offreCovoiturage.setId(faker.random().nextLong());
        offreCovoiturage.setModeleVoiture(faker.cat().breed());
        offreCovoiturage.setDateOffre(new Date("2024/06/05"));
        offreCovoiturage.setNombrePlaces(faker.number().numberBetween(1, 3));
        offreCovoiturage.setHeureDepart(new Date("2024/06/05 10:00"));
        offreCovoiturage.setHeureArrive(new Date("2024/06/05 20:00"));
        offreCovoiturage.setCovoitureur(createCovoitureurTest());
        offreCovoiturage.setFestival(createFestivalTest());
        return offreCovoiturageRepository.save(offreCovoiturage);
    }

    public OffreCovoiturage createOffreCovoiturageTest(int qte) {
        Faker faker = initFaker();
        OffreCovoiturage offreCovoiturage = new OffreCovoiturage();
        offreCovoiturage.setId(faker.random().nextLong());
        offreCovoiturage.setModeleVoiture(faker.cat().breed());
        offreCovoiturage.setDateOffre(new Date("2024/06/05"));
        offreCovoiturage.setNombrePlaces(qte);
        offreCovoiturage.setHeureDepart(new Date("2024/06/05 10:00"));
        offreCovoiturage.setHeureArrive(new Date("2024/06/05 20:00"));
        offreCovoiturage.setCovoitureur(createCovoitureurTest());
        offreCovoiturage.setFestival(createFestivalTest());
        return offreCovoiturageRepository.save(offreCovoiturage);
    }

    public DomainePrincipal createDomainePrincipalTest(){
        Faker faker = initFaker();
        DomainePrincipal domainePrincipal = new DomainePrincipal();
        domainePrincipal.setNom(faker.lorem().characters(30));
        return domainePrincipalRepository.save(domainePrincipal);
    }

    public SousDomaine createSousDomaineTest(){
        Faker faker = initFaker();
        SousDomaine sousDomaine = new SousDomaine();
        sousDomaine.setNom(faker.lorem().characters(20));
        sousDomaine.setDomainePrincipal(createDomainePrincipalTest());
        return sousDomaineRepository.save(sousDomaine);
    }

    public Festival createFestivalTest(){
        Festival festival = new Festival();
        festival.setOrganisateur(createOrganisateurTest());
        festival.setCommune(createCommuneTest());
        festival.setId(1776L);
        festival.setSiteWeb("www.les-grimaldines.com");
        festival.setSousDomaine(createSousDomaineTest());
        festival.setNombrePass(84);
        festival.setTarifPass(12.0F);
        festival.setNom("LES GRIMALDINES");
        festival.setDateDebut(new Date("2024/07/08"));
        festival.setDateFin(new Date("2024/08/01"));
        return festivalRepository.save(festival);
    }

    public Article createArticleTest(int qte, Panier panier, Long idPointPassage){
        Article article = new Article();
        article.setPanier(panier);
        article.setQuantite(qte);
        article.setPointPassageCovoiturage(createPointPassageCovoiturageTest(idPointPassage));
        return articleRepository.save(article);
    }

    public Article createArticleTest(int qte, Panier panier, Long idPointPassage, int qteOffreCovoiturage){
        Article article = new Article();
        article.setPanier(panier);
        article.setQuantite(qte);
        article.setPointPassageCovoiturage(createPointPassageCovoiturageTest(idPointPassage,qteOffreCovoiturage));
        return articleRepository.save(article);
    }

    public Panier createPanierTest(Long id,String email, StatutPanier statutPanier){
        Panier panier = new Panier();
        panier.setId(id);
        panier.setFestivalier(createFestivalierTest(email));
        panier.setStatut(statutPanier);
        return  panierRepository.save(panier);
    }

    public static Faker initFaker() {
        return new Faker(new Locale("fr"));
    }


}
