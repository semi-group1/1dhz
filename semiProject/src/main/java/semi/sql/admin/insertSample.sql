select * from semi_job_category;

INSERT INTO SEMI_USER(USER_ID, USER_NAME, USER_EMAIL, USER_PW,USER_JOB)
VALUES(seq_semi_user_id.nextval, '손흥민', 'hmson@naver.com', '1234', 28);
INSERT INTO SEMI_USER(USER_ID, USER_NAME, USER_EMAIL, USER_PW,USER_JOB)
VALUES(seq_semi_user_id.nextval, '김연경', 'ykkim@daum.net', '1234', 28);
INSERT INTO SEMI_USER(USER_ID, USER_NAME, USER_EMAIL, USER_PW,USER_JOB)
VALUES(seq_semi_user_id.nextval, '황희찬', 'hchwang@nate.com', '1234', 28);
INSERT INTO SEMI_USER(USER_ID, USER_NAME, USER_EMAIL, USER_PW,USER_JOB)
VALUES(seq_semi_user_id.nextval, '김상식', 'commonsensekim@nate.com', '1234', 28);

select * from semi_report;
select * from semi_comment;

insert into semi_comment
values(0, 0, '댓글 테스트', 9999, default, default, default);

insert into semi_report
values(0, 'post', 0, 0, '욕설', sysdate, default);
insert into semi_report
values(999, 'user', 0, 9999, '욕설', sysdate, default);
insert into semi_report
values(9999, 'comment', 0, 9999, '욕설', sysdate, default);

update semi_report
set report_status = default;