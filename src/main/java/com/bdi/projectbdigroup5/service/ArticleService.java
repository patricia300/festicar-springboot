package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.dto.ArticleRequestBodyDto;
import com.bdi.projectbdigroup5.exception.NombrePassFestivalInsuffisantException;
import com.bdi.projectbdigroup5.exception.NombrePlaceCovoiturageInsuffisantException;
import com.bdi.projectbdigroup5.exception.NotFoundException;
import com.bdi.projectbdigroup5.exception.QuantiteZeroException;
import com.bdi.projectbdigroup5.model.Article;
import com.bdi.projectbdigroup5.model.PointPassageCovoiturage;
import com.bdi.projectbdigroup5.repository.ArticleRepository;
import com.bdi.projectbdigroup5.repository.PointPassageCovoiturageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ArticleService {
    private ArticleRepository articleRepository;
    private PointPassageCovoiturageRepository pointPassageCovoiturageRepository;

    public Article saveArticle(ArticleRequestBodyDto requestBodyDto)
    {
        PointPassageCovoiturage pointPassageCovoiturage = pointPassageCovoiturageRepository
                .findById(requestBodyDto
                        .getIdPointPassage())
                .orElseThrow(() -> new NotFoundException("Point passage not found"));

        int nbPlace = getNbPlace(pointPassageCovoiturage);
        int nbPass = getNbPass(pointPassageCovoiturage);


        if(nbPlace < requestBodyDto.getQuantite()) {
            throw new NombrePlaceCovoiturageInsuffisantException(
                    pointPassageCovoiturage.getOffreCovoiturage().getId(),
                    nbPlace
            );
        }

        if(nbPass < requestBodyDto.getQuantite()){
            throw new NombrePassFestivalInsuffisantException(
                    pointPassageCovoiturage.getOffreCovoiturage().getFestival().getId(),
                    nbPass
            );
        }

        Article newArticle = new Article();
        newArticle.setPointPassageCovoiturage(pointPassageCovoiturage);
        newArticle.setQuantite(requestBodyDto.getQuantite());

        return this.articleRepository.save(newArticle);
    }

    public Iterable<Article> saveAllArticle(List<ArticleRequestBodyDto> articleRequestBodyDtos) {
        return articleRequestBodyDtos.stream().map(this::saveArticle).toList();
    }

    private static int getNbPlace(PointPassageCovoiturage pointPassageCovoiturage) {
        int nbPlace = pointPassageCovoiturage.getOffreCovoiturage().getNombrePlaces();
        if(nbPlace == 0) {
            throw new QuantiteZeroException(
                    pointPassageCovoiturage.getOffreCovoiturage().getId(),
                    "OffreCovoiturage"
            );
        }
        return nbPlace;

    }

    private static int getNbPass(PointPassageCovoiturage pointPassageCovoiturage) {
        int nbPass = pointPassageCovoiturage.getOffreCovoiturage().getFestival().getNombrePass();

        if(nbPass == 0) {
            throw new QuantiteZeroException(
                    pointPassageCovoiturage.getOffreCovoiturage().getFestival().getId(),
                    "Festival"
            );
        }

        return nbPass;
    }
}
