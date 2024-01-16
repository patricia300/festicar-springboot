package com.bdi.projectbdigroup5.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "organisateurs")
public class Organisateur extends  Utilisateur{
}
