# 📍 혼잡제로 (CrowdZero)

**🗓 프로젝트 기간: 2025.1.2 ~ 2025.2.28**

서울 도심 주요 집회 장소의 **실시간 혼잡도**와 **교통 통제 정보**를 제공하는 Android 애플리케이션입니다.

## 📌 주요 기능
- **실시간 혼잡도 조회**: 주요 집회 장소의 혼잡도를 실시간으로 확인할 수 있습니다.
- **교통 통제 정보 제공**: 서울 도심 내 교통 통제 구간의 정보를 안내합니다.
- **위치 기반 서비스**: 사용자의 현재 위치를 기반으로 가까운 주요 집회 장소의 혼잡도와 날씨 정보를 확인할 수 있습니다.
- **집회 정보 제공**: 서울 도심 내 주요 집회 일정과 상세 정보를 안내합니다.

## 👩‍💻 Front-End 팀원

<table>
  <tbody>
    <tr>
             <td align="center">
        <a href="https://github.com/nhyeonii">
          <img src="./app/src/main/res/drawable/user_1.png" width="100px;" alt="nhyeonii 프로필 사진"/>
          <br /><sub><b>김나현</b></sub>
        </a>
        <br />숙명여자대학교 소프트웨어융합
      <td align="center">
        <a href="https://github.com/gaeulzzang">
          <img src="./app/src/main/res/drawable/user_2.png" width="100px;" alt="gaeulzzang 프로필 사진"/>
          <br /><sub><b>이가을</b></sub>
        </a>
        <br />숙명여자대학교 컴퓨터과학과
      </td>
            <td align="center">
        <a href="https://github.com/jjwm10625">
          <img src="./app/src/main/res/drawable/user_3.PNG" width="100px;" alt="jjwm10625 프로필 사진"/>
          <br /><sub><b>조영서</b></sub>
        </a>
        <br />숙명여자대학교 컴퓨터과학과
      </td>
    </tr>
  </tbody>
</table>

## 🛠 기술 스택
- **언어**: Kotlin
- **프레임워크**: Jetpack Compose
- **아키텍처**: 클린 아키텍처 (Clean Architecture)
- **백엔드**: Spring Boot
- **지도 API**: Google Maps API
- **데이터베이스**: Firebase / PostgreSQL
- **버전 관리**: Git, GitHub

## 📌 프로젝트 소개
| <img src="https://github.com/user-attachments/assets/479fe920-1691-4773-ab9b-783751544331"/> | <img src="https://github.com/user-attachments/assets/d178be92-e0d7-4d5e-bd17-bbfdb58fadb7"/> | <img src="https://github.com/user-attachments/assets/8c79d35a-19ce-49dd-b236-06f73970fff7"/> |
|:---------:|:---------:|:--------------------------------------------------------------------------------------------:|
| 앱 실행 시 로고 및 초기 로딩 화면 | 서울 도심 지도와 실시간 혼잡도를 제공 |                                     특정 지역 선택 시 혼잡도 정보 표시                                     |

| <img src="https://github.com/user-attachments/assets/63f1881a-18a0-4dcc-b257-3050820ab602"/> | <img src="https://github.com/user-attachments/assets/93c059b1-cc61-4c58-8353-0012e5d4e7a8"/> | <img src="https://github.com/user-attachments/assets/790b4194-5199-4be2-866f-64e54f718fd0"/> |
|:--------------------------------------------------------------------------------------------:|:---------:|:-:|
|                                       선택된 교통 통제 정보 표시                                        | 선택된 지역의 상세 정보 및 실시간 날씨 제공 | 날짜별 집회 일정을 제공 |





## 📂 프로젝트 구조
```
CrowdZero-Android
│── app (Presentation Layer)
│   ├── di # 의존성 주입 모듈
│   ├── main # 메인 액티비티 및 네비게이션 관리
│   ├── navigation # 화면 이동 로직 관리
│   ├── ui # UI 관련 코드
│   ├── viewmodel # ViewModel 및 상태 관리
│
│── core (Core Layer)
│   ├── designsystem # UI 디자인 시스템 및 컴포넌트
│   ├── extension # 확장 함수
│   ├── navigation # 공통 네비게이션 로직
│   ├── state # UI 상태 관리
│   ├── util # 공통 유틸리티
│
│── data (Data Layer)
│   ├── datasource # 원격 및 로컬 데이터 소스
│   ├── datasourceimpl # 데이터 소스 구현체
│   ├── dto # 데이터 전송 객체
│   ├── mapper # 데이터 변환 매퍼
│   ├── repositoryimpl # 데이터 저장소 구현체
│
│── domain (Domain Layer)
│   ├── entity # 비즈니스 도메인 모델
│   ├── repository # 인터페이스 및 비즈니스 로직
│
│── feature (Feature Modules)
│   ├── map # 지도 탭
│   ├── calendar # 집회 일정 탭
│   ├── detail # 장소 상세 화면
│   ├── example # 예제 화면
│
├── build.gradle.kts
├── README.md
└── .gitignore
```

## 📱 주요 화면 구성
1. **스플래시 화면**: 앱 실행 시 로고 및 초기 로딩 화면
2. **메인 지도 화면**: 서울 도심 지도와 실시간 혼잡도를 제공하는 화면
3. **지도 칩 클릭 시 화면**: 특정 지역 선택 시 혼잡도 정보를 제공하는 화면
4. **교통 통제 정보 화면**: 교통 통제 아이콘 클릭시 통제 정보를 표시하는 화면
5. **상세 페이지 화면**: 선택된 지역의 상세 정보 및 실시간 날씨 제공하는 화면
6. **캘린더 화면**: 날짜별 집회 일정과 정보를 표시하는 화면

## 🚀 설치 및 실행 방법
1. **프로젝트 클론**
   ```bash
   git clone https://github.com/Team-CrowdZero/CrowdZero-Android.git
   cd CrowdZero-Android
   ```
2. **Android Studio에서 프로젝트 열기**
3. **필요한 API Key 설정** (Google Maps API, Firebase 등)
4. **앱 실행**
   - Android Studio에서 `Run` 버튼 클릭
   - 또는 `Gradle` 빌드를 사용하여 실행

## 📌 기여 가이드
1. 이슈를 확인하고 작업할 사항을 선택합니다.
2. 새로운 브랜치를 생성합니다.
   ```bash
   git checkout -b feature/기능명
   ```
3. 개발 후 커밋합니다.
   ```bash
   git add .
   git commit -m "[Feat] 기능 설명"
   ```
4. 원격 저장소에 푸시합니다.
   ```bash
   git push origin feature/기능명
   ```
5. PR(Pull Request)을 생성하고 코드 리뷰 후 병합합니다.

## 📞 연락처
- **GitHub**: [Team-CrowdZero](https://github.com/Team-CrowdZero)

---

<혼잡제로>는 서울 도심 내 집회 및 교통 혼잡도를 줄이는 데 기여하고자 합니다. 많은 관심과 기여 부탁드립니다! 🙌
