package ru.yandex.practicum.filmorate.service.user;

import ru.yandex.practicum.filmorate.dto.IncomeUserDto;
import ru.yandex.practicum.filmorate.dto.IncomeUserWithIdDto;
import ru.yandex.practicum.filmorate.dto.OutcomeUserDto;

import java.util.List;

public interface UserService {
    OutcomeUserDto create(IncomeUserDto incomeUserDto);

    OutcomeUserDto update(IncomeUserWithIdDto incomeUserWithIdDto);

    List<OutcomeUserDto> getAll();
}
