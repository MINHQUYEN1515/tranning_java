package tranning.example.demo.dto.response;

import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import tranning.example.demo.model.OrdersDetail;

@Data
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderReponse {
    String name;
    String phone;
    List<OrdersDetail> orderDetailList = new ArrayList<>();
}
