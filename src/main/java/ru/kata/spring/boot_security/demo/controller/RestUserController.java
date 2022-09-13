package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@RestController
public class RestUserController {

    private final UserService userDao;


    @Autowired
    public RestUserController(UserService userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/user")
    public ModelAndView getUserView(Principal principal) {
        ModelAndView mav = new ModelAndView("app");
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

}
