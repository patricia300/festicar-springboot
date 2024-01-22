package com.bdi.projectbdigroup5.pageable;

import org.springframework.data.domain.PageRequest;


public class FestivalPageable {
    public static int NUMERO_PAGE_PAR_DEFAUT = 0;
    public static int TAILLE_ELEMENT_DANS_LA_PAGE_PAR_DEFAUT = 10;

    public static PageRequest createPageable(int numeroPage , int taillePage) {
        return PageRequest.of(numeroPage, taillePage);
    }

    public static PageRequest createPageable()
    {
        return createPageable(NUMERO_PAGE_PAR_DEFAUT, TAILLE_ELEMENT_DANS_LA_PAGE_PAR_DEFAUT);
    }

}
