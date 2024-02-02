package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.dto.ArticleRequestBodyDto;
import com.bdi.projectbdigroup5.dto.ErreurPaiementPanierResponseDto;

import com.bdi.projectbdigroup5.exception.NotFoundException;
import com.bdi.projectbdigroup5.model.Article;
import com.bdi.projectbdigroup5.model.ErreurPaiementClass;
import com.bdi.projectbdigroup5.model.PointPassageCovoiturage;
import com.bdi.projectbdigroup5.repository.ArticleRepository;
import com.bdi.projectbdigroup5.repository.PointPassageCovoiturageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private PointPassageCovoiturageRepository pointPassageCovoiturageRepository;

    public Article findByID(Long id){
        return articleRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(
                        "Article non trouvé")
                );    }

    public Article saveArticle(ArticleRequestBodyDto requestBodyDto)
    {
        PointPassageCovoiturage pointPassageCovoiturage = pointPassageCovoiturageRepository
                .findById(requestBodyDto
                        .getIdPointPassage())
                .orElseThrow(() -> new NotFoundException("Point passage not found"));

        Article newArticle = new Article();
        newArticle.setPointPassageCovoiturage(pointPassageCovoiturage);
        newArticle.setQuantite(requestBodyDto.getQuantite());

        return this.articleRepository.save(newArticle);
    }

    public Article deleteArticle(Long id) {
        Article article = this.articleRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Article avec l'Id "+ id +" non trouvé"));
        this.articleRepository.delete(article);
        return  article;
    }

    public Iterable<Article> saveAllArticle(List<ArticleRequestBodyDto> articleRequestBodyDtos) {
        return articleRequestBodyDtos.stream().map(this::saveArticle).toList();
    }

    public static int getNbPlace(PointPassageCovoiturage pointPassageCovoiturage) {
        return pointPassageCovoiturage.getOffreCovoiturage().getNombrePlaces();
    }

    public static int getNbPass(PointPassageCovoiturage pointPassageCovoiturage) {
        return pointPassageCovoiturage.getOffreCovoiturage().getFestival().getNombrePass();
    }

    public static Optional<ErreurPaiementPanierResponseDto> verifierNombrePlaceOffreCovoiturage(int nbPlace, int quantite, Long idArticle){
        if(nbPlace < quantite) {
            return Optional.of(new ErreurPaiementPanierResponseDto(idArticle, nbPlace, ErreurPaiementClass.OFFRE_COVOITURAGE));
        }
        return Optional.empty();
    }

    public static Optional<ErreurPaiementPanierResponseDto> verifierNombrePassFestival(int nbPass, int quantite, Long idArticle) {
        if(nbPass < quantite){
            return Optional.of(new ErreurPaiementPanierResponseDto(idArticle, nbPass, ErreurPaiementClass.FESTIVAL));
        }
        return Optional.empty();
    }
}
