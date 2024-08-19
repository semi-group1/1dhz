select * from semi_job_category;
select * from semi_user;
select * from semi_topic;
select * from semi_user_inactive;
select * from semi_post_inactive;

INSERT INTO SEMI_USER(USER_ID, USER_NAME, USER_EMAIL, USER_PW, USER_JOB)
VALUES(0, '테스트 유저', 'test@email.com', '1234', 0);

SELECT user_id, user_name, user_email, job_category_super, job_category_sub, user_status, to_char(user_join_date, 'YYYY-MM-DD') user_join_date
FROM SEMI_USER su JOIN SEMI_JOB_CATEGORY sjc ON su.USER_JOB = sjc.JOB_CATEGORY_ID
WHERE user_id = 0;

insert into semi_topic
values(0, 0, '테스트 게시글 제목', '테스트 게시글 내용', default, default, 'test_tag', 0, default, default);

insert into semi_topic
values(0, 99999, '직무 테스트 게시글 제목', '직무 테스트 게시글 내용', default, default, 'test_tag', 0, default, 'y');

SELECT COUNT(*) AS user_count
FROM SEMI_USER_INACTIVE sui
WHERE sui.INACTIVE_USER_ID = 0 AND SYSDATE BETWEEN sui.INACTIVE_START_DATE AND sui.INACTIVE_END_DATE


delete from semi_user_inactive;
delete from semi_post_inactive;

insert into semi_user_inactive
values(0, '테스트', sysdate, sysdate + 3);

select * from semi_report;

insert into semi_report
values(0, 'post', 0, 0, '욕설', sysdate, default);