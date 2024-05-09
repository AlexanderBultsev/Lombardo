package com.pristine.lombardo.repository;

import com.pristine.lombardo.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {
}
