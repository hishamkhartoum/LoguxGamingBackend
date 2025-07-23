package com.hadef.loguxgaming.controller;

import com.hadef.loguxgaming.domain.dto.CreateAdminDto;
import com.hadef.loguxgaming.domain.dto.CreateAdminRequest;
import com.hadef.loguxgaming.domain.dto.UserDto;
import com.hadef.loguxgaming.domain.entity.User;
import com.hadef.loguxgaming.mapper.UserMapper;
import com.hadef.loguxgaming.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserDto>> findAllAdminUsers() {
        List<UserDto> userDtosList = userService.getAllAdminUsers().stream().map(
                userMapper::toDto
        ).toList();
        return ResponseEntity.ok(userDtosList);
    }

    @PostMapping
    public ResponseEntity<UserDto> createProduct(@RequestBody CreateAdminDto createAdminDto) {
        CreateAdminRequest adminRequest = userMapper.toCreateAdminRequest(createAdminDto);
        User user = userService.addUser(adminRequest);
        UserDto dto = userMapper.toDto(user);
        return ResponseEntity.ok(dto);
    }
}
