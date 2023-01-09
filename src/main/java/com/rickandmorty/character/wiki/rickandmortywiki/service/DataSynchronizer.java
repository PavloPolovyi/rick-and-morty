package com.rickandmorty.character.wiki.rickandmortywiki.service;

import com.rickandmorty.character.wiki.rickandmortywiki.dto.external.ApiCharacter;
import com.rickandmorty.character.wiki.rickandmortywiki.dto.external.ApiResponse;
import com.rickandmorty.character.wiki.rickandmortywiki.model.MovieCharacter;
import com.rickandmorty.character.wiki.rickandmortywiki.repository.MovieCharacterRepository;
import com.rickandmorty.character.wiki.rickandmortywiki.service.mapper.RequestMapper;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DataSynchronizer {
    private static final int INITIAL_PAGE = 1;
    private final ApiClient client;
    private final MovieCharacterRepository characterRepository;
    private final RequestMapper<MovieCharacter, ApiCharacter> requestMapper;

    @PostConstruct
    public void onStartUp() {
        synchronizeData();
    }

    @Scheduled(cron = "0 0 6 * * *")
    public void onSchedule() {
        synchronizeData();
    }

    public void synchronizeData() {
        List<ApiCharacter> characters = fetchData();
        saveAll(filterAndMap(characters));
    }

    public List<ApiCharacter> fetchData() {
        int page = INITIAL_PAGE;
        ApiResponse apiResponse;
        List<ApiCharacter> characters = new ArrayList<>();
        do {
            apiResponse = client.getCharactersPaginated(page++);
            characters.addAll(apiResponse.getResults());
        } while (apiResponse.getInfo().getNext() != null);
        return characters;
    }

    public List<MovieCharacter> filterAndMap(Collection<ApiCharacter> data) {
        Set<Integer> allExternalIndexes = characterRepository.getAllExternalIndexes();
        return data.stream()
                .filter(apiCharacter -> !allExternalIndexes.contains(apiCharacter.getId()))
                .map(requestMapper::toModel)
                .toList();
    }

    private void saveAll(Collection<MovieCharacter> characters) {
        characterRepository.saveAll(characters);
    }
}
