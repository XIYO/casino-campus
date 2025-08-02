-- JPA와 MyBatis 분리 테이블 생성 스크립트
-- Spring Boot가 자동으로 실행하는 DB 초기화 스크립트

-- 기존 테이블이 있으면 삭제 (외래키 때문에 순서 중요)
DROP TABLE IF EXISTS jpamemberprofile CASCADE;
DROP TABLE IF EXISTS mybatismemberprofile CASCADE;
DROP TABLE IF EXISTS jpamember CASCADE;
DROP TABLE IF EXISTS mybatismember CASCADE;

-- 시퀀스 삭제 (Oracle)
DROP SEQUENCE IF EXISTS jpa_member_seq;
DROP SEQUENCE IF EXISTS mybatis_member_seq;
DROP SEQUENCE IF EXISTS jpa_profile_seq;
DROP SEQUENCE IF EXISTS mybatis_profile_seq;

-- JPA용 시퀀스 생성
CREATE SEQUENCE jpa_member_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE;

-- MyBatis용 시퀀스 생성  
CREATE SEQUENCE mybatis_member_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE;

-- JPA 프로필용 시퀀스 생성
CREATE SEQUENCE jpa_profile_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE;

-- MyBatis 프로필용 시퀀스 생성
CREATE SEQUENCE mybatis_profile_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE;

-- JPA 회원 테이블 생성 (JPA가 자동으로 생성하지만 명시적으로 정의)
CREATE TABLE jpamember (
    id NUMBER(19) PRIMARY KEY,
    email VARCHAR2(100) NOT NULL UNIQUE,
    name VARCHAR2(50) NOT NULL,
    phone VARCHAR2(20) NOT NULL,
    age NUMBER(3),
    gender VARCHAR2(10) CHECK (gender IN ('MALE', 'FEMALE', 'OTHER')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- MyBatis 회원 테이블 생성
CREATE TABLE mybatismember (
    id NUMBER(19) PRIMARY KEY,
    email VARCHAR2(100) NOT NULL UNIQUE,
    name VARCHAR2(50) NOT NULL,
    phone VARCHAR2(20) NOT NULL,
    age NUMBER(3),
    gender VARCHAR2(10) CHECK (gender IN ('MALE', 'FEMALE', 'OTHER')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- JPA 회원 프로필 테이블 생성
CREATE TABLE jpamemberprofile (
    id NUMBER(19) PRIMARY KEY,
    member_id NUMBER(19) NOT NULL UNIQUE,
    nickname VARCHAR2(50),
    name VARCHAR2(50),
    profile_image_url VARCHAR2(500),
    postal_code VARCHAR2(10),
    address VARCHAR2(200),
    address_detail VARCHAR2(200),
    mobile_phone VARCHAR2(20),
    memo VARCHAR2(1000),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_jpa_profile_member FOREIGN KEY (member_id) REFERENCES jpamember(id) ON DELETE CASCADE
);

-- MyBatis 회원 프로필 테이블 생성
CREATE TABLE mybatismemberprofile (
    id NUMBER(19) PRIMARY KEY,
    member_id NUMBER(19) NOT NULL UNIQUE,
    nickname VARCHAR2(50),
    name VARCHAR2(50),
    profile_image_url VARCHAR2(500),
    postal_code VARCHAR2(10),
    address VARCHAR2(200),
    address_detail VARCHAR2(200),
    mobile_phone VARCHAR2(20),
    memo VARCHAR2(1000),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_mybatis_profile_member FOREIGN KEY (member_id) REFERENCES mybatismember(id) ON DELETE CASCADE
);

-- 인덱스 생성
CREATE INDEX idx_jpamember_email ON jpamember(email);
CREATE INDEX idx_jpamember_name ON jpamember(name);
CREATE INDEX idx_mybatismember_email ON mybatismember(email);
CREATE INDEX idx_mybatismember_name ON mybatismember(name);
CREATE INDEX idx_jpa_profile_member ON jpamemberprofile(member_id);
CREATE INDEX idx_mybatis_profile_member ON mybatismemberprofile(member_id);