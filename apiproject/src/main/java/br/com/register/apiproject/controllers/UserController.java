package br.com.register.apiproject.controllers;

import br.com.register.apiproject.models.UserModel;
import br.com.register.apiproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/users/v1")
public class UserController {
    @Autowired
    UserService service;

    //findById
    @GetMapping(value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserModel> findById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(200).body(service.findById(id));
    }

    //findAll
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserModel>> findAll() {
        return ResponseEntity.status(200).body(service.findAll());
    }

    //createId
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserModel> create(@RequestBody UserModel addUser) {
        return ResponseEntity.status(201).body(service.create(addUser));
    }

    //updateId
    @PutMapping(value = "{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserModel> update(@RequestBody UserModel updateUser) {
        return ResponseEntity.status(201).body(service.update(updateUser));
    }

    //deleteId
    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
