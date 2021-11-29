package com.lysenko.Payments.model.repository;

import com.lysenko.Payments.model.entity.user.Role;
import com.lysenko.Payments.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByEmail(String email);
    List<User> findAllByRole(Role role);
    User findUserByUserId(int id);
}
