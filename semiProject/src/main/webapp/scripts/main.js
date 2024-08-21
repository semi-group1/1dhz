document.addEventListener('DOMContentLoaded', () => {
    const loginBtn = document.getElementById('login-btn');
    const writeBtn = document.getElementById('write-btn');
    const mainContent = document.getElementById('main-content');
    
    // 상태 추적 변수
    let isLoggedIn = false;
    
    // 초기 화면 구성
    function renderBoard(isLoggedIn) {
        const boardContent = document.getElementById('board-content');
        boardContent.innerHTML = ''; // 기존 내용 지우기

        // 검색 기능
        document.querySelector('.search-input').addEventListener('input', function() {
            const query = this.value.toLowerCase();
            const posts = document.querySelectorAll('#board-content .card');
            posts.forEach(post => {
                const title = post.querySelector('.post-title').textContent.toLowerCase(); 
                const content = post.querySelector('.post-text').textContent.toLowerCase(); // 태그로 변경 가능
                if (title.includes(query) || content.includes(query)) {
                    post.style.display = '';
                } else {
                    post.style.display = 'none';
                }
            });
        });    
		
		
		function loadBoardList(currentPageNo) {
		        if (currentPageNo === undefined) {
		            currentPageNo = "1";
		        }

		        $.ajax({
		            url: "/board/getBoardList",
		            data: { function_name: "getBoardList", current_page_no: currentPageNo },
		            dataType: "JSON",
		            cache: false,
		            async: true,
		            type: "POST",
		            success: function(obj) {
		                displayBoardList(obj);
		            },
		            error: function(xhr, status, error) {
		                console.error("Error fetching board list:", status, error);
		            }
		        });
		    }

		    // 게시판 목록 카드 형태로 표시
		    function displayBoardList(obj) {
		        var state = obj.state;
		        if (state === "SUCCESS") {
		            var data = obj.data;
		            var list = data.list;
		            var totalCount = data.totalCount;
		            var pagination = data.pagination;

		            var cardsContainer = $("#board-cards-container");
		            cardsContainer.empty(); // Clear existing content

		            if (list.length > 0) {
		                list.forEach(function(board) {
		                    var cardHtml = "<div class='card' onclick='goBoardDetail(" + board.board_seq + ");'>";
		                    cardHtml += "<div class='card-header'>" + board.board_subject + "</div>";
		                    cardHtml += "<div class='card-body'>";
		                    cardHtml += "<p><strong>작성자:</strong> " + board.board_writer + "</p>";
		                    cardHtml += "<p><strong>조회수:</strong> " + board.board_hits + "</p>";
		                    cardHtml += "<p><strong>작성일:</strong> " + board.ins_date + "</p>";
		                    cardHtml += "</div></div>";

		                    cardsContainer.append(cardHtml);
		                });

		                // Optional: Add pagination controls if needed
		                $("#pagination").html(pagination);
		            } else {
		                cardsContainer.append("<p>등록된 글이 존재하지 않습니다.</p>");
		            }
		        } else {
		            alert("관리자에게 문의하세요.");
		        }
		    }

		    window.goBoardDetail = function(boardSeq) {
		        location.href = "/board/boardDetail?boardSeq=" + boardSeq;
		    };    

        // 게시판 내용 예시
        const posts = [
            { title: '첫번째 게시글', content: '내용 1' },
            { title: '두번째 게시글', content: '내용 2' },
            { title: '세번째 게시글', content: '내용 3' },
            { title: '네번째 게시글', content: '내용 4' },
            { title: '다섯번째 게시글', content: '내용 5' },
            { title: '여섯번째 게시글', content: '내용 6' },
            { title: '일곱번째 게시글', content: '내용 7' },
            { title: '여덟번째 게시글', content: '내용 8' },
            { title: '아홉번째 게시글', content: '내용 9' },
            { title: '열번째 게시글', content: '내용 10' },
        ];

        posts.forEach(post => {
            const postElement = document.createElement('div');
            postElement.className = 'col-md-6 mb-3';
            postElement.innerHTML = `
                <div class="card">
                    <div class="card-body">
                        <h5 class="post-title">${post.title}</h5>
                        <p class="post-text">${post.content}</p>
                    </div>
                </div>
            `;
            boardContent.appendChild(postElement);
        });

        // 글쓰기 버튼 처리
        writeBtn.style.display = isLoggedIn ? 'inline-block' : 'none';
    }

    // 초기화
    renderBoard(isLoggedIn);

    // 로그인 버튼 클릭 이벤트
    loginBtn.addEventListener('click', () => {
        if (isLoggedIn) {
            isLoggedIn = false;
            loginBtn.textContent = '로그인';
            renderBoard(isLoggedIn);
        } else {
            // 로그인 페이지로 이동
            isLoggedIn = true;
            loginBtn.textContent = '로그아웃';
            renderBoard(isLoggedIn);
        }
    });

    document.getElementById('logo-link').addEventListener('click', () => {
        if (isLoggedIn) {
            if (writeBtn.style.display === 'inline-block') {
                if (confirm('작성 중인 내용이 있습니까? 저장 후 홈으로 돌아가겠습니까?')) {
                    renderBoard(isLoggedIn);
                }
            } else {
                renderBoard(isLoggedIn);
            }
        } else {
            renderBoard(isLoggedIn);
        }
    });

    // 글쓰기 버튼 클릭 이벤트
    writeBtn.addEventListener('click', () => {
        if (isLoggedIn) {
            alert('글쓰기 페이지로 이동합니다.');
            // 실제 글쓰기 페이지로 이동
        } else {
            alert('로그인 후 글쓰기가 가능합니다.');
        }
    });
});