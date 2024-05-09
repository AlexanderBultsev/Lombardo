package com.pristine.lombardo.repository;

import com.pristine.lombardo.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByTel(String tel);
}
