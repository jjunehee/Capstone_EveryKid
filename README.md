# EveryKid : 어린이집 통합 관리 서비스

경기대학교 컴퓨터공학부 캡스톤디자인 ' 어린이집 통합 관리서비스 - EveryKid '의 repository입니다.

### 개발일지
<details>
 <summary> Frontend </summary>
 <div markdown="1">
  
### 04/03
```[노수진] Android Studio 연결```
- 로그인/회원가입 초기 화면 구현
- 선생님 메인화면 하단바 틀 구현
  
### 04/05
```[최희정] Android Studio 레이아웃, 클래스 추가```
- fragment_setting.xml / SettingFragment.java
- parent_main.xml / MainParent.java

```[노수진] Android Studio 회원가입 화면 레이아웃, 클래스 추가```
- create_account.xml / AccountCreate.java
- create_account.xml 스크롤뷰 추가
 
### 04/06
```[최희정] Android Studio 레이아웃, 클래스 수정```
- bottom_menu.xml / menu_selector_color.xml 하단바 아이콘 선택시 색상 변경
- MainParent.java  signin 이후 home 아이콘 선택
  
```[노수진] Android Studio 레이아웃 수정```
- create_account.xml 정보 입력 창 추가
  
### 04/06
```[조준희] 부모 Profile 레이아웃 추가 ```
- fragment_profile.xml
- enter.png, exit.png, default_profile.png
  
### 04/14
```[최희정] 채팅```
- activity_caht.xml / ChatActivity.java 채팅 액티비티
- ChatAdapter.java / G.java / MessageItem.java
- back_et_mymsgbox.xml / back_et_othermsgbox.xml 메시지 박스 디자인
- my_msgbox_xml / other_msgbox.xml 메시지 박스 레이아웃

### 04/15
```[노수진] Activity 생성, 등하원 리스트 클래스 추가```
- fragment_list.xml / LisftFragment.java / ListItem.java / ListItemAdapter.java 등하원리스트
- AccountCreate2, 3, 4 회원가입 단계별 액티비티 

  
### 4/16
```[노수진] 로그인, 회원가입```  
- MainActivity.java, LoginRequest.java, RegisterRequest.java 로그인, 회원가입 코드 작성
  
### 04/20
```[최희정] UI 디자인, MVC ```
- colors.xml, themes.xml 항목별 색상 지정
- MVC 패턴 패키지 분류

```[노수진] UI 디자인, 레이아웃 수정```
- 디자인 수정
- 하단 메뉴 아이콘 적용
- WriteRequest.java 글작성
  
### 04/23
```[최희정] 회원가입 ```
- create_account2.xml 버튼 아이디 수정
- RegisterRequest.java, AccountCreate2.java 회원가입 첫 단계 값 요청/처리(서버 url 연동 필요)
  
### 04/29
```[노수진] 회원가입 ```
- AccountCreate.java, AccountCreate2.java, AccountCreate3.java, AccountCreate4.java 코드 수정
- Globals.java, AndroidManifest.xml  전역변수 추가
- create_account2.xml, create_account3.xml, create_account4.xml 레이아웃 및 아이디 수정

### 05/04
```[노수진] 프로필화면 ```
- ProfileFragment.xml, fragment_profile.java 수정   

### 05/11
```[최희정] 회원가입, 데이터베이스```
- Spring Boot - mysql - Android Studio 서버 통신 성공
- 학부모 회원가입 정상 실행 확인(+레이아웃 수정해서 모든 값 받을 수 있게 수정 필요)
- entity.Teacher.java 내의 컬럼명 K_ID -> K_KID 수정
  
### 05/16
```[노수진] 홈화면, 회원가입, 아이등록화면 ```
- AccountCreate.java, SignupActivity.java, activity_signup.xml, RegisterInterface.java 회원가입: 회원가입시 선생님과 학부모 구별하게 수정(선생님 회원가입시 데이터베이스 컬럼 오류남)
- HomeFragment.java, fragment_home.xml 홈화면: 학사일정 달력, 공지사항 레이아웃 추가 
- notice_item.xml, NoticeItemAdapter.java, RecyclerItem.java 홈화면: 공지사항 제목 리싸이클러뷰 추가, 수정
- ScheduleActivity.java, activity_schedule.xml 달력: 특정 날짜 클릭 시 그 날짜의 학사일정 보여주는 팝업창 
- activity_child_add.xml, ChildAddActivity.java 아이등록화면: 갤러리에서 사진등록가능 
 
### 05/17
```[최희정] 선생님 회원가입```
- 선생님 회원가입 오류 수정, 정상 실행 확인(+레이아웃 수정해서 모든 값 받을 수 있게 수정 필요, 현재 t_name, t_phone, t_email, t_id, t_pwd만 insert됨)
 <img src="https://user-images.githubusercontent.com/83461991/168654330-b257de6f-6914-4626-991e-02cf475b9d68.png" width="150"/>
 <img src="https://user-images.githubusercontent.com/83461991/168654770-d9441c22-9978-41d9-905c-36bd15d17e37.png" width="400"/>
  
  
```[노수진] 공지사항 글쓰기, 글 확인 ```
- 공지사항 글쓰기 화면, 글 확인 화면 생성
  
### 05/22
```[노수진] 로그인```
- MainActivity, initMyApi, LoginRequest, LoginResponse, RetrofitClient 자바파일 추가

### 05/24
```[노수진] 로그인```
- MainActivity에서 선생님 학부모 선택하고 로그인 하는것으로 나눔
- SigninParentActivity.java, SigninTeacherActivity.java 생성

### 05/28
```[노수진] 선생님 회원가입, 로그인```
- 선생님 회원가입 로그인 연결

### 05/29
```[노수진, 최희정] 회원가입```
- 회원가입시 유치원 이름 선택 후 DB에 삽입할 때 k_kid 삽입 가능
- 선생님 회원가입 가능
- 로그인시 회원정보를 CreateAccountItem.java의 전역변수에 저장 -> 프로필 정보, 게시물, 채팅시 사용 
  
### 05/31
```[노수진] 로그아웃, 사진 bitmap```
- 사진 bitmap으로 변경, blob으로 데이터베이스에 추가 가능
- 로그아웃 가능
  
  
 </div>
</details>




<details>
 <summary> Backend </summary>
 <div markdown="1">
### 04/03 
데이터베이스 초기설계
이미지


### 04/06 
스프링부트 init 설정
---------------------------------------------------------------
SPRINGBOOT INITIALIZE
build : Gradle
Language : Java
version : 2.6.6
Packaging : Jar
Name : Everykid
DatabaseAccessLibray : JDBC
Package name : com.aaop.everykid
jdk : 11
init Dependencies
[Lombok, Spring Web, Spring Data JDBC, MySQL Driver]
-----------------------------------------------------------------

### 04/08
   ```[김혁진]```
DataSource 설정(application.properties)
ㄴatasource.url=jdbc:mysql://localhost:3306/everykid?useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC
ㄴspring.jpa.database=mysql

### 04/13 
   ```[김혁진]```
회원가입 요구사항 작성

### 04/19
   ```[김혁진]```
초기 회원가입 구현
security dependency REST.FUL 가능하도록 추가 설정

### 04/20
   ```[김혁진]```
부모 회원가입 구현
Dto/Entity/Service/Controller
ㄴ(com.aaop.everykid.dto.ParentFormDto.java)
ㄴ(com.aaop.everykid.repository.ParentRepository)
ㄴ(com.aaop.everykid.service.ParentService)
ㄴ(com.aaop.everykid.Controller.ParentController)

### 04/27
   ```[김혁진]```
로그인 구현 및 회원가입 테스트
데이터베이스 1차 수정
SpringSecurity 추가 및 설정
ㄴcsrf().disable().cors().disable().headers().frameOptions().disable()
ㄴ비밀번호 암호화 

### 05/04
   ```[김혁진]```
jwt 토큰 추가 및 회원가입 전면 재수정
ㄴ(package com.aaop.everykid.Jwt.TokenUtils)
ㄴ((package com.aaop.everykid.service.ParentService)
데이터베이스 2차 수정 및 jpa 관계 매핑

### 05/11
   ```[김혁진]```
SWAGGER API 추가
토큰저장 테이블 생성 및 부모 토큰 저장 구현

### 05/18
  ```[김혁진]```
시스템 개념도 구상


### 05/20
  ```[박경주] 게시판, DB컬럼 수정```
- backend Dto, Entity 클래스 변수명 db에 맞게 변경
- DB kindergarten table에 K_ID 추가
create TABLE KINDERGARTEN (
K_KID INT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
K_ID varchar(12) NOT NULL unique,
K_PHONE VARCHAR(13),
K_ADDRESS VARCHAR(80),
K_NAME VARCHAR(20)
);
- 게시판 등록 기능 복원
  
### 05/21
```박경주 유치원 select 관련 기능```
  
```[Android]```
- 유치원 선택시 서버 DB에 유치원 insert하고 KKID 받아오도록 selectKindergartenAdapter 수정
- 회원가입시 유치원 목록 받아오는 Activity연결
- SignupActivity에 Long Type 변수 kkid 추가

```[Spring]```
- kindergarten 관련한 Service, Repository, Controller 작성
  
### 05/23
```[박경주] 게시판 기능 보완```
  
```[Android]```
- 게시판 activity_post.xml 연결
- MainParent NavigationSelect 수정 -> 게시글 등록 후 community fragment로 이동
- 날짜, 조회수 제대로 출력되게 수정

```[database]```
- board테이블 WRITE_DATE 컬럼 형식 변경 ->
alter table board modify WRITE_DATE datetime;

```[spring]```
- BoardController 매핑관련해서 url 수정
  
### 05/25
```[김혁진]```
선생님 서비스 추가 구현
ㄴ(package com.aaop.everykid.Jwt.TokenUtils2)
ㄴ((package com.aaop.everykid.service.TeacherService)
아이 등록 api create 추가
  
### 05/31
```[박경주] 공지사항, 게시판에 필요한 유치원별 kkid 받아와서 저장하는 작업 실시함```
  
```[android]```
- loginresponseTeacher(Model) kkid항목 추가
- signinteacherActivity/signinParentActivity KKid받도록 수정

```[spring]```
- ParentService signin -> kkid 반환하도록 추가
- tokenresponsedto kkid 추가
- TeacherService signin -> kkid 반환하도록 추가
- tokenresponsedto2 kkid추가
  
</div>
</details>





<details>
 <summary> Raspberry pi </summary>
 <div markdown="1">
  
  ### 04/04
``` [조준희] 라즈베리파이 초기설정 ```
- model 4 조립 완료
- 학교 지원 통해 부속재료 구입 지원서 제출 완료 (04/05 주문실시 한다고 함)
- 구입 전 운영체제(라즈비안) 설치, 와이파이, 한글 설정 및 부가 설정 공부
<img width="20%" src="https://user-images.githubusercontent.com/83155528/161475595-96a3c612-087d-48f5-84b1-e2375cbf1220.jpg"/>
  
  ### ~04/10
  ``` [조준희] 라즈베리파이 원격데스크탑 설정```
  - 모니터 연결없이 노트북을 통해 원격으로 작업하기 위한 환경 조성을 하고있는데, remote 계정으로는 로그인이 되는데,
  pi 계정으로의 원격 접속이 자꾸 오류가 나고있음. pi로 로그인을 해야 나중에 자동실행과 같은 기능을 수행할 수 있기 때문에 꼭 해결해야함.
  - 구글링을 통해 여러 조치들을 해보고, OS도 다시 설치해보면서 해결하는 중. 아직 미해결
  
  ### 04/11
  ```[조준희] VNC Viewer를 통한 원격설정 완료```
  - 원격데스크탑으로는 연결 실패했지만 라즈베리파이4의 버그라는 말이 있기도하고 해결이 잘 되지않아, VNC Viewer를 통해 원격접속 완료.
  - 카몌라 모듈 연결 완료하고 카메라 캡쳐 Test 성공.
  <img width="50%" src="https://user-images.githubusercontent.com/83155528/162694620-ad6623ed-6d45-466f-b8c5-0ba2632bc0f9.PNG"/>  
  <img width="30%" src="https://user-images.githubusercontent.com/83155528/162709515-e773a66d-ea8c-4ec5-bf27-d015215475a7.jpg"/>
  
  ### 04/12
  ```[조준희] openCV 설치 ~ing ```
  - 이해하지 못할 오류가 다수 발생. 계속해서 구글링을 통해 해결 중에 있음
  <img width="50%" src="https://user-images.githubusercontent.com/83155528/162980283-7067f8d0-2f01-49ca-893d-8d4df0bc3855.PNG"/>
  
  ### 04/13
  ```[조준희] opencv 설치 완료 및 눈,코,미소 인식 가능 확인```
  - <img width="50%" src="https://user-images.githubusercontent.com/83155528/162995979-1a190d68-251f-4ca9-9233-1d53f45e0388.PNG"/>
  - <img width="50%" src="https://user-images.githubusercontent.com/83155528/162998415-6dd1869d-6e8d-4472-bf4a-f93450e69d2a.PNG"/>
  - <img width="50%" src="https://user-images.githubusercontent.com/83155528/163096689-f259bed1-631d-4e42-927b-326c8e9af6df.PNG"/>
  
  ### 04/14
  ```[조준희] 얼굴 학습 및 인식 성공```
  - <img width="50%" src="https://user-images.githubusercontent.com/83155528/163320253-be08344c-5ff8-4ca5-9ffa-04b236a78c93.PNG"/>
  
  ### ~04/17
  ```[조준희] 다수의 얼굴 인식 및 식별 성공```
  - <img width="50%" src="https://user-images.githubusercontent.com/83155528/163717459-48aa7c2b-d79c-4717-b34b-3b661f34c786.PNG"/>
  
  ### ~04/23
  ```[조준희] 등록된 사람 인식 후 처리```
  - 얼굴인식 정확도가 낮은 이유가 학습할때 동일한 환경에서 하는 것이 중요함을 인지하고, 재학습하니 정확도 크게 향상. 
  - 실제로 어린이집에서 얼굴을 등록할때, 촬영하기에 적합한 환경(적절한 조명 및 뒤에 사물이 없는 지) 을 마련하여 동일한 환경에서 얼굴을 등록해야 할 듯.
  - 카메라를 통해 등록된 사람일 확률인 confidence가 50% 이상이면(정확한 수치로 표현하기 때문에 낮은 것이 아님. 이정도 수치면 확실) 이미지 캡쳐 후 result폴더에 이름.해당날짜로 이미지 저장
  
  ### ~05/01
  ```[조준희] firebase 연동```
  - faceDetection을 통해 학습된 개인이 인식이 되면 이미지 캡쳐 후 firebase storage에 올리기 구현 완료
  <img width="50%" src="https://user-images.githubusercontent.com/83155528/166153652-0156d300-86e8-4ab6-8fb6-5fe1e93c76a6.PNG"/>
  
  ### 05/02
  ```[조준희] FaceDetection.py, firebase```
  - 하루에 개인별 등원, 하원 총 2장 사진 storage에 저장될 수 있도록 구현
  
  ### 05/04
  ```[조준희] 원인을 알 수없는 에러로 라즈베리파이 초기화 진행```
  - 복원 완료
  
  ### 05/12
  ```[조준희] 안드로이드 부분 FCM 연동 및 설정페이지 완료 ```
  - 안드로이드에서 앱 토큰을 Firebase Database로 보내기 완료
  - 앞으로 라즈베리파이에서 해당 Database에 접근해서 사용자별 Token값을 가져올 예정
  <img width="20%" src="https://user-images.githubusercontent.com/83155528/168300733-c461ce30-dcde-407b-9641-f210c5f1b055.png"/>
  <img width="20%" src="https://user-images.githubusercontent.com/83155528/167659290-91715664-bc86-4089-ba2f-a7018a397392.png"/>
  <img width="80%" src="https://user-images.githubusercontent.com/83155528/168300453-7772b1e5-ebfa-4621-ade2-b70e5a647b9e.png"/>
  
  ### 05/16
  ```[조준희] 라즈베리파이로 Token 가져오고, 안드로이드 기기로 푸쉬알림 보내기```
  - Firebase Databbase로 부터 알림설정 허용한 기기들의 token 정보를 가져오고, 해당 기기로 푸쉬 알림 보내기 완료 ( notification.py )
  
  <img src="https://user-images.githubusercontent.com/83155528/168578247-743822da-2f8a-4fdd-b115-966cc2085c3c.gif" width="500" height="490">
  <img src="https://user-images.githubusercontent.com/83155528/168578280-5ac2cdfb-d2a0-43fa-bfde-aa07afa1aa7f.gif" width="300" height="490">
  
  ### 05/18
  ```[조준희] 개별 알림 구현, firebase Realtime DB 통합(everykid)```
  - notificationSetting.java 구현
  - firebase everykid 저장소로 통합
  
  ### 05/19
  ```[조준희] 앱안에서도 푸쉬알림 받을 수 있도록 구현```
  - notificationClass.java 구현
  - forground 알림 아이콘 변경
  
  ### 05/20
  ```[조준희] 안드로이드에서 등하원 사진 가져오기```
  - Firestorage image_store 폴더에 저장된 등하원 캡쳐 이미지들 중에 해당 이름,날짜,등/하원 이미지를 안드로이드에서 조회가능하도록 구현  
  (LoadActivity.java, activity_imgload.xml)
  <img src="https://user-images.githubusercontent.com/83155528/169556047-42055067-e30c-4e02-a3a5-41cdc18a2a5f.png" width="300" height="490">
  
  ### 05/21
  ```[조준희] 이름,날짜,등/하원에 따른 사진 가져오기```
  - getName, getDate, getTime 변수를 통해 Firebase에서 가져오는 사진 구별화. (LoadActivity.java)
  
  ### 05/22
  ```[조준희] ListFragment, LoadActivity 90%```
  - 현재 시점부터 일주일전까지 등하원 리스트 조회가능
  - 조회할때 로딩화면, 조회된 이미지 처리 및 예외 처리
  - ListFragment에서 이미지 조회 미리하고, 조회된 날짜에 대해서 버튼을 밝은 초록색으로 바꾸려고하는데, 이미지 조회여부 처리가 잘 해결 되지않음. (~ing)
  <img src="https://user-images.githubusercontent.com/83155528/169707294-51d5ca7b-4701-4f79-8ffd-66e648c693f7.gif" width="300" height="490">

  ### 05/23
  ```[조준희] 03.faceDetection.py, FCM.java```
  - [안드로이드] 알림 title 변경, 알림 클릭 시 이벤트 처리
  - [라즈베리파이] 등/하원 알림 구분화, 캡쳐 주기 변경
  
  ### 05/26
  ```[조준희] [사용성 향상]  1. 라즈베리파이 피에조 부저 추가```
  - 카메라 앞에 서서 얼굴학습을 마치거나 등/하원시 얼굴인식을 마쳤을 때, 나오는 부저음 추가
  
  ### ~05/31
  ```[조준희] 2022 한국정보기술학회 대학생 논문 경진대회```
  - '어린이집 등하원 관리를 위한 사물인터넷 서비스 개발'
  - 발표영상 녹화, 제출 완료
  - 6/3 10시 학회 온라인 발표 예정

  
  
  
</div>
</details>
