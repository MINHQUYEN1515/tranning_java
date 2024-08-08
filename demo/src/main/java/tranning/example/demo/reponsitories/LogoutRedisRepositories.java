package tranning.example.demo.reponsitories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tranning.example.demo.model.LogoutEntity;

@Repository
public interface LogoutRedisRepositories extends CrudRepository<LogoutEntity, String> {

}