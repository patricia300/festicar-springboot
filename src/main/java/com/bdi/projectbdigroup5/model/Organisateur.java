package com.bdi.projectbdigroup5.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Organisateur extends Utilisateur {
    public String getNomComplet()
    {
        return this.getNom() + " " + this.getPrenom();
    }
}
