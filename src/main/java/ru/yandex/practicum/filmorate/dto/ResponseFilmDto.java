package ru.yandex.practicum.filmorate.dto;

import java.time.LocalDate;

/**
 * DTO for {@link ru.yandex.practicum.filmorate.model.Film}
 */
public record ResponseFilmDto(
        int id,
        String name,
        String description,
        LocalDate releaseDate,
        int duration
) {
}