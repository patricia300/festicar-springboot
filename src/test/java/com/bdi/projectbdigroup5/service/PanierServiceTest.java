package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.InitDataTest;
import com.bdi.projectbdigroup5.dto.ArticleRequestBodyDto;
import com.bdi.projectbdigroup5.dto.PanierRequestBodyDto;
import com.bdi.projectbdigroup5.dto.PanierResponseDto;
import com.bdi.projectbdigroup5.model.*;
import com.bdi.projectbdigroup5.populating.controller.Fakedata;
import com.bdi.projectbdigroup5.repository.*;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureDataJpa
class PanierServiceTest {
    @Autowired
    private PanierService panierService;

    @Autowired
    private InitDataTest initDataTest;

    @BeforeEach
    public void init()
    {
        initDataTest.createFestivalierTest("mohamed.rey@gmail.com");
        initDataTest.createPointPassageCovoiturageTest(1L);
        initDataTest.createPointPassageCovoiturageTest(2L);
        initDataTest.createPointPassageCovoiturageTest(3L);
        initDataTest.createPointPassageCovoiturageTest(4L);
    }

    @Test
    public void getCurrentPanier() {
        ArticleRequestBodyDto a1 = ArticleRequestBodyDto.builder()
                .idPointPassage(1L)
                .quantite(1)
                .build();

        ArticleRequestBodyDto a2 = ArticleRequestBodyDto.builder()
                .idPointPassage(2L)
                .quantite(2)
                .build();

        PanierRequestBodyDto requestBodyDto = PanierRequestBodyDto.builder()
                .emailFestivalier("mohamed.rey@gmail.com")
                .articles(List.of(a1,a2))
                .build();

        PanierResponseDto panierResponseDto = this.panierService.savePanierFestivalier(requestBodyDto);
        assertNotNull(panierResponseDto);
        assertNotNull(panierResponseDto.getArticles());
        assertEquals(2, panierResponseDto.getArticles().size());
        assertNotNull(panierResponseDto.getPanier());
    }
}