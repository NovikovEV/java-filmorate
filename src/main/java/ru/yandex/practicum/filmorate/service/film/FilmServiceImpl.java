package ru.yandex.practicum.filmorate.service.film;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.dto.film.RequestFilmDto;
import ru.yandex.practicum.filmorate.dto.film.ResponseFilmDto;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.mapper.film.FilmMapper;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {
    private final FilmStorage filmStorage;
    private final UserStorage userStorage;
    private final FilmMapper filmMapper;

    private static final Logger log = LoggerFactory.getLogger(FilmServiceImpl.class);

    public FilmServiceImpl(FilmStorage filmStorage, UserStorage userStorage, FilmMapper filmMapper) {
        this.filmStorage = filmStorage;
        this.userStorage = userStorage;
        this.filmMapper = filmMapper;
    }

    @Override
    public ResponseFilmDto create(RequestFilmDto requestFilmDto) {
        Film film = filmMapper.convertRequestFilmDtoToFilm(requestFilmDto);

        return filmMapper.convertFilmToResponseFilmDto(filmStorage.create(film));
    }

    @Override
    public ResponseFilmDto update(RequestFilmDto requestFilmDto) {
        Film film = filmMapper.convertRequestFilmDtoToFilm(requestFilmDto);

        Film updatedFilm = filmStorage.update(film).orElseThrow(() -> {
            final String message = String.format("Фильм с id= %d не найден", requestFilmDto.id());
            log.info(message);

            return new NotFoundException(message);
        });

        return filmMapper.convertFilmToResponseFilmDto(updatedFilm);
    }

    @Override
    public List<ResponseFilmDto> getAll() {
        List<Film> films = filmStorage.getAll();

        return films.stream()
                .map(filmMapper::convertFilmToResponseFilmDto)
                .toList();
    }

    @Override
    public void addLike(Integer filmId, Integer userId) {
        userStorage.checkUserId(userId).orElseThrow(() -> {
            final String message = String.format("Пользователь с id= %d не найден", userId);
            log.info(message);

            return new NotFoundException(message);
        });

        filmStorage.addLike(filmId, userId).orElseThrow(() -> {
            final String message = String.format("Фильм с id= %d не найден", filmId);
            log.info(message);

            return new NotFoundException(message);
        });
    }

    @Override
    public void removeLike(Integer filmId, Integer userId) {
        userStorage.checkUserId(userId).orElseThrow(() -> {
            final String message = String.format("Пользователь с id= %d не найден", userId);
            log.info(message);

            return new NotFoundException(message);
        });

        filmStorage.removeLike(filmId, userId).orElseThrow(() -> {
            final String message = String.format("Фильм с id= %d не найден", filmId);
            log.info(message);

            return new NotFoundException(message);
        });
    }

    @Override
    public List<ResponseFilmDto> getPopularFilms(int count) {
        List<Film> popularFilms = filmStorage.getPopularFilms(count);

        return popularFilms.stream()
                .map(filmMapper::convertFilmToResponseFilmDto)
                .toList();
    }
}
