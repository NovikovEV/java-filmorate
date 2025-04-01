package ru.yandex.practicum.filmorate.storage.user;

import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    @Override
    public Optional<Boolean> addFriend(Integer userId, Integer friendId) {
        if (checkId(userId) && checkId(friendId)) {
            User user = userHashMap.get(userId);
            User friend = userHashMap.get(friendId);
            user.setFriendId(friendId);
            friend.setFriendId(userId);
            update(user);
            update(friend);

            return Optional.of(true);
        }

        return Optional.empty();
    }

    @Override
    public Optional<Boolean> removeFriend(Integer userId, Integer friendId) {
        if (checkId(userId) && checkId(friendId)) {
            User user = userHashMap.get(userId);
            User friend = userHashMap.get(friendId);
            user.removeFriendId(friendId);
            friend.removeFriendId(userId);
            update(user);
            update(friend);

            return Optional.of(true);
        }

        return Optional.empty();
    }

    @Override
    public Optional<List<User>> getCommonFriends(Integer userId, Integer friendId) {
        if (checkId(userId) && checkId(friendId)) {
            Set<Integer> user = userHashMap.get(userId).getFriendIds();
            Set<Integer> friend = userHashMap.get(friendId).getFriendIds();
            List<User> users = user.stream()
                    .filter(friend::contains)
                    .map(userHashMap::get)
                    .toList();

            return Optional.of(users);
        }

        return Optional.empty();
    }

    @Override
    public Optional<List<User>> getUserFriends(Integer userId) {
        if (checkId(userId)) {
            return Optional.of(
                    userHashMap.get(userId).getFriendIds().stream()
                            .map(userHashMap::get)
                            .toList()
            );
        }

        return Optional.empty();
    }

    @Override
    public Optional<Boolean> checkUserId(Integer id) {
        if (checkId(id)) {
            return Optional.of(true);
        }

        return Optional.empty();
    }

    private int nexId() {
        return this.id++;
    }

    private boolean checkId(Integer id) {
        return userHashMap.containsKey(id);
    }
}
