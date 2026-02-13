package com.pts.user.repository;

import com.pts.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {


    Optional<User> findById(String id);

    Optional<User> findByPhoneNumber(String phoneNumber);

    Optional<User>  findByEmail(String email);

    Optional<User> findByEmailOrPhoneNumber(String email, String phoneNumber);



}
