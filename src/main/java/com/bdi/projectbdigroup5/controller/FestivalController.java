package com.bdi.projectbdigroup5.controller;

import com.bdi.projectbdigroup5.dto.FestivalResponseDto;
import com.bdi.projectbdigroup5.property.PageableProperties;
import com.bdi.projectbdigroup5.model.Festival;
import com.bdi.projectbdigroup5.service.FestivalService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/festivals")
public class FestivalController {
    private FestivalService festivalService;
    private PageableProperties pageableProperties;

    @PostMapping
    public Festival createFestival(@RequestBody Festival festival) {
        return festivalService.createFestival(festival);
    }

    @GetMapping
    public Iterable<FestivalResponseDto> getAllFestivals(
            @RequestParam(required = false) Integer numeroPage,
            @RequestParam(required = false) Integer taillePage,
            @RequestParam(required = false) String tri) {
        Pageable festivalPage = pageableProperties.createPageable(numeroPage, taillePage, "dateDebut", tri);
        return this.festivalService.getAllFestivalPerPage(festivalPage);
    }

    @GetMapping("/by-commune")
    public Iterable<FestivalResponseDto> getAllFestivalsByCommune(
            @RequestParam(required = false) Integer numeroPage,
            @RequestParam(required = false) Integer taillePage,
            @RequestParam String commune) {
        Pageable festivalByCommunePage = pageableProperties.createPageable(numeroPage, taillePage);
        return this.festivalService.getAllFestivalByCommune(commune, festivalByCommunePage);
    }

    @GetMapping("/by-date")
    public Iterable<FestivalResponseDto> getAllFestivalsByDate(
            @RequestParam(required = false) Integer numeroPage,
            @RequestParam(required = false) Integer taillePage,
            @RequestParam(required = false) String dateDebut,
            @RequestParam(required = false) String dateFin
    ) {
        Pageable festivalByDatePage = pageableProperties.createPageable(numeroPage, taillePage);
        return this.festivalService.getAllFestivalByDateDebutOrDateFin(dateDebut, dateFin, festivalByDatePage);
    }

    @GetMapping("/filtre")
    public Iterable<FestivalResponseDto> getAllFestivalByFiltre(
            @RequestParam(required = false) Integer numeroPage,
            @RequestParam(required = false) Integer taillePage,
            @RequestParam(required = false) String tri,
            @RequestParam String triPar,
            @RequestParam(required = false) String dateDebut,
            @RequestParam String communeCodeInsee,
            @RequestParam String sousDomaine
    ) {
        Pageable festivalPage = pageableProperties.createPageable(numeroPage, taillePage,triPar, tri);
        return this.festivalService.getAllFestivalByFilter( dateDebut, communeCodeInsee, sousDomaine,festivalPage);
    }

    @GetMapping("/{id}")
    public FestivalResponseDto getOneFestival(@PathVariable Long id){
        return this.festivalService.getOneFestival(id);
    }

    @GetMapping("/by-nom")
    public List<FestivalResponseDto> getAllFestivalsByName(
            @RequestParam(required = false) Integer numeroPage,
            @RequestParam(required = false) Integer taillePage,
            @RequestParam String nom){
        Pageable pageable = pageableProperties.createPageable(numeroPage, taillePage);
        return this.festivalService.getAllFestivalsByName(nom, pageable);
    }
}
