package hu.unideb.inf.sfm.bau_javafx.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.username != :username")
    List<User> findAllExcludingUser(@Param("username") String username);
}
