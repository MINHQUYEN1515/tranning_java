package tranning.example.demo.reponsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tranning.example.demo.model.ReportEntity;

@Repository
public interface ReportReposirories extends JpaRepository<ReportEntity, Long> {

}
