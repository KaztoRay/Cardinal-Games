# 🎮 Cardinal Games - 온라인 오목 (Gomoku)

Java Swing 기반 온라인 멀티플레이어 오목 게임

## 📋 프로젝트 개요

Cardinal Games는 Java Swing과 소켓 통신을 활용한 온라인 오목 대전 게임입니다.
서버-클라이언트 구조로 여러 사용자가 동시에 접속하여 오목 대전을 즐길 수 있습니다.

## 🎯 주요 기능

### 회원 시스템
- **회원가입/로그인** - 아이디, 비밀번호, 닉네임, 이메일 등 회원 정보 관리
- **아이디/비밀번호 찾기** - 이름과 닉네임으로 계정 정보 복구
- **회원정보 조회/수정** - 이름, 비밀번호, 이메일, 전화번호 변경 가능
- **프로필 이미지** - 프로필 사진 업로드 및 표시
- **주소 검색** - 주소 API 연동 검색 기능

### 게임 시스템
- **방 생성/입장** - 대기실에서 게임 방을 생성하거나 기존 방에 입장
- **오목 대전** - 2인 실시간 오목 대국
- **관전 기능** - 다른 플레이어의 경기를 관전
- **기권** - 게임 중 기권 가능
- **전적 관리** - 승/패 기록 자동 업데이트

### 커뮤니티
- **로비 채팅** - 대기실 전체 채팅
- **방 내 채팅** - 게임 방 내 채팅
- **접속자 목록** - 현재 접속 중인 사용자 확인
- **전체 랭킹** - 모든 회원의 전적 조회
- **전적 검색** - 특정 사용자의 전적 검색

### 관리자 기능
- **회원 관리** - 전체 회원 정보 조회/검색
- **회원 탈퇴 처리** - 관리자 권한으로 회원 삭제 (백업 후 삭제)

## 🏗️ 기술 스택

- **언어:** Java
- **GUI:** Java Swing
- **통신:** TCP Socket (DataInputStream/DataOutputStream)
- **DB:** MySQL (JDBC) / SQLite 지원
- **프로토콜:** 커스텀 태그 기반 메시지 (`태그//데이터1//데이터2`)

## 📁 프로젝트 구조

```
src/
├── Server.java          # 서버 메인 + CCUser(클라이언트 핸들러) 스레드
├── Client.java          # 클라이언트 메인 (서버 연결 및 프레임 관리)
├── Database.java        # DB 쿼리 처리 (회원가입, 로그인, 전적 등)
├── DBConfig.java        # DB 설정 관리 (MySQL/SQLite 전환)
├── Create_DB.java       # 초기 테이블 생성
├── Room.java            # 게임 방 객체 (플레이어, 관전자, 채팅/오목 기록)
├── LoginFrame.java      # 로그인 화면
├── JoinFrame.java       # 회원가입 화면
├── MainFrame.java       # 대기실 (방 목록, 접속자 목록)
├── GameFrame.java       # 오목 게임 화면 (보드, 돌 배치, 채팅)
├── ChatRoomFrame.java   # 로비 채팅방
├── InfoFrame.java       # 회원정보 조회 화면
├── CInfoFrame.java      # 회원정보 변경 화면
├── RankingFrame.java    # 전체 랭킹 화면
├── SRankFrame.java      # 전적 검색 결과 화면
├── AdminFrame.java      # 관리자 화면
├── AdminModel.java      # 관리자 테이블 모델
├── AddressFrame.java    # 주소 검색 화면
├── findIdFrame.java     # 아이디 찾기 화면
└── findPwFrame.java     # 비밀번호 찾기 화면
```

## 🚀 실행 방법

### 1. 데이터베이스 설정
```properties
# db.properties (MySQL 사용 시)
db.type=mysql
db.url=jdbc:mysql://localhost/login?serverTimezone=Asia/Seoul
db.user=root
db.password=비밀번호

# 설정 파일 없으면 자동으로 SQLite 사용
```

### 2. 테이블 생성
```bash
javac src/Create_DB.java
java -cp src Create_DB
```

### 3. 서버 실행
```bash
javac src/*.java
java -cp src Server
```

### 4. 클라이언트 실행
```bash
java -cp src Client
```

## 📅 프로젝트 기간

2023년 9월 ~ 12월

