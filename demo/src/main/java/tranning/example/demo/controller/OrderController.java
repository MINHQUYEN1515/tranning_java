package tranning.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tranning.example.demo.dto.request.Orderrequest;
import tranning.example.demo.dto.response.ApiResponse;
import tranning.example.demo.model.OrderEntity;
import tranning.example.demo.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/order")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/order-yard")
    public ResponseEntity postMethodName(@RequestBody @Valid Orderrequest entity) {
        try {
            OrderEntity entity_reponse = orderService.order(entity);
            return ResponseEntity.ok()
                    .body(new ApiResponse(200, "Create order success!", entity_reponse));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(400, "Create order faild", e.getMessage()));
        }

    }

}
