package com.hadef.loguxgaming.service;

import com.hadef.loguxgaming.domain.entity.Tag;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface TagService {
    List<Tag> addTags(Set<String> tag);
    List<Tag> getAllTags();
    Tag findById(UUID id);
    void deleteById(UUID id);
    List<Tag> getTagsByIds(Set<UUID> ids);
}
