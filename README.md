# 전공 기반 자격증 추천 시스템

수강한 전공 과목을 선택하면 Jaccard 유사도 알고리즘을 기반으로 연관도 높은 IT 자격증을 추천하는 시스템입니다.

## 프로젝트 구조

```
KWRecommendation/
├── console/          ← Java 콘솔 프로그램 (핵심 로직)
│   └── src/certrecom/
│       ├── Item.java
│       ├── Subject.java / CourseSubject.java
│       ├── Certification.java / NationalCertification.java / InternationalCertification.java
│       ├── DataLoader.java
│       ├── JaccardCalculator.java
│       ├── RecommendationEngine.java
│       ├── RecommendationResult.java
│       └── Main.java
│
└── server/           ← Spring Boot 웹 서버
    ├── pom.xml
    └── src/main/
        ├── java/certrecom/
        │   ├── CertRecommendationApplication.java
        │   └── CertApiController.java  (+위 Java 파일들)
        └── resources/
            ├── application.properties
            └── static/
                ├── index.html
                ├── style.css
                └── script.js
```

## 실행 방법

### 웹 서버 실행
```bash
cd server
mvn spring-boot:run
```
브라우저에서 `http://localhost:8080` 접속

## 주요 기능
- 31개 전공 과목 키워드 기반 추천
- 17개 IT 자격증 (국가/국제) 데이터
- Jaccard 유사도 알고리즘 기반 연관도 계산
- Spring Boot REST API + 웹 UI 연동

  (Railway 구현 UI 고정 도메인 - http://localhost:8080으로 접속이 안될 시 사용)
- certrecommendationserver-production.up.railway.app 

  
