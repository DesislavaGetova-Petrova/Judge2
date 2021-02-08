package dessy.part_one.service;

import dessy.part_one.model.entity.User;
import dessy.part_one.model.service.UserServiceModel;

import java.util.List;

public interface UserService {
    UserServiceModel registerUser(UserServiceModel userServiceModel);

    UserServiceModel findUserByUsernameAndPassword(String username, String password);

    void login(UserServiceModel userServiceModel);

    void lodout();

    List<String> findAllUserNames();

    void changeRole(String username, String role);

    User findByUserId(String id);
}
