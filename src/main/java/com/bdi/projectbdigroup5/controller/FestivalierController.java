package com.bdi.projectbdigroup5.controller;

import com.bdi.projectbdigroup5.exception.NotFoundException;
import com.bdi.projectbdigroup5.model.Festivalier;
import com.bdi.projectbdigroup5.service.FestivalierService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/festivalier")
public class FestivalierController {
    private FestivalierService festivalierService;

    @PostMapping
    public Festivalier createFestivalier(Festivalier festivalier){
        return festivalierService.createFestivalier(festivalier);
    }

    @GetMapping
    public Festivalier getFestivalier(@RequestParam String email){
        return festivalierService.findById(email)
            .orElseThrow(
                () -> new NotFoundException("Festivalier non trouvé"));
    }
}
