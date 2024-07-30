package tranning.example.demo.dto.response;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import tranning.example.demo.model.OrderEntity;
import tranning.example.demo.model.PriceEntity;

@Data
@Getter
@Setter
public class YardReponse {
    Long id;
    @JsonFormat(pattern = "HH:mm:ss")
    LocalTime timeStart;
    @JsonFormat(pattern = "HH:mm:ss")
    LocalTime timeEnd;
    String name;
    String address;
    String image;
    Integer status;
    List<PriceEntity> price;
    List<OrderReponse> timeEservations;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime updatedAt;
}
