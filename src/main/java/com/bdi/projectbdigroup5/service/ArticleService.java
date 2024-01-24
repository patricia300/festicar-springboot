package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.dto.ArticleRequestBodyDto;
import com.bdi.projectbdigroup5.model.Article;
import com.bdi.projectbdigroup5.model.PointPassageCovoiturage;
import com.bdi.projectbdigroup5.repository.ArticleRepository;
import com.bdi.projectbdigroup5.repository.FestivalRepository;
import com.bdi.projectbdigroup5.repository.PointPassageCovoiturageRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ArticleService {
    private ArticleRepository articleRepository;
    private PointPassageCovoiturageRepository pointPassageCovoiturageRepository;

    private FestivalRepository festivalRepository;

    public Article saveArticle(ArticleRequestBodyDto requestBodyDto)
    {
        PointPassageCovoiturage pointPassageCovoiturage = pointPassageCovoiturageRepository
                .findById(requestBodyDto
                        .getIdPointPassage())
                .orElseThrow(() -> new RuntimeException("Poit passage covoiturage non trouvé"));

        Article newArticle = new Article();
        newArticle.setPointPassageCovoiturage(pointPassageCovoiturage);
        newArticle.setQuantite(requestBodyDto.getQuantite());

        return this.articleRepository.save(newArticle);
    }
}
