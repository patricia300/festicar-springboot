package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.InitData;
import com.bdi.projectbdigroup5.dto.ArticleRequestBodyDto;
import com.bdi.projectbdigroup5.model.Article;
import com.bdi.projectbdigroup5.model.Panier;
import com.bdi.projectbdigroup5.model.StatutPanier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ArticleServiceTest {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private InitData initData;

    private final String EMAIL_FESTIVALIER = "mohamed.rey@gmail.com";

    @BeforeEach
    public void init(){
        initData.createFestivalierTest(EMAIL_FESTIVALIER);
        initData.createPointPassageCovoiturageTest(1L,2);
        initData.createPointPassageCovoiturageTest(2L,1);
        initData.createPointPassageCovoiturageTest(3L,1 );
        initData.createPointPassageCovoiturageTest(4L,3);
    }

    @Test
    void saveArticle() {
        ArticleRequestBodyDto a = ArticleRequestBodyDto.builder().quantite(1).idPointPassage(1L).build();
        Article article = this.articleService.saveArticle(a);

        assertNotNull(article);
        assertEquals(1L,article.getPointPassageCovoiturage().getId());
    }

    @Test
    void deleteArticle() {
        Panier panier = initData.createPanierTest(1L, EMAIL_FESTIVALIER, StatutPanier.EN_COURS);
        Article a1 = initData.createArticleTest(1, panier, 1L, 2);
        Article article = this.articleService.deleteArticle(a1.getId());
    }

    @Test
    void saveAllArticle() {
    }

    @Test
    void verifierNombrePlaceOffreCovoiturage() {
    }

    @Test
    void verifierNombrePassFestival() {
    }
}