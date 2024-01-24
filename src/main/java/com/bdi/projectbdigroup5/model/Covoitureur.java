package com.bdi.projectbdigroup5.model;

import jakarta.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Covoitureur extends Utilisateur {
    @Column(nullable = false)
    private String numeroTelephone;
}
