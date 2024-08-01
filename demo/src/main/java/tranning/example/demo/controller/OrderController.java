package tranning.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tranning.example.demo.dto.request.ChangeTimeOrder;
import tranning.example.demo.dto.request.DeleteOrderRequest;
import tranning.example.demo.dto.request.Orderrequest;
import tranning.example.demo.dto.response.ApiResponse;
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
    public ResponseEntity postMethodName(@RequestBody @Valid Orderrequest request) {
        try {
            orderService.saveOrder(request);
            return ResponseEntity.ok().body(new ApiResponse(200, "Save order success!", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(400, "Save order faild!", e.getMessage()));

        }
    }

    @PostMapping("/delete-order")
    public ResponseEntity postMethodName(@RequestBody @Valid DeleteOrderRequest request) {
        try {
            orderService.deleteOrder(request);
            return ResponseEntity.ok().body(new ApiResponse(200, "Delete order success!", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(400, "Delete order faild!", e.getMessage()));

        }
    }

    @PostMapping("change-time")
    public ResponseEntity postMethodName(@RequestBody @Valid ChangeTimeOrder request) {
        try {
            orderService.changeTime(request);
            return ResponseEntity.ok().body(new ApiResponse(200, "Change time order success!", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(400, "Change time order faild!", e.getMessage()));

        }
    }

}