package com.bdi.projectbdigroup5.controller;

import com.bdi.projectbdigroup5.model.Organisateur;
import com.bdi.projectbdigroup5.service.OrganisateurService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("organisateurs")
public class OrganisateurController {
    private OrganisateurService organisateurService;

    @PostMapping
    public Organisateur createOrganisateur(@RequestBody Organisateur organisateur) {
        return organisateurService.createOrganisateur(organisateur);
    }

    @GetMapping
    public Iterable<Organisateur> getAllOrganisateurs() {
        return organisateurService.findAllOrganisateurs();
    }
}
