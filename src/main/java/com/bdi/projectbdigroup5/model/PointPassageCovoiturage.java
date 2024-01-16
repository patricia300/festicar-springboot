package com.bdi.projectbdigroup5.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="point_passage_covoiturages")
public class PointPassageCovoiturage {
    @Id
    private int differenceHeurePassage;

    private float prix;
}
