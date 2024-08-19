drop table semi_user;
drop sequence seq_semi_user_id;

create table semi_user(
    user_id number primary key,
    user_name varchar2(20) not null,
    user_email varchar2(50) unique not null,
    user_pw varchar2(1000) not null,
    user_job number not null references semi_job_category(job_category_id),
    user_comment varchar2(100),
    user_profile_image varchar2(300),
    user_status varchar2(20) default 'active' not null,
    user_join_date date default sysdate not null,
    user_out_date date,
    user_role varchar2(5) default 'user' not null,
    check (user_status in ('active', 'inactive', 'danger') and user_role in ('user', 'admin'))
);

create sequence seq_semi_user_id
start with 1
increment by 1
nocache
nocycle;

/* 테스트 데이터 */
insert into semi_user values (0, '테스트이름', '테스트이메일', '테스트비밀번호', 0, '테스트한줄소개', '테스트 프로필 이미지', 'active', sysdate, null, 'user');