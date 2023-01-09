package com.rickandmorty.character.wiki.rickandmortywiki.dto.external;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiInfo {
    private Integer count;
    private Integer pages;
    private String next;
}
