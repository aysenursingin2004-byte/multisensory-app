package com.multisensory.controller;

import com.multisensory.entity.Word;
import com.multisensory.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/words")
@CrossOrigin(origins = "*")
public class WordController {
    @Autowired
    private WordService wordService;

    // GET: Tüm kelimeleri getir
    @GetMapping
    public ResponseEntity<List<Word>> getAllWords() {
        List<Word> words = wordService.getAllWords();
        return new ResponseEntity<>(words, HttpStatus.OK);
    }

    // GET: Ünitedeki kelimeleri getir
    @GetMapping("/unit/{unitId}")
    public ResponseEntity<List<Word>> getWordsByUnitId(@PathVariable Long unitId) {
        List<Word> words = wordService.getWordsByUnitId(unitId);
        return new ResponseEntity<>(words, HttpStatus.OK);
    }

    // GET: ID'ye göre kelimeyi getir
    @GetMapping("/{id}")
    public ResponseEntity<Word> getWordById(@PathVariable Long id) {
        Optional<Word> word = wordService.getWordById(id);
        return word.map(w -> new ResponseEntity<>(w, HttpStatus.OK))
                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // POST: Yeni kelime oluştur
    @PostMapping
    public ResponseEntity<Word> createWord(@RequestBody Word word) {
        Word createdWord = wordService.createWord(word);
        return new ResponseEntity<>(createdWord, HttpStatus.CREATED);
    }

    // PUT: Kelimeyi güncelle
    @PutMapping("/{id}")
    public ResponseEntity<Word> updateWord(@PathVariable Long id, @RequestBody Word wordDetails) {
        Word updatedWord = wordService.updateWord(id, wordDetails);
        if (updatedWord != null) {
            return new ResponseEntity<>(updatedWord, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DELETE: Kelimeyi sil
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWord(@PathVariable Long id) {
        wordService.deleteWord(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}