## 크레이지 스페이스 뽐뿌의 백엔드입니다.
### 목표: 한국으로 국제결혼을 하러 오는 외국인과 일하러 오는 외국인 노동자들의 한글 교육 부족 문제를 AI와 라즈베리파이 모듈을 활용한 서비스를 통해 문제를 해결함으로써 대한민국에서 더 나은 삶을 영위할 수 있도록 돕는다.

# 목차
- [개발 환경](#개발-환경)
- [사용 기술](#사용-기술)

## 개발 환경
- Intellij
- Github

## 사용 기술
### 백엔드
#### 주요 프레임워크 / 라이브러리
- Java 21 openjdk
- JAVA 21
- SpringBoot 3.1.7
- SpringBoot Security
- Spring Data JPA
- Lombok

### 서버
- apache tomcat
- naver cloud

#### Build tool
- Gradle 8.6

#### Database
- H2
- Mysql

### 프론트엔드
- Javascript
- Typescript
- Html/Css
- Bootstrap5
- Vue.js

group = 'com.ssarylog'
version = '0.0.1-SNAPSHOT'


#### 비밀번호 암호화
1. 해시
2. 해시 방식
    - SHA1
    - SHA256
    - MD5
    - 왜 이런걸로 비번 암호화 하면 안되는지 (salt 값이 없다.)
3. BCrypt SCrypt, Argon2
    - salt 값 (어떻게 넣는냐에 따라 달라짐)
