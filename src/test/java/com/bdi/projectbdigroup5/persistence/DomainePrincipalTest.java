package com.bdi.projectbdigroup5.persistence;

import com.bdi.projectbdigroup5.model.DomainePrincipal;
import com.bdi.projectbdigroup5.service.DomainePrincipalService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DomainePrincipalTest {
    @Autowired
    private DomainePrincipalService domainePrincipalService;

    @Test
    public void givenDomainePrincipalService_whenSaveAndRetrieveEntity_thenOk() {
        final String domaineNom = "Pluridisciplinaire Musique";
        DomainePrincipal domainePrincipal = new DomainePrincipal();
        domainePrincipal.setNom(domaineNom);

        domainePrincipalService.createDomainePrincipal(domainePrincipal);
        Optional<DomainePrincipal> newDomainePrincipal = domainePrincipalService.findById(domaineNom);

        assertTrue(newDomainePrincipal.isPresent());
        assertEquals(newDomainePrincipal.get().getNom(), domaineNom);
    }
}
