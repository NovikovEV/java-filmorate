package ru.yandex.practicum.filmorate.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.dto.film.RequestFilmDto;
import ru.yandex.practicum.filmorate.dto.film.ResponseFilmDto;
import ru.yandex.practicum.filmorate.group.validation.film.FilmCreateValidation;
import ru.yandex.practicum.filmorate.group.validation.film.FilmUpdateValidation;
import ru.yandex.practicum.filmorate.service.film.FilmService;

import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController {
    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @PostMapping
    public ResponseFilmDto createFilm(@Validated(FilmCreateValidation.class) @RequestBody RequestFilmDto requestFilmDto) {
        return filmService.create(requestFilmDto);
    }

    @PutMapping
    public ResponseFilmDto updateFilm(@Validated(FilmUpdateValidation.class) @RequestBody RequestFilmDto requestFilmDto) {
        return filmService.update(requestFilmDto);
    }

    @GetMapping
    public List<ResponseFilmDto> getAll() {
        return filmService.getAll();
    }

    @PutMapping("/{filmId}/like/{userId}")
    public void addLike(
            @PathVariable Integer filmId,
            @PathVariable Integer userId
    ) {
        filmService.addLike(filmId, userId);
    }

    @DeleteMapping("/{filmId}/like/{userId}")
    public void removeLike(
            @PathVariable Integer filmId,
            @PathVariable Integer userId
    ) {
        filmService.removeLike(filmId, userId);
    }

    @GetMapping("/popular")
    public List<ResponseFilmDto> getPopularFilms(@RequestParam(name = "count", required = false, defaultValue = "10") int count) {
        return filmService.getPopularFilms(count);
    }

}
