package org.julie.architecture.hw5;

public interface UserDao {
    User findById(int id);
    void save(User user);
    void update(User user);
    void delete(User user);
}