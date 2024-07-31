package com.anderson.testing_docker.entrypoint.user.controller;

import com.anderson.testing_docker.core.user.domain.User;
import com.anderson.testing_docker.core.user.dtos.UserDTO;
import com.anderson.testing_docker.core.user.usecases.ports.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private RegisterUserUseCasePort registerUser;

    @Autowired
    private FindUserByIdUseCasePort findUserById;

    @Autowired
    private FindAllUsersUseCasePort findAllUsers;

    @Autowired
    private DeleteUserUseCasePort deleteUser;

    @Autowired
    private UpdateUserUseCasePort updateUser;

    @PostMapping("/register")
    public ResponseEntity<User> post(@RequestBody UserDTO dto) {
        User user = registerUser.execute(dto);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri())
                .body(user);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<User> get(@PathVariable UUID id) {
        User user = findUserById.execute(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/get")
    public ResponseEntity<List<User>> getAll() {
        List<User> users = findAllUsers.execute();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUser.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<User> put(@PathVariable UUID id, @RequestBody UserDTO dto) {
        User user = updateUser.execute(id, dto);
        return ResponseEntity.ok(user);
    }

}
