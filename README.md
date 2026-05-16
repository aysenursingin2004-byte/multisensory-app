# Multisensory Educational App - Backend

Multisensory Educational App için Java Spring Boot ve PostgreSQL ile geliştirilmiş backend uygulaması.

## 🎯 Özellikler

- ✅ Spring Boot 3.1.5 + Gradle
- ✅ PostgreSQL Veritabanı
- ✅ JPA/Hibernate ORM
- ✅ RESTful API
- ✅ Entity Sınıfları (Unit, Word, GameScore)

## 📋 Sistem Gereksinimleri

- Java 17+
- PostgreSQL 12+
- Gradle 7.0+

## 🚀 Kurulum

### 1. Veritabanı Oluşturma

```bash
creatdb multisensory_db
```

### 2. Proje Ayarları

`src/main/resources/application.properties` dosyasını düzenleyin:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/multisensory_db
spring.datasource.username=postgres
spring.datasource.password=your_password
```

### 3. Projeyi Çalıştırma

```bash
./gradlew bootRun
```

Uygulama `http://localhost:8080/api` adresinde başlayacak.

## 📊 Veritabanı Şeması

### Units Tablosu
```sql
CREATE TABLE units (
    id SERIAL PRIMARY KEY,
    grade INTEGER NOT NULL,
    name VARCHAR(255) NOT NULL
);
```

### Words Tablosu
```sql
CREATE TABLE words (
    id SERIAL PRIMARY KEY,
    unit_id INTEGER NOT NULL REFERENCES units(id),
    word VARCHAR(255) NOT NULL,
    translation VARCHAR(255) NOT NULL,
    image TEXT NOT NULL,
    sentence TEXT NOT NULL,
    sentence_translation TEXT NOT NULL
);
```

### Game Scores Tablosu
```sql
CREATE TABLE game_scores (
    id SERIAL PRIMARY KEY,
    user_id VARCHAR(255) NOT NULL,
    unit_id INTEGER NOT NULL REFERENCES units(id),
    correct_answers INTEGER NOT NULL,
    total_questions INTEGER NOT NULL,
    game_mode VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

## 📦 Entity Sınıfları

### Unit
- `id`: Ünite ID
- `grade`: Sınıf (5, 6, 7, 8)
- `name`: Ünite adı
- `words`: Kelime listesi

### Word
- `id`: Kelime ID
- `unit`: Ait olduğu ünite
- `word`: İngilizce kelime
- `translation`: Türkçe çevirisi
- `image`: Resim URL'si
- `sentence`: Örnek cümle
- `sentenceTranslation`: Cümle çevirisi

### GameScore
- `id`: Score ID
- `userId`: Oyuncu ID
- `unit`: Ait olduğu ünite
- `correctAnswers`: Doğru cevaplar
- `totalQuestions`: Toplam sorular
- `gameMode`: Oyun modu (match/race)
- `createdAt`: Oluşturulma tarihi

## 🔗 API Endpoints (Planlanıyor)

```
GET    /api/units?grade=5
GET    /api/units/{id}
GET    /api/units/{id}/words
POST   /api/scores
GET    /api/scores/{userId}
```

## 👨‍💻 Geliştirici

[aysenursingin2004-byte](https://github.com/aysenursingin2004-byte)

## 📄 Lisans

MIT License