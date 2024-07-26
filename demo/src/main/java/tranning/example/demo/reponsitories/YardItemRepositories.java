package tranning.example.demo.reponsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tranning.example.demo.model.YardItemEntity;

@Repository
public interface YardItemRepositories extends JpaRepository<YardItemEntity, Long> {

}
