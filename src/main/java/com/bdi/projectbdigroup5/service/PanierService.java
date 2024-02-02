package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.dto.*;
import com.bdi.projectbdigroup5.exception.NotFoundException;
import com.bdi.projectbdigroup5.model.*;
import com.bdi.projectbdigroup5.repository.*;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.bdi.projectbdigroup5.dto.FestivalResponseDto.createFestivalResponseDtoFromArticle;
import static com.bdi.projectbdigroup5.service.ArticleService.*;

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
        return this.panierRepository
                .findAllByFestivalierEmailAndStatutNot(festivalier.getEmail(), StatutPanier.EN_COURS).stream()
                .map(panier -> {
                    List<ArticleResponseDto> articleResponseDtos = panier.getArticles().stream()
                            .map(article -> ArticleResponseDto.builder()
                                    .id(article.getId())
                                    .festival(createFestivalResponseDtoFromArticle(article))
                                    .quantite(article.getQuantite())
                                    .build())
                            .toList();

                    return PanierResponseDto.builder()
                            .articles(articleResponseDtos)
                            .panier(panier)
                            .build();
                }).toList();
    }

    public PanierResponseDto savePanierFestivalier(PanierRequestBodyDto panierRequestBodyDto) {

        Festivalier festivalier = getFestivalier(panierRequestBodyDto.getEmailFestivalier());

        List<Article> articles = (List<Article>) this.articleService.saveAllArticle(panierRequestBodyDto.getArticles());

        Panier existingPanier = this.panierRepository.findFirstByFestivalierEmailAndStatut(festivalier.getEmail(),
                StatutPanier.EN_COURS);
        if (existingPanier != null) {
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

    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
    public PanierResponseDto updatePanierStatusToPayed(Long id) {
        // Chercher panier
        Panier panier = panierRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Panier avec l'ID " + id + " non trouvé"));

        List<Optional<ErreurPaiementPanierResponseDto>> erreurPaiementPanierResponseDtosList = new ArrayList<>();

        panier.getArticles().forEach(article -> verifierArticle(article, erreurPaiementPanierResponseDtosList));

        if (erreurPaiementPanierResponseDtosList.isEmpty()) {
            List<ArticleResponseDto> articles = panier.getArticles().stream().map(article -> {
                reduireNombrePlaceFestivalEtOffreCovoiturage(article);

                panier.setStatut(StatutPanier.PAYER);
                this.panierRepository.save(panier);

                return ArticleResponseDto.builder()
                        .id(article.getId())
                        .quantite(article.getQuantite())
                        .festival(createFestivalResponseDtoFromArticle(article))
                        .build();
            }).toList();

            return PanierResponseDto.builder()
                    .articles(articles)
                    .panier(panier)
                    .build();
        }

        return PanierResponseDto.builder()
                .articlesNonDisponible(erreurPaiementPanierResponseDtosList)
                .build();
    }

    @Transactional
    public Optional<PanierResponseDto> getCurrentPanier(String email) {
        Festivalier festivalier = getFestivalier(email);
        Panier panier = this.panierRepository.findFirstByFestivalierEmailAndStatut(festivalier.getEmail(),
                StatutPanier.EN_COURS);

        if (panier == null) {
            return Optional.empty();
        }

        List<ArticleResponseDto> articleResponseDtos = panier.getArticles().stream()
                .map(article -> ArticleResponseDto.builder()
                        .id(article.getId())
                        .festival(createFestivalResponseDtoFromArticle(article))
                        .quantite(article.getQuantite())
                        .build())
                .toList();

        return Optional.of(PanierResponseDto.builder()
                .panier(panier)
                .articles(articleResponseDtos)
                .build());

    }

    public static List<ArticleResponseDto> createListArticle(Panier panier, List<Article> articles,
            ArticleRepository articleRepository) {
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
                        "Festivalier avec l'email '" + email + "' non trouvé"));
    }

    public PanierResponseDto updatePanierStatutPatchPaid(
            PanierPartielPaiementRequestDto panierRequestPaimentPartielDto) {

        List<Optional<ErreurPaiementPanierResponseDto>> erreurPaiementPanierResponseDtosList = new ArrayList<>();

        panierRequestPaimentPartielDto.getArticles().forEach(id -> {
            Article article = this.articleRepository.findById(id).orElseThrow(
                    () -> new NotFoundException("Article avec l'ID " + id + " non trouvé"));

            verifierArticle(article, erreurPaiementPanierResponseDtosList);
        });

        if (erreurPaiementPanierResponseDtosList.isEmpty()) {
            Festivalier festivalier = this.festivalierRepository
                    .findById(panierRequestPaimentPartielDto.getEmailFestivalier())
                    .orElseThrow(() -> new NotFoundException("Festivalier avec l'email "
                            + panierRequestPaimentPartielDto.getEmailFestivalier() + " non trouvé"));

            Panier panierPayed = new Panier();
            panierPayed.setFestivalier(festivalier);
            panierPayed.setStatut(StatutPanier.PAYER);
            this.panierRepository.save(panierPayed);

            List<ArticleResponseDto> articles = panierRequestPaimentPartielDto.getArticles().stream().map(id -> {
                Article article = this.articleRepository.findById(id).orElseThrow(
                        () -> new NotFoundException("Article avec l'ID " + id + " non trouvé"));

                reduireNombrePlaceFestivalEtOffreCovoiturage(article);

                article.setPanier(panierPayed);
                this.articleRepository.save(article);
                return ArticleResponseDto.builder()
                        .id(article.getId())
                        .quantite(article.getQuantite())
                        .festival(createFestivalResponseDtoFromArticle(article))
                        .build();
            }).toList();

            return PanierResponseDto.builder()
                    .articles(articles)
                    .panier(panierPayed)
                    .build();
        }

        return PanierResponseDto.builder()
                .articlesNonDisponible(erreurPaiementPanierResponseDtosList)
                .build();

    }

    public void reduireNombrePlaceFestivalEtOffreCovoiturage(Article article) {
        int nbPlace = getNbPlace(article.getPointPassageCovoiturage());
        int nbPass = getNbPass(article.getPointPassageCovoiturage());

        Festival festival = article.getPointPassageCovoiturage().getOffreCovoiturage().getFestival();
        OffreCovoiturage offreCovoiturage = article.getPointPassageCovoiturage().getOffreCovoiturage();

        offreCovoiturage.setNombrePlaces((nbPlace - article.getQuantite()));
        festival.setNombrePass((nbPass - article.getQuantite()));

        this.offreCovoiturageRepository.save(offreCovoiturage);
        this.festivalRepository.save(festival);
    }

    public void verifierArticle(Article article,
            List<Optional<ErreurPaiementPanierResponseDto>> erreurPaiementPanierResponseDtosList) {
        int nbPlace = getNbPlace(article.getPointPassageCovoiturage());
        int nbPass = getNbPass(article.getPointPassageCovoiturage());

        Optional<ErreurPaiementPanierResponseDto> erreurPaiementPanierResponseDtoOffreCovoiturage = verifierNombrePlaceOffreCovoiturage(
                nbPlace, article.getQuantite(), article.getId());
        Optional<ErreurPaiementPanierResponseDto> erreurPaiementPanierResponseDtoFestival = verifierNombrePassFestival(
                nbPass, article.getQuantite(), article.getId());

        if (erreurPaiementPanierResponseDtoFestival.isPresent()) {
            erreurPaiementPanierResponseDtosList.add(erreurPaiementPanierResponseDtoFestival);
        }

        if (erreurPaiementPanierResponseDtoOffreCovoiturage.isPresent()) {
            erreurPaiementPanierResponseDtosList.add(erreurPaiementPanierResponseDtoOffreCovoiturage);
        }
    }
}
