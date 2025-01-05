# Spring Tutorial
[Spring Boot 3.4.1](https://docs.spring.io/spring-boot/tutorial/first-application/index.html) 공식 튜토리얼

## 목차 

- [프로젝트 환경설정](#프로젝트-환경설정)
- [스프링 웹 개발 기초](#스프링-웹-개발-기초)
- [회원 관리 예제 - 백엔드 개발](#View-환경설정-1)
- [스프링 빈과 의존관계](#빌드하고-실행하기-1)


## 프로젝트 환경설정
### 프로젝트 생성 
* Java 17이상 설치
* IDE: IntelliJ

**[spring Starter](https://start.spring.io)로 이동해서 스프링 프로젝트 생성**

* 프로젝트 선택
  * Project: **Gradle - Groovy** Project
  * Spring Boot: 3.x.x
  * Language: java
  * Java: 17 or 21
  
* Project Metadata
  * Group: spring
  * Artifact: spring
  * Name: tutorial
  * Package Name: com.spring

* Dependencies
  1. Spring Web
  2. Thymeleaf

#### IntelliJ Gradle 대신에 자바 직접 실행
> 최근 IntelliJ 버전은 Gradle을 통해서 실행 하는 것이 기본 설정이다. 이렇게 하면 실행속도가 느리다. 다음과 같이 변경하면 자바로 바로 실행해서 실행속도가 더 빠르다.

* Preferences Build, Execution, Deployment Build Tools Gradle
  1. Build and run using: Gradle IntelliJ IDEA
  2. Run tests using: Gradle IntelliJ IDEA
 

## 라이브러리 살펴보기 
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


<img width="800" alt="Screenshot 2025-01-05 at 6 26 45 pm" src="https://github.com/user-attachments/assets/82053e84-e75e-4ffc-b5c8-b0db8a074dd2" />
<br>

- 컨트롤러에서 리턴 값으로 문자를 반환하면 뷰 리졸버(viewResolver)가 화면을 찾아서 처리한다.
    - 스프링 부트 템플릿엔진 기본 viewName mapping
    - resources:templates/ + {ViewName} + .html


## 빌드하고 실행하기 
**Terminal 이동**
1. ./gradlew build
2. cd build/libs
3. java -jar spring-0.0.1-SNAPSHOT.jar
4. 실행 확인

## 스프링 웹 개발 기초 

### 정적 컨텐츠 
- 스프링 부트 정적 컨텐츠 기능

```html 
<!DOCTYPE HTML>
<html>
 <head>
  <title>static content</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
 </head>
  <body>
   정적 컨텐츠 입니다.
  </body>
</html>
```
**실행:** http://localhost:8080/hello-static.html

<img width="800" alt="Screenshot 2025-01-05 at 8 06 23 pm" src="https://github.com/user-attachments/assets/4d5d52f1-43a2-4004-a077-84b329fe4da5" />
<br>

### MVC와 템플릿 엔진 
- MVC: Model, View, Controller

**[Controller](https://github.com/in27sung/spring/tree/main/src/main/java/com/spring/controller)**
```java
@Controller
public class HelloController {

 @GetMapping("hello-mvc")
 public String helloMvc(@RequestParam("name") String name, Model model) {
   model.addAttribute("name", name);
   return "hello-template";
 }
}
```
**[View](https://github.com/in27sung/spring/tree/main/src/main/resources/templates)**
```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Hello</title>
</head>
<body>
  <h1 th:text="'hello ' + ${name}">Hello!</h1>
</body>
</html>
```
**실행:** http://localhost:8080/hello-mvc?name=spring

### API
**@ResponseBody 문자 반환**
- `@ResponseBody`를 사용하면 뷰 리볼저(viewResolver)를 사용하지 않는다.
- 대신에 http의 body에 문자 내용을 직접 반환한다.
- `@ResponseBody`를 사용하고, 객체를 반환하면 객체가 JSON으로 변환된다.

```java
@Controller
public calls HelloController {

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {

        // HttpMessageConverter
        // StringConverter
        return "hello " + name; // "hello Spring"
    }
}
```
**실행:** http://localhost:8080/hello-string?name=spring

**@ResponseBody 사용 원리**

<img width="800" alt="Screenshot 2025-01-05 at 8 18 12 pm" src="https://github.com/user-attachments/assets/69f00f91-f421-466b-ba4f-aeaebe12c72b" />
<br>

**@ResponseBody를 사용**
- HTTP의 BODY에 문자 내용을 직접 반환한다.
- `viewResolver` 대신에 `HttpMessageConverter`가 동작한다.
- 기본 문자처리: `StringHttpMessageConverter`
- 기본 객체처리: `MappingJackson2HttpMessageConverter`
- byte 처리 등등 기타 여러 HttpMessageConverter가 기본으로 등록되어 있다.

