package com.multisensory.service;

import com.multisensory.entity.Word;
import com.multisensory.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class WordService {
    @Autowired
    private WordRepository wordRepository;

    // Tüm kelimeleri getir
    public List<Word> getAllWords() {
        return wordRepository.findAll();
    }

    // Ünitedeki kelimeleri getir
    public List<Word> getWordsByUnitId(Long unitId) {
        return wordRepository.findByUnitId(unitId);
    }

    // ID'ye göre kelimeyi getir
    public Optional<Word> getWordById(Long id) {
        return wordRepository.findById(id);
    }

    // Yeni kelime oluştur
    public Word createWord(Word word) {
        return wordRepository.save(word);
    }

    // Kelimeyi güncelle
    public Word updateWord(Long id, Word wordDetails) {
        Optional<Word> word = wordRepository.findById(id);
        if (word.isPresent()) {
            Word w = word.get();
            w.setWord(wordDetails.getWord());
            w.setTranslation(wordDetails.getTranslation());
            w.setImage(wordDetails.getImage());
            w.setSentence(wordDetails.getSentence());
            w.setSentenceTranslation(wordDetails.getSentenceTranslation());
            return wordRepository.save(w);
        }
        return null;
    }

    // Kelimeyi sil
    public void deleteWord(Long id) {
        wordRepository.deleteById(id);
    }
}