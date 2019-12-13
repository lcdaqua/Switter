package Switter.Repose;

import Switter.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepose extends JpaRepository<User, Long> {
    User findByUsername(String name);
}
