package vn.com.tma.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.com.tma.models.User;

import java.util.Optional;

@Repository
public interface UserRepo extends MongoRepository<User, ObjectId> {
    Optional<User> findByUsername(String username);
}
