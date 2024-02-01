package com.bdi.projectbdigroup5.dto;

import lombok.Getter;

import java.util.List;


@Getter
public class PanierPartielPaiementRequestDto {
    private String emailFestivalier;
    private List<Long> articles;
}
