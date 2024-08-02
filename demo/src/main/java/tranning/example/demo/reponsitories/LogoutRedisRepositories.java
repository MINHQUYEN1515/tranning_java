package tranning.example.demo.reponsitories;

import org.springframework.data.repository.CrudRepository;

import tranning.example.demo.model.LogoutEntity;

@org.springframework.stereotype.Repository
public interface LogoutRedisRepositories extends CrudRepository<LogoutEntity, String> {

}