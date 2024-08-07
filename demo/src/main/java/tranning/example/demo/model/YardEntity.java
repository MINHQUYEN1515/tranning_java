package tranning.example.demo.model;

import java.net.InetAddress;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
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
@Table(name = "yard")
@Data
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class YardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @JsonFormat(pattern = "HH:mm:ss")
    @Column(name = "time_start")
    LocalDateTime timeStart;
    @JsonFormat(pattern = "HH:mm:ss")
    @Column(name = "time_end")
    LocalDateTime timeEnd;
    @Column(name = "address")
    String address;
    @Column (name = "status")
   
    Integer status;
    @Column(name = "image")
    String image;
    @Column(name = "name")
    String name;
    @Column(name = "time_eservations")
    String timeEservations;
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_at")
    LocalDateTime createdAt;
    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    public String getImageName() {
        return image;
    }
    public String getImage() {
        return image != null ? InetAddress.getLoopbackAddress().getHostName() + ":8080" + "/api/v1/user/image/" + image
                : image;
    }

   


    

}
