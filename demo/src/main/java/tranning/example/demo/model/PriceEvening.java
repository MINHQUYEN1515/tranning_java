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
@Table(name = "price_evening")
@Data
@Getter
@Setter
public class PriceEvening {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "18:00_19:00")
    Long price18Hto19H;
    @Column(name = "19:00_20:00")
    Long price19Hto20H;
    @Column(name = "20:00_21:00")
    Long price20Hto21H;
    @Column(name = "21:00_22:00")
    Long price21Hto22H;
    @Column(name = "22:00_23:00")
    Long price22Hto23H;

}
