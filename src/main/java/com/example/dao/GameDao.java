package com.example.dao;

import com.example.domain.GameDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameDao extends JpaRepository<GameDomain,Long> {

}
