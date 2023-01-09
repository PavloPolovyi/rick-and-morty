package com.rickandmorty.character.wiki.rickandmortywiki.service;

import com.rickandmorty.character.wiki.rickandmortywiki.model.MovieCharacter;
import com.rickandmorty.character.wiki.rickandmortywiki.repository.MovieCharacterRepository;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private static final long MINIMUM_INDEX = 1;
    private final MovieCharacterRepository characterRepository;

    @Override
    public List<MovieCharacter> findByNameContains(String substring) {
        return characterRepository.findByNameContains(substring);
    }

    @Override
    public MovieCharacter getRandomCharacter() {
        long charactersCount = characterRepository.count();
        long index = ThreadLocalRandom.current().nextLong(MINIMUM_INDEX, charactersCount + 1);
        return characterRepository.getReferenceById(index);
    }
}
