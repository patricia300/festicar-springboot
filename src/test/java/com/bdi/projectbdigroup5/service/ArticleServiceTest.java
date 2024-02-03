package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.InitData;
import com.bdi.projectbdigroup5.dto.ArticleRequestBodyDto;
import com.bdi.projectbdigroup5.dto.ErreurPaiementPanierResponseDto;
import com.bdi.projectbdigroup5.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
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
    void ArticleService_saveArticle_ReturnsArticle() {
        ArticleRequestBodyDto a = ArticleRequestBodyDto.builder().quantite(1).idPointPassage(1L).build();
        Article article = this.articleService.saveArticle(a);

        assertNotNull(article);
        assertEquals(1L,article.getPointPassageCovoiturage().getId());
    }

    @Test
    void ArticleService_deleteArticle_ReturnsArticle() {
        Panier panier = initData.createPanierTest(1L, EMAIL_FESTIVALIER, StatutPanier.EN_COURS);
        Article a1 = initData.createArticleTest(1, panier, 1L, 2);
        Article a2 = initData.createArticleTest(1, panier, 2L, 2);

        Article article = this.articleService.deleteArticle(a1.getId());
        Article article2 = this.articleService.deleteArticle(a2.getId());

        assertNotNull(article);
        assertEquals(a1.getId(), article.getId());
        assertNotNull(article2);
        assertEquals(a2.getId(),article2.getId());
    }

    @Test
    void ArticleService_saveAllArticle_ReturnsListArticles() {
        ArticleRequestBodyDto a1 = ArticleRequestBodyDto.builder().quantite(1).idPointPassage(1L).build();
        ArticleRequestBodyDto a2 = ArticleRequestBodyDto.builder().quantite(1).idPointPassage(2L).build();
        ArticleRequestBodyDto a3 = ArticleRequestBodyDto.builder().quantite(1).idPointPassage(3L).build();

        List<Article> articles = (List<Article>) this.articleService.saveAllArticle(List.of(a1,a2,a3));

        assertNotNull(articles);
        assertEquals(3, articles.size());
        assertEquals(1L, articles.get(0).getPointPassageCovoiturage().getId());
        assertEquals(2L, articles.get(1).getPointPassageCovoiturage().getId());
        assertEquals(3L, articles.get(2).getPointPassageCovoiturage().getId());
    }

    @Test
    void ArticleService_GetNbPlace_ReturnIntNombrePlace(){
        PointPassageCovoiturage p1 = initData.createPointPassageCovoiturageTest(5L,3);

        int nbPlace = ArticleService.getNbPlace(p1);

        assertEquals(3,nbPlace);
    }


    @Test
    void ArticleService_GetNbPlace_ReturnIntNombrePass(){
        PointPassageCovoiturage p1 = initData.createPointPassageCovoiturageTest(5L,3);

        int nbPass = ArticleService.getNbPass(p1);

        assertEquals(84,nbPass);
    }

    @Test
    void ArticleService_verifierNombrePlaceOffreCovoiturage_ReturnOptionalErreurPaiementPanierResponseDto(){
        Panier panier = initData.createPanierTest(1L, EMAIL_FESTIVALIER, StatutPanier.EN_COURS);
        Article a = initData.createArticleTest(1, panier, 1L, 0);

        Optional<ErreurPaiementPanierResponseDto> erreurPaiementPanierResponseDto = ArticleService.verifierNombrePlaceOffreCovoiturage(0,1,a.getId());

       assertTrue(erreurPaiementPanierResponseDto.isPresent());
       assertEquals(0,erreurPaiementPanierResponseDto.get().getNbPassDisponible());
       assertEquals(ErreurPaiementClass.OFFRE_COVOITURAGE,erreurPaiementPanierResponseDto.get().getClassType() );
    }

    @Test
    void ArticleService_verifierNombrePlaceOffreCovoiturage_ReturnOptionalErreurPaiementPanierResponseDtoEmpty(){
        Panier panier = initData.createPanierTest(1L, EMAIL_FESTIVALIER, StatutPanier.EN_COURS);
        Article a = initData.createArticleTest(1, panier, 1L, 3);

        Optional<ErreurPaiementPanierResponseDto> erreurPaiementPanierResponseDto = ArticleService.verifierNombrePlaceOffreCovoiturage(3,1,a.getId());

        assertTrue(erreurPaiementPanierResponseDto.isEmpty());
    }

    @Test
    void ArticleService_verifierNombrePassFestival_ReturnOptionalErreurPaiementPanierResponseDto(){
        Panier panier = initData.createPanierTest(1L, EMAIL_FESTIVALIER, StatutPanier.EN_COURS);
        Article a = initData.createArticleTest(1, panier, 1L, 0);

        Optional<ErreurPaiementPanierResponseDto> erreurPaiementPanierResponseDto = ArticleService.verifierNombrePassFestival(1,2,a.getId());

        assertTrue(erreurPaiementPanierResponseDto.isPresent());
        assertEquals(1,erreurPaiementPanierResponseDto.get().getNbPassDisponible());
        assertEquals(ErreurPaiementClass.FESTIVAL,erreurPaiementPanierResponseDto.get().getClassType() );
    }

    @Test
    void ArticleService_verifierNombrePassFestival_ReturnOptionalErreurPaiementPanierResponseDtoEmpty(){
        Panier panier = initData.createPanierTest(1L, EMAIL_FESTIVALIER, StatutPanier.EN_COURS);
        Article a = initData.createArticleTest(1, panier, 1L, 3);

        Optional<ErreurPaiementPanierResponseDto> erreurPaiementPanierResponseDto = ArticleService.verifierNombrePassFestival(3,1,a.getId());

        assertTrue(erreurPaiementPanierResponseDto.isEmpty());
    }
}
