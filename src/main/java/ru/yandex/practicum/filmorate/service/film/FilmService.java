package ru.yandex.practicum.filmorate.service.film;

import ru.yandex.practicum.filmorate.dto.RequestFilmDto;
import ru.yandex.practicum.filmorate.dto.ResponseFilmDto;

import java.util.List;

public interface FilmService {
    ResponseFilmDto create(RequestFilmDto requestFilmDto);

    ResponseFilmDto update(RequestFilmDto requestFilmWithIdDto);

    List<ResponseFilmDto> getAll();
}
