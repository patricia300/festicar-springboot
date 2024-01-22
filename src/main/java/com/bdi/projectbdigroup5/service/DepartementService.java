package com.bdi.projectbdigroup5.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdi.projectbdigroup5.model.Departement;
import com.bdi.projectbdigroup5.repository.DepartementRepository;

@Service
public class DepartementService {
    
    @Autowired
    private DepartementRepository departementRepository;

    public Departement createDepartement(Departement departement){
        return departementRepository.save(departement);
    }

    public Optional<Departement> findById(String numero){
        return departementRepository.findById(numero);
    }

    public Iterable<Departement> findByNom(String nom){
        return departementRepository.findByNom(nom);
    }
}
