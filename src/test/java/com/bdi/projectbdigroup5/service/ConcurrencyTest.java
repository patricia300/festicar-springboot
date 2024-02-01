package com.bdi.projectbdigroup5.service;

import java.sql.Date;
import java.time.Instant;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.bdi.projectbdigroup5.ProjectBdiGroup5Application;
import com.bdi.projectbdigroup5.model.Article;
import com.bdi.projectbdigroup5.model.Festival;
import com.bdi.projectbdigroup5.model.Festivalier;
import com.bdi.projectbdigroup5.model.LieuCovoiturage;
import com.bdi.projectbdigroup5.model.OffreCovoiturage;
import com.bdi.projectbdigroup5.model.Panier;
import com.bdi.projectbdigroup5.model.PointPassageCovoiturage;
import com.bdi.projectbdigroup5.model.StatutPanier;

import lombok.AllArgsConstructor;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = ProjectBdiGroup5Application.class,
        properties = {
        "spring.datasource.embedded-database-connection=h2",
                "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect",
                "spring.jpa.show-sql=true"
        }
)
public class ConcurrencyTest {
    
    @Autowired
    private PanierService panierService;
    @Autowired
    private OffreCovoiturageService offreCovoiturageService;
    @Autowired
    private PointPassageCovoiturageService  pointPassageCovoiturageService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private OrganisateurService organisateurService;
    @Autowired
    private FestivalierService festivalierService;
    @Autowired 
    private FestivalService festivalService;
    @Autowired
    private LieuCovoiturageService lieuCovoiturageService; 

    @Test
    public void testConcurrentPanierPayment(){

        FakeDataUtility testData = new FakeDataUtility();
        
        Festival festival = FakeDataUtility.getFestival();
        festival.setOrganisateur(testData.getOrganisateur());

        festivalService.createFestival(festival);

        LieuCovoiturage lieuCovoiturageA = testData.getLieuCovoiturageA();
        lieuCovoiturageService.createLieuCovoiturage(lieuCovoiturageA);

        LieuCovoiturage lieuCovoiturageB = testData.getLieuCovoiturageB();
        lieuCovoiturageService.createLieuCovoiturage(lieuCovoiturageB);

        OffreCovoiturage offreCovoiturage = testData.getOffreCovoiturage();
        offreCovoiturageService.createOffreCovoiturage(offreCovoiturage);

        PointPassageCovoiturage pointPassageCovoiturageA = new PointPassageCovoiturage();
        pointPassageCovoiturageA.setLieuCovoiturage(lieuCovoiturageA);
        pointPassageCovoiturageA.setOffreCovoiturage(offreCovoiturage);
        pointPassageCovoiturageA.setPrix(Float.parseFloat("35"));
        pointPassageCovoiturageA.setDifferenceHeurePassage(2);

        pointPassageCovoiturageService.createPointPassageCovoiturage(pointPassageCovoiturageA);

        PointPassageCovoiturage pointPassageCovoiturageB = new PointPassageCovoiturage();
        pointPassageCovoiturageA.setLieuCovoiturage(lieuCovoiturageB);
        pointPassageCovoiturageA.setOffreCovoiturage(offreCovoiturage);
        pointPassageCovoiturageA.setPrix(Float.parseFloat("20"));
        pointPassageCovoiturageA.setDifferenceHeurePassage(1);

        pointPassageCovoiturageService.createPointPassageCovoiturage(pointPassageCovoiturageB);

        offreCovoiturageService.createOffreCovoiturage(offreCovoiturage);


        Festivalier festivalier = testData.getFestivalier();
        
        Panier panier = new Panier();
        panier.setDateCreation(Instant.now());
        panier.setFestivalier(festivalier);
        panier.setStatut(StatutPanier.EN_COURS);
        





    }
    
}
