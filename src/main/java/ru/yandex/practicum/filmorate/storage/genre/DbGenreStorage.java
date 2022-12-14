package ru.yandex.practicum.filmorate.storage.genre;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.SearchedObjectNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Slf4j
@RequiredArgsConstructor
public class DbGenreStorage implements GenreStorage {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Genre getGenreById(int genreId) {
        if (genreId < 1) {
            log.error("Идентификатор жанров меньше нуля");
            throw new SearchedObjectNotFoundException("Идентификатор жанров меньше нуля");
        }

        String sql =
                "SELECT * " +
                        "FROM GENRES " +
                        "WHERE GENRE_ID = ?";

        List<Genre> genres = jdbcTemplate.query(sql, (rs, rowNum) -> makeGenre(rs), genreId);

        if (!genres.isEmpty()) {
            Genre genre = new Genre();

            genres.forEach(g -> {
                if (g.getId().equals(genreId)) {
                    genre.setId(g.getId());
                    genre.setName(g.getName());
                }
            });
            return genre;
        } else {
            log.error("Введен некорректный идентификатор жанра.");
            throw new SearchedObjectNotFoundException("Введен некорректный идентификатор жанра.");
        }
    }

    @Override
    public List<Genre> getAllGenres() {
        String sql =
                "SELECT * " +
                        "FROM GENRES " +
                        "ORDER BY GENRE_ID";
        return jdbcTemplate.query(sql, (rs, rowNum) -> makeGenre(rs));
    }

    @Override
    public Set<Genre> getGenresByFilm(Film film) {
        String sql =
                "SELECT GEN.GENRE_ID, GEN.NAME " +
                        "FROM GENRES GEN " +
                        "NATURAL JOIN FILMS_GENRES fg " +
                        "WHERE fg.FILM_ID = ?";
        return new HashSet<>(jdbcTemplate.query(sql, (rs, rowNum) -> makeGenre(rs), film.getId()));
    }

    private Genre makeGenre(ResultSet rs) throws SQLException {
        return new Genre(rs.getInt("GENRE_ID"), rs.getString("NAME"));
    }
}
