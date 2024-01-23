package com.bdi.projectbdigroup5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.boot.ApplicationArguments;

@Component
public class AppStarterRunner implements ApplicationRunner {
    
    @Autowired
    private UtilisateurFaker utilisateurFaker;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        utilisateurFaker.createFakeOrganisateurs(200);
        utilisateurFaker.createFakeCovoitureurs(5000);
        utilisateurFaker.createFakeFestivaliers(10000);
    }
}
