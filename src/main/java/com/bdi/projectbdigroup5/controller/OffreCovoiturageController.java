package com.bdi.projectbdigroup5.controller;

import com.bdi.projectbdigroup5.configuration.PageableConfiguration;
import com.bdi.projectbdigroup5.model.OffreCovoiturage;
import com.bdi.projectbdigroup5.service.OffreCovoiturageService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
public class OffreCovoiturageController {
    private OffreCovoiturageService offreCovoiturageService;
    private PageableConfiguration pageableConfiguration;

    @GetMapping("/offre-covoiturages")
    public Iterable<OffreCovoiturage> getAllCovoiturages(
            @RequestParam(required = false) Integer numeroPage,
            @RequestParam(required = false) Integer taillePage ,
            @RequestParam(required = false) String tri)
    {

        Pageable offreCovoituragePage =  pageableConfiguration.createPageable(numeroPage, taillePage,"dateOffre", tri);

        return this.offreCovoiturageService.getAllOffreCovoiturages(offreCovoituragePage);
    }


}
