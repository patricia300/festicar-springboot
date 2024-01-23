package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.model.Organisateur;
import com.bdi.projectbdigroup5.repository.OrganisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganisateurService {
    @Autowired
    private OrganisateurRepository organisateurRepository;

    public Organisateur createOrganisateur(Organisateur organisateur){
        return organisateurRepository.save(organisateur);
    }

    public Iterable<Organisateur> createOrganisateurs(Iterable<Organisateur> organisateurs){
        return organisateurRepository.saveAll(organisateurs);
    }
}
