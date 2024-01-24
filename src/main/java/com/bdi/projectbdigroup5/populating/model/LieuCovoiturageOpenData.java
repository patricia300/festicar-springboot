package com.bdi.projectbdigroup5.populating.model;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;
import lombok.Data;

@Data
@ExcelSheet("LieuxCovoiturage")
public class LieuCovoiturageOpenData {
    @ExcelCellName("idLieu")
    private String idLieu;

    @ExcelCellName("nomLieu")
    private String nomLieu;

    @ExcelCellName("adresseLieu")
    private String adresseLieu;

    @ExcelCellName("communeLieu")
    private String communeLieu;

    @ExcelCellName("codeINSEELieu")
    private String codeINSEELieu;

    @ExcelCellName("typeLieu")
    private String typeLieu;

    @ExcelCellName("longitude")
    private float longitude;

    @ExcelCellName("latitude")
    private float latitude;



}
