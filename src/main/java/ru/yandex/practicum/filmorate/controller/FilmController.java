package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.dto.IncomeFilmDto;
import ru.yandex.practicum.filmorate.dto.IncomeFilmWithIdDto;
import ru.yandex.practicum.filmorate.dto.OutcomeFilmDto;
import ru.yandex.practicum.filmorate.service.film.FilmService;

import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController {
    @Autowired
    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

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
