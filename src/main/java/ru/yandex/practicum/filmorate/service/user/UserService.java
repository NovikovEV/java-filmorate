package ru.yandex.practicum.filmorate.service.user;

import ru.yandex.practicum.filmorate.dto.user.RequestUserDto;
import ru.yandex.practicum.filmorate.dto.user.ResponseUserDto;

import java.util.List;

public interface UserService {
    ResponseUserDto create(RequestUserDto requestUserDto);

    ResponseUserDto update(RequestUserDto requestUserDto);

    List<ResponseUserDto> getAll();

    Boolean addFriend(Integer userId, Integer friendId);

    Boolean removeFriend(Integer userId, Integer friendId);

    List<ResponseUserDto> getCommonFriends(Integer userId, Integer friendId);

    List<ResponseUserDto> getUserFriends(Integer userId);
}
