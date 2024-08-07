<h2 align="center">📚 [ BookHub ] 📚</h2>

<br>

> 책을 찾고, 찜하고, 결제하는 모든 과정을 손쉽게!

>BookHub는 사용자에게 다양한 도서 검색, 찜하기, 결제, 상세 정보 제공 등의 기능을 제공하는 종합 도서 쇼핑몰입니다.
>
>간편한 인터페이스와 많은 도서 정보를 통해 사용자들이 원하는 책을 쉽고 빠르게 찾을 수 있습니다.




<br>

## 🐥 목차
- [⚙ 기술 스택](#기술-스택)
- [🧩 인프라 구조](#인프라)
- [🔥 개선 사항](#개선-사항)
- [💻 기능 구현](#기능-구현)
<br>
<br>

### 💻 <a href="http://15.165.134.135" target="_blank"><s>http://15.165.134.135</s></a> (배포 종료)

### ▶ <a href="https://www.youtube.com/watch?v=pa9IGdqlc6Q">  시연 동영상 </a>

[![Video Label](http://img.youtube.com/vi/pa9IGdqlc6Q/0.jpg)](https://www.youtube.com/watch?v=pa9IGdqlc6Q)

## ⚙ 기술 스택 <a name="기술-스택"></a>


| 분류       | 기술명                                                        |
|----------|------------------------------------------------------------|
| BackEnd  | Java, Spring Boot, Spring Security, Junit, MySQL , MyBatis |
| FrontEnd | HTML, Javascript, Bootstrap,  Thymeleaf, jQuery, Ajax      |
| DevOps   | CodeDeploy, GithubAction                                   |
| Infra    | AWS EC2, AWS RDS (MySQL 8.0), AWS S3                       |
| Tools    | IntelliJ, Gradle, nGrinder , MobaXterm                     |

### 사용 스택 선정이유
- Java 17
  - SpringBoot 3.x 사용으로 인한 Java11이 아닌 Java17 선택
- MySQL 8
  - MySQL 5 : 데이터 유효성 검사 기능이 제한적
  - MySQL 8 : CHECK 제약조건이 추가되어 데이터 입력시 정교한 유효성 검사가 가능
- SpringBoot 3.2.4
  - Spring Boot 2.x : Java8 이상 지원, Java8이 주로 사용 
    - 안정적인 성능을 제공하지만 , 최신 기술에 대한 최적화가 제한적
  - Spring Boot 3.x : Java17 이상 요구 , 최신 버전의 기능과 성능
    - 종속성 라이브러리의 최신버전을 사용
- Github Action
  - GitHub에서 직접 제공하므로 추가 설치가 필요 없어 설정이 간편함
  - Jenkins :  자체 서버에 설치 및 유지보수가 필요함
    - 초기 설정에 지속적 관리와 시간이 소요됨
- nGrinder
  - 실시간 테스트 진행상황 모니터링 , 종료 후 상세한 보고서 제공
  - 쉬운 사용 및 관리 : 웹 기반 UI , 테스트 스크립트를 작성하고 관리하는 과정이 간편함


<br>

## 🧩 인프라 <a name="인프라"></a>
![인파르](src/testImg/인프라%20구조.png)

## 🔥 개선사항 <a name="개선-사항"></a><br>


### AWS EC2 포트포워딩
  - 프로젝트 배포 후 URL에서 :8080 포트제거 [ [BLOG ](https://gi-dor.tistory.com/260)]
    - EC2 보안그룹 설정
    - 포트 리다이렉트 설정 
      -  설정 전 : 15.165.134.135/:8080 
      -  설정 후 : 15.165.134.135


### AWS EC2와 RDS를 활용한 CICD 배포
  - 프로젝트 당시 경험하지 못한 CI/CD 배포방식 적용
    - AWS EC2와 RDS를 사용한 데이터베이스 호스팅 : 로컬 MySQL 대신 AWS RDS를 이용하여 데이터베이스를 호스팅
    - CodeDeploy 사용 : AWS CodeDeploy를 이용하여 배포를 자동화
    - GitHub Actions 설정 : GitHub Actions를 설정하여, 프로젝트를 푸시하면 자동으로 배포되도록 구성


### AWS RDS MySQL로 데이터베이스를 이전
  - 기존 로컬 `MySQL` 데이터베이스를 `AWS RDS MySQL` 로 전환


### Master - Slave DB 간의 `Write/Read` 쿼리 분산 [ [코드](https://github.com/gi-dor/BookHub_AWS/blob/672abc619e616fa5e0b973afdbf3bddd3d666314/src/main/java/com/example/bookhub/user/service/UserService.java#L52-L66) ]
  - DB의 데이터 누적으로 발생하는 과부하를 염두해 AWS RDS 읽기  전용 replica 생성
  - 데이터가 쌓이면서 DB의 부하를 분산 시키기 위해 요청이 가장 많은 읽기 작업(Select문)만을 위한 Read Replica 복제 본을 생성하여 DataSource 구분
    - `@Transactional` 의 `readOnly` 속성을 이용한 쿼리 분산 (@Transactional : 스프링 어노테이션)
    - `@RouteDataSource` 의 `dataSourceType` 속성을 이용한 쿼리분산


### 중요정보 암호화 처리 [ [ 코드 ](https://github.com/gi-dor/BookHub_AWS/blob/672abc619e616fa5e0b973afdbf3bddd3d666314/src/test/java/com/example/bookhub/JasyptConfigTest.java#L10-L28) / [ BLOG ](https://gi-dor.tistory.com/250) ]
  - DB 접속정보 및 EmailAPI 정보 누출로 인한 보안위험 대응
    - 지난 프로젝트에서 application.properties 에 저장된 DB 연결정보를 GitHub에 push 되어 데이터베이스를 해킹 당한 사례가 있었습니다
      - `application.properties`에 저장된 `DB 연결 정보 와 EmailAPI 접속 정보`를 `jasypt` 암호화를 통한 보안을 강화하였습니다
  

### 회원 가입 완료시 `비동기 이메일 전송` [ [ 코드 ](https://github.com/gi-dor/BookHub_AWS/blob/30fcb619ad22758e65fe71214f00f1c8ec493e8e/src/main/java/com/example/bookhub/config/AsyncConfig.java#L8-L21) / [ BLOG ](https://gi-dor.tistory.com/255) ]
  - 초기에 동기 방식으로 진행했을 때는 회원 가입 완료 후 이메일 전송 및 완료 페이지 표시까지 약 3~5초가 소요되었습니다.
    - 스프링프레임워크에서 제공하는 `@Async` 어노테이션을 사용하여  메서드를 비동기 처리
      - 이메일 전송이 비동기적으로 처리되어 `TPS  :  3231ms → 110ms`  로 줄어들었습니다.
      
        <details>
          <summary> 테스트 결과 동기 비동기 </summary>
          <br>
          <strong>[ 동기 ]</strong>
  
          ![동기 이메일](src/testImg/Async/동기회원가입.jpg)
          <br><br>
  
          <strong>[ 비동기 ]</strong>
  
          ![비동기 이메일](src/testImg/Async/비동기%20회원가입.jpg)
  
          <strong>[ 성능개선 비교 ]</strong>
  
          ![성능개선 비교](src/testImg/Async/동기%20비동기%20성능개선%20측정.jpg)
  
          </details>


### 1:1 문의사항 `INDEX 조회성능 개선`   [ [코드](https://github.com/gi-dor/BookHub_AWS/blob/f330f85d60b0ba8639fda9bc610c3df3083a08fc/src/testImg/index/INDEXSQL.sql#L18-L26) / [BLOG](https://gi-dor.tistory.com/252) ]

  - 지속해서 누적되는 데이터로 인해 조회성능이 저하됩니다 이를 개선하기위해  INDEX를 설정하여 성능을 향상 시켰습니다
    - 카디널리티 수치가 높은 컬럼을 기준으로 INDEX 를 설정하였습니다
      - 평균 `TPS : 8.8ms  → 62.5ms` 로 610% 개선
        <details>
        <summary>테스트 결과 INDEX</summary>
        <br>
        <strong> INDEX 적용 전 </strong>
    
        ![인덱스 사용하기 전](src/testImg/index/인덱스%20사용하기%20전.jpg)
        <br><br>
    
        <strong>INDEX 적용 후</strong>
    
        ![인덱스 사용하기 전](src/testImg/index/인덱스%20사용%20후.jpg)
    
        </details>
    

<br>

## 💻 기능구현 <a name="기능-구현"></a>

  - ### 인증된 사용자에 조건에 맞는 navbar <br>
    ![navbar 로그인](src/testImg/Implementation/로그인_navbar.jpg)

  - ### 로그인 / 로그아웃
    ![로그인 기능 구현](src/testImg/Implementation/로그인.png)

  
- ### 회원가입
  - 정규표현식 , @valid 검증
  - kakao 우편번호 API
  - 아이디 중복 체크
    - aJax 비동기  
    - 중복체크 사용자 예외처리 클래스
    
    ![회원가입](src/testImg/Implementation/회원가입.png)  


- ### 회원가입 완료 ,  이메일 전송

  <img src="src/testImg/Implementation/회원가입_완료.png" width="450" height="500" alt="회원가입 완료 이미지">

  <img src="src/testImg/Implementation/회원가입_완료_이메일.png" width="600" height=auto alt="회원가입 완료 이메일">


<br><br>

- ### 마이페이지
    - 회원정보 조회
  
      <img src="src/testImg/Implementation/회원정보.png" width="700" height="400">
    
    <br>
  
    - 회원정보 수정
  
      <img src="src/testImg/Implementation/회원정보_수정.png" width="700" height="450">

    <br>
  
    - 비밀번호 변경
      - aJax 비동기
      
      <img src="src/testImg/Implementation/비밀번호_변경.png" width="600" height="400">
    
    <br>
  
    - 주문 내역 조회

      <img src="src/testImg/Implementation/주문내역_조회.png" width="600" height="450">
    
    <br>
  
    - 주문 상세 내역 조회
  
       <img src="src/testImg/Implementation/주문_상세내역_조회.png" width="600" height="450">
  
    <br>
  
    - 찜 목록
  
      <img src="src/testImg/Implementation/찜목록.png" width="700" height="450">
  
    <br>
  
    - 회원 탈퇴
      - 회원 탈퇴시 로그인 불가 처리
      
        <img src="src/testImg/Implementation/회원_탈퇴.png" width="700" height="450">

    <br>
  
    - 1 : 1 문의 내역 조회
      - INDEX 조회 성능개선
      
      <img src="src/testImg/Implementation/문의사항.png" width="700" height="450"> 
    
    <br>
  
    - 비밀번호 찾기
      - Regex 에 허용된 임시 비밀번호 생성 
      - 이메일 전송
    
        <img src="src/testImg/Implementation/비밀번호_찾기.jpg" width="600" height="200">

---
