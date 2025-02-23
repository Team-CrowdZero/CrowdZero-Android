# 📍 혼잡제로 (CrowdZero)

**🗓 프로젝트 기간: 2025.1.2 ~ 2025.2.28**

서울 도심 주요 집회 장소의 **실시간 혼잡도**와 **교통 통제 정보**를 제공하는 Android 애플리케이션입니다.

## 📌 주요 기능
- **실시간 혼잡도 조회**: 주요 집회 장소의 혼잡도를 실시간으로 확인할 수 있습니다.
- **교통 통제 정보 제공**: 서울 도심 내 교통 통제 구간의 정보를 안내합니다.
- **집회 정보 제공**: 서울 도심 내 주요 집회 일정과 상세 정보를 안내합니다.

## 👩‍💻 Front-End 팀원

<table>
  <tbody>
    <tr>
      <td align="center">
        <a href="https://github.com/nhyeonii">
          <img src="https://github.com/user-attachments/assets/e8af2acd-414b-47a1-996d-24e9b2b9b4cf" width="250px;" alt="nhyeonii 프로필 사진"/>
          <br /><span style="font-size: 1.5em; font-weight: bold;">김나현</span>
        </a>
        <br />숙명여자대학교
        <br />소프트웨어융합
      </td>
      <td align="center">
        <a href="https://github.com/gaeulzzang">
          <img src="https://github.com/user-attachments/assets/f9cc751b-e10f-4e01-9cb2-1d8e7d546045" width="250px;" alt="gaeulzzang 프로필 사진"/>
          <br /><span style="font-size: 1.5em; font-weight: bold;">이가을</span>
        </a>
        <br />숙명여자대학교
        <br />컴퓨터과학과
      </td>
      <td align="center">
        <a href="https://github.com/jjwm10625">
          <img src="https://github.com/user-attachments/assets/7cca1162-9d36-4db0-b118-412ea116c886" width="250px;" alt="jjwm10625 프로필 사진"/>
          <br /><span style="font-size: 1.5em; font-weight: bold;">조영서</span>
        </a>
        <br />숙명여자대학교
        <br />컴퓨터과학과
      </td>
    </tr>
  </tbody>
</table>




## 🛠 기술 스택
- **언어**: Kotlin
- **프레임워크**: Jetpack Compose
- **아키텍처**: 클린 아키텍처 (Clean Architecture)
- **백엔드**: Spring Boot
- **지도 API**: Naver Maps API
- **버전 관리**: Git, GitHub

## 📌 프로젝트 소개
| <img src="https://github.com/user-attachments/assets/479fe920-1691-4773-ab9b-783751544331"/> | <img src="https://github.com/user-attachments/assets/d178be92-e0d7-4d5e-bd17-bbfdb58fadb7"/> | <img src="https://github.com/user-attachments/assets/8c79d35a-19ce-49dd-b236-06f73970fff7"/> |
|:---------:|:--------------------------------------------------------------------------------------------:|:--------------------------------------------------------------------------------------------:|
| 앱 실행 시 로고 및 초기 로딩 화면 |                                     서울 도심 지도와 실시간 혼잡도 제공                                     |                                     특정 지역 선택 시 혼잡도 정보 표시                                     |

| <img src="https://github.com/user-attachments/assets/63f1881a-18a0-4dcc-b257-3050820ab602"/> | <img src="https://github.com/user-attachments/assets/93c059b1-cc61-4c58-8353-0012e5d4e7a8"/> | <img src="https://github.com/user-attachments/assets/790b4194-5199-4be2-866f-64e54f718fd0"/> |
|:--------------------------------------------------------------------------------------------:|:---------:|:--------------------------------------------------------------------------------------------:|
|                                       선택된 교통 통제 정보 표시                                        | 선택된 지역의 상세 정보 및 실시간 날씨 제공 |                                         날짜별 집회 일정 제공                                         |





## 📂 프로젝트 구조
```
📦 CrowdZero-Android
│── 📁 app (Presentation Layer)
│   ├── 📁 di 
│   ├── 📁 main 
│   ├── 📁 navigation 
│
│── 📁 core (Core Layer)
│   ├── 📁 designsystem 
│   │   ├── 📁 component
│   │   │   ├── 📁 bar
│   │   │   ├── 📁 calendar
│   │   │   ├── 📁 chip
│   │   │   ├── 📁 indicator
│   │   │   ├── 📁 snackbar
│   │   │   └── 📁 toappbar
│   │   └── 📁 theme
│   ├── 📁 extension 
│   ├── 📁 navigation 
│   ├── 📁 state 
│   ├── 📁 type
│   ├── 📁 util 
│
│── 📁 data (Data Layer)
│   ├── 📁 datasource 
│   ├── 📁 datasourceimpl 
│   ├── 📁 dto 
│   │   ├── 📁 request
│   │   └── 📁 response
│   ├── 📁 mapper 
│   ├── 📁 repositoryimpl 
│   ├── 📁 service
│
│── 📁 domain (Domain Layer)
│   ├── 📁 entity 
│   ├── 📁 repository 
│
│── 📁 feature (Feature Modules)
│   ├── 📁 calendar
│   ├── 📁 detail 
│   ├── 📁 map 
│   ├── 📁 splash 
│
└── 📄 build.gradle.kts
```

## 📱 주요 화면 구성
1. **스플래시 화면**: 앱 실행 시 로고 및 초기 로딩 화면
2. **메인 지도 화면**: 서울 도심 지도와 실시간 혼잡도를 제공하는 화면
3. **지도 칩 클릭 시 화면**: 특정 지역 선택 시 혼잡도 정보를 제공하는 화면
4. **교통 통제 정보 화면**: 교통 통제 아이콘 클릭 시 상세한 통제 정보를 제공하는 화면
5. **상세 페이지 화면**: 선택된 지역의 상세 정보 및 실시간 날씨를 제공하는 화면
6. **캘린더 화면**: 날짜별 집회 일정과 정보를 제공하는 화면

