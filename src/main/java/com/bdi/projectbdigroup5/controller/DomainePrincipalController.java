package com.bdi.projectbdigroup5.controller;

import com.bdi.projectbdigroup5.model.DomainePrincipal;
import com.bdi.projectbdigroup5.service.DomainePrincipalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("domaine-principals")
public class DomainePrincipalController {
    private DomainePrincipalService domainePrincipalService;

    @GetMapping()
    public Iterable<DomainePrincipal> getAllDomainePrincipals() {
        return domainePrincipalService.findAllDomainePrincipal();
    }
}
