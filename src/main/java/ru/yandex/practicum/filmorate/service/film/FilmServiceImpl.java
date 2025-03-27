package ru.yandex.practicum.filmorate.service.film;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.dto.RequestFilmDto;
import ru.yandex.practicum.filmorate.dto.ResponseFilmDto;
import ru.yandex.practicum.filmorate.exception.FilmNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;

import java.time.Duration;
import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {
    private final FilmStorage filmStorage;

    private static final Logger log = LoggerFactory.getLogger(FilmServiceImpl.class);

    public FilmServiceImpl(FilmStorage filmStorage) {
        this.filmStorage = filmStorage;
    }

    @Override
    public ResponseFilmDto create(RequestFilmDto requestFilmDto) {
        Film film = convertToFilm(requestFilmDto);

        return convertToOutcomeFilmDto(filmStorage.create(film));
    }

    @Override
    public ResponseFilmDto update(RequestFilmDto requestFilmDto) {
        Film film = convertToFilm(requestFilmDto);

        Film updatedFilm = filmStorage.update(film).orElseThrow(() -> {
            final String message = "Фильм с id= " + requestFilmDto.id() + " не найден";
            log.info(message);
            return new FilmNotFoundException(message);
        });

        return convertToOutcomeFilmDto(updatedFilm);
    }

    @Override
    public List<ResponseFilmDto> getAll() {
        List<Film> films = filmStorage.getAll();
        return films.stream()
                .map(this::convertToOutcomeFilmDto)
                .toList();
    }

    private Film convertToFilm(RequestFilmDto requestFilmDto) {
        return new Film(
                requestFilmDto.id(),
                requestFilmDto.name(),
                requestFilmDto.description(),
                requestFilmDto.releaseDate(),
                Duration.ofMinutes(requestFilmDto.duration())
        );
    }

    private ResponseFilmDto convertToOutcomeFilmDto(Film film) {
        return new ResponseFilmDto(
                film.getId(),
                film.getName(),
                film.getDescription(),
                film.getReleaseDate(),
                (int) film.getDuration().toMinutes()
        );
    }
}
