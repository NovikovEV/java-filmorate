package ru.yandex.practicum.filmorate.service.user;

import ru.yandex.practicum.filmorate.dto.RequestUserDto;
import ru.yandex.practicum.filmorate.dto.RequestUserWithIdDto;
import ru.yandex.practicum.filmorate.dto.ResponseUserDto;

import java.util.List;

public interface UserService {
    ResponseUserDto create(RequestUserDto requestUserDto);

    ResponseUserDto update(RequestUserWithIdDto requestUserWithIdDto);

    List<ResponseUserDto> getAll();
}
