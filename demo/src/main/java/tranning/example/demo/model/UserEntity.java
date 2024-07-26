package tranning.example.demo.model;

import java.net.InetAddress;
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

@Table(name = "user")
@FieldDefaults(level = AccessLevel.PRIVATE)

@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String user_name;

    public Long getId() {
        return id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getImage() {
        return image != null ? InetAddress.getLoopbackAddress().getHostName() + ":8080" + "/api/v1/user/image/" + image
                : image;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public String getRole() {
        return role;
    }

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
