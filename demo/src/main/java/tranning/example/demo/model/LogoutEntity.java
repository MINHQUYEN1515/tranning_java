package tranning.example.demo.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.redis.core.RedisHash;

import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@RedisHash("logout")
@Getter
@Setter
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LogoutEntity implements Serializable {
    @Id
    String id;
    Date expiration_date;

}
