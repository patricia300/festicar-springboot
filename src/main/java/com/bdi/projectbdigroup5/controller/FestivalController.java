package com.bdi.projectbdigroup5.controller;

import com.bdi.projectbdigroup5.model.Festival;
import com.bdi.projectbdigroup5.service.FestivalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.bdi.projectbdigroup5.pageable.FestivalPageable.NUMERO_PAGE_PAR_DEFAUT;
import static com.bdi.projectbdigroup5.pageable.FestivalPageable.TAILLE_ELEMENT_DANS_LA_PAGE_PAR_DEFAUT;

@RestController
public class FestivalController {
    @Autowired
    private FestivalService festivalService;

    @GetMapping("/festivals")
    public List<Festival> getAllFestivals(@RequestParam(required = false) Integer numeroPage, @RequestParam(required = false) Integer taillePage, @RequestParam(required = false) String tri ) {
        Pageable festivalPage = PageRequest.of(
                numeroPage != null ? numeroPage : NUMERO_PAGE_PAR_DEFAUT,
                taillePage != null ? taillePage : TAILLE_ELEMENT_DANS_LA_PAGE_PAR_DEFAUT,
                tri != null ? (tri.equalsIgnoreCase("asc") ? Sort.by("tarifPass").ascending() : Sort.by("tarifPass").descending() )
                        : Sort.by("tarifPass").ascending()
        );
        return festivalService.getAllFestivalPerPage(festivalPage);
    }
}