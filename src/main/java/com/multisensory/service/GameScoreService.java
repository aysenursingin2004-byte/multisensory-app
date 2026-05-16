package com.multisensory.service;

import com.multisensory.entity.GameScore;
import com.multisensory.repository.GameScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GameScoreService {
    @Autowired
    private GameScoreRepository gameScoreRepository;

    // Tüm skorları getir
    public List<GameScore> getAllScores() {
        return gameScoreRepository.findAll();
    }

    // Kullanıcının skorlarını getir
    public List<GameScore> getScoresByUserId(String userId) {
        return gameScoreRepository.findByUserId(userId);
    }

    // Ünitedeki skorları getir
    public List<GameScore> getScoresByUnitId(Long unitId) {
        return gameScoreRepository.findByUnitId(unitId);
    }

    // ID'ye göre skoru getir
    public Optional<GameScore> getScoreById(Long id) {
        return gameScoreRepository.findById(id);
    }

    // Yeni skor kaydet
    public GameScore createScore(GameScore gameScore) {
        return gameScoreRepository.save(gameScore);
    }

    // Skoru güncelle
    public GameScore updateScore(Long id, GameScore scoreDetails) {
        Optional<GameScore> score = gameScoreRepository.findById(id);
        if (score.isPresent()) {
            GameScore gs = score.get();
            gs.setCorrectAnswers(scoreDetails.getCorrectAnswers());
            gs.setTotalQuestions(scoreDetails.getTotalQuestions());
            gs.setGameMode(scoreDetails.getGameMode());
            return gameScoreRepository.save(gs);
        }
        return null;
    }

    // Skoru sil
    public void deleteScore(Long id) {
        gameScoreRepository.deleteById(id);
    }

    // Başarı yüzdesini hesapla
    public double getSuccessPercentage(String userId) {
        List<GameScore> scores = gameScoreRepository.findByUserId(userId);
        if (scores.isEmpty()) return 0.0;
        
        int totalCorrect = scores.stream().mapToInt(GameScore::getCorrectAnswers).sum();
        int totalQuestions = scores.stream().mapToInt(GameScore::getTotalQuestions).sum();
        
        return totalQuestions > 0 ? (double) totalCorrect / totalQuestions * 100 : 0.0;
    }
}