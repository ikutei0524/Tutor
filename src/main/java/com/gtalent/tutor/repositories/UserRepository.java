package com.gtalent.tutor.repositories;

import com.gtalent.tutor.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    // 你可以自訂搜尋方法，例如：
    List<User> findByUsernameContainingIgnoreCase(String keyword);
}
