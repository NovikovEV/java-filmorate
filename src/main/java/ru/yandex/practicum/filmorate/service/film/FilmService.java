package ru.yandex.practicum.filmorate.service.film;

import ru.yandex.practicum.filmorate.dto.RequestFilmDto;
import ru.yandex.practicum.filmorate.dto.RequestFilmWithIdDto;
import ru.yandex.practicum.filmorate.dto.ResponseFilmDto;

import java.util.List;

public interface FilmService {
    ResponseFilmDto create(RequestFilmDto requestFilmDto);

    ResponseFilmDto update(RequestFilmWithIdDto requestFilmWithIdDto);

    List<ResponseFilmDto> getAll();
}
