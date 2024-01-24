package com.bdi.projectbdigroup5.controller;

import com.bdi.projectbdigroup5.property.PageableProperties;
import com.bdi.projectbdigroup5.model.Festival;
import com.bdi.projectbdigroup5.service.FestivalService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("festivals")
public class FestivalController {
    private FestivalService festivalService;
    private PageableProperties pageableProperties;

    @PostMapping
    public Festival createFestival(@RequestBody Festival festival) {
        return festivalService.createFestival(festival);
    }

    @GetMapping
    public Iterable<Festival> getAllFestivals(
            @RequestParam(required = false) Integer numeroPage,
            @RequestParam(required = false) Integer taillePage,
            @RequestParam(required = false) String tri) {
        Pageable festivalPage = pageableProperties.createPageable(numeroPage, taillePage, "tarifPass", tri);
        return this.festivalService.getAllFestivalPerPage(festivalPage);
    }

    @GetMapping("by-commune")
    public Iterable<Festival> getAllFestivalsByCommune(
            @RequestParam(required = false) Integer numeroPage,
            @RequestParam(required = false) Integer taillePage,
            @RequestParam String commune) {
        Pageable festivalByCommunePage = pageableProperties.createPageable(numeroPage, taillePage);
        return this.festivalService.getAllFestivalByCommune(commune, festivalByCommunePage);
    }

    @GetMapping("by-date")
    public Iterable<Festival> getAllFestivalsByDate(
            @RequestParam(required = false) Integer numeroPage,
            @RequestParam(required = false) Integer taillePage,
            @RequestParam(required = false) String dateDebut,
            @RequestParam(required = false) String dateFin) {
        Pageable festivalByDatePage = pageableProperties.createPageable(numeroPage, taillePage);
        return this.festivalService.getAllFestivalByDateDebutOrDateFin(dateDebut, dateFin, festivalByDatePage);
    }
}
