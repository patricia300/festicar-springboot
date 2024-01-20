package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.repository.FestivalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FestivalService {

    @Autowired
    private FestivalRepository festivalRepository;
}
