package com.rickandmorty.character.wiki.rickandmortywiki.dto.external;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class ApiCharacter {
    private Integer id;
    private String name;
    private String status;
    private String gender;
}
