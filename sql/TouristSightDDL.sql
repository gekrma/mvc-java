--*DDL 전 필수작업
-- cmd -> sqlplus / as sysdba -> alter system set processes=300 scope=spfile -> shutdown immediate -> startup
-- 한 번에 많은 작업을 하기 위한 프로세스 수 변경(프로세스 수의 default 는 100)
-- 이후 데이터베이스에 대하여 shutdown immediate(강제종료) 와 startup(시작) 을 해야만 비로소 프로세스 수 변경이 반영됨

-- Service
DROP TABLE service CASCADE CONSTRAINTS;

-- Sight
DROP TABLE sight CASCADE CONSTRAINTS;

-- Detail
DROP TABLE detail CASCADE CONSTRAINTS;

-- sequence
DROP SEQUENCE SEQ;

CREATE TABLE service (
    fname                       VARCHAR2(100) PRIMARY KEY,
    entrance               	    CHAR(1) NOT NULL,
    parking         		    CHAR(1) NOT NULL,
    remove_height               CHAR(1) NOT NULL,
    alevator          	        CHAR(1) NOT NULL,
    toilet		                CHAR(1) NOT NULL,
    guestroom                   CHAR(1) NOT NULL,
    seats                       CHAR(1) NOT NULL,
    ticket_office               CHAR(1) NOT NULL,
    blind_service               CHAR(1) NOT NULL,
    deaf_service                CHAR(1) NOT NULL,
    info_service                CHAR(1) NOT NULL,
    wheelchair                  CHAR(1) NOT NULL
);

CREATE TABLE sight (
    id              number PRIMARY KEY,
    fname           VARCHAR2(100) NOT NULL,
    district        VARCHAR2(20) NOT NULL,
    section         VARCHAR2(10) NOT NULL,
    hits            number NOT NULL
);

CREATE TABLE detail (
    id              number NOT NULL,
    tel             VARCHAR2(15),
    address         VARCHAR2(100) NOT NULL,
    image           VARCHAR2(200),
    homepage        VARCHAR2(200)
);

ALTER TABLE sight ADD FOREIGN KEY (fname) REFERENCES service (fname);
ALTER TABLE detail ADD FOREIGN KEY (id) REFERENCES sight (id); 

CREATE SEQUENCE SEQ INCREMENT BY 1 START WITH 1;
