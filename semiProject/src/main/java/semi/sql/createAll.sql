drop table semi_job_category;
drop sequence seq_semi_job_category_id;

create table semi_job_category(
        job_category_id number primary key,
        job_category_super varchar2(30 char) not null,
        job_category_sub varchar2(30 char) unique not null
);

create sequence seq_semi_job_category_id
start with 1
increment by 1
nocache
nocycle;

/* 테스트 데이터 */
insert into semi_job_category values(0, '테스트_대분류', '테스트_중분류');
drop table semi_topic_category;
drop sequence seq_semi_topic_category_id;


create table semi_topic_category(
        topic_category_id number primary key,
        topic_category_name varchar2(30 char) unique not null
);

create sequence seq_semi_topic_category_id
start with 1
increment by 1
nocache
nocycle;

/* 테스트 데이터 */
insert into semi_topic_category values(0, '테스트_게시판');
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
drop table semi_topic;

create table semi_topic(
        cate_no number not null,
        post_id number primary key not null,
        post_title varchar2(300) not null,
        post_cont varchar2(1000) not null,
        post_date date default sysdate not null,
        post_modify date default sysdate not null,
        post_tag varchar2(300) not null,
        user_id number references semi_user(user_id)
);
create sequence seq_topic_id
start with 1
increment by 1
nocache
nocycle;

drop table semi_report;
drop sequence seq_semi_report_id;

create table semi_report(
        report_id number primary key,
        report_type varchar2(300) not null,
        report_target_id number not null,
        report_user_id number not null references semi_user(user_id),
        report_desc varchar2(500char),
        reported_date date not null,
        report_status varchar2(50char),
        check(report_type in ('POST', 'USER', 'COMMENT') and report_status in ('IN PROGRESS', 'COMPLETED'))
);

create sequence seq_semi_report_id
start with 1
increment by 1
nocache
nocycle;
drop table semi_user_inactive;

create table semi_user_inactive(
        inactive_user_id number references semi_user(user_id),
        inactive_desc varchar2(300) not null,
        inactive_start_date date default sysdate not null,
        inactive_end_date date not null
);
drop table semi_post_inactive;

create table semi_post_inactive(
        inactive_post_id number references semi_topic(post_id),
        inactive_post_type varchar2(300) not null,
        inactive_post_desc varchar2(300),
        inactive_date date default sysdate not null
);
drop table semi_chat;

create table semi_chat (
    chat_room_id number,
    sender_id number references semi_user(user_id),
    receiver_id number references semi_user(user_id),
    message varchar2(1000) not null,
    sent_time date default sysdate,
    is_read number(1) default 0
);

drop table semi_comment;
drop sequence seq_semi_comment_id;

create table semi_comment (
    comment_id number(10) primary key,
    post_id number references semi_topic(post_id),
    comment_text varchar2(500) not null,
    user_id number references semi_user(user_id),
    created_date timestamp default current_timestamp not null,
    updated_date timestamp default current_timestamp not null,
    comment_status varchar2(20) default 'approved' not null
);

create sequence seq_semi_comment_id
start with 1
increment by 1
nocache
nocycle;