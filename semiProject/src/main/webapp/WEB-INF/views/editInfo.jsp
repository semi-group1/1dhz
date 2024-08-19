<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>회원 정보 수정</title>
        <style>
            #container{
                position: relative;
                margin: 0px auto;
                width: 1280px;
                height: 100vh;
                border-collapse: collapse;
                border: 1px solid #333;
            }
            .title{
                font-size: 40px;
                text-align: center;
                margin-bottom: 50px;
            }
            .editInfo_form{
                width: 80%;
                margin: 0px auto;
                font-size: 30px;
                margin-left: 350px;
            }
            .editInfo_form p{
                width: 100%;
            }
            .editInfo_form input{
                width: 500px;
                height: 30px;
            }

            .editInfo_form select{
                width: 300px;
                height: 30px;
            }

            #auth_btn{
                width: 120px;
                height: 36px;
                background-color: lightblue;
                border: 1px solid #333;
            }

            #cancel_btn{
                background-color: lightcoral;
                width: 150px;
                height: 50px;
                font-size: 25px;
                border: 1px solid #333;
                margin-left: 50px;
            }

            #complete_btn{
                background-color: lightgreen;
                width: 150px;
                height: 50px;
                font-size: 25px;
                border: 1px solid #333;
                margin-left: 100px;
            }

        </style>
    </head>
    <body>
        <div id="container">
            <div class="title">회원 정보 수정</div>
            <form class="editInfo_form" action="/semiProject/editInfoProcess" method="post">
                <p>
                    <label for="email">이메일</label><br />
                    <input type="text" name="email" id="email" value="${user.user_email}" />
                    <a href="#"><button id="auth_btn">이메일 인증</button></a>
                </p>
                <p>
                    <label for="passwd">비밀번호</label><br />
                    <input type="password" name="passwd" id="passwd" />
                </p>
                <p>
                    <label for="passwdCheck">비밀번호 확인</label><br />
                    <input type="password" name="passwdCheck" id="passwdCheck" />
                </p>
                <p>
                    <label for="name">닉네임</label><br />
                    <input type="text" name="name" id="name" value="${user.user_name }" />
                </p>
                <p>
                    <label>직군 정보</label><br />
                    <select name="sup_job" id="sup_job">
                        <option>경영&사무</option>
                        <option>무역&유통</option>
                        <option>영업&고객상담</option>
                        <option>서비스</option>
                        <option>마케팅&광고&홍보</option>
                        <option>IT&인터넷</option>
                        <option>디자인</option>
                        <option>연구개발&설계</option>
                        <option>생산&제조</option>
                    </select>
                    <select name="sub_job" id="sub_job">
                        <option>------------------[경영&사무]------------------</option>
                        <option>기획&전략&경영</option>
                        <option>사무&총무&법무</option>
                        <option>인사&노무&교육</option>
                        <option>경리&회계&결산</option>
                        <option>재무&세무&IR</option>
                        <option>------------------[무역&유통]------------------</option>
                        <option>해외영업&무역영업</option>
                        <option>수출입&무역사무</option>
                        <option>구매&자재</option>
                        <option>상품기획&MD</option>
                        <option>유통&물류&재고</option>
                        <option>배송&택배&운송</option>
                        <option>운전&기사</option>
                        <option>화물&중장비</option>
                        <option>------------------[영업&고객상담]------------------</option>
                        <option>제품&서비스영업</option>
                        <option>금융&보험영업</option>
                        <option>IT&솔루션&기술영업</option>
                        <option>영업관리&지원&영업기획</option>
                        <option>광고영업</option>
                        <option>법인영업</option>
                        <option>판매&서빙&매장관리</option>
                        <option>단순홍보&회원관리</option>
                        <option>아웃바운드</option>
                        <option>고객상담&인바운드</option>
                        <option>CS관리&강의</option>
                        <option>------------------[서비스]------------------</option>
                        <option>요리&영양&제과제빵&바리스타</option>
                        <option>설치&정비&A/S</option>
                        <option>시설&보안&경비&안전</option>
                        <option>레저&스포츠</option>
                        <option>여행&항공&숙박</option>
                        <option>뷰티&미용&애완</option>
                        <option>주차&세차&주유</option>
                        <option>청소&가사&육아</option>
                        <option>이벤트&웨딩&도우미</option>
                        <option>------------------[마케팅&광고&홍보]------------------</option>
                        <option>마케팅&광고&분석</option>
                        <option>홍보&PR</option>
                        <option>전시&컨벤션</option>
                        <option>------------------[IT&인터넷]------------------</option>
                        <option>웹프로그래머</option>
                        <option>응용프로그래머</option>
                        <option>시스템프로그래머</option>
                        <option>DBA&데이터베이스</option>
                        <option>네트워크&서버&보안</option>
                        <option>웹기획&PM</option>
                        <option>웹마케팅</option>
                        <option>컨텐츠&사이트운영</option>
                        <option>HTML&퍼블리싱&UI개발</option>
                        <option>웹디자인</option>
                        <option>QA&테스터&검증</option>
                        <option>게임</option>
                        <option>ERP&시스템분석&설계</option>
                        <option>IT&디자인&컴퓨터강사</option>
                        <option>동영상제작&편집</option>
                        <option>빅데이터&AI</option>
                        <option>소프트웨어&하드웨어</option>
                        <option>------------------[디자인]------------------</option>
                        <option>그래픽디자인&CG</option>
                        <option>출판&편집디자인</option>
                        <option>제품&산업디자인</option>
                        <option>캐릭터&애니매이션</option>
                        <option>광고&시각디자인</option>
                        <option>의류&패션&잡화디자인</option>
                        <option>전시&공간디자인</option>
                        <option>디자인기타</option>
                        <option>------------------[연구개발&설계]------------------</option>
                        <option>자동차&조선&기계</option>
                        <option>반도체&디스플레이</option>
                        <option>화학&에너지&환경</option>
                        <option>전기&전자&제어</option>
                        <option>기계설계&CAD&CAM</option>
                        <option>통신기술&네트워크구축</option>
                        <option>바이오&제약&식품</option>
                        <option>------------------[생산&제조]------------------</option>
                        <option>생산관리&공정관리&품질관리</option>
                        <option>생산&제조&설비&조립</option>
                        <option>포장&가공</option>
                        <option>섬유&의류&패션</option>
                    </select>
                </p>
                <p>
                    <label for="comment">한 줄 소개</label><br />
                    <input type="text" max="15" name="comment" id="comment" value="${user.user_comment }" />
                </p>
                <p>
                	<a href="#"><button id="cancel_btn">취소</button></a>
                	<button id="complete_btn">수정 완료</button></a>
                </p>
            </form>
        </div>
    </body>
</html>