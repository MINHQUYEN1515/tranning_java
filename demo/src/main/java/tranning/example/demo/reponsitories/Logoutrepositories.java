package tranning.example.demo.reponsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import tranning.example.demo.model.Logout;

public interface Logoutrepositories extends JpaRepository<Logout, String> {
    @Modifying
    @Query(value = "delete from logout where logout.expiration_date<now()", nativeQuery = true)
    public void deleteRowsLogoutTable();
}
