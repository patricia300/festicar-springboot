package com.bdi.projectbdigroup5.controller;

import com.bdi.projectbdigroup5.dto.FestivalResponseDto;
import com.bdi.projectbdigroup5.exception.NotFoundException;
import com.bdi.projectbdigroup5.property.PageableProperties;
import com.bdi.projectbdigroup5.model.Festival;
import com.bdi.projectbdigroup5.service.FestivalService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Iterable<FestivalResponseDto>> getAllFestivals(
            @RequestParam(required = false) Integer numeroPage,
            @RequestParam(required = false) Integer taillePage,
            @RequestParam(required = false) String tri)
    {
        try {
            Pageable festivalPage = pageableProperties.createPageable(numeroPage, taillePage, "dateDebut", tri);
            return ResponseEntity.ok(this.festivalService.getAllFestivalPerPage(festivalPage));
        } catch (Exception e) {
            return ResponseEntity
                    .of(ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage()))
                    .build();
        }
    }

    @GetMapping("/by-commune")
    public ResponseEntity<Iterable<FestivalResponseDto>> getAllFestivalsByCommune(
            @RequestParam String communeCodeInsee) {
        try {
            return ResponseEntity.ok(this.festivalService.getAllFestivalByCommune(communeCodeInsee));
        } catch (Exception e) {
            return ResponseEntity
                    .of(ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage()))
                    .build();
        }
    }

    @GetMapping("/by-date")
    public ResponseEntity<Iterable<FestivalResponseDto>> getAllFestivalsByDate(
            @RequestParam String dateDebut
    ) {
        try {
            return ResponseEntity.ok(this.festivalService.getAllFestivalByDateDebut(dateDebut));
        } catch (Exception e) {
            return ResponseEntity
                    .of(ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage()))
                    .build();
        }
    }

    @GetMapping("/filtre")
    public  ResponseEntity<Iterable<FestivalResponseDto>> getAllFestivalByFiltre(
            @RequestParam(required = false) Integer numeroPage,
            @RequestParam(required = false) Integer taillePage,
            @RequestParam(required = false) String tri,
            @RequestParam(required = false) String triPar,
            @RequestParam(required = false) String dateDebut,
            @RequestParam String communeCodeInsee,
            @RequestParam String sousDomaine,
            @RequestParam String domainePrincipal
    ) {
        try {
            String sortBy = triPar != null ? triPar : "dateDebut";
            Pageable festivalPage = pageableProperties.createPageable(numeroPage, taillePage, sortBy, tri);
            return ResponseEntity.ok(this.festivalService.getAllFestivalByFilter(
                    dateDebut,
                    communeCodeInsee,
                    sousDomaine,
                    domainePrincipal,
                    festivalPage)
            );
        } catch (Exception e) {
            return ResponseEntity
                    .of(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage()))
                    .build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<FestivalResponseDto> getOneFestival(@PathVariable Long id){
        try {
            return ResponseEntity.ok(this.festivalService.getOneFestival(id));
        }
        catch (NotFoundException notFoundException) {
            return ResponseEntity
                    .of(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, notFoundException.getMessage()))
                    .build();
        }
    }

    @GetMapping("/by-nom")
    public ResponseEntity<List<FestivalResponseDto>> getAllFestivalsByName(
            @RequestParam String nom){
        try {
            return ResponseEntity.ok(this.festivalService.getAllFestivalsByName(nom));
        } catch (Exception e) {
            return ResponseEntity
                    .of(ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage()))
                    .build();
        }
    }

    @GetMapping("/by-domaine")
    public ResponseEntity<List<FestivalResponseDto>> getAllFestivalByDomaine(
            @RequestParam String domaine
    ) {
        try {
            return ResponseEntity.ok(this.festivalService.getAllFestivalsByDomaine(domaine));
        } catch (Exception e) {
            return ResponseEntity
                    .of(ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage()))
                    .build();
        }
    }
}
