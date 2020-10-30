package vn.com.tma.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document(collection = "user")
public class User {

    @MongoId
    private ObjectId _id;
    private String username;
    private String position;
    private String location;

    public User() {
        this._id = ObjectId.get();
    }

    public User(String username, String position, String location) {
        this.username = username;
        this.position = position;
        this.location = location;
    }
}
