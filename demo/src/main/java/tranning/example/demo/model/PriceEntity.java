package tranning.example.demo.model;

import java.time.LocalDateTime;
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
@Table(name = "price")
@Data
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PriceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "type_price")
    Integer typePrice;
    @Column(name = "price")
    Long price;
    @Column(name = "time_start")
    LocalDateTime timeStart;
    @Column(name = "status")
    Integer status;
    @Column(name = "time_end")
    LocalDateTime timeEnd;
    @Column(name = "yard_id")
    Long yardId;

}
