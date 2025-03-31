package ru.yandex.practicum.filmorate.service.film;

import ru.yandex.practicum.filmorate.dto.film.RequestFilmDto;
import ru.yandex.practicum.filmorate.dto.film.ResponseFilmDto;

import java.util.List;

public interface FilmService {
    ResponseFilmDto create(RequestFilmDto requestFilmDto);

    ResponseFilmDto update(RequestFilmDto requestFilmWithIdDto);

    List<ResponseFilmDto> getAll();

    Boolean addLike(Integer filmId, Integer userId);

    Boolean removeLike(Integer filmId, Integer userId);

    List<ResponseFilmDto> getPopularFilms(int count);
}
