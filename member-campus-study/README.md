# Member Campus Study

## 🎯 프로젝트 목적

이 프로젝트는 **JPA와 MyBatis 쿼리 작성법을 학습하는 예제**입니다.

### 학습 목표
- JPA Repository 메서드 작성법 익히기
- MyBatis Mapper SQL 쿼리 작성법 익히기
- 회원/프로필 테이블 관계(외래키) 이해
- CRUD 완전 구현 연습

### 프로젝트 구조
- **서비스**: 완성된 비즈니스 로직 (수정 불필요)
- **쿼리**: JPA Repository와 MyBatis Mapper 구현 필요

## 🚀 실행 방법

```bash
./gradlew bootRun
```

쿼리를 구현하면 정상 작동, 구현하지 않으면 에러 발생!

## 📋 테스트할 API

### JPA 방식: `/api/jpa/members`
- POST `/api/jpa/members` - 회원가입
- GET `/api/jpa/members` - 전체 조회
- GET `/api/jpa/members/{id}` - ID로 조회
- GET `/api/jpa/members/email/{email}` - 이메일로 조회

### MyBatis 방식: `/api/mybatis/members`  
- POST `/api/mybatis/members` - 회원가입
- GET `/api/mybatis/members` - 전체 조회
- GET `/api/mybatis/members/{id}` - ID로 조회
- GET `/api/mybatis/members/email/{email}` - 이메일로 조회

### 테스트 예시
```bash
# 회원가입 테스트
curl -X POST localhost:8080/api/jpa/members \
  -H "Content-Type: application/json" \
  -d '{"email":"test@test.com","name":"홍길동","phone":"010-1234-5678","age":25,"gender":"MALE"}'
```

## 📁 구현할 파일

### JPA (완성품 - 참고용)
- `MemberRepository.java` ← 회원 관련 모든 메서드 구현 완료
- `MemberProfileRepository.java` ← 프로필 관련 모든 메서드 구현 완료

### MyBatis 쿼리 문제 (학습 구현 필요)
- `MemberMapper.java` ← 회원 SQL 쿼리 작성 필요
- `MemberProfileMapper.java` ← 프로필 SQL 쿼리 작성 필요

### 참고 자료 (Reference)
- `MemberMapperRef.java` ← 회원 MyBatis 참고 코드
- `MemberProfileMapperRef.java` ← 프로필 MyBatis 참고 코드

## 📊 데이터베이스 구조

### 회원 테이블 (Member)
- **JPA**: `jpamember` 테이블
- **MyBatis**: `mybatismember` 테이블

### 프로필 테이블 (MemberProfile) 
- **JPA**: `jpamemberprofile` 테이블 → `jpamember.id` 외래키
- **MyBatis**: `mybatismemberprofile` 테이블 → `mybatismember.id` 외래키

### 프로필 필드
- **닉네임** (nickname): 사용자 닉네임
- **이름** (name): 실제 이름  
- **프로필 이미지** (profileImageUrl): 프로필 사진 URL
- **우편번호** (postalCode): 우편번호
- **주소** (address): 기본 주소
- **상세주소** (addressDetail): 상세 주소
- **휴대전화번호** (mobilePhone): 휴대폰 번호
- **메모** (memo): 추가 메모

## 📝 쿼리 구현 방법

### 1. 회원(Member) 쿼리 구현

#### JPA Repository 메서드 (참고용)
JPA는 메서드 이름만으로 쿼리를 자동 생성합니다:
```java
// 이메일 관련
Optional<Member> findByEmail(String email);
boolean existsByEmail(String email);

// 검색 메서드들 (모두 자동 구현됨)
List<Member> findByNameContaining(String name);
List<Member> findByAgeBetween(Integer minAge, Integer maxAge);
List<Member> findByGender(Gender gender);
List<Member> findByNameContainingAndGender(String name, Gender gender);
```

#### MyBatis SQL 쿼리 (`MemberMapper.java`)
```java
// TODO: 이 어노테이션들을 완성하세요

@Select("SELECT * FROM mybatismember WHERE name LIKE ?")
List<Member> findByNameContaining(String name);

@Select("SELECT * FROM mybatismember WHERE age BETWEEN ? AND ?") 
List<Member> findByAgeBetween(Integer minAge, Integer maxAge);

@Select("SELECT * FROM mybatismember WHERE gender = ?")
List<Member> findByGender(String gender);

@Insert("INSERT INTO mybatismember (...) VALUES (...)")
void insertMember(Member member);

@Update("UPDATE mybatismember SET ... WHERE id = ?")
void updateMember(Member member);

@Delete("DELETE FROM mybatismember WHERE id = ?")
void deleteMember(Long id);
```

### 2. 프로필(MemberProfile) 쿼리 구현

#### JPA Repository 메서드 (완성품 - 참고용)
```java
// 회원 관련
Optional<MemberProfile> findByMemberId(Long memberId);
boolean existsByMemberId(Long memberId);

// 닉네임 관련
Optional<MemberProfile> findByNickname(String nickname);
boolean existsByNickname(String nickname);
List<MemberProfile> findByNicknameContaining(String nickname);

// 검색 메서드들 (모두 자동 구현됨)
List<MemberProfile> findByNameContaining(String name);
List<MemberProfile> findByPostalCode(String postalCode);
List<MemberProfile> findByAddressContaining(String address);
Optional<MemberProfile> findByMobilePhone(String mobilePhone);
```

#### MyBatis SQL 쿼리 (`MemberProfileMapper.java`)
```java
// TODO: 이 어노테이션들을 완성하세요

@Insert("INSERT INTO mybatismemberprofile (...) VALUES (...)")
@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
void insertProfile(MemberProfile profile);

@Select("SELECT * FROM mybatismemberprofile WHERE member_id = ?")
@Results({ /* 컬럼 매핑 */ })
MemberProfile findProfileByMemberId(@Param("memberId") Long memberId);

@Select("SELECT * FROM mybatismemberprofile WHERE nickname = ?")
@Results({ /* 컬럼 매핑 */ })
MemberProfile findProfileByNickname(@Param("nickname") String nickname);

@Update("UPDATE mybatismemberprofile SET ... WHERE id = ?")
void updateProfile(MemberProfile profile);

@Delete("DELETE FROM mybatismemberprofile WHERE id = ?")
void deleteProfile(@Param("id") Long id);
```

---

💡 **꿀팁**: 막히면 다음 참고 파일들을 확인하세요!
- **회원 쿼리**: `MemberMapperRef.java` 
- **프로필 쿼리**: `MemberProfileMapperRef.java`

📚 **학습 단계**:
1. 먼저 **회원(Member)** 쿼리부터 구현하기
2. 다음으로 **프로필(MemberProfile)** 쿼리 구현하기  
3. 1:1 관계와 외래키 개념 이해하기