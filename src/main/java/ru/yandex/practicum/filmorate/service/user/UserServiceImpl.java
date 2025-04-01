package ru.yandex.practicum.filmorate.service.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.dto.user.RequestUserDto;
import ru.yandex.practicum.filmorate.dto.user.ResponseUserDto;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.mapper.user.UserMapper;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserStorage userStorage;
    private final UserMapper userMapper;

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserStorage userStorage, UserMapper userMapper) {
        this.userStorage = userStorage;
        this.userMapper = userMapper;
    }

    @Override
    public ResponseUserDto create(RequestUserDto requestUserDto) {
        User user = userMapper.convertRequestUserDtoToUser(requestUserDto);
        if (user.getName() == null) {
            user.setName("common");
        }

        return userMapper.convertUserToResponseUserDto(userStorage.create(user));
    }

    @Override
    public ResponseUserDto update(RequestUserDto requestUserWithIdDto) {
        User user = userMapper.convertRequestUserDtoToUser(requestUserWithIdDto);

        User updatedUser = userStorage.update(user).orElseThrow(() -> {
            final String message = String.format("Пользователь с id=:%d не найден", requestUserWithIdDto.id());
            log.info(message);

            return new NotFoundException(message);
        });

        return userMapper.convertUserToResponseUserDto(updatedUser);
    }

    @Override
    public List<ResponseUserDto> getAll() {
        return userStorage.getAll().stream()
                .map(userMapper::convertUserToResponseUserDto)
                .toList();
    }

    @Override
    public void addFriend(Integer userId, Integer friendId) {
        userStorage.addFriend(userId, friendId).orElseThrow(() -> {
            final String message = String.format("Пользователь с id=%d или id=%d не найден", userId, friendId);
            log.info(message);

            return new NotFoundException(message);
        });
    }

    @Override
    public void removeFriend(Integer userId, Integer friendId) {
        userStorage.removeFriend(userId, friendId).orElseThrow(() -> {
            final String message = String.format("Пользователь с id=%d или id=%d не найден", userId, friendId);
            log.info(message);

            return new NotFoundException(message);
        });
    }

    @Override
    public List<ResponseUserDto> getCommonFriends(Integer userId, Integer friendId) {
        List<User> users = userStorage.getCommonFriends(userId, friendId).orElseThrow(() -> {
            final String message = String.format("Пользователь с id=%d или id=%d не найден", userId, friendId);
            log.info(message);

            return new NotFoundException(message);
        });

        return users.stream()
                .map(userMapper::convertUserToResponseUserDto)
                .toList();
    }

    @Override
    public List<ResponseUserDto> getUserFriends(Integer userId) {
        List<User> users = userStorage.getUserFriends(userId).orElseThrow(() -> {
            final String message = String.format("Пользователь с id=%d не найден", userId);
            log.info(message);

            return new NotFoundException(message);
        });

        return users.stream()
                .map(userMapper::convertUserToResponseUserDto)
                .toList();
    }
}
