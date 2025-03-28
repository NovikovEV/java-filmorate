package ru.yandex.practicum.filmorate.dto.film;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

/**
 * DTO for {@link ru.yandex.practicum.filmorate.model.Film}
 */
public record ResponseFilmDto(
        Integer id,
        String name,
        String description,
        @JsonFormat(pattern = "yyyy-MM-dd") LocalDate releaseDate,
        int duration
) {
}