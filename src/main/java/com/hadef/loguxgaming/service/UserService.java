package com.hadef.loguxgaming.service;

import com.hadef.loguxgaming.domain.dto.CreateAdminRequest;
import com.hadef.loguxgaming.domain.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> getAllAdminUsers();
    User getUserById(UUID id);
    User addUser(CreateAdminRequest createAdminRequest);
    User updateUser(User user, UUID id);
    void deleteUser(UUID id);
}
