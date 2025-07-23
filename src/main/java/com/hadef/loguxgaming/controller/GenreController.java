package com.hadef.loguxgaming.controller;

import com.hadef.loguxgaming.domain.dto.CreateGenreDto;
import com.hadef.loguxgaming.domain.dto.GenreDto;
import com.hadef.loguxgaming.domain.entity.Genre;
import com.hadef.loguxgaming.mapper.GenreMapper;
import com.hadef.loguxgaming.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;
    private final GenreMapper genreMapper;

    @GetMapping
    public ResponseEntity<List<GenreDto>> getAllGenre() {
        List<GenreDto> genreDtoList = genreService.getAllGenre().stream().map(
                genreMapper::toDto).toList();
        return ResponseEntity.ok(genreDtoList);
    }

    @PostMapping
    public ResponseEntity<List<GenreDto>> saveGenre(@RequestBody CreateGenreDto createGenreDto) {
        List<Genre> saved = genreService.save(createGenreDto.getNames());
        List<GenreDto> genreDtosList = saved.stream().map(genreMapper::toDto).toList();
        return ResponseEntity.ok(genreDtosList);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable UUID id) {
        genreService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
