package com.perceiveindia.user.manager;
import com.perceiveindia.user.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserManager {
    void addUser(User user1) throws SQLException;
    void updateUser(User user) throws SQLException;
    List<User> listUsers();
    void deleteUser(int id) throws SQLException;
    List<User> searchUsers(String keyword);
}
