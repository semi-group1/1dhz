<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>마이페이지</title>
    <style>
        #container{
            position: relative;
            margin: 0px auto;
            width: 1280px;
            height: 100vh;
            border-collapse: collapse;
            border: 1px solid #333;
        }
        .left{
            position: absolute;
            width: 50%;
            height: 100%;
            left: 0;
        }
        .right{
            position: absolute;
            width: 50%;
            height: 100%;
            right: 0;
            border-left: 1px solid #333;
        }
        /* 말풍선 */
        .speech-bubble {
            position: relative;
            background: #eee;
            color: #333;
            width: 400px;
            height: 70px;
            margin-left: 200px;
            border-radius: .4em;
            text-align: center;
            line-height: 70px;
        }

        .speech-bubble:after {
            content: '';
            position: absolute;
            bottom: 0;
            left: 28%;
            width: 0;
            height: 0;
            border: 46px solid transparent;
            border-top-color: #eee;
            border-bottom: 0;
            border-left: 0;
            margin-bottom: -46px;
        }
        /* 프로필 이미지 */
        .profile{
            margin: 50px auto;
            width: 40%;
            text-align: center;
            font-size: 30px;
        }
        .profile_image{
            width: 100%;
            border-radius: 50%;
        }
        /* 회원 정보 수정 버튼 */
        .info_edit_btn{
            margin-top: 30px;
            width: 200px;
            height: 50px;
            border-radius: 5px;
            background-color: lightgreen;
            border: 1px solid #333;
            color: #333;
            font-size: 20px;
            &:hover{
                background-color: rgb(100, 200, 100);
                color: #000;
            }
        }

        /* 작성한 게시글 */
        .wrote_post_title{
            width: 90%;
            margin: 10px auto;
            margin-top: 100px;
            font-size: 30px;
        }
        .wrote_post_item{
            width: 100%;
            height: 40px;
            line-height: 40px;
        }
        .wrote_post{
            margin: 0px auto;
            width: 90%;
            height: 400px;
            border: 1px solid #000;
            font-size: 20px;
            overflow: hidden;
        }
        .wrote_post a{
            text-decoration: none;
            color: #333;
        }
        .wrote_post_item #date{
            float: right;
            margin-right: 10px;
            color: gray;
            clear: both;
        }
    </style>
</head>
<body>
    
    <div id="container">
        <div class="left">
            <div class="speech-bubble">
                <h1>${user.user_comment}</h1>
            </div>
            <div class="profile">
                <img src="/semiProject/res/images/profile.jpg" alt="프로필이미지" class="profile_image"/>
                <p>${user.user_name}</p>
                <p>${user.user_job}</p>
                <p>#태그1 #태그2</p>
                <p>#태그3 #태그4</p>
                <a href="/semiProject/editInfo/${user.user_id}"><button class="info_edit_btn">정보 수정</button></a>
            </div>
        </div>
        
        <div class="right">
            <div class="wrote_post_title">작성한 게시글</div>
            <div class="wrote_post">
                <a href="#"><div class="wrote_post_item">
                    <span id="category">[분류1]</span>
                    <span id="title">게시글 제목1</span>
                    <span id="date">2024-08-14</span>
                </div></a>
                <a href="#"><div class="wrote_post_item">
                    <span id="category">[분류2]</span>
                    <span id="title">게시글 제목2</span>
                    <span id="date">2024-08-13</span>
                </div></a>
                <a href="#"><div class="wrote_post_item">
                    <span id="category">[분류3]</span>
                    <span id="title">게시글 제목3</span>
                    <span id="date">2024-08-12</span>
                </div></a>
                <a href="#"><div class="wrote_post_item">
                    <span id="category">[분류4]</span>
                    <span id="title">게시글 제목4</span>
                    <span id="date">2024-08-11</span>
                </div></a>
                <a href="#"><div class="wrote_post_item">
                    <span id="category">[분류5]</span>
                    <span id="title">게시글 제목5</span>
                    <span id="date">2024-08-10</span>
                </div></a>
                <a href="#"><div class="wrote_post_item">
                    <span id="category">[분류6]</span>
                    <span id="title">게시글 제목6</span>
                    <span id="date">2024-08-09</span>
                </div></a>
                <a href="#"><div class="wrote_post_item">
                    <span id="category">[분류7]</span>
                    <span id="title">게시글 제목7</span>
                    <span id="date">2024-08-08</span>
                </div></a>
                <a href="#"><div class="wrote_post_item">
                    <span id="category">[분류8]</span>
                    <span id="title">게시글 제목8</span>
                    <span id="date">2024-08-07</span>
                </div></a>
                <a href="#"><div class="wrote_post_item">
                    <span id="category">[분류9]</span>
                    <span id="title">게시글 제목9</span>
                    <span id="date">2024-08-06</span>
                </div></a>
                <a href="#"><div class="wrote_post_item">
                    <span id="category">[분류10]</span>
                    <span id="title">게시글 제목10</span>
                    <span id="date">2024-08-05</span>
                </div></a>
                <a href="#"><div class="wrote_post_item">
                    <span id="category">[분류10]</span>
                    <span id="title">게시글 제목10</span>
                    <span id="date">2024-08-05</span>
                </div></a>
            </div>
        </div>
    </div>

</body>
</html>