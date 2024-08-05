package br.com.register.apiproject.controllers;

import br.com.register.apiproject.models.UserModel;
import br.com.register.apiproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users/v1")
public class UserController {
    @Autowired
    UserService service;

    //findById
    @GetMapping(value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserModel findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    //findAll
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserModel> findAll() {
        return service.findAll();
    }

    //createId
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserModel create(@RequestBody UserModel addUser) {
        return service.create(addUser);
    }

    //updateId
    @PutMapping(value = "{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserModel update(@RequestBody UserModel updateUser) {
        return service.update(updateUser);
    }

    //deleteId
    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
    }
}
