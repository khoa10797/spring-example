package com.dangkhoa.springexample.controller;

import com.dangkhoa.springexample.entities.User;
import com.dangkhoa.springexample.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class DemoController {

    @Autowired
    UserRepository repository;

    @GetMapping("")
    ResponseEntity hello() {
        return new ResponseEntity<>("Hello world", HttpStatus.OK);
    }

    @GetMapping("add/{name}")
    ResponseEntity insert(@PathVariable("name") String name) {
        repository.save(new User(new ObjectId(), name));
        return new ResponseEntity<>("Insert success!", HttpStatus.OK);
    }

    @PostMapping("add/")
    ResponseEntity insert(@RequestBody User user) {
        repository.save(user);
        return new ResponseEntity<>("Insert success!", HttpStatus.OK);
    }
}
