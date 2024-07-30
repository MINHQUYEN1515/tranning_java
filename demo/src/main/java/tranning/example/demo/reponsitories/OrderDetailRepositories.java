package tranning.example.demo.reponsitories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tranning.example.demo.model.OrdersDetail;

@Repository
public interface OrderDetailRepositories extends JpaRepository<OrdersDetail, Long> {
    @Query(value = "Select * from order_detail where order_id=:id  and status=1 and yard_id=:yard_id", nativeQuery = true)
    public List<OrdersDetail> findByOrderId(@Param("id") Long id, @Param("yard_id") Long yard_id);
}
