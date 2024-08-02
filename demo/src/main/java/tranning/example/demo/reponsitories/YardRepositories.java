package tranning.example.demo.reponsitories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tranning.example.demo.model.YardEntity;

@Repository
public interface YardRepositories extends JpaRepository<YardEntity, Long> {
    @Modifying
    @Query(value = "update yard set status=0 where id=:id", nativeQuery = true)
    public void deleteYard(@Param("id") Long id);

    @Modifying
    @Query(value = "update yard set status=1 where id=:id", nativeQuery = true)
    public void enableYard(@Param("id") Long id);

    @Query(value = "select * from yard where status=1", nativeQuery = true)
    public List<YardEntity> getAll();

}
