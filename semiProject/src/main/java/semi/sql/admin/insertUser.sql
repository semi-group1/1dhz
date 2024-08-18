select * from semi_job_category;
select * from semi_user;
select * from semi_topic;

INSERT INTO SEMI_USER(USER_ID, USER_NAME, USER_EMAIL, USER_PW, USER_JOB)
VALUES(0, '테스트 유저', 'test@email.com', '1234', 0);

SELECT user_id, user_name, user_email, job_category_super, job_category_sub, user_status, to_char(user_join_date, 'YYYY-MM-DD') user_join_date
FROM SEMI_USER su JOIN SEMI_JOB_CATEGORY sjc ON su.USER_JOB = sjc.JOB_CATEGORY_ID
WHERE user_id = 0;

ALTER TABLE SEMI_TOPIC ADD JOB_YN VARCHAR2(1) DEFAULT 'n' NOT NULL;
ALTER TABLE SEMI_TOPIC ADD CONSTRAINT SEMI_TOPIC_CHECK CHECK (job_yn in ('y', 'n'));

insert into semi_topic
values(0, 0, '테스트 게시글 제목', '테스트 게시글 내용', default, default, 'test_tag', 0, default, default);