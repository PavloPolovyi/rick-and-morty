package com.rickandmorty.character.wiki.rickandmortywiki.service;

import com.rickandmorty.character.wiki.rickandmortywiki.dto.external.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "httpClient", url = "${external.api.url}")
public interface ApiClient {
    @GetMapping
    ApiResponse getCharactersPaginated(@RequestParam Integer page);
}
