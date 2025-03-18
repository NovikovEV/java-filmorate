package ru.yandex.practicum.filmorate.dto;

import java.time.LocalDate;

/**
 * DTO for {@link ru.yandex.practicum.filmorate.model.User}
 */
public record OutcomeUserDto(
        int id,
        String email,
        String login,
        String name,
        LocalDate birthday
) {
}