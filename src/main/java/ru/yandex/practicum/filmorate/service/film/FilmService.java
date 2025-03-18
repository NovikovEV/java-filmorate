package ru.yandex.practicum.filmorate.service.film;

import ru.yandex.practicum.filmorate.dto.IncomeFilmDto;
import ru.yandex.practicum.filmorate.dto.IncomeFilmWithIdDto;
import ru.yandex.practicum.filmorate.dto.OutcomeFilmDto;

import java.util.List;

public interface FilmService {
    OutcomeFilmDto create(IncomeFilmDto incomeFilmDto);

    OutcomeFilmDto update(IncomeFilmWithIdDto incomeFilmWithIdDto);

    List<OutcomeFilmDto> getAll();
}
