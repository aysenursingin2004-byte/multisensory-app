package com.multisensory.repository;

import com.multisensory.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {
    List<Unit> findByGrade(Integer grade);
    List<Unit> findAll();
}