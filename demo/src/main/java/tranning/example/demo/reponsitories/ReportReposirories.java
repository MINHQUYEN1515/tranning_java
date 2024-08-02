package tranning.example.demo.reponsitories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tranning.example.demo.model.ReportEntity;

@Repository
public interface ReportReposirories extends JpaRepository<ReportEntity, Long> {
    @Query(value = "select * from report where status=1 and type=:type", nativeQuery = true)
    public List<ReportEntity> getAll(@Param("type") Integer type);
}
