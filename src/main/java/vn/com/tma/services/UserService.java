package vn.com.tma.services;

import vn.com.tma.models.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    boolean updateUser(User user) throws InterruptedException;

    boolean isValidateUsername(String username);

    User getUserByUsername(String username);

    User createUser(User user);

}
