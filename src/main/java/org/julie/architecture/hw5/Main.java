package org.julie.architecture.hw5;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();

        User user = new User(1, "John", "Doe");
        userDao.save(user);

        User retrievedUser = userDao.findById(1);
        System.out.println(retrievedUser);

        user.setFirstName("Jane");
        userDao.update(user);

        userDao.delete(user);
    }
}
