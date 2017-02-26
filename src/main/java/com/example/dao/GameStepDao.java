package com.example.dao;

import com.example.domain.GameStepDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameStepDao extends JpaRepository<GameStepDomain,Long> {
}
