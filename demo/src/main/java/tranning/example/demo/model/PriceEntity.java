package tranning.example.demo.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    Long price;
    @Column(name = "time_start")
    LocalDate timeStart;
    @Column(name = "time_end")
    LocalDate timeEnd;
    @JsonIgnore
    @Column(name = "yard_id")
    Long yardId;

}
