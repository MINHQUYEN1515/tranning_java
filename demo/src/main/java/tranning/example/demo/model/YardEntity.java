package tranning.example.demo.model;

import java.util.Date;

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
    @JsonFormat(pattern = " HH:mm:ss")
    @Column(name = "time_start")
    Date timeStart;
    @JsonFormat(pattern = "HH:mm:ss")
    @Column(name = "time_end")
    Date timeEnd;
    @Column(name = "address")
    String address;
    @Column(name = "image")
    String image;
    @Column(name = "price_morning_id")
    Long priceMorningId;
    @Column(name = "price_afternoon_id")
    Long priceAfternoonId;
    @Column(name = "time_eservations")
    String timeEservations;
    @Column(name = "price_evening_id")
    Long priceEveningId;
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_at")
    Date createdAt;
    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "updated_at")
    Date updatedAt;

}
