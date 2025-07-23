package com.hadef.loguxgaming.service;

import com.hadef.loguxgaming.domain.entity.Genre;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface GenreService {
    List<Genre> getAllGenre();
    Genre findById(UUID id);
    List<Genre> save(Set<String> genres);
    void delete(UUID id);
    List<Genre> getGenresByIds(Set<UUID> genreIds);
}
