# Spring Tutorial
[Spring Boot 3.4.1](https://docs.spring.io/spring-boot/tutorial/first-application/index.html) 공식 튜토리얼

## 목차 

- [프로젝트 환경설정](#프로젝트-환경설정)
- [스프링 웹 개발 기초](#스프링-웹-개발-기초)
- [회원 관리 예제 - 백엔드 개발](#회원-관리-예제---백엔드-개발)
- [스프링 빈과 의존관계](#스프링-빈과-의존관계)
- [스프링 DB 접근 기술](#스프링-db-접근-기술)


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

## 회원 관리 예제 - 백엔드 개발

### 비즈니스 요구사항 정리
- 데이터: 회원ID, 이름
- 기능: 회원 등록, 조회
- 아직 데이터 저장소가 선정되지 않음(가상의 시나리오)

**일반적인 웹 애플리케이션 계층 구조**
<img width="629" alt="Screenshot 2025-01-10 at 3 54 48 pm" src="https://github.com/user-attachments/assets/d38daba4-c05e-4c5f-be50-a021d0bafc16" />

- Controller: 웹 MVC의 컨트롤러 역할
- Service: 핵심 비즈니스 로직 구현(중복 확인)
- Repository: 데이터베이스에 접근, 도메인 객체를 DB에 저장하고 관리
- Domain: 비즈니스 도메인 객체, 예) 회원, 주문, 쿠폰 등등 주로 데이터베이스에 저장하고 관리됨


**클래스 의존관계**
<img width="633" alt="Screenshot 2025-01-10 at 3 56 29 pm" src="https://github.com/user-attachments/assets/1468ff8a-6d91-4d0c-932c-457bd3eda436" />

- 아직 데이터 저장소가 선정되지 않아서, 우선 인터페이스로 구현 클래스를 변경할 수 있도록 설계한다.
- 데이터 저장소는 RDB, NoSQL 등 다양한 저장소를 고민중인 상황으로 가정한다.
- 개발을 진행하기 위해서 초기 개발 단계에서는 구현체로 가벼운 메모리 기반의 데이터 저장소를 사용한다.

### 회원 리포지토리 테스트 케이스 작성 
개발한 기능을 실행해서 테스트 할 때 자바의 main 메서드를 통해서 실행하거나, 웹 애플리케이션의 컨트롤러를 통해서 해당 기능을 실행한다. 이러한 방법은 준비하고 실행하는데 오래 걸리고, 반복 실행하기 어렵고 여러 테스트를 한번에 실행하기 어렵다는 단점이 있다. 자바는 JUnit이라는 프레임워크로 테스트를 실행해서 이러한 문제를 해결한다.

**@AfterEach**
```JAVA
public void clearStore() {
    store.clear();
}
```

```JAVA
@AfterEach
public void afterEach() {
    repository.clearStore();
}
```
- '@AfterEach': 한번에 여러 테스트를 실행하면 메모리 DB에 직전 테스트의 결과가 남을 수 있다. 이렇게 되면 다음 이전 테스트 때문에 다음 테스트가 실패할 가능성이 있다. '@AfterEach'를 사용하면 각 테스트가 종료될 때 마다 이 기능을 실행한다. 메모리 DB에 저장된 데이터를 삭제한다.
- 테스트는 각각 독립적으로 실행되어야 한다. 테스트 순서에 의존관계가 있는 것은 좋은 테스트가 아니다.

### 회원 서비스 테스트
- 테스트는 given, when, then 패턴으로 이루어진다.
- given: 이러한 상황이 주어졌을 때
- when: 이것을 실행했을 때
- then: 이러한 결과가 나와야 한다.

**@BeforeEach**
```JAVA
@BeforeEach
public void beforeEach() {
    memberrepository = new MemoryMemberRepository();
    memberservice = new MemberService(memberRepository);
}
```
- '@BeforeEach': 각 테스트를 실행하기 전에 호출된다. 각 테스트가 서로 영향을 주지 않도록 항상 새로운 객체를 생성하고, 의존관계도 새로 맺어준다.

## 스프링 빈과 의존관계

### 컴포넌트 스캔과 자동 의존관계 설정
- 생성자에 `@Autowired`가 있으면 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어준다. 이렇게 객체 의존관계를 외부에서 넣어주는 것을 DI(Dependency Injection), 의존성 주입이라 한다.
- 이전 테스트에서는 개발자가 직접 주입했고, 여기서는 @Autowired에 의해 스프링이 주입해준다.

### 스프링 빈을 등록하는 2가지 방법
- 컴포넌트 스캔과 자동 의존관계 설정
- 자바 코드로 직접 스프링 빈 등록하기

### 컴포넌트 스캔 원리
- `@Component` 어노테이션이 있으면 스프링 빈으로 자동 등록된다.
- `@Controller` 컨트롤러가 스프링 빈으로 자동 등록된 이유도 컴포넌트 스캔 때문이다.
- `@Component`를 포함하는 다음 어노테이션도 스프링 빈으로 자동 등록된다.
   - `@Controller`
   - `@Service`
   - `@Repository`

## 스프링 DB 접근 기술

### 구현 클래스 추가 이미지 
<img width="647" alt="Screenshot 2025-01-26 at 7 09 54 pm" src="https://github.com/user-attachments/assets/8073538d-2b7b-4347-a33a-753f94e50932" />
- 개방-폐쇄 원칙(OCP, Open-Closed Principle)
  - 확장에는 열려있고, 수정, 변경에는 닫혀있다. 
- 스프링 DI(Dependencies Injection)을 사용하면 **기존 코드를 전혀 손대지 않고, 설정만으로 구현 클래스를 변경**할 수 있다.
- 회원을 등록하고 DB에 결과가 잘 입력되는지 확인하자.
- 데이터를 DB에 저장하므로 스프링 서버를 다시 실행해도 데이터가 안전하게 저장된다.


### 스프링 통합 테스트 
- `@SpringBootTest`: 스프링 컨테이너와 테스트를 함께 실행한다. 
- `@Transactional`: 테스트 케이스에 이 어노테이션이 있으면, 테스트 시작 전에 트랜잭션을 시작하고, 테스트 완료 후에 항상 롤백한다. 이렇게 하면 DB에 데이터가 남지 않으므로 다음 테스트에 영향을 주지 않는다.

**[회원 서비스 스프링 통합 테스트](src/test/java/com/spring/service/MemberServiceIntegrationTest.java)**


### 스프링 JdbcTemplate
- 순수 Jdbc와 동일한 환경설정을 하면 된다.
- 스프링 JdbcTemplate과 MyBatis 같은 라이브러리는 JDBC API에서 본 반복 코드를 대부분 제거해준다. 하지만 SQL은 직접 작성해야 한다.

**[JdbcTemplate 사용](src/main/java/com/spring/repository/JdbcTemplateMemberRepository.java)**

### JPA
- JPA는 기존의 반복 코드는 무롤닝고, 기본적인 SQL도 JPA가 직접 만들어서 실행해준다.
- JPA를 사용하면, SQL과 데이터 중심의 설계에서 객체 중심의 설계로 패러다임을 전환을 할 수 있다.
- JPA를 사용하면 개발 생산선을 크게 높일 수 있다.

**[JPA 사용](src/main/java/com/spring/repository/JpaMemberRepository.java)**
- `@Entity`: JPA가 관리하는 객체

**[JPA 회원 리포지토리](src/main/java/com/spring/repository/JpaMemberRepository.java)**

**[서비스 계층에 트랜잭션 추가](src/main/java/com/spring/service/MemberService.java)**
- `@Transactional`: 데이터를 저장하거나 변경할 때는 트랜잭션 안에서 실행해야 한다.
- JPA를 사용하면 모든 데이터 변경은 트랜잭션 안에서 실행해야 한다.
- JPA를 사용하면 `@Transactional` 어노테이션을 클래스 레벨에 붙이는 것이 좋다.

### 스프링 데이터 JPA
스프링 부트와 JPA만 사용해도 개발 생산성이 정말 많이 증가하고, 개발해야할 코드도 확연히 줄어듭니다. 여기에 스프링 데이터 JPA를 사용하면,
기존의 한계를 넘어 마치 마법처럼, 리포지토리에 구현 클래스 없이 인터페이스 만으로 개발을 오나료할 수 있습니다.
그리고 반복 개발해온 기본 CRUD 기능도 스프링 데이터 JPA가 모두 제공합니다. 스프링 부트와 JPA라는 기반 위에, 스프링 데이터 JPA라는 프레임워크를 더하면 
개발이 정말 즐거워집니다. 지금까지 조금이라도 단순하고 반복이라 생각했떤 개발 코드들이 확연하게 줄어듭니다. 따라서 개발자는 핵심 비즈니스 로직을 개발하는데,
집중할 수 있습니다. 실무에서 관계형  데이터베이스를 사용한다면 스프링 데이터 JPA는 이제 선택이 아니라 필수 입니다.

> 주의: 스프링 데이터 JPA는 JPA를 편리하게 사용하도록 도와주는 기술입니다. 따라서 JPA를 먼저
> 학습한 후에 스프링 데이터 JPA를 학습해야 합니다.


[Screenshot 2025-01-28 at 10.48.44 pm.png](../../../var/folders/7b/6s43j55556d1qtk2kmm3gdlw0000gn/T/TemporaryItems/NSIRD_screencaptureui_fdFv2X/Screenshot%202025-01-28%20at%2010.48.44%E2%80%AFpm.png)

- 앞의 JPA 설정을 그대로 사용한다.

**[스프링 데이터 JPA 회원 리포지토리](src/main/java/com/spring/repository/SpringDataJpaMemberRepository.java)**

**[스프링 데이터 JPA 회원 리포지토리를 사용하도록 스프링 설정 변경](src/main/java/com/spring/SpringConfig.java)**

- 스프링 데이터 JPA가 `SpringDataJpaMemberRepository` 를 스프링 빈으로 자동 등록해준다.

**스프링 데이터 JPA 제공 기능**
- 인터페이스를 통한 기본적인 CRUD
- `findByName()`, `findByEmail()` 처럼 메서드 이름 만으로 조회 기능 제공
- 페이징 기능 자동 제공

> 창고: 실무에서는 JPA와 스프링 데이터 JPA를 기본으로 사용하고, 복잡한 동적 쿼리는 Querydsl이라는 라이브러리를 사용하면된다. QueryDsl을 사용하면 쿼리도 자바 코드로 안전하게 작성할 수 있고, 동적 쿼리도 편리하게 작성할 수 있다. 이 조합으로 해결하기 어려운 쿼리는 JPA가 제공하는 네이티브 쿼리를 사용하거나, 앞ㅇ서 학습한 스프링 JdbcTemplate를 사용하면 된다.
