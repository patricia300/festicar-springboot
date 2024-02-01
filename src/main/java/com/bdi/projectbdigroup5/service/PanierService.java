package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.dto.*;
import com.bdi.projectbdigroup5.exception.NotFoundException;
import com.bdi.projectbdigroup5.model.*;
import com.bdi.projectbdigroup5.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.bdi.projectbdigroup5.dto.FestivalResponseDto.createFestivalResponseDtoFromArticle;
import static com.bdi.projectbdigroup5.service.ArticleService.*;
import com.bdi.projectbdigroup5.service.ServiceInterface.PanierServiceInterface;

@Service
public class PanierService {
    @Autowired
    private PanierRepository panierRepository;

    @Autowired
    private FestivalierRepository festivalierRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private FestivalRepository festivalRepository;

    @Autowired
    private OffreCovoiturageRepository offreCovoiturageRepository;

    @Autowired
    private ArticleService articleService;

    @Transactional
    public Iterable<PanierResponseDto> getPanierByFestivalierEmail(String email) {
        Festivalier festivalier = getFestivalier(email);
        return this.panierRepository.findAllByFestivalierEmail(festivalier.getEmail()).stream().map(panier -> {
            List<ArticleResponseDto> articleResponseDtos = panier.getArticles().stream()
                    .map(article -> ArticleResponseDto.builder()
                            .id(article.getId())
                            .festival(createFestivalResponseDtoFromArticle(article))
                            .quantite(article.getQuantite())
                            .build()
                    ).toList();

            return PanierResponseDto.builder()
                    .articles(articleResponseDtos)
                    .panier(panier)
                    .build();
        }).toList();
    }

    public PanierResponseDto savePanierFestivalier(PanierRequestBodyDto panierRequestBodyDto){
        // Search festivalier owner of the panier
        Festivalier festivalier = getFestivalier(panierRequestBodyDto.getEmailFestivalier());

        List<Article> articles = (List<Article>) this.articleService.saveAllArticle(panierRequestBodyDto.getArticles());

        Panier existingPanier = this.panierRepository.findFirstByFestivalierEmailAndStatut(festivalier.getEmail(), StatutPanier.EN_COURS);
        if (existingPanier != null )
        {
            List<ArticleResponseDto> articlesDtos = createListArticle(existingPanier, articles, articleRepository);
            return PanierResponseDto.builder()
                    .panier(existingPanier)
                    .articles(articlesDtos)
                    .build();

        }

        Panier panier = new Panier();
        panier.setFestivalier(festivalier);
        this.panierRepository.save(panier);

        List<ArticleResponseDto> articlesDtos = createListArticle(panier, articles, articleRepository);

       return PanierResponseDto.builder()
               .panier(panier)
               .articles(articlesDtos)
               .build();
    }

    @Transactional
    public Panier updatePanierStatusToPayed(Long id){
        //Chercher panier
        Panier panier = panierRepository.findById(id).orElseThrow(() -> new NotFoundException("Panier avec l'ID "+ id +" non trouvé"));
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

    @Transactional
    public PanierResponseDto getCurrentPanier(String email)
    {
        Festivalier festivalier = getFestivalier(email);
        Panier panier = this.panierRepository.findFirstByFestivalierEmailAndStatut(festivalier.getEmail(), StatutPanier.EN_COURS);
        List<ArticleResponseDto> articleResponseDtos = panier.getArticles().stream()
                .map(article -> ArticleResponseDto.builder()
                        .id(article.getId())
                        .festival(createFestivalResponseDtoFromArticle(article))
                        .quantite(article.getQuantite())
                        .build()
                ).toList();

        return PanierResponseDto.builder()
                .panier(panier)
                .articles(articleResponseDtos)
                .build();

    }

    public static List<ArticleResponseDto> createListArticle(Panier panier, List<Article> articles, ArticleRepository articleRepository){
        return articles.stream().map(article -> {
            article.setPanier(panier);
            articleRepository.save(article);
            return ArticleResponseDto.builder()
                    .id(article.getId())
                    .festival(createFestivalResponseDtoFromArticle(article))
                    .quantite(article.getQuantite())
                    .build();
        }).toList();
    }

    private Festivalier getFestivalier(String email) {
        return festivalierRepository
                .findById(email)
                .orElseThrow(() -> new NotFoundException(
                        "Festivalier avec l'email '" + email + "' non trouvé")
                );
    }


}
