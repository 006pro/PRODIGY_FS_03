package com.grocery.grocery.repository;

import com.grocery.grocery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByEmail(String e);
}
