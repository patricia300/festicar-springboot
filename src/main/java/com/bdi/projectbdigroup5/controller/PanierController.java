package com.bdi.projectbdigroup5.controller;

import com.bdi.projectbdigroup5.dto.PanierPartielPaiementRequestDto;
import com.bdi.projectbdigroup5.dto.PanierRequestBodyDto;
import com.bdi.projectbdigroup5.dto.PanierResponseDto;
import com.bdi.projectbdigroup5.exception.NotFoundException;
import com.bdi.projectbdigroup5.service.PanierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class PanierController {
    @Autowired
    private PanierService panierService;

    @GetMapping("/paniers")
    public ResponseEntity<Iterable<PanierResponseDto>> getAllFestivalierPanier(@RequestParam String email) {
        try {
            return ResponseEntity.ok(this.panierService.getPanierByFestivalierEmail(email));
        }
        catch (NotFoundException notFoundException) {
            return ResponseEntity
                    .of(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, notFoundException.getMessage()))
                    .build();
        }
    }

    @PostMapping("/panier")
    public ResponseEntity<PanierResponseDto> createPanier(@RequestBody PanierRequestBodyDto panierRequestBodyDto)
    {
        try {
            return ResponseEntity.ok(panierService.savePanierFestivalier(panierRequestBodyDto));
        }
        catch (NotFoundException notFoundException) {
            return ResponseEntity
                    .of(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, notFoundException.getMessage()))
                    .build();
        }
    }

    @PutMapping("/panier/payer")
    public ResponseEntity<PanierResponseDto> payerPanier(@RequestParam Long id)
    {
        try {
            return ResponseEntity.ok(this.panierService.updatePanierStatusToPayed(id));
        }
        catch (NotFoundException notFoundException) {
            return ResponseEntity
                    .of(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, notFoundException.getMessage()))
                    .build();
        }
    }

    @GetMapping("/panier")
    public ResponseEntity<Optional<PanierResponseDto>> getCurrentPanier(@RequestParam String email)
    {
        try {
            return ResponseEntity.ok(this.panierService.getCurrentPanier(email));
        }
        catch (NotFoundException notFoundException) {
            return ResponseEntity
                    .of(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, notFoundException.getMessage()))
                    .build();
        }

    }

    @PutMapping("/panier/payer/partiel")
    public ResponseEntity<PanierResponseDto> payerPanierPartiel(@RequestBody PanierPartielPaiementRequestDto panierPartielPaiementRequestDto)
    {
        try {
             return ResponseEntity.ok(this.panierService.updatePanierStatutPatchPaid(panierPartielPaiementRequestDto));
        }
        catch (NotFoundException notFoundException) {
            return ResponseEntity
                    .of(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, notFoundException.getMessage()))
                    .build();
        }
    }
}
