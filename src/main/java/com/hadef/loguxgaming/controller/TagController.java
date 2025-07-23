package com.hadef.loguxgaming.controller;

import com.hadef.loguxgaming.domain.dto.CreateTagsDto;
import com.hadef.loguxgaming.domain.dto.TagDto;
import com.hadef.loguxgaming.domain.entity.Tag;
import com.hadef.loguxgaming.mapper.TagMapper;
import com.hadef.loguxgaming.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;
    private final TagMapper tagMapper;

    @GetMapping
    public ResponseEntity<List<TagDto>> findAll() {
        List<TagDto> dtoList = tagService.getAllTags().stream().map(tagMapper::toDto).toList();
        return ResponseEntity.ok(dtoList);
    }

    @PostMapping
    public ResponseEntity<List<TagDto>> addTags(@RequestBody CreateTagsDto createTagsDto) {
        List<Tag> tagList = tagService.addTags(createTagsDto.getNames());
        List<TagDto> dtoList = tagList.stream().map(tagMapper::toDto).toList();
        return new ResponseEntity<>(dtoList, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable UUID id) {
        tagService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
