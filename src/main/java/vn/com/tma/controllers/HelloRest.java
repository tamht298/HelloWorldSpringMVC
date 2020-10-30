package vn.com.tma.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.tma.models.HelloWorld;
import vn.com.tma.services.UserService;

@RestController
@RequestMapping(value = "/api")
public class HelloRest {

    private Logger logger = Logger.getLogger(HelloRest.class);
    private MongoTemplate mongoTemplate;
    private UserService userService;

    @Autowired
    public HelloRest(MongoTemplate mongoTemplate, UserService userService) {
        this.mongoTemplate = mongoTemplate;
        this.userService = userService;
    }

    @GetMapping(value = "/hello", produces = MediaType.TEXT_PLAIN_VALUE)
    public String hello() {
        logger.info("hello: "+ mongoTemplate);
        return "Hello World";
    }

    @GetMapping(value = "/hello-json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HelloWorld> helloJson() {
        HelloWorld helloWorld = new HelloWorld("hello");
        return ResponseEntity.ok(helloWorld);
    }

}
