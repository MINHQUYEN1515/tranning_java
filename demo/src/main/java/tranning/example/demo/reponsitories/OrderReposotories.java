package tranning.example.demo.reponsitories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tranning.example.demo.model.OrderEntity;

@Repository
public interface OrderReposotories extends JpaRepository<OrderEntity, Long> {
    @Query(value = "Select orders.* From orders,order_detail,yard where orders.id=order_detail.order_id and order_detail.yard_id=:id  group by orders.id", nativeQuery = true)
    public List<OrderEntity> findOrderInYard(@Param("id") Long id);

    @Query(value = "select * from orders where name=:name and phone=:phone", nativeQuery = true)
    public OrderEntity existsByUser(@Param("name") String name, @Param("phone") String phone);

    @Query(value = "select id from orders where name=:name and phone=:phone", nativeQuery = true)
    public Long getId(@Param("name") String name, @Param("phone") String phone);

    @Modifying
    @Query(value = "update orders set sum_bill=((sum_bill-:price_current)+:price_new) where orders.id=:id ", nativeQuery = true)
    public void setSumbill(@Param("price_current") Long price_current, @Param("id") Long id,
            @Param("price_new") Long price_new);

}
