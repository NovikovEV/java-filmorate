package ru.yandex.practicum.filmorate.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import ru.yandex.practicum.filmorate.group.validation.user.UserCreateValidation;
import ru.yandex.practicum.filmorate.group.validation.user.UserUpdateValidation;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

/**
 * DTO for {@link User}
 */
public record RequestUserDto(
        @NotNull(message = "Поле id пустое", groups = UserUpdateValidation.class)
        @Positive(message = "Поле id должно быть положительным", groups = UserUpdateValidation.class)
        Integer id,
        @Email(message = "Не верный формат e-mail", regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", groups = {UserCreateValidation.class, UserUpdateValidation.class})
        @NotEmpty(message = "поле e-mail пустое", groups = {UserCreateValidation.class, UserUpdateValidation.class})
        @NotBlank(groups = {UserCreateValidation.class, UserUpdateValidation.class})
        String email,
        @NotEmpty(message = "Поле login пустое", groups = {UserCreateValidation.class, UserUpdateValidation.class})
        @NotBlank(message = "Поле login пустое", groups = {UserCreateValidation.class, UserUpdateValidation.class})
        @Pattern(message = "В поле login есть пробелы", regexp = "^[0-9A-Za-z]{6,16}$", groups = {UserCreateValidation.class, UserUpdateValidation.class})
        String login,
        String name,
        @JsonFormat(pattern = "yyyy-MM-dd")
        @Past(groups = {UserCreateValidation.class, UserUpdateValidation.class})
        LocalDate birthday
) {
}