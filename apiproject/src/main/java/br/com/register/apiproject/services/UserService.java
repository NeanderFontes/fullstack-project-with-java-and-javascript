package br.com.register.apiproject.services;

import br.com.register.apiproject.models.UserModel;
import br.com.register.apiproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public UserModel findById(Long id) {
        UserModel entityFindById = repository.findById(id).orElseThrow();
        return entityFindById;
    }

    public List<UserModel> findAll() {
        return repository.findAll();
    }

    public UserModel create(UserModel addUser) {
        UserModel entityCreate = repository.save(addUser);
        return entityCreate;
    }

    public UserModel update(UserModel updateUser) {
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
}
