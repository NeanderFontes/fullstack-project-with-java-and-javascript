package br.com.register.apiproject.repositories;

import br.com.register.apiproject.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findByNameOrEmail(String name, String email);
}
