// NumberingRepository.java
package org.example.peek.repository;

import org.example.peek.entity.Numbering;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NumberingRepository extends JpaRepository<Numbering, Long> {
    Optional<Numbering> findByNumbering(String numbering);

}