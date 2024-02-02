package com.bdi.projectbdigroup5.controller;

import org.springframework.web.bind.annotation.RestController;

import com.bdi.projectbdigroup5.exception.FestivalierNotFoundException;
import com.bdi.projectbdigroup5.model.Festivalier;
import com.bdi.projectbdigroup5.service.FestivalierService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@AllArgsConstructor
@RequestMapping("/festivalier")
public class FestivalierController {

    private FestivalierService festivalierService;

    @PostMapping
    public Festivalier createFestivalier(@RequestBody Festivalier festivalier){
        return festivalierService.createFestivalier(festivalier);
    }

    @GetMapping
    public Festivalier getFestivalier(@RequestParam String email){
        return festivalierService.findById(email).orElseThrow(() -> new FestivalierNotFoundException("Festivalier non trouvé"));
    }

    @GetMapping("/by-token")
    public Festivalier getFestivalierByToken(@RequestParam String token){
        return festivalierService.findByToken(token).orElseThrow(() -> new FestivalierNotFoundException("Festivalier non trouvé"));
    }
    
    
}
