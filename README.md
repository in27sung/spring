# Spring Tutorial

## 프로젝트 환경 설정
**[프로젝트 생성](#프로젝트-생성)** <br>
**[라이브러리 살펴보기](#라이브러리-살펴보기)** <br>
**[View 환경설정](#View-환경설정)** <br>
**[빌드하고 실행하기](#빌드하고-실행하기)** <br>

### 프로젝트 생성 
* Java 17이상 설치
* IDE: IntelliJ

**[spring Starter](https://start.spring.io)로 이동해서 스프링 프로젝트 생성**

* 프로젝트 선택
  1. Project: **Gradle - Groovy** Project
  2. Spring Boot: 3.x.x
  3. Language: java
  4. Java: 17 or 21
* Dependencies
  1. Spring Web
  2. Thymeleaf

#### IntelliJ Gradle 대신에 자바 직접 실행
> 최근 IntelliJ 버전은 Gradle을 통해서 실행 하는 것이 기본 설정이다. 이렇게 하면 실행속도가 느리다. 다음과 같이 변경하면 자바로 바로 실행해서 실행속도가 더 빠르다.

* Preferences Build, Execution, Deployment Build Tools Gradle
  1. Build and run using: Gradle IntelliJ IDEA
  2. Run tests using: Gradle IntelliJ IDEA
 

### 라이브러리 살펴보기 
> Gradle은 의존관계가 있는 라이브러리를 함께 다운로드 한다.
