package com.rickandmorty.character.wiki.rickandmortywiki.dto;

import com.rickandmorty.character.wiki.rickandmortywiki.model.MovieCharacter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
@Schema(title = "Rick&Morty Character", accessMode = Schema.AccessMode.READ_ONLY)
public class MovieCharacterResponseDto {
    @Schema(example = "2")
    private Long id;
    @Schema(example = "Morty Smith")
    private String name;
    @Schema(example = "ALIVE")
    private MovieCharacter.CharacterStatus status;
    @Schema(example = "MALE")
    private MovieCharacter.CharacterGender gender;
}
