package com.bdi.projectbdigroup5.controller;

import com.bdi.projectbdigroup5.model.Covoitureur;
import com.bdi.projectbdigroup5.service.CovoitureurService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("covoitureurs")
@AllArgsConstructor
public class CovoitureurController {
    private CovoitureurService covoitureurService;

    @PostMapping
    public Covoitureur createCovoitureur(@RequestBody Covoitureur covoitureur) {
        return covoitureurService.createCovoitureur(covoitureur);
    }

    @GetMapping
    public Iterable<Covoitureur> getAllCovoitureurs() {
        return covoitureurService.findAllCovoitureur();
    }
}
