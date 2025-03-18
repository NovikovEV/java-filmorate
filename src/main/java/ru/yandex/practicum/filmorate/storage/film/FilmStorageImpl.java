package ru.yandex.practicum.filmorate.storage.film;

import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.Film;

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

    private int nexId() {
        return id++;
    }

    private boolean checkId(Integer id) {
        return filmHashMap.containsKey(id);
    }
}
