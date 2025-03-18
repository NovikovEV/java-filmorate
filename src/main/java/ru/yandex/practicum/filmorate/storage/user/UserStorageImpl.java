package ru.yandex.practicum.filmorate.storage.user;

import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class UserStorageImpl implements UserStorage {
    private final HashMap<Integer, User> userHashMap = new HashMap<>();
    private Integer id = 1;

    @Override
    public User create(User user) {
        user.setId(nexId());
        userHashMap.put(user.getId(), user);
        return userHashMap.get(user.getId());
    }

    @Override
    public Optional<User> update(User user) {
        Optional<User> updatedUser = Optional.empty();

        if (checkId(user.getId())) {
            userHashMap.replace(user.getId(), user);
            updatedUser = Optional.of(userHashMap.get(user.getId()));
        }
        return updatedUser;
    }

    @Override
    public List<User> getAll() {
        return userHashMap.values().stream().toList();
    }

    private int nexId() {
        return id++;
    }

    private boolean checkId(Integer id) {
        return userHashMap.containsKey(id);
    }
}
