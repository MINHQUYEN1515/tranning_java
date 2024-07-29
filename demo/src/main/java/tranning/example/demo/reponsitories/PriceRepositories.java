package tranning.example.demo.reponsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tranning.example.demo.model.PriceEntity;

@Repository
public interface PriceRepositories extends JpaRepository<PriceEntity, Long> {

}
