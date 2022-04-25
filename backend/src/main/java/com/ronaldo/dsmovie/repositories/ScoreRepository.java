package com.ronaldo.dsmovie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ronaldo.dsmovie.entities.Score;
import com.ronaldo.dsmovie.entities.ScorePK;

public interface ScoreRepository extends JpaRepository<Score, ScorePK> {

}
