package com.bdi.projectbdigroup5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdi.projectbdigroup5.repository.DepartementRepository;

@Service
public class DepartementService {
    
    @Autowired
    private DepartementRepository departementRepository;
}
