package vn.com.tma.controllers;

import com.mongodb.client.MongoClients;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.tma.models.HelloWorld;
import vn.com.tma.models.User;
import vn.com.tma.services.UserService;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@RestController
@RequestMapping(value = "/api")
public class HelloRest {

    private UserService userService;

    @Autowired
    public HelloRest(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/hello", produces = MediaType.TEXT_PLAIN_VALUE)
    public String hello() {
        return "Hello World";
    }

    @GetMapping(value = "/hello-json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HelloWorld> helloJson() {
        HelloWorld helloWorld = new HelloWorld("hello");
        return ResponseEntity.ok(helloWorld);
    }

}
