# Coffee Review App (React + Spring Boot)

Bu proje hocaya gösterilebilir, **basit ama “gerçek proje” gibi duran** bir örnektir:

- **React (Vite)**: Kahve listesi + modal ile yorum ekleme
- **Spring Boot REST API**: Kahveleri çekme, yorum ekleme
- **DB**: H2 (in-memory) — kurulum derdi yok

## 1) Backend (Spring Boot) çalıştırma

> Java 17 + Maven gerekir.

```bash
cd backend
mvn spring-boot:run
```

API:
- `GET http://localhost:8080/api/coffees`
- `POST http://localhost:8080/api/reviews`

H2 Console:
- `http://localhost:8080/h2`

## 2) Frontend (React) çalıştırma

```bash
npm install
# (opsiyonel) .env dosyası oluşturup VITE_API_BASE ayarlayabilirsin
npm run dev
```

Frontend varsayılan olarak `http://localhost:5173` üzerinde açılır.

## 3) Postman örnek (Review ekleme)

`POST /api/reviews`

```json
{
  "coffeeId": 1,
  "author": "Doğan",
  "rating": 5,
  "comment": "Çok iyi!"
}
```

> Not: `author` ve `comment` boş olursa backend **400 Bad Request** döner (validasyon).
