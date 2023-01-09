package com.rickandmorty.character.wiki.rickandmortywiki.service.mapper;

public interface ResponseMapper<T, E> {
    T toDto(E e);
}
