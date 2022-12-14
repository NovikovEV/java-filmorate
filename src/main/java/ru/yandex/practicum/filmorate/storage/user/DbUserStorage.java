package ru.yandex.practicum.filmorate.storage.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.UserNotFoundException;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.validator.UserValidator;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Component
@Slf4j
@RequiredArgsConstructor
public class DbUserStorage implements UserStorage {
    private final JdbcTemplate jdbcTemplate;
    private final UserValidator customValidator;


    @Override
    public List<User> getUsers() {
        String sql =
                "SELECT * FROM USERS";
        log.info("Все пользователи :");
        return jdbcTemplate.query(sql, (rs, rowNum) -> makeUser(rs));
    }

    @Override
    public User createUser(User user) {
        if (!customValidator.isValid(user)) {
            log.info("Попытка добавить пользователя с некорректной информацией");
            throw new ValidationException("Некорректно заполнено одно из полей");
        } else if (getUsers().contains(user)) {
            log.error("Такой пользователь уже существует.");
            throw new ValidationException("Такой пользователь уже существует.");
        } else {
            if (user.getName().isBlank()) {
                log.debug("Имя не указано. В качестве имени используется логин.");
                user.setName(user.getLogin());
            }
            KeyHolder keyHolder = new GeneratedKeyHolder();
            final String sql = "INSERT INTO USERS(EMAIL, LOGIN, USER_NAME, BIRTHDAY) values (?, ?, ?, ?)";
            jdbcTemplate.update(connection -> {
                PreparedStatement stmt = connection.prepareStatement(sql, new String[]{"USER_ID"});
                stmt.setString(1, user.getEmail());
                stmt.setString(2, user.getLogin());
                stmt.setString(3, user.getName());
                stmt.setDate(4, Date.valueOf(user.getBirthday()));
                return stmt;
            }, keyHolder);
            user.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());
            log.info("Добавлен пользователь {}", user.getLogin());
            return user;
        }
    }

    @Override
    public User updateUser(User user) {
        if (!getUsers().contains(user)) {
            log.error("Пользователь с id={} не найден.", user.getId());
            throw new UserNotFoundException("Пользователь с id=" + user.getId() + " не найден.");
        } else {
            String sql =
                    "UPDATE USERS " +
                            "SET EMAIL = ?,  LOGIN = ?, USER_NAME = ?, BIRTHDAY = ?" +
                            " WHERE USER_ID = ?";
            jdbcTemplate.update(sql, user.getEmail(), user.getLogin(), user.getName(), user.getBirthday(),
                    user.getId());
            log.info("Обновлён пользователь {}", user.getLogin());
            return user;
        }
    }

    @Override
    public User findById(int id) {
        if (id < 1) {
            throw new UserNotFoundException("Введен некорректный идентификатор пользователя.");
        }

        String sql =
                "SELECT * " +
                        "FROM USERS " +
                        "WHERE USER_ID = ?";

        List<User> users = jdbcTemplate.query(sql, (rs, rowNum) -> makeUser(rs), id);

        if (!users.isEmpty()) {
            User user = new User();

            users.forEach(u -> {
                if (u.getId().equals(id)) {
                    user.setId(u.getId());
                    user.setEmail(u.getEmail());
                    user.setLogin(u.getLogin());
                    user.setName(u.getName());
                    user.setBirthday(u.getBirthday());
                }
            });
            return user;
        } else {
            log.error("Пользователь с id={} не найден.", id);
            throw new UserNotFoundException("Пользователь с id=" + id + " не найден.");
        }
    }

    @Override
    public void deleteUser(User user) {
        if (!customValidator.isValid(user)) {
            log.info("Попытка добавить пользователя с некорректной информацией");
            throw new ValidationException("Некорректно заполнено одно из полей");
        }
        if (!getUsers().contains(user)) {
            throw new UserNotFoundException("Пользователь не найден для удаления.");
        }
        if (user.getId() < 1) {
            throw new UserNotFoundException("Введен некорректный идентификатор пользователя.");
        }
        String sql =
                "DELETE FROM USERS " +
                        "WHERE USER_ID = ?";
        log.info("Удалён пользователь {}", user.getLogin());
        jdbcTemplate.update(sql, user.getId());
    }

    private User makeUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("USER_ID"));
        user.setEmail(rs.getString("EMAIL"));
        user.setLogin(rs.getString("LOGIN"));
        user.setName(rs.getString("USER_NAME"));
        user.setBirthday(rs.getDate("BIRTHDAY").toLocalDate());
        return user;
    }
}
