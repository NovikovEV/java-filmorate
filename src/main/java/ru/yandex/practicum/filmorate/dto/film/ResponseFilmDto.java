package ru.yandex.practicum.filmorate.dto.film;

import java.time.LocalDate;

/**
 * DTO for {@link ru.yandex.practicum.filmorate.model.Film}
 */
public record ResponseFilmDto(
        Integer id,
        String name,
        String description,
        LocalDate releaseDate,
        int duration
) {
}