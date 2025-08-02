-- JPA와 MyBatis 분리 테이블 초기 데이터 삽입 스크립트
-- Spring Boot가 자동으로 실행하는 데이터 초기화 스크립트

-- JPA 샘플 회원 데이터 삽입
INSERT INTO jpamember (id, email, name, phone, age, gender, created_at, updated_at) 
VALUES (jpa_member_seq.NEXTVAL, 'jpa.user1@example.com', 'JPA홍길동', '010-1111-1111', 25, 'MALE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO jpamember (id, email, name, phone, age, gender, created_at, updated_at) 
VALUES (jpa_member_seq.NEXTVAL, 'jpa.user2@example.com', 'JPA김영희', '010-2222-2222', 30, 'FEMALE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO jpamember (id, email, name, phone, age, gender, created_at, updated_at) 
VALUES (jpa_member_seq.NEXTVAL, 'jpa.user3@example.com', 'JPA박철수', '010-3333-3333', 28, 'MALE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- MyBatis 샘플 회원 데이터 삽입
INSERT INTO mybatismember (id, email, name, phone, age, gender, created_at, updated_at) 
VALUES (mybatis_member_seq.NEXTVAL, 'mybatis.user1@example.com', 'MyBatis홍길동', '010-4444-4444', 26, 'MALE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO mybatismember (id, email, name, phone, age, gender, created_at, updated_at) 
VALUES (mybatis_member_seq.NEXTVAL, 'mybatis.user2@example.com', 'MyBatis김영희', '010-5555-5555', 31, 'FEMALE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO mybatismember (id, email, name, phone, age, gender, created_at, updated_at) 
VALUES (mybatis_member_seq.NEXTVAL, 'mybatis.user3@example.com', 'MyBatis박철수', '010-6666-6666', 29, 'MALE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);