package com.bdi.projectbdigroup5.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ArticleRequestBodyDto {
    private Long idPointPassage;
    private int quantite;
}
