package com.hadef.loguxgaming.mapper;

import com.hadef.loguxgaming.domain.dto.CreateAdminDto;
import com.hadef.loguxgaming.domain.dto.CreateAdminRequest;
import com.hadef.loguxgaming.domain.dto.UserDto;
import com.hadef.loguxgaming.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserDto toDto(User user);
    CreateAdminRequest  toCreateAdminRequest(CreateAdminDto createAdminDto);
}
