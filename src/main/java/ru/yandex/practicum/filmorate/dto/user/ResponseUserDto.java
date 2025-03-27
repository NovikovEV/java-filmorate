package ru.yandex.practicum.filmorate.dto.user;

import java.time.LocalDate;

/**
 * DTO for {@link ru.yandex.practicum.filmorate.model.User}
 */
public record ResponseUserDto(
        Integer id,
        String email,
        String login,
        String name,
        LocalDate birthday
) {
}