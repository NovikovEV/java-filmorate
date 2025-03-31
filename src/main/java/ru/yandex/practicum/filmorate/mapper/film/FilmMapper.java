package ru.yandex.practicum.filmorate.mapper.film;

import ru.yandex.practicum.filmorate.dto.film.RequestFilmDto;
import ru.yandex.practicum.filmorate.dto.film.ResponseFilmDto;
import ru.yandex.practicum.filmorate.model.Film;

public interface FilmMapper {
    Film convertRequestFilmDtoToFilm(RequestFilmDto requestFilmDto);

    ResponseFilmDto convertFilmToResponseFilmDto(Film film);
}
