package com.vsvdev.MultiDB.user.repository;

import com.vsvdev.MultiDB.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
