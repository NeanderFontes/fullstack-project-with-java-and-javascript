package br.com.register.apiproject.controllers;

import br.com.register.apiproject.models.UserModel;
import br.com.register.apiproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    //findAll
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserModel>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    //createId
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserModel> create(@RequestBody UserModel addUser) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(addUser));
    }

    //updateId
    @PutMapping(value = "{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserModel> update(@RequestBody UserModel updateUser) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.update(updateUser));
    }

    //deleteId
    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    //Test Login
    @PostMapping(value = "/test-login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserModel> testLogin(@RequestBody UserModel userModelValidaPassword) {
        Boolean validPassword = service.validateUser(userModelValidaPassword);
        if (!validPassword) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
