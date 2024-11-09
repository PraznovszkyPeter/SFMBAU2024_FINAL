package hu.unideb.inf.sfm.bau_javafx.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
    List<User> findAllByOrderByUsernameAsc();
}
