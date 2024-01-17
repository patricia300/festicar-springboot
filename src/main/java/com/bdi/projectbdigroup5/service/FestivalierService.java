package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.repository.FestivalierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FestivalierService {
    @Autowired
    private FestivalierRepository festivalierRepository;
}
