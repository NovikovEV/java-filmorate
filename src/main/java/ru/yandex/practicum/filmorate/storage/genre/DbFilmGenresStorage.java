package ru.yandex.practicum.filmorate.storage.genre;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.validator.FilmValidator;

import java.util.Set;

@Component
@Slf4j
@RequiredArgsConstructor
public class DbFilmGenresStorage implements FilmGenresStorage {
    private final JdbcTemplate jdbcTemplate;
    private final FilmValidator customValidator;

    @Override
    public void createGenreByFilm(Film film) {
        if (!customValidator.isValid(film)) {
            log.info("Попытка добавить фильм с некорректной информацией");
            throw new ValidationException("Некорректно заполнено одно из полей");
        }
        String sql =
                "INSERT INTO FILMS_GENRES (FILM_ID, GENRE_ID) " +
                        "VALUES(?, ?)";
        Set<Genre> genres = film.getGenres();
        if (genres == null) {
            return;
        }
        for (Genre genre : genres) {
            jdbcTemplate.update(sql, film.getId(), genre.getId());
        }
    }

    @Override
    public void deleteGenreFilm(Film film) {
        String sql = "DELETE FROM FILMS_GENRES WHERE FILM_ID = ?";
        jdbcTemplate.update(sql, film.getId());
    }
}
