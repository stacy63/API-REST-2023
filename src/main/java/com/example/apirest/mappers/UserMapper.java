package com.example.apirest.mappers;

import com.example.apirest.dtos.UserDto;
import com.example.apirest.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto userToUserDto(UserEntity user);
}
