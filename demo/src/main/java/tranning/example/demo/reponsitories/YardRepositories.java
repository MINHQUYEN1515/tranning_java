package tranning.example.demo.reponsitories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import tranning.example.demo.model.YardEntity;

@Repository
public interface YardRepositories extends JpaRepository<YardEntity, Long> {

}
