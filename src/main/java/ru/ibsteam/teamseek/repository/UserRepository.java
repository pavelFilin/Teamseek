package ru.ibsteam.teamseek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ibsteam.teamseek.domain.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByNickname(String nickname);
    User findByActivationCode(String activationCode);
}
