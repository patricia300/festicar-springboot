package com.bdi.projectbdigroup5.controller;

import com.bdi.projectbdigroup5.InitData;
import com.bdi.projectbdigroup5.property.PageableProperties;
import com.bdi.projectbdigroup5.service.FestivalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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
    public void init()
    {
        for (int i =0; i< 10; i++ ){
            initData.createFestivalTest((long) i);
        }
    }

    @Test
    void getAllFestivals() throws Exception {
        for (int i =0; i< 10; i++ ){
            initData.createFestivalTest((long) i);
        }
        mockMvc.perform(get("/festivals"))
                .andExpect(status().isOk());
    }

    @Test
    void getOneFestival() throws Exception {
        mockMvc.perform(get("/festivals/1776"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllFestivalsByName() throws Exception {
        mockMvc.perform(get("/festivals/by-nom")
                        .param("nom", "vagamondes"))
                .andExpect(status().isOk());

    }
}