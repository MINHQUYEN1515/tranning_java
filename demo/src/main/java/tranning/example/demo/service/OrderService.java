package tranning.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tranning.example.demo.reponsitories.OrderReposotories;

@Service
public class OrderService {
    @Autowired
    private OrderReposotories orderReposotories;

}