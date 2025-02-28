package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.InitData;
import com.bdi.projectbdigroup5.dto.*;

import com.bdi.projectbdigroup5.model.Article;
import com.bdi.projectbdigroup5.model.ErreurPaiementClass;
import com.bdi.projectbdigroup5.model.Panier;
import com.bdi.projectbdigroup5.model.StatutPanier;
import com.bdi.projectbdigroup5.repository.ArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class PanierServiceTest {
    @Autowired
    private PanierService panierService;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private InitData initData;

    private final String EMAIL_FESTIVALIER = "mohamed.rey@gmail.com";

    @BeforeEach
    public void init()
    {
        initData.createFestivalierTest(EMAIL_FESTIVALIER);
        initData.createPointPassageCovoiturageTest(1L);
        initData.createPointPassageCovoiturageTest(2L);
        initData.createPointPassageCovoiturageTest(3L);
        initData.createPointPassageCovoiturageTest(4L);
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
    void PanierService_GetCurrentPanier_ReturnsPanierResponseDto(){
        Panier panier = initData.createPanierTest(1L, EMAIL_FESTIVALIER, StatutPanier.EN_COURS);
        initData.createArticleTest(1, panier, 1L, 2);
        initData.createArticleTest(1, panier, 2L,2);


        Optional<PanierResponseDto> panierResponseDto = this.panierService.getCurrentPanier(EMAIL_FESTIVALIER);

        assertTrue(panierResponseDto.isPresent());
        assertEquals(StatutPanier.EN_COURS, panierResponseDto.get().getPanier().getStatut());
    }

    @Test
    void PanierService_GetCurrentPanier_ReturnsPanierResponseDtoEmpty(){
        Optional<PanierResponseDto> panierResponseDto = this.panierService.getCurrentPanier(EMAIL_FESTIVALIER);

        assertTrue(panierResponseDto.isEmpty());
    }


    @Test
    void PanierService_GetPanierByFestivalierEmail_ReturnsIterablePanierResponseDto(){
        Panier panier = initData.createPanierTest(1L, EMAIL_FESTIVALIER, StatutPanier.EN_COURS);
        initData.createArticleTest(1, panier, 1L, 2);
        initData.createArticleTest(1, panier, 2L,2);

        initData.createPanierTest(1L, EMAIL_FESTIVALIER, StatutPanier.PAYER);
        initData.createArticleTest(1, panier, 3L, 2);
        initData.createArticleTest(1, panier, 4L,2);

        List<PanierResponseDto> panierResponseDto = (List<PanierResponseDto>) this.panierService.getPanierByFestivalierEmail(EMAIL_FESTIVALIER);

        assertNotNull(panierResponseDto);
        assertNotNull(panierResponseDto.get(0).getArticles());
        assertNotNull(panierResponseDto.get(0).getPanier());
    }

    @Test
    void PanierService_UpdatePanierStatusToPayed_ReturnsIterablePanierResponseDtoWithError(){
        Panier panier = initData.createPanierTest(1L, EMAIL_FESTIVALIER, StatutPanier.EN_COURS);
        initData.createArticleTest(1, panier, 1L, 0);
        initData.createArticleTest(1, panier, 2L);

        PanierResponseDto panierPayed = this.panierService.updatePanierStatusToPayed(panier.getId());

        assertNotNull(panierPayed);
        assertNotNull(panierPayed.getArticlesNonDisponible());
        assertNull(panierPayed.getArticles());
        assertNull(panierPayed.getPanier());
    }

    @Test
    void PanierService_UpdatePanierStatusToPayed_ReturnsIterablePanierResponseDto(){
        Panier panier = initData.createPanierTest(1L, EMAIL_FESTIVALIER, StatutPanier.EN_COURS);
        initData.createArticleTest(1, panier, 1L, 2);
        initData.createArticleTest(1, panier, 2L,1);

        PanierResponseDto panierPayed = this.panierService.updatePanierStatusToPayed(panier.getId());

        assertNotNull(panier);
        assertNotNull(panierPayed.getPanier());
        assertNotNull(panierPayed.getArticles());
        assertNull(panierPayed.getArticlesNonDisponible());
    }

    @Test
    void PanierService_UpdatePanierStatutPatchPaid_ReturnsIterablePanierResponseDtoWithError(){
        Panier panier = initData.createPanierTest(1L, EMAIL_FESTIVALIER, StatutPanier.EN_COURS);
        Article a1 = initData.createArticleTest(1, panier, 1L, 0);
        Article a2 = initData.createArticleTest(1, panier, 2L,3);

        PanierPartielPaiementRequestDto p =  PanierPartielPaiementRequestDto
                .builder()
                .emailFestivalier(EMAIL_FESTIVALIER).articles(List.of(a1.getId(), a2.getId()))
                .build();

        PanierResponseDto panierPayed = this.panierService.updatePanierStatutPatchPaid(p);

        assertNotNull(panierPayed);
        assertNotNull(panierPayed.getArticlesNonDisponible());
        assertNull(panierPayed.getArticles());
        assertNull(panierPayed.getPanier());
        assertEquals(1, panierPayed.getArticlesNonDisponible().size());
    }


    @Test
    void PanierService_UpdatePanierStatutPatchPaid_ReturnsIterablePanierResponseDtoSucces(){
        Panier panier = initData.createPanierTest(1L, EMAIL_FESTIVALIER, StatutPanier.EN_COURS);
        Article a1 = initData.createArticleTest(1, panier, 1L, 2);
        Article a2 = initData.createArticleTest(1, panier, 2L,2);

        PanierPartielPaiementRequestDto p =  PanierPartielPaiementRequestDto
                .builder()
                .emailFestivalier(EMAIL_FESTIVALIER)
                .articles(List.of(a1.getId(), a2.getId()))
                .build();

        PanierResponseDto panierPayed = this.panierService.updatePanierStatutPatchPaid(p);

        assertNotNull(panierPayed);
        assertNull(panierPayed.getArticlesNonDisponible());
        assertNotNull(panierPayed.getArticles());
        assertNotNull(panierPayed.getPanier());
        assertEquals(2, panierPayed.getArticles().size());
    }

    @Test
    void PanierService_verifierArticle_ReturnVoidWithErrorNotSuffisantOffreCovoiturage() {
        Panier panier = initData.createPanierTest(1L, EMAIL_FESTIVALIER, StatutPanier.EN_COURS);
        Article a = initData.createArticleTest(2, panier, 1L, 1);
        List<Optional<ErreurPaiementPanierResponseDto>> errors = new ArrayList<>();

        panierService.verifierArticle(a, errors);

       assertTrue(errors.get(0).isPresent());
       assertEquals(1, errors.get(0).get().getNbPassDisponible());
       assertEquals(ErreurPaiementClass.OFFRE_COVOITURAGE, errors.get(0).get().getClassType());
       assertEquals(1, a.getQuantite());

    }

    @Test
    void PanierService_verifierArticle_ReturnVoidWithErrorNotSuffisantZeroOffreCovoiturage() {
        Panier panier = initData.createPanierTest(1L, EMAIL_FESTIVALIER, StatutPanier.EN_COURS);
        Article a = initData.createArticleTest(2, panier, 1L, 0);
        List<Optional<ErreurPaiementPanierResponseDto>> errors = new ArrayList<>();

        panierService.verifierArticle(a, errors);
        Optional<Article> aDeleted = this.articleRepository.findById(a.getId());

        assertTrue(errors.get(0).isPresent());
        assertEquals(0, errors.get(0).get().getNbPassDisponible());
        assertEquals(ErreurPaiementClass.OFFRE_COVOITURAGE, errors.get(0).get().getClassType());
        assertTrue(aDeleted.isEmpty());
    }

    @Test
    void PanierService_verifierArticle_ReturnVoidWithErrorNotSuffisantFestival() {
        Panier panier = initData.createPanierTest(1L, EMAIL_FESTIVALIER, StatutPanier.EN_COURS);
        Article a = initData.createArticleTest(87, panier, 1L, 90);
        List<Optional<ErreurPaiementPanierResponseDto>> errors = new ArrayList<>();

        panierService.verifierArticle(a, errors);

        assertTrue(errors.get(0).isPresent());
        assertEquals(84, errors.get(0).get().getNbPassDisponible());
        assertEquals(ErreurPaiementClass.FESTIVAL, errors.get(0).get().getClassType());
        assertEquals(84, a.getQuantite());
    }

    @Test
    void PanierService_verifierArticle_ReturnVoidWithoutError() {
        Panier panier = initData.createPanierTest(1L, EMAIL_FESTIVALIER, StatutPanier.EN_COURS);
        Article a = initData.createArticleTest(1, panier, 1L, 2);
        List<Optional<ErreurPaiementPanierResponseDto>> errors = new ArrayList<>();

        panierService.verifierArticle(a, errors);

        assertTrue(errors.isEmpty());
    }
}
