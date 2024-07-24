## 크레이지 스페이스 뽐뿌의 백엔드입니다.
### 목표: 한국으로 국제결혼을 하러 오는 외국인과 일하러 오는 외국인 노동자들의 한글 교육 부족 문제를 AI와 라즈베리파이 모듈을 활용한 서비스를 통해 문제를 해결함으로써 대한민국에서 더 나은 삶을 영위할 수 있도록 돕는다.
<img width="234" alt="스크린샷 2024-07-24 오후 12 18 18" src="https://github.com/user-attachments/assets/d3e1b964-4f23-4797-bb7f-eca7042eec12">
<img width="238" alt="스크린샷 2024-07-24 오후 12 43 37" src="https://github.com/user-attachments/assets/719cdb77-db56-45b2-975f-24b9c91d5955">

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
- SpringBoot 3.3.1
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

### 프론트엔드 *안광윤*
- Flutter (Language: Dart)

### 디자인 *최준빈*
- Figma

group = 'com.crazyspace'
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
