package tranning.example.demo.reponsitories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tranning.example.demo.model.OrderEntity;

@Repository
public interface OrderReposotories extends JpaRepository<OrderEntity, Long> {
    @Query(value = "Select orders.* From orders,order_detail,yard where orders.id=order_detail.order_id and order_detail.yard_id=:id and Day(orders.updated_at)=Day(:date) and Month(orders.updated_at)=Month(:date) group by orders.id", nativeQuery = true)
    public List<OrderEntity> findOrderInYard(@Param("id") Long id, @Param("date") LocalDate date);

    @Query(value = "select * from orders where name=:name and phone=:phone", nativeQuery = true)
    public OrderEntity existsByUser(@Param("name") String name, @Param("phone") String phone);

    @Query(value = "select id from orders where name=:name and phone=:phone", nativeQuery = true)
    public Long getId(@Param("name") String name, @Param("phone") String phone);
}
