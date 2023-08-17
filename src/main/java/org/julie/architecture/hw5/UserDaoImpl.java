package org.julie.architecture.hw5;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private List<User> users;

    public UserDaoImpl() {
        users = new ArrayList<>();
    }

    public User findById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public void save(User user) {
        int newId = users.size() + 1;
        user.setId(newId);
        users.add(user);
    }

    public void update(User user) {
        User existingUser = findById(user.getId());
        if (existingUser != null) {
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
        }
    }

    public void delete(User user) {
        users.remove(user);
    }
}

