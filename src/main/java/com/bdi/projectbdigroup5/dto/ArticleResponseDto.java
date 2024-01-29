package com.bdi.projectbdigroup5.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ArticleResponseDto {

    private Long id;
    private FestivalResponseDto festival;
    private int quantite;
}
