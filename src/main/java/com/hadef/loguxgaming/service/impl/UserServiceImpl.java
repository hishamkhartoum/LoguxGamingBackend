package com.hadef.loguxgaming.service.impl;

import com.hadef.loguxgaming.domain.dto.CreateAdminRequest;
import com.hadef.loguxgaming.domain.entity.User;
import com.hadef.loguxgaming.domain.value.UserRole;
import com.hadef.loguxgaming.repository.UserRepository;
import com.hadef.loguxgaming.service.UserService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository  userRepository;
    @Override
    public List<User> getAllAdminUsers() {
        return userRepository.findAllByRole(UserRole.ADMIN_ROLE);
    }

    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(
                ()->  new EntityNotFoundException("User with id " + id + " not found")
                );
    }

    @Override
    public User addUser(CreateAdminRequest createAdminRequest) {
        userRepository.findByEmail(createAdminRequest.getEmail()).ifPresent(
                user -> {
                    throw new EntityExistsException("User with email " + user.getEmail() + " already exists");
                }
        );
        User user = User.builder()
                .email(createAdminRequest.getEmail())
                .password(createAdminRequest.getPassword())
                .role(UserRole.ADMIN_ROLE)
                .fullName(createAdminRequest.getFullName())
                .phoneNumber(createAdminRequest.getPhoneNumber())
                .product(new ArrayList<>())
                .build();
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user, UUID id) {
        return null;
    }

    @Override
    public void deleteUser(UUID id) {

    }
}
