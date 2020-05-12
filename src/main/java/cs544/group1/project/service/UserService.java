package cs544.group1.project.service;

import cs544.group1.project.domain.User;

import java.util.List;

public interface UserService {
    void save(User user);
    List<User> findAll();
    User update(int userId, String password);
    void delete(int userId);
    User findById(int userId);
}
