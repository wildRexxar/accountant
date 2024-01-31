package com.example.accountant.controller;

import com.example.accountant.dto.UserCreateUpdateDto;
import com.example.accountant.dto.UserReadDto;
import com.example.accountant.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<UserReadDto> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return userService.findById(id)
                .map(u -> new ResponseEntity<>(u, HttpStatus.OK))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserCreateUpdateDto userCreateUpdateDto) {
        return userService.create(userCreateUpdateDto)
                .map(u -> new ResponseEntity<>(u, HttpStatus.CREATED))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UserCreateUpdateDto userCreateUpdateDto) {
        return userService.update(id, userCreateUpdateDto)
                .map(u -> new ResponseEntity<>(u, HttpStatus.OK))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (userService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}