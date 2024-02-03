package com.bdi.projectbdigroup5.dto;

import com.bdi.projectbdigroup5.InitData;
import com.bdi.projectbdigroup5.model.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class FestivalResponseDtoTest {
    @Autowired
    private InitData initData;

    private final String EMAIL_FESTIVALIER = "mohamed.rey@gmail.com";

    @Test
    void FestivalResponseDto_CreateFestivalResponseDtoFromArticle_ReturnListOffreCovoiturageFestivalDto() {
        OffreCovoiturage o1 = initData.createOffreCovoiturageTest();
        OffreCovoiturage o2 = initData.createOffreCovoiturageTest();
        OffreCovoiturage o3 = initData.createOffreCovoiturageTest();

        List<OffreCovoiturageFestivalDto> o = FestivalResponseDto.createOffreCovoiturageFestivalDtos(List.of(o1,o2,o3));
        assertNotNull(o);
        assertEquals(3, o.size());
    }

    @Test
    void FestivalResponseDto_CreateFestivalResponseDtoFromFestivalWithoutOfrreCovoiturage_ReturnFestivalResponseDto() {
        Festival festival = initData.createFestivalTest(1L);

        FestivalResponseDto festivalResponseDtoWithoutOfrreCovoiturage = FestivalResponseDto.createFestivalResponseDtoFromFestival(festival,List.of(), 10 );
        assertNotNull(festivalResponseDtoWithoutOfrreCovoiturage);
        assertEquals(1L, festivalResponseDtoWithoutOfrreCovoiturage.getId());
        assertTrue(festivalResponseDtoWithoutOfrreCovoiturage.getOffreCovoiturages().isEmpty());
    }

    @Test
    void FestivalResponseDto_CreateFestivalResponseDtoFromFestival_ReturnFestivalResponseDto() {
        Festival festival = initData.createFestivalTest(1L);
        OffreCovoiturage o1 = initData.createOffreCovoiturageTest(1L, 3);
        OffreCovoiturage o2 = initData.createOffreCovoiturageTest(1L,2);
        OffreCovoiturage o3 = initData.createOffreCovoiturageTest(1L, 2);

        List<OffreCovoiturageFestivalDto> ofDTO = FestivalResponseDto.createOffreCovoiturageFestivalDtos(List.of(o1,o2,o3));

        FestivalResponseDto festivalResponseDtoWitOfrreCovoiturage = FestivalResponseDto.createFestivalResponseDtoFromFestival(festival,ofDTO, 10 );
        assertNotNull(festivalResponseDtoWitOfrreCovoiturage);
        assertEquals(1L, festivalResponseDtoWitOfrreCovoiturage.getId());
        assertEquals(3,festivalResponseDtoWitOfrreCovoiturage.getOffreCovoiturages().size());
    }

    @Test
    void FestivalResponseDto_CreateOffreCovoiturageFestivalDtos_ReturnFestivalResponseDto() {
        Panier panier = initData.createPanierTest(1L,EMAIL_FESTIVALIER, StatutPanier.EN_COURS);


        Article article = initData.createArticleTest(2, panier, 1L, 4);

        FestivalResponseDto festivalResponseDto = FestivalResponseDto.createFestivalResponseDtoFromArticle(article);

        assertNotNull(festivalResponseDto);
        assertNotNull(festivalResponseDto.getOffreCovoiturages());
        assertEquals(4, festivalResponseDto.getNombrePlaceOffreCovoiturage());
    }
}