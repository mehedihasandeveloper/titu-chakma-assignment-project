package com.mehedihasandev.inventoryCRUD.repository;

import com.mehedihasandev.inventoryCRUD.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
