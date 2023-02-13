package com.tus.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tus.user.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
