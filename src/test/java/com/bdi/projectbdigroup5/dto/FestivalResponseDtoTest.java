package com.bdi.projectbdigroup5.dto;

import com.bdi.projectbdigroup5.InitData;
import com.bdi.projectbdigroup5.model.OffreCovoiturage;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class FestivalResponseDtoTest {
    @Autowired
    private InitData initData;

    @Test
    void createFestivalResponseDtoFromArticle() {
        OffreCovoiturage o1 = initData.createOffreCovoiturageTest();
        OffreCovoiturage o2 = initData.createOffreCovoiturageTest();
        OffreCovoiturage o3 = initData.createOffreCovoiturageTest();


        List<OffreCovoiturageFestivalDto> o = FestivalResponseDto.createOffreCovoiturageFestivalDtos(List.of(o1,o2,o3));
        assertNotNull(o);
        assertEquals(3, o.size());
    }

    @Test
    void createFestivalResponseDtoFromFestival() {


    }

    @Test
    void createOffreCovoiturageFestivalDtos() {
    }
}