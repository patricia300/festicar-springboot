package com.bdi.projectbdigroup5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bdi.projectbdigroup5.model.Festival;
import com.bdi.projectbdigroup5.model.Festivalier;
import com.bdi.projectbdigroup5.service.UtilisateurFaker;

import java.util.*;

@RestController
@RequestMapping("/fakeUtilisateur")
public class FakeUtilisateur {

    @Autowired
    public UtilisateurFaker utilisateurFaker;
    
    @GetMapping("/Festivalier")
    public List<Festivalier> festivaliersList(@RequestParam int nombreFestivaliers) {
        List<Festivalier> festivaliers = new ArrayList<Festivalier>();
        Iterable<Festivalier> iterableFestivalier = utilisateurFaker.createFakeFestivaliers(nombreFestivaliers); 
        for(Festivalier festivalier : iterableFestivalier){
            festivaliers.add(festivalier);
        }
        return festivaliers;
    }
}
