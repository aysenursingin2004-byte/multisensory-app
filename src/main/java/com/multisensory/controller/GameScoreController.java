package com.multisensory.controller;

import com.multisensory.entity.GameScore;
import com.multisensory.service.GameScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/scores")
@CrossOrigin(origins = "*")
public class GameScoreController {
    @Autowired
    private GameScoreService gameScoreService;

    // GET: Tüm skorları getir
    @GetMapping
    public ResponseEntity<List<GameScore>> getAllScores() {
        List<GameScore> scores = gameScoreService.getAllScores();
        return new ResponseEntity<>(scores, HttpStatus.OK);
    }

    // GET: Kullanıcının skorlarını getir
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<GameScore>> getScoresByUserId(@PathVariable String userId) {
        List<GameScore> scores = gameScoreService.getScoresByUserId(userId);
        return new ResponseEntity<>(scores, HttpStatus.OK);
    }

    // GET: Ünitedeki skorları getir
    @GetMapping("/unit/{unitId}")
    public ResponseEntity<List<GameScore>> getScoresByUnitId(@PathVariable Long unitId) {
        List<GameScore> scores = gameScoreService.getScoresByUnitId(unitId);
        return new ResponseEntity<>(scores, HttpStatus.OK);
    }

    // GET: Kullanıcının başarı yüzdesini getir
    @GetMapping("/user/{userId}/success-rate")
    public ResponseEntity<Double> getSuccessRate(@PathVariable String userId) {
        double successRate = gameScoreService.getSuccessPercentage(userId);
        return new ResponseEntity<>(successRate, HttpStatus.OK);
    }

    // GET: ID'ye göre skoru getir
    @GetMapping("/{id}")
    public ResponseEntity<GameScore> getScoreById(@PathVariable Long id) {
        Optional<GameScore> score = gameScoreService.getScoreById(id);
        return score.map(s -> new ResponseEntity<>(s, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // POST: Yeni skor kaydet
    @PostMapping
    public ResponseEntity<GameScore> createScore(@RequestBody GameScore gameScore) {
        GameScore createdScore = gameScoreService.createScore(gameScore);
        return new ResponseEntity<>(createdScore, HttpStatus.CREATED);
    }

    // PUT: Skoru güncelle
    @PutMapping("/{id}")
    public ResponseEntity<GameScore> updateScore(@PathVariable Long id, @RequestBody GameScore scoreDetails) {
        GameScore updatedScore = gameScoreService.updateScore(id, scoreDetails);
        if (updatedScore != null) {
            return new ResponseEntity<>(updatedScore, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DELETE: Skoru sil
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScore(@PathVariable Long id) {
        gameScoreService.deleteScore(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}