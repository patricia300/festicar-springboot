package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.dto.ArticleRequestBodyDto;
import com.bdi.projectbdigroup5.model.Article;
import com.bdi.projectbdigroup5.model.PointPassageCovoiturage;
import com.bdi.projectbdigroup5.repository.ArticleRepository;
import com.bdi.projectbdigroup5.repository.PointPassageCovoiturageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
                .orElseThrow(() -> new RuntimeException("Point passage covoiturage non trouvé"));

        Article newArticle = new Article();
        newArticle.setPointPassageCovoiturage(pointPassageCovoiturage);
        newArticle.setQuantite(requestBodyDto.getQuantite());

        return this.articleRepository.save(newArticle);
    }

    public Iterable<Article> saveAllArticle(List<ArticleRequestBodyDto> articleRequestBodyDtos){
        return articleRequestBodyDtos.stream().map(this::saveArticle).collect(Collectors.toList());
    }
}
