package com.luismolina.quipux.database.repos;

import com.luismolina.quipux.database.entity.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayListRepo extends JpaRepository<PlayList, Integer> {
    Optional<PlayList> findByName(String name);
    Integer deleteByName(String name);
}
