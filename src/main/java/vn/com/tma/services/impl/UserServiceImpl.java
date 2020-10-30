package vn.com.tma.services.impl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.tma.models.User;
import vn.com.tma.repositories.UserRepo;
import vn.com.tma.services.UserService;

import java.util.*;

import org.apache.log4j.Logger;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Update.update;

@Service
@Transactional
public class UserServiceImpl extends Thread implements UserService {

    private Logger logger = Logger.getLogger(UserServiceImpl.class);
    private UserRepo userRepo;

    private MongoTemplate mongoTemplate;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, MongoTemplate mongoTemplate) {
        this.userRepo = userRepo;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<User> getAllUsers() {
        return mongoTemplate.findAll(User.class);
//        return userRepo.findAll();
    }

    @Override
    public boolean updateUser(User user) {
        logger.info(mongoTemplate);
        Optional<User> isPresentUser = userRepo.findByUsername(user.getUsername());
        if (!isValidateUsername(user.getUsername()) || !isPresentUser.isPresent()) {
            return false;
        }
        User existUser = isPresentUser.get();
        Query query = new Query(Criteria.where("username").is(user.getUsername()));

        if (user.getLocation() != null) {
            mongoTemplate.findAndModify(query, Update.update("location", user.getLocation()), User.class);
        }
        if (user.getPosition() != null) {
            mongoTemplate.findAndModify(query, Update.update("position", user.getPosition()), User.class);
        }

        Thread t1 = new Thread(){
            @Override
            public void run() {
                mongoTemplate.findAndModify(query, new Update().inc("count", 1), User.class);

            }
        };
        t1.start();
        mongoTemplate.findAndModify(query, new Update().inc("count", 1), User.class);

        return true;

    }

    @Override
    public boolean isValidateUsername(String username) {
        if (username == null || username == "")
            return false;
        return true;
    }

    @Override
    public User getUserByUsername(String username) {
        Optional<User> userOptional = userRepo.findByUsername(username);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        return null;
    }

    @Override
    public User createUser(User user) {
        user.set_id(ObjectId.get());
        logger.info(user);
//        return mongoTemplate.insert(user);
        return mongoTemplate.save(user, "user");
    }


}
