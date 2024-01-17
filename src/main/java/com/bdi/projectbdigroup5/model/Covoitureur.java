package com.bdi.projectbdigroup5.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "covoitureurs")
public class Covoitureur extends Utilisateur {
    private String numeroTelephone;
}
