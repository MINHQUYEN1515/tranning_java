package tranning.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import tranning.example.demo.dto.request.YardRequest;
import tranning.example.demo.dto.response.OrderReponse;
import tranning.example.demo.dto.response.YardReponse;
import tranning.example.demo.mapper.OrderParseOrderReponse;
import tranning.example.demo.mapper.YardEntityToYardReponse;
import tranning.example.demo.mapper.YardRequestToYard;
import tranning.example.demo.model.OrderEntity;
import tranning.example.demo.model.OrdersDetail;
import tranning.example.demo.model.PriceEntity;
import tranning.example.demo.model.YardEntity;
import tranning.example.demo.reponsitories.PriceRepositories;
import tranning.example.demo.reponsitories.YardRepositories;
import tranning.example.demo.reponsitories.OrderDetailRepositories;
import tranning.example.demo.reponsitories.OrderReposotories;

@Service
public class YardService {
    @Autowired
    private YardRepositories yardRepositories;
    @Autowired
    private PriceRepositories priceRepositories;
    @Autowired
    private OrderDetailRepositories orderDetailRepositories;
    @Autowired
    private OrderReposotories orderReposotories;

    private YardRequestToYard mapper;

    private OrderParseOrderReponse orderParseOrderReponse;

    public List<YardReponse> getAll() {
        try {

            List<YardEntity> yard = yardRepositories.findAll();
            List<YardReponse> yardReponses = new ArrayList<YardReponse>();
            for (Integer i = 0; i < yard.size(); i++) {// duyệt qua các sân
                List<OrderReponse> orderReponses = new ArrayList<OrderReponse>();
                YardReponse temp = YardEntityToYardReponse.parseYardReponse(yard.get(i));
                List<PriceEntity> price = priceRepositories.findByIdYard(yard.get(i).getId());// Trả về giá
                List<OrderEntity> order = orderReposotories.findOrderInYard(yard.get(i).getId());// Trả về các order
                for (Integer j = 0; j < order.size(); j++) {// lọc qua các order
                    List<OrdersDetail> orderDetails = orderDetailRepositories.findByOrderId(order.get(j).getId());// Tìm
                                                                                                                  // các
                                                                                                                  // order
                                                                                                                  // item
                    OrderReponse temp1 = orderParseOrderReponse.parse(order.get(j), orderDetails);
                    orderReponses.add(temp1);
                }
                temp.setPrice(price);
                temp.setTimeEservations(orderReponses);
                yardReponses.add(temp);
            }
            return yardReponses;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public void craeteYard(YardRequest request) {
        try {
            YardEntity yard = mapper.parseToYard(request);
            yardRepositories.save(yard);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

}
