package com.example.apirestdb.crud;

import com.example.apirestdb.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    Iterable<User> findByName(String name);

    User findById(int id);

    void deleteById(int id);
}
