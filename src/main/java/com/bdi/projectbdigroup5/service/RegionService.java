package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.model.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdi.projectbdigroup5.repository.RegionRepository;

import java.util.Optional;

@Service
public class RegionService {
    
    @Autowired
    private RegionRepository regionRepository;

    public Region createRegion(Region region) {
        return regionRepository.save(region);
    }

    public Iterable<Region> createRegions(Iterable<Region> regions) {
        return regionRepository.saveAll(regions);
    }

    public Optional<Region> findById(String nom){
        return regionRepository.findById(nom);
    }

    public Iterable<Region> findAllRegion() {
        return regionRepository.findAll();
    }
}
