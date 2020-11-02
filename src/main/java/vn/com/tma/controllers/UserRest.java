package vn.com.tma.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClients;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.tma.models.User;
import vn.com.tma.repositories.UserRepo;
import vn.com.tma.services.UserService;

import java.util.*;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@RestController
@RequestMapping(value = "/api")
public class UserRest {
    private Logger logger = Logger.getLogger(UserRest.class);

    private UserService userService;
    private UserRepo userRepo;

    @Autowired
    public UserRest(UserService userService, UserRepo userRepo) {
        this.userService = userService;
        this.userRepo = userRepo;
    }

    @GetMapping(value = "/users")
    public List<User> getAllUser() {
        List<User> users = userService.getAllUsers();
        return users;
    }

    @PutMapping(value = "users")
    public ResponseEntity<?> updateUser(@RequestBody User user) throws InterruptedException {
        userService.updateUser(user);
        User updatedUser = userService.getUserByUsername(user.getUsername());
        return ResponseEntity.ok(updatedUser);

    }

}
