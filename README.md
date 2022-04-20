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
```[노수진] Activity 생성```
- fragment_list.xml / LisftFragment.java / ListItem.java / ListItemAdapter.java 등하원리스트
- AccountCreate2, 3, 4 회원가입 단계별 액티비티 
  
### 04/20
```[최희정] UI 디자인, MVC ```
- colors.xml, themes.xml 항목별 색상 지정
- MVC 패턴 패키지 분류

```[노수진] UI 디자인, 레이아웃 수정```
- 디자인 수정
- 하단 메뉴 아이콘 적용
- 
  
 </div>
</details>




<details>
 <summary> Backend </summary>
 <div markdown="1">
  
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
  
  ### ~ing
  ```[조준희] Firebase 연동```
   
  
</div>
</details>
