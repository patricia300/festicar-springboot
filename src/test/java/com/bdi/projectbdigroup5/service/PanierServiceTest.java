package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.InitDataTest;
import com.bdi.projectbdigroup5.dto.ArticleRequestBodyDto;
import com.bdi.projectbdigroup5.dto.PanierRequestBodyDto;
import com.bdi.projectbdigroup5.dto.PanierResponseDto;

import com.bdi.projectbdigroup5.model.Article;
import com.bdi.projectbdigroup5.model.Panier;
import com.bdi.projectbdigroup5.model.StatutPanier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
class PanierServiceTest {
    @Autowired
    private PanierService panierService;

    @Autowired
    private InitDataTest initDataTest;

    private final String EMAIL_FESTIVALIER = "mohamed.rey@gmail.com";

    @BeforeEach
    public void init()
    {
        initDataTest.createFestivalierTest(EMAIL_FESTIVALIER);
        initDataTest.createPointPassageCovoiturageTest(1L);
        initDataTest.createPointPassageCovoiturageTest(2L);
        initDataTest.createPointPassageCovoiturageTest(3L);
        initDataTest.createPointPassageCovoiturageTest(4L);
    }

    @Test
    void  PanierService_SavePanierFestivalier_ReturnsPanierResponseDto() {
        ArticleRequestBodyDto a1 = ArticleRequestBodyDto.builder()
                .idPointPassage(1L)
                .quantite(1)
                .build();

        ArticleRequestBodyDto a2 = ArticleRequestBodyDto.builder()
                .idPointPassage(2L)
                .quantite(2)
                .build();

        PanierRequestBodyDto requestBodyDto = PanierRequestBodyDto.builder()
                .emailFestivalier(EMAIL_FESTIVALIER)
                .articles(List.of(a1,a2))
                .build();

        PanierResponseDto panierResponseDto = this.panierService.savePanierFestivalier(requestBodyDto);

        assertNotNull(panierResponseDto);
        assertNotNull(panierResponseDto.getArticles());
        assertEquals(2, panierResponseDto.getArticles().size());
        assertNotNull(panierResponseDto.getPanier());
        assertEquals(StatutPanier.EN_COURS, panierResponseDto.getPanier().getStatut());
    }

    @Test
    public void PanierService_GetCurrentPanier_ReturnsPanierResponseDto(){
        ArticleRequestBodyDto a1 = ArticleRequestBodyDto.builder()
                .idPointPassage(1L)
                .quantite(1)
                .build();

        ArticleRequestBodyDto a2 = ArticleRequestBodyDto.builder()
                .idPointPassage(2L)
                .quantite(1)
                .build();

        PanierRequestBodyDto requestBodyDto = PanierRequestBodyDto.builder()
                .emailFestivalier(EMAIL_FESTIVALIER)
                .articles(List.of(a1,a2))
                .build();

        this.panierService.savePanierFestivalier(requestBodyDto);
        PanierResponseDto panierResponseDto = this.panierService.getCurrentPanier(EMAIL_FESTIVALIER);

        assertNotNull(panierResponseDto);
        assertEquals(StatutPanier.EN_COURS, panierResponseDto.getPanier().getStatut());
    }

    @Test
    public void PanierService_GetPanierByFestivalierEmail_ReturnsIterablePanierResponseDto(){
        ArticleRequestBodyDto a1 = ArticleRequestBodyDto.builder()
                .idPointPassage(1L)
                .quantite(1)
                .build();

        ArticleRequestBodyDto a2 = ArticleRequestBodyDto.builder()
                .idPointPassage(2L)
                .quantite(1)
                .build();

        PanierRequestBodyDto requestBodyDto = PanierRequestBodyDto.builder()
                .emailFestivalier(EMAIL_FESTIVALIER)
                .articles(List.of(a1,a2))
                .build();

        this.panierService.savePanierFestivalier(requestBodyDto);
        List<PanierResponseDto> panierResponseDto = (List<PanierResponseDto>) this.panierService.getPanierByFestivalierEmail(EMAIL_FESTIVALIER);

        assertNotNull(panierResponseDto);
        assertNotNull(panierResponseDto.get(0).getArticles());
        assertNotNull(panierResponseDto.get(0).getPanier());
    }

    @Test
    public void PanierService_UpdatePanierStatusToPayed_ReturnsIterablePanierResponseDto(){
        Panier panier = initDataTest.createPanierTest(1L, EMAIL_FESTIVALIER, StatutPanier.EN_COURS);
        Article article = initDataTest.createArticleTest(1, panier, 1L);
        Article article1 = initDataTest.createArticleTest(1, panier, 2L);

        Panier panierPayed = this.panierService.updatePanierStatusToPayed(panier.getId());

        assertNotNull(panierPayed);
        assertEquals(StatutPanier.PAYER, panierPayed.getStatut());
    }
}