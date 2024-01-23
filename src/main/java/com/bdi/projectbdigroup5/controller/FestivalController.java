package com.bdi.projectbdigroup5.controller;

import com.bdi.projectbdigroup5.configuration.PageableConfiguration;
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
    private PageableConfiguration pageableConfiguration;

    @GetMapping("/festivals")
    public Iterable<Festival> getAllFestivals(@RequestParam(required = false) Integer numeroPage, @RequestParam(required = false) Integer taillePage, @RequestParam(required = false) String tri ) {
        Pageable festivalPage =  pageableConfiguration.createPageable(numeroPage, taillePage, "tarifPass", tri);

        return festivalService.getAllFestivalPerPage(festivalPage);
    }
}