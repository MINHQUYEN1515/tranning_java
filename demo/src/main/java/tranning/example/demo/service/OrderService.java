package tranning.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tranning.example.demo.dto.request.Orderrequest;
import tranning.example.demo.mapper.OrderrequestParseOrderEntity;
import tranning.example.demo.model.OrderEntity;
import tranning.example.demo.model.YardItemEntity;
import tranning.example.demo.model.enums.Status_Yard;
import tranning.example.demo.reponsitories.OrderReposotories;
import tranning.example.demo.reponsitories.YardItemRepositories;

@Service
public class OrderService {
    @Autowired
    private OrderReposotories orderReposotories;

    @Autowired
    private YardItemRepositories yardItemRepositories;

    private OrderrequestParseOrderEntity mapper;

    @Transactional
    public OrderEntity order(Orderrequest request) {
        OrderEntity entity = mapper.parsetoEntity(request);
        orderReposotories.save(entity);
        YardItemEntity yard_item = yardItemRepositories.findById(request.getYard_item_id())
                .orElseThrow(() -> new RuntimeException("Cannot found yard_item"));
        System.out.print(yard_item.getStatus().toString() == Status_Yard.CHUA_DAT.name().toString());
        if (yard_item.getStatus().compareTo(Status_Yard.CHUA_DAT.name()) == 0) {
            yard_item.setStatus(Status_Yard.getStatus(request.getPrice()));
        }
        if (yard_item.getStatus().compareTo(Status_Yard.getStatus(request.getPrice())) == 0) {
            throw new RuntimeException("Khung giờ này đã có người dặt");
        } else {
            yard_item.setStatus(yard_item.getStatus() + "," + Status_Yard.getStatus(request.getPrice()));

        }

        yardItemRepositories.save(yard_item);
        return entity;
    }
}