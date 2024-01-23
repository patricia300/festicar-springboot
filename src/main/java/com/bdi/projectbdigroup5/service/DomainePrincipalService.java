package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.model.DomainePrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdi.projectbdigroup5.repository.DomainePrincipalRepository;

import java.util.Optional;

@Service
public class DomainePrincipalService {
    
    @Autowired
    private DomainePrincipalRepository domainePrincipalRepository;

    public DomainePrincipal createDomainePrincipal(DomainePrincipal domaine) {
        return domainePrincipalRepository.save(domaine);
    }

    public Iterable<DomainePrincipal> createDomainePrincipals(Iterable<DomainePrincipal> domaines) {
        return domainePrincipalRepository.saveAll(domaines);
    }

    public Iterable<DomainePrincipal> findAllDomainePrincipal() {
        return domainePrincipalRepository.findAll();
    }

    public Optional<DomainePrincipal> findById(String nom) {
        return domainePrincipalRepository.findById(nom);
    }
}
