package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.dto.film.RequestFilmDto;
import ru.yandex.practicum.filmorate.dto.film.ResponseFilmDto;
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
    public ResponseFilmDto createFilm(@Valid @RequestBody RequestFilmDto requestFilmDto) {
        return filmService.create(requestFilmDto);
    }

    @PutMapping
    public ResponseFilmDto updateFilm(@Valid @RequestBody RequestFilmDto requestFilmDto) {
        return filmService.update(requestFilmDto);
    }

    @GetMapping
    public List<ResponseFilmDto> getAll() {
        return filmService.getAll();
    }
}
