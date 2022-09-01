package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.configs.MyPasswordEncoder;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MyPasswordEncoder myPasswordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, MyPasswordEncoder myPasswordEncoder) {
        this.userRepository = userRepository;
        this.myPasswordEncoder = myPasswordEncoder;

    }

    @Transactional
    public User addUser(User user) {
        User findUser = userRepository.findByUsername(user.getUsername());
        if (findUser != null) {
            return findUser;
        }
        user.setPassword(myPasswordEncoder.getPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    public User getUser(long id) {
        return userRepository.findById(id).get();
    }

    @SuppressWarnings("unchecked")
    public User getUserByName(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public User editUser(User user) {
        if (userRepository.findById(user.getId()) != null) {
            user.setPassword(myPasswordEncoder.getPasswordEncoder().encode(user.getPassword()));
            return userRepository.save(user);
        }
        return user;
    }

    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}