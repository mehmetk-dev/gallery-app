# GalleryApp - Araç Kiralama Sistemi

Bu proje, Spring Boot tabanlı bir araç kiralama uygulamasıdır. PostgreSQL veri tabanı ile çalışır ve kullanıcıların araç kiralama işlemlerini gerçekleştirmesini sağlar. Uygulama, aynı zamanda güncel dolar kuru üzerinden fiyat hesaplama özelliğine de sahiptir.

##  Özellikler

- Araç ekleme, güncelleme, silme, listeleme (CRUD)
- Müşteri ekleme, güncelleme, silme, listeleme (CRUD)
- JWT ile kimlik doğrulama
- Dolar kuru ile fiyat hesaplama (TL → USD)
- PostgreSQL veri tabanı
- DTO <-> Entity dönüşümleri için MapStruct
- Validation ve Exception Handling

## Kullanılan Teknolojiler

| Teknoloji            | Açıklama                             |
|----------------------|--------------------------------------|
| Java 21              | Uygulama dili                        |
| Spring Boot 3.5.4    | Ana framework                        |
| Spring Data JPA      | ORM işlemleri                        |
| Spring Security      | Kimlik doğrulama ve yetkilendirme   |
| PostgreSQL           | Veritabanı                           |
| MapStruct            | DTO-Entity dönüşümleri               |
| JWT (jjwt)           | Token bazlı güvenlik                 |
| Lombok               | Boilerplate kodları azaltır          |
| Bean Validation      | Giriş verisi doğrulama               |

## 🛠️ Kurulum

### 1. Klonla

git clone https://github.com/kullaniciAdi/galleryApp.git
cd galleryApp

### 2. PostgreSQL Veritabanı Oluştur

CREATE DATABASE gallery_app;

### 3. application.yml Ayarları

spring.application.name=galleryApp
spring.datasource.url=jdbc:postgresql://localhost:5432/galleryApp
spring.datasource.username=postgres
spring.datasource.password=
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.hibernate.show-sql=true
jwt.secret=kUokFv0WKgRH6QxatsMYShg3fDLcFYPNUXpS9CUMEaY=.dialect.PostgreSQLDialect


### 4. Maven Bağımlılıklarını Yükle

mvn clean install

### 5. Uygulamayı Başlat

mvn spring-boot:run

## Auth (JWT)

- /authenticate - Kullanıcı girişi
- /register - Kayıt işlemi
- /refresh-token - Token yenileme işlemi
- JWT token ile korunan endpoint'lere erişim sağlanır.

## Dolar Kuru Entegrasyonu

- Döviz kuru bilgisi bir API üzerinden alınır (örnek: https://exchangerate.host)
- Araç ve müşteri işlemlerinde TL → USD dönüşümleri otomatik yapılır.

## Örnek Endpointler

| HTTP   | Endpoint           | Açıklama         |
|--------|--------------------|------------------|
| GET    | /v1/cars/{id}      | Araç getirir     |
| POST   | /v1/cars           | Araç ekler       |
| PUT    | /v1/cars/{id}      | Araç günceller   |
| DELETE | /v1/cars/{id}      | Araç siler       |
