package tranning.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tranning.example.demo.dto.request.Orderrequest;
import tranning.example.demo.model.OrderEntity;
import tranning.example.demo.model.OrdersDetail;
import tranning.example.demo.model.YardEntity;
import tranning.example.demo.model.enums.StatusOrder;
import tranning.example.demo.reponsitories.OrderDetailRepositories;
import tranning.example.demo.reponsitories.OrderReposotories;
import tranning.example.demo.reponsitories.YardRepositories;

@Service
public class OrderService {
    @Autowired
    private OrderReposotories orderReposotories;
    @Autowired
    private OrderDetailRepositories detailRepositories;
    @Autowired
    private YardRepositories yardRepositories;

    @Transactional
    public void saveOrder(Orderrequest request) {
        OrderEntity entity = orderReposotories.existsByUser(request.getName(), request.getPhone());

        YardEntity yard = yardRepositories.findById(request.getYard_id())
                .orElseThrow(() -> new RuntimeException("Không thể tìm thấy sân"));

        if (yard.getTimeEservations().contains(request.getTime_start() + "-" + request.getTime_end())) {
            throw new RuntimeException("Sân đã có người đặt");
        }
        if (entity == null || yard.getTimeEservations() == null) {
            // Tạo order
            OrderEntity entity_temp = new OrderEntity();
            entity_temp.setName(request.getName());
            entity_temp.setPhone(request.getPhone());
            entity_temp.setStatus(StatusOrder.getStatus(StatusOrder.MOIDAT.name()));
            entity_temp.setSumBill(request.getPrice());
            // Lưu thời gian người đặt nếu trùng thí cảnh báo sân đã có người đặt

            yard.setTimeEservations(
                    yard.getTimeEservations() + "," + request.getTime_start() + "-" + request.getTime_end());
            orderReposotories.save(entity_temp);
            // Tạo order item
            Long id = orderReposotories.getId(request.getName(), request.getPhone());
            OrdersDetail ordersDetail = new OrdersDetail();
            ordersDetail.setTimeStart(request.getTime_start());
            ordersDetail.setTimeEnd(request.getTime_end());
            ordersDetail.setPrice(request.getPrice());
            ordersDetail.setOrderId(id);
            ordersDetail.setYardId(request.getYard_id());
            ordersDetail.setStatus(StatusOrder.getStatus(StatusOrder.MOIDAT.name()));
            detailRepositories.save(ordersDetail);

        } else {
            // Tim order item

            OrdersDetail ordersDetail = new OrdersDetail();
            ordersDetail.setTimeStart(request.getTime_start());
            ordersDetail.setTimeEnd(request.getTime_end());
            ordersDetail.setPrice(request.getPrice());
            ordersDetail.setOrderId(entity.getId());
            ordersDetail.setYardId(request.getYard_id());
            // Lưu thời gian người đặt nếu trùng thí cảnh báo sân đã có người đặt

            yard.setTimeEservations(
                    yard.getTimeEservations() + "," + request.getTime_start() + "-" + request.getTime_end());

            ordersDetail.setStatus(StatusOrder.getStatus(StatusOrder.MOIDAT.name()));
            entity.setSumBill(entity.getSumBill() + request.getPrice());
            entity.setStatus(StatusOrder.getStatus(StatusOrder.MOIDAT.name()));
            detailRepositories.save(ordersDetail);
            orderReposotories.save(entity);

        }

    }

}