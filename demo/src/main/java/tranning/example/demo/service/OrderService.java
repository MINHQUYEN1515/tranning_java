package tranning.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tranning.example.demo.Config.Constant;
import tranning.example.demo.dto.request.ChangeTimeOrder;
import tranning.example.demo.dto.request.DeleteOrderRequest;
import tranning.example.demo.dto.request.Orderrequest;
import tranning.example.demo.model.OrderEntity;
import tranning.example.demo.model.OrdersDetail;
import tranning.example.demo.model.ReportEntity;
import tranning.example.demo.model.YardEntity;
import tranning.example.demo.model.enums.StatusOrder;
import tranning.example.demo.reponsitories.OrderDetailRepositories;
import tranning.example.demo.reponsitories.OrderReposotories;
import tranning.example.demo.reponsitories.ReportReposirories;
import tranning.example.demo.reponsitories.YardRepositories;

@Service
public class OrderService {
        @Autowired
        private OrderReposotories orderReposotories;
        @Autowired
        private OrderDetailRepositories detailRepositories;
        @Autowired
        private YardRepositories yardRepositories;
        @Autowired
        private ReportReposirories reportReposirories;

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
                                        yard.getTimeEservations() + "," + request.getTime_start() + "-"
                                                        + request.getTime_end());
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
                                        yard.getTimeEservations() + "," + request.getTime_start() + "-"
                                                        + request.getTime_end());

                        ordersDetail.setStatus(StatusOrder.getStatus(StatusOrder.MOIDAT.name()));
                        entity.setSumBill(entity.getSumBill() + request.getPrice());
                        entity.setStatus(StatusOrder.getStatus(StatusOrder.MOIDAT.name()));
                        detailRepositories.save(ordersDetail);
                        orderReposotories.save(entity);

                }

        }

        @Transactional
        public void deleteOrder(DeleteOrderRequest request) {
                OrderEntity orderEntity = orderReposotories.findById(request.getOrder_id())
                                .orElseThrow(() -> new RuntimeException("Order Not found"));
                OrdersDetail ordersDetail = detailRepositories.findById(request.getOrder_detail_id())
                                .orElseThrow(() -> new RuntimeException("Order Not found"));
                YardEntity yardEntity = yardRepositories.findById(ordersDetail.getYardId())
                                .orElseThrow(() -> new RuntimeException("Yard Not found"));

                orderEntity.setSumBill(orderEntity.getSumBill() - ordersDetail.getPrice());
                String time_replace = yardEntity.getTimeEservations().replace(
                                ordersDetail.getTimeStart() + "-" + ordersDetail.getTimeEnd(),
                                "");
                yardEntity.setTimeEservations(time_replace);
                if (orderEntity.getSumBill() == 0) {
                        orderReposotories.delete(orderEntity);
                }
                // Tạo report
                ReportEntity reportEntity = new ReportEntity();
                reportEntity.setContent(request.getMessage_delete());
                reportEntity.setOrderDetailId(ordersDetail.getId());
                reportEntity.setStatus(1);
                reportEntity.setType(Constant.typeReport.get("report_delete"));
                // Tạo hóa đơn mới
                ordersDetail.setStatus(0);
                detailRepositories.save(ordersDetail);
                yardRepositories.save(yardEntity);
                reportReposirories.save(reportEntity);
        }

        @Transactional
        public void changeTime(ChangeTimeOrder request) {
                OrdersDetail ordersDetail = detailRepositories.findById(request.getOrder_detail_id())
                                .orElseThrow(() -> new RuntimeException("Order Detail not found"));
                // Xóa hóa đơn cũ
                ordersDetail.setStatus(0);
                YardEntity yardEntity = yardRepositories.findById(request.getYard_id())
                                .orElseThrow(() -> new RuntimeException("Yard not found"));
                // Lấy giá cũ
                Long price_current = ordersDetail.getPrice();
                // Checl thời gian trống

                if (yardEntity.getTimeEservations() != null && yardEntity.getTimeEservations()
                                .contains(request.getTime_start() + "-" + request.getTime_end())) {
                        throw new RuntimeException("Sân đã có người đặt");
                }
                // Thay thế thời gian đặt sân

                if (yardEntity.getTimeEservations() == null || yardEntity.getTimeEservations().isEmpty()) {
                        yardEntity.setTimeEservations(request.getTime_start() + "-" + request.getTime_end());
                } else {
                        String time_replace = yardEntity.getTimeEservations().replace(
                                        ordersDetail.getTimeStart() + "-" + ordersDetail.getTimeEnd(),
                                        request.getTime_start() + "-" + request.getTime_end());
                        yardEntity.setTimeEservations(time_replace);
                }
                // * */ Lưu vào database

                orderReposotories.setSumbill(price_current, ordersDetail.getOrderId(), request.getPrice());
                // Tạo hóa đơn mới
                OrdersDetail newOrder = new OrdersDetail();
                newOrder.setPrice(request.getPrice());
                newOrder.setTimeStart(request.getTime_start());
                newOrder.setTimeEnd(request.getTime_end());
                newOrder.setYardId(request.getYard_id());
                newOrder.setOrderId(ordersDetail.getOrderId());
                newOrder.setStatus(1);
                // Tạo report

                ReportEntity reportEntity = new ReportEntity();
                reportEntity.setContent(request.getMessage_report());
                reportEntity.setOrderDetailId(ordersDetail.getId());
                reportEntity.setStatus(1);
                reportEntity.setType(Constant.typeReport.get("report_change_time"));

                // Lưu
                detailRepositories.save(ordersDetail);
                detailRepositories.save(newOrder);
                yardRepositories.save(yardEntity);
                reportReposirories.save(reportEntity);

        }

}