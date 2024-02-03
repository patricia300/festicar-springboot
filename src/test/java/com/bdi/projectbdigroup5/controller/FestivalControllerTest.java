package com.bdi.projectbdigroup5.controller;

import com.bdi.projectbdigroup5.InitData;
import com.bdi.projectbdigroup5.property.PageableProperties;
import com.bdi.projectbdigroup5.service.FestivalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(controllers = FestivalController.class)
class FestivalControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InitData initData;

    @MockBean
    private FestivalService festivalService;

    @MockBean
    private PageableProperties pageableProperties;

    @BeforeEach
    public void init() {
        for (int i = 0; i < 10; i++) {
            initData.createFestivalTest();
        }

        initData.createFestivalTest(1L);
        initData.createFestivalTest("38100");
    }

    @Test
    void FestivalController_getAllFestivals_ReturnOkStatus() throws Exception {
        mockMvc.perform(get("/festivals"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    void FestivalController_getOneFestival_ReturnOkStatus() throws Exception {
        mockMvc.perform(get("/festivals/1"))
                .andExpect(status().isOk());
    }

    @Test
    void FestivalController_getOneFestival_BadRequestStatus() throws Exception {
        mockMvc.perform(get("/festivals/1L"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void FestivalController_getAllFestivalsByName_ReturnOkStatus() throws Exception {
        mockMvc.perform(get("/festivals/by-nom")
                        .param("nom", "vagamondes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));

    }

    @Test
    void FestivalController_getAllFestivalsByName_ReturnBadRequestStatus() throws Exception {
        mockMvc.perform(get("/festivals/by-nom"))
                .andExpect(status().isBadRequest());

    }

    @Test
    void FestivalController_getAllFestivalsByCommuneCodeInsee_ReturnOkStatus() throws Exception {
        mockMvc.perform(get("/festivals/by-commune")
                        .param("communeCodeInsee", "38100"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    void FestivalController_getAllFestivalsByCommuneCodeInsee_ReturnBadRequestStatus() throws Exception {
        mockMvc.perform(get("/festivals/by-commune"))
                .andExpect(status().isBadRequest());

    }

    @Test
    void FestivalController_getAllFestivalsByDomaine_ReturnOkStatus() throws Exception {
        mockMvc.perform(get("/festivals/by-domaine")
                        .param("domaine", "festival"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    void FestivalController_getAllFestivalsByDomaine_ReturnBadRequestStatus() throws Exception {
        mockMvc.perform(get("/festivals/by-domaine"))
                .andExpect(status().isBadRequest());

    }

    @Test
    void FestivalController_getAllFestivalsByDate_ReturnOkStatus() throws Exception {
        mockMvc.perform(get("/festivals/by-date")
                        .param("dateDebut", "02/02/2024"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    void FestivalController_getAllFestivalsByDate_ReturnBadRequestStatus() throws Exception {
        mockMvc.perform(get("/festivals/by-date"))
                .andExpect(status().isBadRequest());

    }
}
