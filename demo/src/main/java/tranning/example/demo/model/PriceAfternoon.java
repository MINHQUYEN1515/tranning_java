package tranning.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "price_afternoon")
@Data
@Getter
@Setter
public class PriceAfternoon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "12:00_13:00")
    Long price12Hto13H;
    @Column(name = "13:00_14:00")
    Long price13Hto14H;
    @Column(name = "14:00_15:00")
    Long price14Hto15H;
    @Column(name = "15:00_16:00")
    Long price15Hto16H;
    @Column(name = "16:00_17:00")
    Long price16Hto17H;
    @Column(name = "17:00_18:00")
    Long price17Hto18H;
}
