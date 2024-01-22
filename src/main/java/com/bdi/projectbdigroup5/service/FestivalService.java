package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.model.Festival;
import com.bdi.projectbdigroup5.repository.FestivalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FestivalService {
    @Autowired
    private FestivalRepository festivalRepository;

    public List<Festival> getAllFestivalPerPage(Pageable pageable) {
        return festivalRepository.findAll(pageable).getContent();
    }
}
