package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.dto.IncomeUserDto;
import ru.yandex.practicum.filmorate.dto.IncomeUserWithIdDto;
import ru.yandex.practicum.filmorate.dto.OutcomeUserDto;
import ru.yandex.practicum.filmorate.service.user.UserService;
import ru.yandex.practicum.filmorate.service.user.UserServiceImpl;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;
import ru.yandex.practicum.filmorate.storage.user.UserStorageImpl;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserStorage userStorage = new UserStorageImpl();
    private final UserService userService = new UserServiceImpl(userStorage);

    @PostMapping
    public OutcomeUserDto createUser(@Valid @RequestBody IncomeUserDto incomeUserDto) {
        return userService.create(incomeUserDto);
    }

    @PutMapping
    public OutcomeUserDto updateUser(@Valid @RequestBody IncomeUserWithIdDto incomeUserWithIdDto) {
        return userService.update(incomeUserWithIdDto);
    }

    @GetMapping
    public List<OutcomeUserDto> getAll() {
        return userService.getAll();
    }
}
