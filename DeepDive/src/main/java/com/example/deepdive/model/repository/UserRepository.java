package com.example.deepdive.model.repository;

import com.example.deepdive.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Long, User> {


}
