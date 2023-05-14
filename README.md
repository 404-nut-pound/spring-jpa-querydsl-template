# Spring Boot 3 + JPA + Querydsl Template

## 프로젝트 구성

- Spring Boot - 3.0.6
  - Spring Web MVC
  - Spring Validation 및 Security 제외
- Querydsl - 5.0.0
- H2 Embedded DB
- Spring OpenApi 2.1.0
  - `{contextRoot}/docs`

## Querydsl 설정

### gradle.build 파일

- Spring Boot 3 버전 이상부터 Querydsl 패키지 버전 뒤 `:jakarta`를 붙여야 정상 동작
- P6Spy의 경우 1.9.0 버전부터 Spring Boot 3 지원

  - `P6SpyFormatConfig.java` 설정으로 SQL 포맷팅

  ```groovy
  ...
  dependencies {
    ...
    implementation "com.querydsl:querydsl-jpa:5.0.0:jakarta"
    annotationProcessor "com.querydsl:querydsl-apt:5.0.0:jakarta"
    annotationProcessor "jakarta.annotation:jakarta.annotation-api"
    annotationProcessor "jakarta.persistence:jakarta.persistence-api"
    implementation "com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.9.0"
    ...
  }
  ...
  ```

### Qclass 위치

- `/bin/generated-sources/annotations/{프로젝트 패키지}/entity`
- 프로젝트 전체 빌드(Gradle 빌드 아님) 시 자동 갱신 됨
