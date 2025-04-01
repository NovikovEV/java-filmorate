package ru.yandex.practicum.filmorate.mapper.film;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dto.film.RequestFilmDto;
import ru.yandex.practicum.filmorate.dto.film.ResponseFilmDto;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.Duration;

@Component
public class FilmMapperImpl implements FilmMapper {

    @Override
    public Film convertRequestFilmDtoToFilm(RequestFilmDto requestFilmDto) {
        return new Film(
                requestFilmDto.id(),
                requestFilmDto.name(),
                requestFilmDto.description(),
                requestFilmDto.releaseDate(),
                Duration.ofMinutes(requestFilmDto.duration())
        );
    }

    @Override
    public ResponseFilmDto convertFilmToResponseFilmDto(Film film) {
        return new ResponseFilmDto(
                film.getId(),
                film.getName(),
                film.getDescription(),
                film.getReleaseDate(),
                (int) film.getDuration().toMinutes()
        );
    }
}
