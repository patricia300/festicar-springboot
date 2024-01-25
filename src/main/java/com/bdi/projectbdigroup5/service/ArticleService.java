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


        verifierNombrePlace(nbPlace, requestBodyDto.getQuantite(), pointPassageCovoiturage.getOffreCovoiturage().getId());
        verifierNombrePass(nbPass, requestBodyDto.getQuantite(), pointPassageCovoiturage.getOffreCovoiturage().getFestival().getId());



        Article newArticle = new Article();
        newArticle.setPointPassageCovoiturage(pointPassageCovoiturage);
        newArticle.setQuantite(requestBodyDto.getQuantite());

        return this.articleRepository.save(newArticle);
    }

    public Iterable<Article> saveAllArticle(List<ArticleRequestBodyDto> articleRequestBodyDtos) {
        return articleRequestBodyDtos.stream().map(this::saveArticle).toList();
    }

    public static int getNbPlace(PointPassageCovoiturage pointPassageCovoiturage) {
        int nbPlace = pointPassageCovoiturage.getOffreCovoiturage().getNombrePlaces();
        if(nbPlace == 0) {
            throw new QuantiteZeroException(
                    pointPassageCovoiturage.getOffreCovoiturage().getId(),
                    "OffreCovoiturage"
            );
        }
        return nbPlace;
    }

    public static int getNbPass(PointPassageCovoiturage pointPassageCovoiturage) {
        int nbPass = pointPassageCovoiturage.getOffreCovoiturage().getFestival().getNombrePass();

        if(nbPass == 0) {
            throw new QuantiteZeroException(
                    pointPassageCovoiturage.getOffreCovoiturage().getFestival().getId(),
                    "Festival"
            );
        }

        return nbPass;
    }

    public static void verifierNombrePlace(int nbPlace, int quantite, Long idCovoiturage){
        if(nbPlace < quantite) {
            throw new NombrePlaceCovoiturageInsuffisantException(
                    idCovoiturage,
                    nbPlace
            );
        }
    }

    public static void verifierNombrePass(int nbPass, int quantite, Long idFestival) {
        if(nbPass < quantite){
            throw new NombrePassFestivalInsuffisantException(
                    idFestival,
                    nbPass
            );
        }
    }
}
