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
IntelliJ의 TODO 창에서 구현할 메서드들을 확인하고 어노테이션을 추가하세요.

**참고 예시** (1개만 완성된 상태):
```java
// TODO: @Select 어노테이션으로 ID 조회 쿼리 작성 ✅ 완성 예시
@Select("SELECT * FROM mybatismember WHERE id = #{id}")
@Results({
    @Result(property = "id", column = "id"),
    @Result(property = "email", column = "email"),
    @Result(property = "name", column = "name"),
    @Result(property = "createdAt", column = "created_at")
})
Member findMemberById(@Param("id") Long id);

// TODO: @Insert 어노테이션으로 회원 등록 쿼리 작성
void insertMember(Member member);

// TODO: @Select 어노테이션으로 전체 회원 조회 쿼리 작성  
List<Member> findAllMembers();
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
IntelliJ의 TODO 창에서 구현할 메서드들을 확인하고 어노테이션을 추가하세요.

**참고 예시** (1개만 완성된 상태):
```java
// TODO: @Select 어노테이션으로 닉네임 중복 확인 쿼리 작성 ✅ 완성 예시
@Select("SELECT COUNT(*) FROM mybatismemberprofile WHERE nickname = #{nickname}")
int countByNickname(@Param("nickname") String nickname);

// TODO: @Insert와 @Options 어노테이션으로 프로필 등록 쿼리 작성
void insertProfile(MemberProfile profile);

// TODO: @Select와 @Results 어노테이션으로 회원 ID 조회 쿼리 작성
MemberProfile findProfileByMemberId(@Param("memberId") Long memberId);
```

---

## 🔧 학습 방법

### 1단계: IntelliJ TODO 창 활용
- `View → Tool Windows → TODO` 또는 `Alt+6`
- 구현해야 할 메서드들이 TODO 목록에 표시됩니다

### 2단계: 테스트로 검증  
```bash
./gradlew test --tests "*MapperTest"
```

### 3단계: 막힐 때 참고 파일 확인
- **회원 쿼리**: `MemberMapperRef.java` 
- **프로필 쿼리**: `MemberProfileMapperRef.java`

📚 **추천 학습 순서**:
1. **회원(Member)** 기본 쿼리 → 2. **프로필(MemberProfile)** 관계 쿼리