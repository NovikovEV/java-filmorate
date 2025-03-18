package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.dto.IncomeUserDto;
import ru.yandex.practicum.filmorate.dto.IncomeUserWithIdDto;
import ru.yandex.practicum.filmorate.dto.OutcomeUserDto;
import ru.yandex.practicum.filmorate.service.user.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

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
