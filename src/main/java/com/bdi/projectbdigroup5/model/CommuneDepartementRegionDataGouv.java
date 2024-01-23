package com.bdi.projectbdigroup5.model;

import com.poiji.annotation.ExcelCellName;
import lombok.Data;

@Data
public class CommuneDepartementRegionDataGouv {
    @ExcelCellName("code_commune_INSEE")
    private String codeCommuneINSEE;
    @ExcelCellName("nom_commune_complet")
    private String nomCommuneComplet;
    @ExcelCellName("code_postal")
    private String codePostal;
    @ExcelCellName("latitude")
    private float latitude;
    @ExcelCellName("longitude")
    private float longitude;

    @ExcelCellName("code_departement")
    private String codeDepartement;
    @ExcelCellName("nom_departement")
    private String nomDepartement;

    @ExcelCellName("nom_region")
    private String nomRegion;



}
