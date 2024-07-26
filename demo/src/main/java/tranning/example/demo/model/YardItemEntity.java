package tranning.example.demo.model;

import java.net.InetAddress;
import java.net.UnknownHostException;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;

import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "yard_item")

@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class YardItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String image;
    Long yard_id;

    public Long getId() {
        return id;
    }

    public String getImage() throws UnknownHostException {

        return image != null ? InetAddress.getLoopbackAddress().getHostName() + ":8080" + "/api/v1/yard/image/" + image
                : image;
    }

    public Long getYard_id() {
        return yard_id;
    }
}
