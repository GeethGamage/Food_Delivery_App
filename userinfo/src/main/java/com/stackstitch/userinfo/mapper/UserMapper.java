package com.stackstitch.userinfo.mapper;

import com.stackstitch.userinfo.dto.UserDTO;
import com.stackstitch.userinfo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User mapUserDtoToUser(UserDTO userDTO);

    UserDTO mapUserToUserDto(User user);
}
