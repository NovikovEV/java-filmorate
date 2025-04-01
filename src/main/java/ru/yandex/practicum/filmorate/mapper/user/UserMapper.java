package ru.yandex.practicum.filmorate.mapper.user;

import ru.yandex.practicum.filmorate.dto.user.RequestUserDto;
import ru.yandex.practicum.filmorate.dto.user.ResponseUserDto;
import ru.yandex.practicum.filmorate.model.User;

public interface UserMapper {
    User convertRequestUserDtoToUser(RequestUserDto requestUserDto);

    ResponseUserDto convertUserToResponseUserDto(User user);
}
