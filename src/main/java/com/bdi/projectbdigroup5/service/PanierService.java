package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.dto.PanierRequestBodyDto;
import com.bdi.projectbdigroup5.exception.NotFoundException;
import com.bdi.projectbdigroup5.model.*;
import com.bdi.projectbdigroup5.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.bdi.projectbdigroup5.service.ArticleService.*;

@Service
@AllArgsConstructor
public class PanierService {
    private PanierRepository panierRepository;
    private FestivalierRepository festivalierRepository;
    private ArticleRepository articleRepository;
    private FestivalRepository festivalRepository;
    private OffreCovoiturageRepository offreCovoiturageRepository;
    private ArticleService articleService;

    public Iterable<Panier> getPanierByFestivalierEmail(String email) {
        return this.panierRepository.findAllByFestivalierEmail(email);
    }

    public Panier savePanierFestivalier(PanierRequestBodyDto panierRequestBodyDto){
        // Search festivalier owner of the panier
        Festivalier festivalier = festivalierRepository
                .findById(panierRequestBodyDto.getEmailFestivalier())
                .orElseThrow(() -> new RuntimeException("Festivalier non trouvé"));

        // Create panier
        Panier panier = new Panier();
        panier.setFestivalier(festivalier);
        this.panierRepository.save(panier);

        // create articles
        List<Article> articles = (List<Article>) this.articleService.saveAllArticle(panierRequestBodyDto.getArticles());
        articles.forEach(article -> {
                    article.setPanier(panier);
                    articleRepository.save(article);
                });

        panier.setArticles(articles);

       return panier;
    }

    public Panier updatePanierStatusToPayed(Long id){
        //Chercher panier
        Panier panier = panierRepository.findById(id).orElseThrow(() -> new NotFoundException("Panier non trouvé"));
        // Verifier element panier
        panier.getArticles().forEach(article -> {
            int nbPlace = getNbPlace(article.getPointPassageCovoiturage());
            int nbPass = getNbPass(article.getPointPassageCovoiturage());

            Festival festival =   article.getPointPassageCovoiturage().getOffreCovoiturage().getFestival();
            OffreCovoiturage offreCovoiturage =  article.getPointPassageCovoiturage().getOffreCovoiturage();

            verifierNombrePlace(nbPlace, article.getQuantite(),offreCovoiturage.getId());
            verifierNombrePass(nbPass, article.getQuantite(), festival.getId());

            // Réduire nb de place et pass
            offreCovoiturage.setNombrePlaces((nbPlace - article.getQuantite()));
            festival.setNombrePass((nbPass - article.getQuantite()));

            offreCovoiturageRepository.save(offreCovoiturage);
            festivalRepository.save(festival);
        });

        // Modifier status panier
        panier.setStatut(StatutPanier.PAYER);
        panierRepository.save(panier);
        return panier;
    }

    public Panier getCurrentPanier(String email)
    {
        return this.panierRepository.findFirstByFestivalierEmailAndStatut(email, StatutPanier.EN_COURS);
    }
}
