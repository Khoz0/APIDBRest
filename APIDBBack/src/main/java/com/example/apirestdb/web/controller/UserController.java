package com.example.apirestdb.web.controller;

import com.example.apirestdb.crud.UserRepository;
import com.example.apirestdb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Roberge-Mentec Corentin
 */

@RestController
public class UserController {

    @Autowired
    private UserRepository dao;

    @GetMapping(value="/Users")
    public Iterable<User> getLesUser(){
        return dao.findAll();
    }

    @GetMapping(value="/User/{id}")
    public User getUser(@PathVariable int id){
        return dao.findById(id);
    }

    @PostMapping(value="/Users")
    public void addUser(@RequestBody User u){
        dao.save(u);
    }

    @DeleteMapping(value="/Users/{id}")
    public void deleteUser(@PathVariable int id){
        dao.deleteById(id);
    }

    @PutMapping(value="/Users")
    public void updateUser(@RequestBody User u){
        dao.save(u);
    }
}
