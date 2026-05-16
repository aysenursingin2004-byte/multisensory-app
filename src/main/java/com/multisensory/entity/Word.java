package com.multisensory.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "words")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id", nullable = false)
    private Unit unit;

    @Column(nullable = false)
    private String word; // Kelime (örn: "Apple")

    @Column(nullable = false)
    private String translation; // Türkçe çevirisi

    @Column(nullable = false, columnDefinition = "TEXT")
    private String image; // Resim URL'si

    @Column(nullable = false, columnDefinition = "TEXT")
    private String sentence; // Örnek cümle

    @Column(nullable = false, columnDefinition = "TEXT")
    private String sentenceTranslation; // Cümle çevirisi
}