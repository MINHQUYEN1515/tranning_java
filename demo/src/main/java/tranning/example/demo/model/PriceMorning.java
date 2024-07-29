package tranning.example.demo.model;

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
@Data
@Getter
@Setter
@Table(name = "price_morning")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PriceMorning {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "6:00_7:00")
    Long price6Hto7H;
    @Column(name = "7:00_8:00")
    Long price7Hto8H;
    @Column(name = "8:00_9:00")
    Long price8Hto9H;
    @Column(name = "9:00_10:00")
    Long price9Hto10H;
    @Column(name = "10:00_11:00")
    Long price10Hto11H;
    @Column(name = "11:00_12:00")
    Long price11Hto12H;

}
