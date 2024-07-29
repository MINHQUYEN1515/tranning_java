package tranning.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tranning.example.demo.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/v1/order")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class OrderController {
    @Autowired
    private OrderService orderService;

}
