package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.dto.IncomeFilmDto;
import ru.yandex.practicum.filmorate.dto.IncomeFilmWithIdDto;
import ru.yandex.practicum.filmorate.dto.OutcomeFilmDto;
import ru.yandex.practicum.filmorate.service.film.FilmService;
import ru.yandex.practicum.filmorate.service.film.FilmServiceImpl;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;
import ru.yandex.practicum.filmorate.storage.film.FilmStorageImpl;

import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController {
    private final FilmStorage filmStorage = new FilmStorageImpl();
    private final FilmService filmService = new FilmServiceImpl(filmStorage);

    @PostMapping
    public OutcomeFilmDto createFilm(@Valid @RequestBody IncomeFilmDto incomeFilmDto) {
        return filmService.create(incomeFilmDto);
    }

    @PutMapping
    public OutcomeFilmDto updateFilm(@Valid @RequestBody IncomeFilmWithIdDto incomeFilmWithIdDto) {
        return filmService.update(incomeFilmWithIdDto);
    }

    @GetMapping
    public List<OutcomeFilmDto> getAll() {
        return filmService.getAll();
    }
}
