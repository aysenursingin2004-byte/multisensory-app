package com.multisensory.controller;

import com.multisensory.entity.Unit;
import com.multisensory.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/units")
@CrossOrigin(origins = "*")
public class UnitController {
    @Autowired
    private UnitService unitService;

    // GET: Tüm üniteleri getir
    @GetMapping
    public ResponseEntity<List<Unit>> getAllUnits() {
        List<Unit> units = unitService.getAllUnits();
        return new ResponseEntity<>(units, HttpStatus.OK);
    }

    // GET: Sınıf seviyesine göre üniteleri getir
    @GetMapping("/grade/{grade}")
    public ResponseEntity<List<Unit>> getUnitsByGrade(@PathVariable Integer grade) {
        List<Unit> units = unitService.getUnitsByGrade(grade);
        return new ResponseEntity<>(units, HttpStatus.OK);
    }

    // GET: ID'ye göre üniteyi getir
    @GetMapping("/{id}")
    public ResponseEntity<Unit> getUnitById(@PathVariable Long id) {
        Optional<Unit> unit = unitService.getUnitById(id);
        return unit.map(u -> new ResponseEntity<>(u, HttpStatus.OK))
                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // POST: Yeni ünite oluştur
    @PostMapping
    public ResponseEntity<Unit> createUnit(@RequestBody Unit unit) {
        Unit createdUnit = unitService.createUnit(unit);
        return new ResponseEntity<>(createdUnit, HttpStatus.CREATED);
    }

    // PUT: Üniteyi güncelle
    @PutMapping("/{id}")
    public ResponseEntity<Unit> updateUnit(@PathVariable Long id, @RequestBody Unit unitDetails) {
        Unit updatedUnit = unitService.updateUnit(id, unitDetails);
        if (updatedUnit != null) {
            return new ResponseEntity<>(updatedUnit, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DELETE: Üniteyi sil
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUnit(@PathVariable Long id) {
        unitService.deleteUnit(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}