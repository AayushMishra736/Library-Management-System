package com.SpringBoot.LibraryManagementSystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.SpringBoot.LibraryManagementSystem.Entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{

 @Query(value = "select * from user_table u where u.user_name=?", nativeQuery = true)
 public List<Users> findByuserName(String name);

 @Query(value = "select * from user_table u where u.user_id=?", nativeQuery = true)
 public Users findByuserId(Long value);
 
}
