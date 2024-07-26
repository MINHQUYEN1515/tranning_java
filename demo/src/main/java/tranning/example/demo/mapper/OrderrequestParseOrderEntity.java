package tranning.example.demo.mapper;

import tranning.example.demo.dto.request.Orderrequest;
import tranning.example.demo.model.OrderEntity;

public class OrderrequestParseOrderEntity {
    public static OrderEntity parsetoEntity(Orderrequest request) {
        OrderEntity entity = new OrderEntity();
        entity.setTime_start(request.getTime_start());
        entity.setTime_end(request.getTime_end());
        entity.setPhone(request.getPhone());
        entity.setYard_item_id(request.getYard_item_id());
        entity.setUser_id(request.getUser_id());
        entity.setPrice(request.getPrice());
        return entity;
    }
}
