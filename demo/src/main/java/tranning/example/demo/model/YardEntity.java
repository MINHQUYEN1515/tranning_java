package tranning.example.demo.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    Date time_start;
    Date time_end;
    Integer count_yarn_item;
    String address;
    Long price_morning;
    Long price_afternoon;
    Long price_evening;
    @CreationTimestamp
    Date created_at;
    @UpdateTimestamp
    Date updated_at;

}
