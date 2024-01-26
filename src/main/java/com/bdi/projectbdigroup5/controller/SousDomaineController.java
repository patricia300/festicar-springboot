package com.bdi.projectbdigroup5.controller;

import com.bdi.projectbdigroup5.model.SousDomaine;
import com.bdi.projectbdigroup5.service.SousDomaineService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("sous-domaines")
public class SousDomaineController {
    private SousDomaineService sousDomaineService;

    @GetMapping()
    public Iterable<SousDomaine> getAllSousDomaine() {
        return sousDomaineService.findAllSousDomaine();
    }
}
