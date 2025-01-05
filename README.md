# Spring Tutorial
[Spring Boot 3.4.1](https://docs.spring.io/spring-boot/tutorial/first-application/index.html) 공식 튜토리얼

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

##### 스프링 부트 라이브러리
  * spring-boot-starter-web
    * spring-boot-starter-tomcat: 톰캣(웹서버)
    * spring-webmvc: 스프링 웹 MVC
  * spring-boot-starter-thymeleaf: 타임리프 템플릿 엔진(View)
  * spring-boot-starter(공통): 스프링 부트 + 스프링 코어 + 로깅
    * spring-boot
      * spring-core
    * spring-boot-starter-logging
      * logback, slf4j
      
##### 테스트 라이브러리
  * spring-boot-starter-test
    * junit: Test framework
    * mokito: mock library
    * assertj: 테스트 코드를 좀 더 편하게 작성하게 도와주는 라이브러리
    * spring-test: integrated spring test
      

## View 환경설정 

### Welcome Page만들기



```html
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Welcome Page</title>
</head>
<body>
  <h1>Hello, spring</h1>
  <a href="/hello">hello</a>
</body>
</html>
```
- 스프링 부트가 제공하는 [Welcome Page](https://docs.spring.io/spring-boot/reference/web/servlet.html#web.servlet.spring-mvc.welcome-page) 기능
  - static/index.html을 올려두면 Welcome page 기능을 제공한다.

### thymeleaf 템플릿 엔진 
  - [tymeleaf](https://www.thymeleaf.org/) 공식 사이트


<img width="632" alt="Screenshot 2025-01-05 at 6 26 45 pm" src="https://github.com/user-attachments/assets/82053e84-e75e-4ffc-b5c8-b0db8a074dd2" />
<br>
- 컨트롤러에서 리턴 값으로 문자를 반환하면 뷰 리졸버(viewResolver)가 화면을 찾아서 처리한다.
    - 스프링 부트 템플릿엔진 기본 viewName mapping
    - resources:templates/ + {ViewName} + .html

