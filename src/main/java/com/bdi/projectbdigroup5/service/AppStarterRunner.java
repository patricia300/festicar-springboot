package com.bdi.projectbdigroup5.service;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.boot.ApplicationArguments;

@Component
@AllArgsConstructor
public class AppStarterRunner implements ApplicationRunner {
    private UtilisateurFaker utilisateurFaker;

    @Override
    public void run(ApplicationArguments args) throws UnsupportedOperationException {
            // this is void 'cause nothing to add there yet
    }
}
