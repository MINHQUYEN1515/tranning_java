package tranning.example.demo.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

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
    @JsonFormat(pattern = " HH:mm:ss")
    Date time_start;
    @JsonFormat(pattern = "HH:mm:ss")
    Date time_end;
    Integer count_yarn_item;
    String address;
    Long price_morning;
    Long price_afternoon;
    Long price_evening;
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")

    Date created_at;
    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")

    Date updated_at;

}
