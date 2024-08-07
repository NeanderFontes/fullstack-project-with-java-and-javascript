package br.com.register.apiproject.services;

import br.com.register.apiproject.models.UserModel;
import br.com.register.apiproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository) {
        this.repository = repository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public UserModel findById(Long id) {
        UserModel entityFindById = repository.findById(id).orElseThrow();
        return entityFindById;
    }

    public List<UserModel> findAll() {
        return repository.findAll();
    }

    public UserModel create(UserModel addUser) {
        String encryptedPassword = this.passwordEncoder.encode(addUser.getPassword());
        addUser.setPassword(encryptedPassword);
        UserModel entityCreate = repository.save(addUser);
        return entityCreate;
    }

    public UserModel update(UserModel updateUser) {
        String encryptedPassword = this.passwordEncoder.encode(updateUser.getPassword());
        updateUser.setPassword(encryptedPassword);

        UserModel entityUpdate = repository.findById(updateUser.getId()).orElseThrow();

        entityUpdate.setName(updateUser.getName());
        entityUpdate.setEmail(updateUser.getEmail());
        entityUpdate.setCellphone(updateUser.getCellphone());
        entityUpdate.setPassword(updateUser.getPassword());

        repository.save(entityUpdate);
        return entityUpdate;
    }

    public void delete(Long id) {
        UserModel entityDelete = repository.findById(id).orElseThrow();
        repository.delete(entityDelete);
    }

    public Boolean validateUser(UserModel userModelValidaPassword) {
        String password = repository.findById(userModelValidaPassword.getId()).get().getPassword();
        Boolean validate = passwordEncoder.matches(userModelValidaPassword.getPassword(), password);
        return validate;
    }
}
