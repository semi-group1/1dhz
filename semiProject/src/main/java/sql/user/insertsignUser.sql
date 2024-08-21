/*
 * 이메일
 * 비밀번호
 * */
insert into semi_user(USER_ID, USER_EMAIL, USER_PW) values (9000, 'eogks0815@naver.com', '123456');
insert into semi_user(USER_ID, USER_EMAIL, USER_PW) values (9001, 'eogks0816@naver.com', '123457');
insert into semi_user(USER_ID, USER_EMAIL, USER_PW) values (9002, 'eogks0817@naver.com', '123458');

commit;

select * from semi_user;