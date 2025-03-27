package ru.yandex.practicum.filmorate.service.user;

import ru.yandex.practicum.filmorate.dto.user.RequestUserDto;
import ru.yandex.practicum.filmorate.dto.user.ResponseUserDto;

import java.util.List;

public interface UserService {
    ResponseUserDto create(RequestUserDto requestUserDto);

    ResponseUserDto update(RequestUserDto requestUserDto);

    List<ResponseUserDto> getAll();
}
