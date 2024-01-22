package com.bdi.projectbdigroup5.model;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;
import lombok.Data;

import java.util.Date;

@Data
@ExcelSheet("Festivals")
public class FestivalOpenData {
    @ExcelCellName("nomManifestation")
    private String nomManifestation;

    @ExcelCellName("region")
    private String region;

    @ExcelCellName("domaine")
    private String domaine;

    @ExcelCellName("sousDomaine")
    private String sousDomaine;

    @ExcelCellName("departement")
    private String departement;

    @ExcelCellName("moisDebut")
    private String moisDebut;

    @ExcelCellName("siteWEB")
    private String siteWEB;

    @ExcelCellName("lieuPrincipal")
    private String lieuPrincipal;

    @ExcelCellName("codePostal")
    private String codePostal;

    @ExcelCellName("codeINSEE")
    private String codeINSEE;

    @ExcelCellName("longitude")
    private float longitude;

    @ExcelCellName("latitude")
    private float latitude;

    @ExcelCellName("communeINSEE")
    private String communeINSEE;

    @ExcelCellName("departement2")
    private String departement2;

    @ExcelCellName("dateDebut")
    private Date dateDebut;

    @ExcelCellName("dateFin")
    private Date dateFin;

    @ExcelCellName("tarifPass")
    private Float tarifPass;
}
