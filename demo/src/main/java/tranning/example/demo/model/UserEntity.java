package tranning.example.demo.model;

import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Table(name = "user")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String user_name;
    String image;
    // @UniqueEmail
    String email;
    @JsonIgnore
    String password;
    @CreationTimestamp
    LocalDateTime created_at;
    @UpdateTimestamp
    Date updated_at;
    String role;

}
