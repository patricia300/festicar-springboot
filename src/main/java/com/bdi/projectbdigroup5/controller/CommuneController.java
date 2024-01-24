package com.bdi.projectbdigroup5.controller;

import com.bdi.projectbdigroup5.model.Commune;
import com.bdi.projectbdigroup5.property.PageableProperties;
import com.bdi.projectbdigroup5.service.CommuneService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CommuneController {

    private CommuneService communeService;
    private PageableProperties pageableProperties;

    @GetMapping("/communes")
    public Iterable<Commune> getAllCommune(
            @RequestParam(required = false) Integer numeroPage,
            @RequestParam(required = false) Integer taillePage,
            @RequestParam(required = false) String order
    ){
        Pageable communePageable = pageableProperties.createPageable(numeroPage, taillePage, "nom", order);
        return this.communeService.findAllCommunes(communePageable);
    }

    @GetMapping("/communes/nom")
    public Iterable<String> getAllCommuneNom(
            @RequestParam(required = false) Integer numeroPage,
            @RequestParam(required = false) Integer taillePage,
            @RequestParam(required = false) String order
    ){
        Pageable communeNomPageable = pageableProperties.createPageable(numeroPage, taillePage, "nom", order);
        return this.communeService.findAllCommunesNom(communeNomPageable);
    }

}
