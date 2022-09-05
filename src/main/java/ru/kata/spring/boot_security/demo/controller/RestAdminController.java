package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestAdminController {

    private final UserService userDao;
    private final RoleService roleService;


    @Autowired
    public RestAdminController(UserService userDao, RoleService roleService) {
        this.userDao = userDao;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public ModelAndView getUsers(Principal principal) {
        ModelAndView mav = new ModelAndView("admin");
        mav.addObject("user", userDao.getUserByName(principal.getName()));
        mav.addObject("usersList", userDao.getAllUsers());
        mav.addObject("roleList", roleService.getAllRoles());
        return mav;
    }

    @GetMapping("/api/admin")
    public ResponseEntity<List<User>> getUsers() {
        List<User> userList = userDao.getAllUsers();
        return userList != null && !userList.isEmpty()
                ? new ResponseEntity<>(userList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/user")
    public ModelAndView user(Principal principal) {
        ModelAndView mav = new ModelAndView("user");
        mav.addObject("user", userDao.getUserByName(principal.getName()));
        return mav;
    }

    @GetMapping("/api/user")
    public ResponseEntity<User> getUser(Principal principal) {
        User user = userDao.getUserByName(principal.getName());
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/api/admin")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userDao.addUser(user);
        return newUser != null
                ? new ResponseEntity<>(newUser, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/api/admin")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User editedUser = userDao.editUser(user);
        return editedUser != null
                ? new ResponseEntity<>(editedUser, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/api/admin/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
        User delUser = userDao.getUser(id);
        Boolean del = userDao.deleteUser(id);
        return del ? new ResponseEntity<>(delUser, HttpStatus.OK)
                   : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
