package com.bdi.projectbdigroup5.dto;

import com.bdi.projectbdigroup5.model.ErreurPaiementClass;
import lombok.Getter;

@Getter
public class ErreurPaiementPanierResponseDto {
    private Long id;
    private int nbPassDisponible;
    private ErreurPaiementClass classType;
    private String message;
    public ErreurPaiementPanierResponseDto(Long id, int nbPassDisponible, ErreurPaiementClass classType)
    {
        this.id = id;
        this.nbPassDisponible = nbPassDisponible;
        this.classType = classType;

        this.message = this.classType + " a " + this.nbPassDisponible + " place disponible";
    }
}
