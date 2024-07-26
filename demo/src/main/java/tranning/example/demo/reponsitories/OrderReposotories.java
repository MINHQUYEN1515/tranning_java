package tranning.example.demo.reponsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tranning.example.demo.model.OrderEntity;

@Repository
public interface OrderReposotories extends JpaRepository<OrderEntity, Long> {

}
