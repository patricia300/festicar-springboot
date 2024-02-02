package com.bdi.projectbdigroup5.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Getter
@Builder
public class PanierPartielPaiementRequestDto {
    private String emailFestivalier;
    private List<Long> articles;
}
