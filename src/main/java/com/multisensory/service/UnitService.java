package com.multisensory.service;

import com.multisensory.entity.Unit;
import com.multisensory.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UnitService {
    @Autowired
    private UnitRepository unitRepository;

    // Tüm üniteleri getir
    public List<Unit> getAllUnits() {
        return unitRepository.findAll();
    }

    // Sınıf seviyesine göre üniteleri getir
    public List<Unit> getUnitsByGrade(Integer grade) {
        return unitRepository.findByGrade(grade);
    }

    // ID'ye göre üniteyi getir
    public Optional<Unit> getUnitById(Long id) {
        return unitRepository.findById(id);
    }

    // Yeni ünite oluştur
    public Unit createUnit(Unit unit) {
        return unitRepository.save(unit);
    }

    // Üniteyi güncelle
    public Unit updateUnit(Long id, Unit unitDetails) {
        Optional<Unit> unit = unitRepository.findById(id);
        if (unit.isPresent()) {
            Unit u = unit.get();
            u.setGrade(unitDetails.getGrade());
            u.setName(unitDetails.getName());
            return unitRepository.save(u);
        }
        return null;
    }

    // Üniteyi sil
    public void deleteUnit(Long id) {
        unitRepository.deleteById(id);
    }
}