package com.hadef.loguxgaming.service.impl;

import com.hadef.loguxgaming.domain.entity.Genre;
import com.hadef.loguxgaming.repository.GenreRepository;
import com.hadef.loguxgaming.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public List<Genre> getAllGenre() {
        return genreRepository.findAllWithProductsCount();
    }

    @Override
    public Genre findById(UUID id) {
        return genreRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Genre not found with: "+id));
    }

    @Override
    public List<Genre> save(Set<String> genres) {
        List<Genre> existingGenres = genreRepository.findByNameIn(genres);
        Set<String> existingGenreNames = existingGenres.stream().map(Genre::getName)
                .collect(Collectors.toSet());
        List<Genre> newGenres = genres.stream().filter(name -> !existingGenreNames.contains(name))
                .map(
                        genre -> Genre.builder()
                                .name(genre).products(new HashSet<>()).build()
                ).toList();
        List<Genre> savedGenres = new ArrayList<>();
        if(!newGenres.isEmpty()) {
            savedGenres =  genreRepository.saveAll(newGenres);
        }
        savedGenres.addAll(existingGenres);
        return savedGenres;
    }

    @Override
    public void delete(UUID id) {
        genreRepository.findById(id).ifPresent(genre -> {
            if(!genre.getProducts().isEmpty()) {
                throw new IllegalStateException(String.format("Cannot delete genre because genre ID %s has products.", genre.getId()));
            }
            genreRepository.deleteById(genre.getId());
        });
    }

    @Override
    public List<Genre> getGenresByIds(Set<UUID> genreIds) {
        List<Genre> genres = genreRepository.findAllById(genreIds);
        if(genres.size() != genreIds.size()) {
            throw new IllegalStateException("Not all genres found");
        }
        return genres;
    }
}
