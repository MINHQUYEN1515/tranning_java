package tranning.example.demo.reponsitories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tranning.example.demo.model.PriceEntity;

@Repository
public interface PriceRepositories extends JpaRepository<PriceEntity, Long> {
    @Query(value = "Select *from price where yard_id=:id and status=1", nativeQuery = true)
    public List<PriceEntity> findByIdYard(@Param("id") Long id);

    @Query(value = "Select * from price where status=1", nativeQuery = true)
    public List<PriceEntity> getAll();

    @Modifying
    @Query(value = "update price set status=0 where id=:id", nativeQuery = true)
    public void deletePrice(@Param("id") Long id);

    @Modifying
    @Query(value = "update price set status=1 where id=:id", nativeQuery = true)
    public void enablePrice(@Param("id") Long id);

}
