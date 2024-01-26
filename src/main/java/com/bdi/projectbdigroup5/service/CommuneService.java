package com.bdi.projectbdigroup5.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bdi.projectbdigroup5.model.Commune;
import com.bdi.projectbdigroup5.repository.CommuneRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CommuneService {
    
    private CommuneRepository communeRepository;

    public Commune createCommune(Commune commune){
        return this.communeRepository.save(commune);
    }

    public Iterable<Commune> createCommunes(Iterable<Commune> communes) {
        return this.communeRepository.saveAll(communes);
    }

    public void deleteCommune(Commune commune){
        this.communeRepository.delete(commune);
    }

    public Commune updateCommune(Commune commune){
        return this.communeRepository.save(commune);
    }

    public Optional<Commune> findCommuneById(String codeInsee){
        return this.communeRepository.findById(codeInsee);
    }

    public Iterable<Commune> findAllCommunes(Pageable pageable){
        return this.communeRepository.findAll(pageable);
    }

    public Iterable<String> findAllCommunesNom(Pageable pageable){
        return this.communeRepository.findAllCommuneNom(pageable);
    }
    public Iterable<String> findAllCommunesNom(){
        return this.communeRepository.findAllCommuneNom();
    }
}
