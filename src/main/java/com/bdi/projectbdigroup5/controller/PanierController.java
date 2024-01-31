package com.bdi.projectbdigroup5.controller;

import com.bdi.projectbdigroup5.dto.ErreurPaiementPanierResponseDto;
import com.bdi.projectbdigroup5.dto.PanierRequestBodyDto;
import com.bdi.projectbdigroup5.dto.PanierResponseDto;
import com.bdi.projectbdigroup5.exception.NombrePassFestivalInsuffisantException;
import com.bdi.projectbdigroup5.exception.NombrePlaceCovoiturageInsuffisantException;
import com.bdi.projectbdigroup5.exception.NotFoundException;
import com.bdi.projectbdigroup5.exception.QuantiteZeroException;
import com.bdi.projectbdigroup5.model.ErreurPaiementClass;
import com.bdi.projectbdigroup5.service.PanierService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
public class PanierController {
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
    public ResponseEntity<? extends Object> createPanier(@RequestBody PanierRequestBodyDto panierRequestBodyDto)
    {
        try {
            return ResponseEntity.ok(panierService.savePanierFestivalier(panierRequestBodyDto));
        }
        catch (NotFoundException notFoundException) {
            return ResponseEntity
                    .of(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, notFoundException.getMessage()))
                    .build();
        }
        catch (NombrePassFestivalInsuffisantException nombrePassFestivalInsuffisantException) {
            return ResponseEntity.badRequest().body(new ErreurPaiementPanierResponseDto(
                    nombrePassFestivalInsuffisantException.getIdFestival(),
                    nombrePassFestivalInsuffisantException.getNbPassDisponible(),
                    ErreurPaiementClass.FESTIVAL
            ));
        }
        catch (NombrePlaceCovoiturageInsuffisantException nombrePlaceCovoiturageInsuffisantException)
        {
            return ResponseEntity.badRequest().body(new ErreurPaiementPanierResponseDto(
                    nombrePlaceCovoiturageInsuffisantException.getIdOffreCovoiturage(),
                    nombrePlaceCovoiturageInsuffisantException.getNbPlaceDisponible(),
                    ErreurPaiementClass.OFFRE_COVOITURAGE
            ));
        }
        catch (QuantiteZeroException quantiteZeroException) {
            return ResponseEntity.badRequest().body(new ErreurPaiementPanierResponseDto(
                    quantiteZeroException.getId(),
                    0,
                    quantiteZeroException.getClassType()
            ));
        }

    }

    @PutMapping("/panier/payer")
    public ResponseEntity<? extends Object> payerPanier(@RequestParam Long id)
    {
        try {
            return ResponseEntity.ok(this.panierService.updatePanierStatusToPayed(id));
        }
        catch (NombrePassFestivalInsuffisantException nombrePassFestivalInsuffisantException) {
            return ResponseEntity.badRequest().body(new ErreurPaiementPanierResponseDto(
                    nombrePassFestivalInsuffisantException.getIdFestival(),
                    nombrePassFestivalInsuffisantException.getNbPassDisponible(),
                    ErreurPaiementClass.FESTIVAL
                    ));
        }
        catch (NombrePlaceCovoiturageInsuffisantException nombrePlaceCovoiturageInsuffisantException)
        {
            return ResponseEntity.badRequest().body(new ErreurPaiementPanierResponseDto(
                    nombrePlaceCovoiturageInsuffisantException.getIdOffreCovoiturage(),
                    nombrePlaceCovoiturageInsuffisantException.getNbPlaceDisponible(),
                    ErreurPaiementClass.OFFRE_COVOITURAGE
                    ));
        }
        catch (QuantiteZeroException quantiteZeroException) {
            return ResponseEntity.badRequest().body(new ErreurPaiementPanierResponseDto(
                    quantiteZeroException.getId(),
                    0,
                    quantiteZeroException.getClassType()
                    ));
        }
    }

    @GetMapping("/panier")
    public ResponseEntity<? extends Object> getCurrentPanier(@RequestParam String email)
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
}
