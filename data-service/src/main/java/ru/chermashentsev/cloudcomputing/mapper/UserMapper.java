package ru.chermashentsev.cloudcomputing.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.chermashentsev.cloudcomputing.dto.request.user.CreateUserRequestDTO;
import ru.chermashentsev.cloudcomputing.dto.response.user.UserResponseDTO;
import ru.chermashentsev.cloudcomputing.entity.User;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    User toModel(CreateUserRequestDTO userRequestDTO);

    UserResponseDTO toDto(User user);

}
