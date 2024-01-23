package com.bdi.projectbdigroup5.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


@Getter
@Setter
@Configuration
@ConfigurationProperties("pageable.property")
public class PageableConfiguration {
    private int numeroPageParDefaut;
    private int tailleElementParDefaut;


    public Pageable createPageable(
            Integer numeroPage,
            Integer taillePage,
            String triPar,
            String tri) {
        String ordreTri = tri != null ? tri : "asc";

        return  PageRequest.of(
                numeroPage != null ? numeroPage : this.getNumeroPageParDefaut() ,
                taillePage != null ? taillePage : this.getTailleElementParDefaut(),
                ordreTri.equalsIgnoreCase("asc") ?
                        Sort.by(triPar).ascending() :
                        Sort.by(triPar).descending()
        );
    }
}
