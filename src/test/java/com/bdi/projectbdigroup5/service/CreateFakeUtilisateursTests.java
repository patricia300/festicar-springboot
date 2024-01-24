package com.bdi.projectbdigroup5.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import com.bdi.projectbdigroup5.model.Festivalier;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CreateFakeUtilisateursTests {
/* 
    @Autowired
    private UtilisateurFaker utilisateurFaker;
    @Autowired
    private FestivalierService festivalierService;

    @Test
    public void testThatCreatesFestivaliers(){
        ArrayList<Festivalier> festivaliersCreated = (ArrayList<Festivalier>)utilisateurFaker.createFakeFestivaliers();
        int compteur =0;
        while (compteur<= festivaliersCreated.size()) {
            Festivalier festivalier = festivaliersCreated.get(compteur);
            Optional<Festivalier> result = festivalierService.findById(festivalier.getEmail());
            assertTrue(result.isPresent());
            assertEquals(result.get(), festivalier);
        }
    } */
}

