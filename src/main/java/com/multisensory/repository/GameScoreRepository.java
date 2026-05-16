package com.multisensory.repository;

import com.multisensory.entity.GameScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GameScoreRepository extends JpaRepository<GameScore, Long> {
    List<GameScore> findByUserId(String userId);
    List<GameScore> findByUnitId(Long unitId);
    List<GameScore> findAll();
}