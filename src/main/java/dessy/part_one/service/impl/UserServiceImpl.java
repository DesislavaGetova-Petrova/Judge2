package dessy.part_one.service.impl;

import dessy.part_one.model.entity.User;
import dessy.part_one.model.service.UserServiceModel;
import dessy.part_one.repositoty.UserRepository;
import dessy.part_one.security.CurrentUser;
import dessy.part_one.service.RoleService;
import dessy.part_one.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, CurrentUser currentUser, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {
        userServiceModel
                .setRole(this.roleService
                        .findByName(this.userRepository.count() == 0
                                ? "ADMIN" : "USER"));

        User user = this.modelMapper
                .map(userServiceModel, User.class);


        return this.modelMapper
                .map(this.userRepository.saveAndFlush(user), UserServiceModel.class);
    }

    @Override
    public UserServiceModel findUserByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password)
                .map(user -> modelMapper.map(user, UserServiceModel.class))
                .orElse(null);

    }

    @Override
    public void login(UserServiceModel userServiceModel) {
        currentUser.setId(userServiceModel.getId())
                .setUsername(userServiceModel.getUsername())
                .setRole(userServiceModel.getRole().getName());
    }

    @Override
    public void lodout() {
        currentUser.setId(null)
                .setUsername(null)
                .setRole(null);
    }

    @Override
    public List<String> findAllUserNames() {
        return this.userRepository.findAllUserNames();
    }

    @Override
    public void changeRole(String username, String role) {
        User user = userRepository.findByUsername(username).orElse(null);
        if(user.getRole().getName()!=role){
            user.setRole(roleService.findRoleByName(role));
            userRepository.save(user);
        }
    }
}
