package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.repository.PanierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PanierService {
    @Autowired
    private PanierRepository panierRepository;
}
