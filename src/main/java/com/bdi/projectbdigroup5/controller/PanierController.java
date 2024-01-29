package com.bdi.projectbdigroup5.controller;

import com.bdi.projectbdigroup5.dto.PanierRequestBodyDto;
import com.bdi.projectbdigroup5.model.Panier;
import com.bdi.projectbdigroup5.service.PanierService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class PanierController {
    private PanierService panierService;
    @GetMapping("/paniers")
    public Iterable<Panier> getAllFestivalierPanier(@RequestParam String email) {
        return this.panierService.getPanierByFestivalierEmail(email);
    }

    @PostMapping("/panier")
    public Panier createPanier(@RequestBody PanierRequestBodyDto panierRequestBodyDto)
    {
        return panierService.savePanierFestivalier(panierRequestBodyDto);
    }

    @PutMapping("/panier/payer")
    public Panier createPanier(@RequestParam Long id)
    {
        return panierService.updatePanierStatusToPayed(id);
    }

    @GetMapping("/panier")
    public Panier getCurrentPanier(@RequestParam String email)
    {
        return this.panierService.getCurrentPanier(email);
    }
}
