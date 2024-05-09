package com.pristine.lombardo.repository;

import com.pristine.lombardo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByTel(String tel);
}
