package tranning.example.demo.mapper;

import java.util.List;

import tranning.example.demo.dto.response.OrderReponse;
import tranning.example.demo.model.OrderEntity;
import tranning.example.demo.model.OrdersDetail;

public class OrderParseOrderReponse {
    public static OrderReponse parse(OrderEntity order, List<OrdersDetail> ordersDetai) {
        OrderReponse temp = new OrderReponse();
        temp.setName(order.getName());
        temp.setPhone(order.getPhone());
        temp.setOrderDetailList(ordersDetai);
        return temp;
    }
}
