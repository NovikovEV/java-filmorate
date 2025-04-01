package ru.yandex.practicum.filmorate.storage.film;

import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class FilmStorageImpl implements FilmStorage {
    private final HashMap<Integer, Film> filmHashMap = new HashMap<>();
    private Integer id = 1;

    @Override
    public Film create(Film film) {
        film.setId(nexId());
        filmHashMap.put(film.getId(), film);

        return filmHashMap.get(film.getId());
    }

    @Override
    public Optional<Film> update(Film film) {
        Optional<Film> updatedFilm = Optional.empty();

        if (checkId(film.getId())) {
            filmHashMap.replace(film.getId(), film);
            updatedFilm = Optional.of(filmHashMap.get(film.getId()));
        }

        return updatedFilm;
    }

    @Override
    public List<Film> getAll() {
        return filmHashMap.values().stream().toList();
    }

    @Override
    public Optional<Boolean> addLike(Integer filmId, Integer userId) {
        if (checkId(filmId)) {
            Film film = filmHashMap.get(filmId);
            film.setLike(userId);
            update(film);

            return Optional.of(true);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Boolean> removeLike(Integer filmId, Integer userId) {
        if (checkId(filmId)) {
            Film film = filmHashMap.get(filmId);
            film.removeLike(userId);
            update(film);
            return Optional.of(true);
        }
        return Optional.empty();
    }

    @Override
    public List<Film> getPopularFilms(int count) {
        return filmHashMap.values().stream()
                        .sorted(Comparator.comparingInt(film -> -film.getLikes().size()))
                        .limit(count)
                        .toList();
    }

    private int nexId() {
        return this.id++;
    }

    private boolean checkId(Integer id) {
        return filmHashMap.containsKey(id);
    }
}
