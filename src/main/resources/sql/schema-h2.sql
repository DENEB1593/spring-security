drop table if exists users cascade;

-- 사용자 테이블
create table users
(
    seq           bigint      not null auto_increment,              -- 사용자 pk
    email         varchar(50) not null,                             -- 로그인 이메일
    password      varchar(80) not null,                             -- 비밀번호
    birth         varchar(8)  not null,                             -- 출생일자(yyyymmdd)
    created_at    datetime    not null default current_timestamp(), -- 생성일시
    updated_at    datetime    not null default current_timestamp(), -- 변경일시
    primary key (seq),
    constraint unq_user_email unique (email)
);
