package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.model.Organisateur;
import com.bdi.projectbdigroup5.repository.OrganisateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganisateurService {
    private OrganisateurRepository organisateurRepository;

    public Organisateur createOrganisateur(Organisateur organisateur) {
        return organisateurRepository.save(organisateur);
    }

    public Iterable<Organisateur> findAllOrganisateurs() {
        return organisateurRepository.findAll();
    }

}
