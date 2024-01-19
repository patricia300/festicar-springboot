package com.bdi.projectbdigroup5.service;

import com.bdi.projectbdigroup5.repository.PointPassageCovoiturageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointPassageCovoiturageService {
    @Autowired
    private PointPassageCovoiturageRepository pointPassageCovoiturageRepository;
}
