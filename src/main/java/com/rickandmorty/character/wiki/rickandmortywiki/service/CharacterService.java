package com.rickandmorty.character.wiki.rickandmortywiki.service;

import com.rickandmorty.character.wiki.rickandmortywiki.model.MovieCharacter;
import java.util.List;

public interface CharacterService {
    List<MovieCharacter> findByNameContains(String substring);

    MovieCharacter getRandomCharacter();
}
