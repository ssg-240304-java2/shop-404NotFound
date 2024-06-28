![image](https://github.com/ssg-240304-java2/shop-404NotFound/assets/166422645/2b5a502a-9b54-4355-9e94-7e2008efec03)

# 🛒 shop-404NotFound 관리자 시스템

개발자들을 위한 전문 쇼핑몰 'shop-404NotFound'의 관리자 시스템입니다. 이 시스템을 통해 상품, 주문, 회원 등을 효율적으로 관리할 수 있습니다.

## 🚀 프로젝트 개요

이 프로젝트는 개발자 전문 쇼핑몰의 백엔드 관리 시스템으로, 다음과 같은 특징을 가지고 있습니다:

- 📦 상품 관리 (등록, 수정, 삭제, 재고 관리)
- 🛍️ 주문 관리 및 처리
- 👥 회원 관리
- *수정바람

## 👨‍👩‍👧‍👦 팀원 소개 및 담당 역할

- 👩‍💼 고유진: 주문 관리 도메인 개발 및 디자인
- 👨‍🎨 김동휘: 프론트엔드 개발, UI/UX 디자인 (*수정해주세여)
- 👨‍💻 안준렬: 데이터베이스 설계 및 관리 (*수정해주세여)
- 🧑‍💻 좌상현: 백엔드 개발, API 설계 (*수정해주세여)
- 👨‍🔧 추형진: 회원 관리 기능 개발

## 🔑 주요 기능

- 🗂️ 상품 카테고리 및 상품 정보 관리
- 📊 주문 관리, 상태 추적 및 업데이트
- 🔍 회원 정보 조회 및 관리
- 📝 시스템 로그 모니터링
- *수정바람

## 🛠️ 기술 스택

- 🔙 백엔드: Spring Boot, Java
- 🖥️ 프론트엔드: Thymeleaf, JavaScript, Bootstrap
- 💾 데이터베이스: MySQL
- 🔗 ORM: MyBatis
- 🏗️ 빌드 도구: Gradle

## 📁 프로젝트 구조

```mermaid
graph TD
    A[shop-404NotFound] --> B[src]
    B --> C[main]
    B --> D[test]
    C --> E[java]
    C --> F[resources]
    E --> G[com.nf404.devshop]
    G --> H[controller]
    G --> I[service]
    G --> J[repository]
    G --> K[model]
    F --> L[static]
    F --> M[templates]
    L --> N[css]
    L --> O[js]
    M --> P[user]
    M --> Q[product]
    M --> R[order]
```
```markdown
주요 디렉토리 설명:
- `controller`: MVC 패턴의 컨트롤러 클래스들이 위치
- `service`: 비즈니스 로직을 처리하는 서비스 클래스들이 위치
- `repository`: 데이터베이스 연동을 담당하는 레포지토리 클래스들이 위치
- `model`: 데이터 모델 클래스들이 위치
- `static`: CSS, JavaScript 등 정적 파일들이 위치
- `templates`: Thymeleaf 템플릿 파일들이 위치
```

## 📥 설치 및 실행 방법

1. 저장소 클론
git clone https://github.com/ssg-240304-java2/shop-404NotFound.git

2. 데이터베이스 설정
- MySQL에서 새 데이터베이스 생성
- `src/main/resources/application.yml` 파일에서 데이터베이스 연결 정보 수정

3. 프로젝트 빌드 및 실행
- ./gradlew bootRun

4. 또는 웹 브라우저에서 `http://dokalab.asuscomm.com:28080/` 접속

## 📝 회고록

- 고유진: github Desktop을 사용하면서 브랜치 관리 툴을 알게 되어서 좋았고 많은 회의와 빠른 피드백으로 코드를 짤때 헤매지 않을 수 있었습니다. 아쉬운 거는 제 도메인 UI를 좀 더 개선하고 싶고 더 현실적인 로직을 짜고 싶습니다.
- 좌상현 : 처음으로 백 로직 부분과 프론트 부분을 구현해 봐서 좋은 경험이 되었다. 또 전 프로젝트에서 미흡했던 Git Flow부분도 많이 보완이 된 느낌이다. <br>
이번 계기로 frontend 담당자와 backend 담당자의 커뮤니케이션이 굉장히 중요하다고 느껴졌고, 사용자에게 더 나은 프로젝트를 제공하기 위해 많은 것을 <br>
고려해야 된다고 느끼게 되었다. 아쉬웠던 부분은 내 테스크를 목표 기한까지 못한 일들이 있어서 프로젝트 일정에 딜레이가 생기게 된 것 같다. <br>
다음부터는 더 세밀한 계획을 새워서 관리해야 할 것 같다.
