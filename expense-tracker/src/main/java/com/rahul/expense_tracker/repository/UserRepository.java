package com.rahul.expense_tracker.repository;

import com.rahul.expense_tracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
 public User findByUsername(String username);
}
