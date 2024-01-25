package com.bdi.projectbdigroup5.dto;

import com.bdi.projectbdigroup5.model.Covoitureur;
import com.bdi.projectbdigroup5.model.PointPassageCovoiturage;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
@Builder
public class OffreCovoiturageFestivalDto {
    private Long id;

    private Date dateOffre;

    private int nombrePlaces;

    private String modeleVoiture;

    private Date heureDepart;

    private Date heureArrive;

    private Covoitureur covoitureur;

    private List<PointPassageCovoiturage> pointPassageCovoiturages;
}
