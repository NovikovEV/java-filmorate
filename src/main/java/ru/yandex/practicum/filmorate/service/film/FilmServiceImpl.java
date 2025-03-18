package ru.yandex.practicum.filmorate.service.film;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.dto.IncomeFilmDto;
import ru.yandex.practicum.filmorate.dto.IncomeFilmWithIdDto;
import ru.yandex.practicum.filmorate.dto.OutcomeFilmDto;
import ru.yandex.practicum.filmorate.exception.FilmNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Service
public class FilmServiceImpl implements FilmService {
    @Autowired
    private final FilmStorage filmStorage;

    private static final Logger log = LoggerFactory.getLogger(FilmServiceImpl.class);

    public FilmServiceImpl(FilmStorage filmStorage) {
        this.filmStorage = filmStorage;
    }

    @Override
    public OutcomeFilmDto create(IncomeFilmDto incomeFilmDto) {
        Film film = convertToFilm(incomeFilmDto);

        return convertToOutcomeFilmDto(filmStorage.create(film));
    }

    @Override
    public OutcomeFilmDto update(IncomeFilmWithIdDto incomeFilmWithIdDto) {
        Film film = convertToFilm(incomeFilmWithIdDto);
        Optional<Film> optionalFilm = filmStorage.update(film);

        if (optionalFilm.isPresent()) {
            Film updatedFilm = optionalFilm.get();
            return convertToOutcomeFilmDto(updatedFilm);
        } else {
            final String message = "Фильм с id= " + incomeFilmWithIdDto.id() + " не найден";
            log.info(message);
            throw new FilmNotFoundException(message);
        }
    }

    @Override
    public List<OutcomeFilmDto> getAll() {
        List<Film> films = filmStorage.getAll();
        return films.stream()
                .map(this::convertToOutcomeFilmDto)
                .toList();
    }

    private Film convertToFilm(IncomeFilmDto incomeFilmDto) {
        return new Film(
                incomeFilmDto.name(),
                incomeFilmDto.description(),
                incomeFilmDto.releaseDate(),
                Duration.ofMinutes(incomeFilmDto.duration())
        );
    }

    private Film convertToFilm(IncomeFilmWithIdDto incomeFilmWithIdDto) {
        return new Film(
                incomeFilmWithIdDto.id(),
                incomeFilmWithIdDto.name(),
                incomeFilmWithIdDto.description(),
                incomeFilmWithIdDto.releaseDate(),
                Duration.ofMinutes(incomeFilmWithIdDto.duration())
        );
    }

    private OutcomeFilmDto convertToOutcomeFilmDto(Film film) {
        return new OutcomeFilmDto(
                film.getId(),
                film.getName(),
                film.getDescription(),
                film.getReleaseDate(),
                (int) film.getDuration().toMinutes()
        );
    }
}
