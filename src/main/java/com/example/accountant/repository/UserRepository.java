package com.example.accountant.repository;

import com.example.accountant.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {


//    @Modifying(clearAutomatically = true, flushAutomatically = true)
//    @Query(value = "UPDATE users SET firstname = :newName WHERE id = :userId",
//            nativeQuery = true)
//    int updateUser(Long userId, String newName);

//    @Query(value = "SELECT * FROM users WHERE firstname = :firstName",
//            nativeQuery = true)
//    Optional<User> findByFirstName(String firstName);
}
