package com.rickandmorty.character.wiki.rickandmortywiki.service.mapper;

public interface RequestMapper<T, E> {
    T toModel(E e);
}
