package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.InitData;
import com.bdi.projectbdigroup5.dto.FestivalResponseDto;
import com.bdi.projectbdigroup5.model.Festival;
import com.bdi.projectbdigroup5.model.Organisateur;
import com.bdi.projectbdigroup5.property.PageableProperties;
import com.bdi.projectbdigroup5.repository.FestivalRepository;
import org.assertj.core.internal.Iterables;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class FestivalServiceTest {
    @Autowired
    private FestivalService festivalService;

    @Autowired
    private InitData initData;

    @Autowired
    private PageableProperties pageableProperties;



    @BeforeEach
    void setUp() {
        for (int i = 0; i< 300; i++)
        {
            initData.createOffreCovoiturageTest();
        }
        initData.createOffreCovoiturageTest( 1L);
        initData.createOffreCovoiturageTest( 1L);
        initData.createOffreCovoiturageTest( 3L);
        initData.createOffreCovoiturageTest( 4L);
    }

    @Test
    void createFestival() {

        Festival festival = new Festival();
        festival.setOrganisateur(initData.createOrganisateurTest());
        festival.setCommune(initData.createCommuneTest());
        festival.setId(5L);
        festival.setSiteWeb("www.les-grimaldines.com");
        festival.setSousDomaine(initData.createSousDomaineTest());
        festival.setNombrePass(84);
        festival.setTarifPass(12.0F);
        festival.setNom("LES GRIMALDINES");
        festival.setDateDebut(new Date("2024/07/08"));
        festival.setDateFin(new Date("2024/08/01"));

        Festival festivalCreated = this.festivalService.createFestival(festival);
        initData.createOffreCovoiturageTest(5L);

        assertEquals(5L, festivalCreated.getId());
        assertNotNull(festivalCreated);
    }

    @Test
    void getAllFestivalPerPage() {
        Pageable pageable = pageableProperties.createPageable(0, 20);
        List<FestivalResponseDto> festivals = this.festivalService.getAllFestivalPerPage(pageable);

        Pageable pageable1 = pageableProperties.createPageable(0, 40);
        List<FestivalResponseDto> festivals1 = this.festivalService.getAllFestivalPerPage(pageable1);


        assertEquals(20,festivals.size());
        assertTrue(festivals.get(0).getOffreCovoiturages().isEmpty());

        assertEquals(40,festivals1.size());
        assertTrue(festivals1.get(9).getOffreCovoiturages().isEmpty());
    }

    @Test
    void getAllFestivalByCommune() {
        String codeInsee = "38109";
        initData.createFestivalTest(codeInsee);
        initData.createFestivalTest(codeInsee);
        initData.createFestivalTest(codeInsee);

        List<FestivalResponseDto>  festivals = this.festivalService.getAllFestivalByCommune(codeInsee);

        assertEquals(3, festivals.size());
        assertNotNull(festivals);
        assertTrue(festivals.get(0).getOffreCovoiturages().isEmpty());
    }

    @Test
    void getAllFestivalByDateDebut() {
        String date = "2024/07/08";

        List<FestivalResponseDto> festivalResponseDtos = this.festivalService.getAllFestivalByDateDebut(date);

        assertNotNull(festivalResponseDtos);
        assertTrue(festivalResponseDtos.get(0).getOffreCovoiturages().isEmpty());
        assertEquals(new Date(date), festivalResponseDtos.get(0).getDateDebut());
    }

    @Test
    void getOneFestival() {
        FestivalResponseDto festival = this.festivalService.getOneFestival(1L);

        assertNotNull(festival);
        assertNotNull(festival.getOffreCovoiturages());
        assertNotEquals(0, festival.getOffreCovoiturages().size());
    }

    @Test
    void getAllFestivalsByName() {
        List<FestivalResponseDto> festivals = this.festivalService.getAllFestivalsByName("GRIMALDINES");

        assertNotNull(festivals);
        assertNotEquals(0, festivals.size());
        assertTrue(festivals.get(0).getOffreCovoiturages().isEmpty());
    }

    @Test
    void getAllFestivalsByDomaine() {
        initData.createFestivalWithNomDomaineTest("france malagasy", "jazz");
        initData.createFestivalWithNomDomaineTest("musique lorem", "actuelle malagasy");
        List<FestivalResponseDto> festivals = this.festivalService.getAllFestivalsByDomaine("maLagAsy");
        List<FestivalResponseDto> festivals1 = this.festivalService.getAllFestivalsByDomaine("jazZ");


        assertNotNull(festivals);
        assertNotEquals(0, festivals.size());
        assertEquals(2,festivals.size());
        assertTrue(festivals.get(0).getOffreCovoiturages().isEmpty());

        assertNotNull(festivals1);
        assertNotEquals(0, festivals1.size());
        assertEquals(1,festivals1.size());
        assertTrue(festivals1.get(0).getOffreCovoiturages().isEmpty());
    }

    @Test
    void findById() {
        Festival festival = this.festivalService.findById(1L);

        assertNotNull(festival);
        assertEquals(1L, festival.getId());
    }
}
