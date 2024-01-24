package com.bdi.projectbdigroup5.controller;

import com.bdi.projectbdigroup5.property.PageableProperties;
import com.bdi.projectbdigroup5.model.Festival;
import com.bdi.projectbdigroup5.service.FestivalService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class FestivalController {
    private FestivalService festivalService;
    private PageableProperties pageableProperties;

    @GetMapping("/festivals")
    public Iterable<Festival> getAllFestivals(
            @RequestParam(required = false) Integer numeroPage,
            @RequestParam(required = false) Integer taillePage,
            @RequestParam(required = false) String tri ) {
        Pageable festivalPage =  pageableProperties.createPageable(numeroPage, taillePage, "tarifPass", tri);
        return this.festivalService.getAllFestivalPerPage(festivalPage);
    }

    @GetMapping("/festivals/by-commune")
    public Iterable<Festival> getAllFestivalsByCommune(
            @RequestParam(required = false) Integer numeroPage,
            @RequestParam(required = false) Integer taillePage,
            @RequestParam String commune){
        Pageable festivalByCommunePage = pageableProperties.createPageable(numeroPage, taillePage);
        return this.festivalService.getAllFestivalByCommune(commune,festivalByCommunePage);
    }

    @GetMapping("/festivals/by-date")
    public Iterable<Festival> getAllFestivalsByDate(
            @RequestParam(required = false) Integer numeroPage,
            @RequestParam(required = false) Integer taillePage,
            @RequestParam(required = false) String dateDebut,
            @RequestParam(required = false) String dateFin
    ) {
        Pageable festivalByDatePage = pageableProperties.createPageable(numeroPage, taillePage);
        return this.festivalService.getAllFestivalByDateDebutOrDateFin(dateDebut, dateFin, festivalByDatePage);
    }
}