package tranning.example.demo.reponsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tranning.example.demo.model.UserEntity;

@Repository
public interface UserRepositories extends JpaRepository<UserEntity, Long> {
    @Query(value = "SELECT password FROM user WHERE email= :email", nativeQuery = true)
    public String findUser(@Param("email") String email);

    @Query(value = "select * from user where email= :email", nativeQuery = true)
    public UserEntity findByEmail(@Param("email") String email);

    @Query(value = "select image from user where id=:id", nativeQuery = true)
    public String getImage(@Param("id") long id);

}
