package tranning.example.demo.reponsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tranning.example.demo.model.OrdersDetail;

@Repository
public interface OrderDetailRepositories extends JpaRepository<OrdersDetail, Long> {

}
