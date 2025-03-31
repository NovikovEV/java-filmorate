package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.dto.user.RequestUserDto;
import ru.yandex.practicum.filmorate.dto.user.ResponseUserDto;
import ru.yandex.practicum.filmorate.group.validation.user.UserCreateValidation;
import ru.yandex.practicum.filmorate.group.validation.user.UserUpdateValidation;
import ru.yandex.practicum.filmorate.service.user.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseUserDto createUser(@Validated(UserCreateValidation.class) @RequestBody RequestUserDto requestUserDto) {
        return userService.create(requestUserDto);
    }

    @PutMapping
    public ResponseUserDto updateUser(@Validated(UserUpdateValidation.class) @RequestBody RequestUserDto requestUserDto) {
        return userService.update(requestUserDto);
    }

    @GetMapping
    public List<ResponseUserDto> getAll() {
        return userService.getAll();
    }

    @PutMapping("/{userId}/friends/{friendId}")
    public Boolean addFriend(
            @PathVariable(required = false) @NotNull(message = "Не указан id пользователя") Integer userId,
            @PathVariable(required = false) @NotNull(message = "Не указан id друга") Integer friendId
    ) {
        return userService.addFriend(userId, friendId);
    }

    @DeleteMapping("/{userId}/friends/{friendId}")
    public Boolean removeFriend(
            @PathVariable(required = false) @NotNull(message = "Не указан id пользователя") Integer userId,
            @PathVariable(required = false) @NotNull(message = "Не указан id друга") Integer friendId
    ) {
        return userService.removeFriend(userId, friendId);
    }

    @GetMapping("/{userId}/friends/common/{friendId}")
    public List<ResponseUserDto> getCommonFriends(
            @PathVariable(required = false) @NotNull(message = "Не указан id пользователя") Integer userId,
            @PathVariable(required = false) @NotNull(message = "Не указан id друга") Integer friendId
    ) {
        return userService.getCommonFriends(userId, friendId);
    }

    @GetMapping("/{userId}/friends")
    public List<ResponseUserDto> getUserFriends(
            @PathVariable Integer userId
    ) {
        return userService.getUserFriends(userId);
    }
}
