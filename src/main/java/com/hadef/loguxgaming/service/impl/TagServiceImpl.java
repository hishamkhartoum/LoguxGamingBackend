package com.hadef.loguxgaming.service.impl;

import com.hadef.loguxgaming.domain.entity.Tag;
import com.hadef.loguxgaming.repository.TagRepository;
import com.hadef.loguxgaming.service.TagService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    @Transactional
    public List<Tag> addTags(Set<String> tags) {
        List<Tag> existingTags = tagRepository.findByNameIn(tags);
        Set<String> existingTagNames = existingTags.stream().map(Tag::getName)
                .collect(Collectors.toSet());
        List<Tag> missedTags = tags.stream().filter(name -> !existingTagNames.contains(name)).map(
                name -> Tag.builder().name(name)
                        .products(new HashSet<>())
                        .build()
        ).toList();
        List<Tag> savedTags = new ArrayList<>();
        if(!missedTags.isEmpty()) {
            savedTags = tagRepository.saveAll(missedTags);
        }
        savedTags.addAll(existingTags);
        return savedTags;
    }

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAllWithProductsCount();
    }

    @Override
    public Tag findById(UUID id) {
        return tagRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("No tag found with id " + id)
        );
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        tagRepository.findById(id).ifPresent(tag -> {
            if(!tag.getProducts().isEmpty()) {
                throw new IllegalStateException(String.format("Cannot delete tag because tag ID %s has posts.", tag.getId()));
            }
            tagRepository.deleteById(id);
        });
    }

    @Override
    public List<Tag> getTagsByIds(Set<UUID> ids) {
        List<Tag> foundTags = tagRepository.findAllById(ids);
        if(foundTags.size() != ids.size()) {
            throw new IllegalStateException("Not all tags are found");
        }
        return foundTags;
    }
}
