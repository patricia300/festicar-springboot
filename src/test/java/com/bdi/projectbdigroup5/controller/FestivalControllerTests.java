package com.bdi.projectbdigroup5.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;


@WebMvcTest(FestivalController.class)
@AutoConfigureRestDocs("target/generated-snippets")
public class FestivalControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void listFestival() {

    }

}
