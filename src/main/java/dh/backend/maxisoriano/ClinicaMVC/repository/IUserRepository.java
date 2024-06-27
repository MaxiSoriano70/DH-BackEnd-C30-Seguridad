package dh.backend.maxisoriano.ClinicaMVC.repository;

import dh.backend.maxisoriano.ClinicaMVC.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
