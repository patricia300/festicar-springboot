package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.model.Festival;
import com.bdi.projectbdigroup5.repository.FestivalRepository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class FestivalService {
    private FestivalRepository festivalRepository;

    public Iterable<Festival> getAllFestivalPerPage(Pageable pageable) {
        return festivalRepository.findAll(pageable);
    }
}
