/**
 * 
 */

function userInfoBtnClickHandler(event) {
	let userId = event.target.dataset.id;
	window.location.href = 'info?userId=' + userId;
}

function inactivateButtonClickHandler(event) {
	let isPost = event.target.classList.contains('post');
	let id = event.target.dataset.id;
	let url = 'inactivate?';

	if (isPost) {
		url += 'postId=' + id;
	} else {
		url += 'userId=' + id;
	}
	window.location.href = url;
}

function backButtonClickHandler(event) {
	event.preventDefault();
	window.history.back();
}

function addUserFunctions() {
	console.log('회원 관리 페이지 함수 추가');
	let btns = document.querySelectorAll(".admin-userinfo-btn");
	let backBtn = document.querySelector(".historyBack");
	let inactiveBtns = document.querySelectorAll('.inactivateBtn')

	if (btns != null) {
		for (let btn of btns) {
			btn.addEventListener('click', userInfoBtnClickHandler);
		}
	}

	if (backBtn != null) {
		backBtn.addEventListener('click', backButtonClickHandler);
	}

	if (inactiveBtns != null) {
		for (let btn of inactiveBtns) {
			btn.addEventListener('click', inactivateButtonClickHandler);
		}
	}
}

function changeTypeHandler(event) {
	let keywordTag = document.querySelector('.admin-search #keyword');
	keywordTag.value = '';
	switch (event.target.value) {
		case 'userId':
			keywordTag.setAttribute('type', 'number');
			break;
		case 'userName':
			keywordTag.setAttribute('type', 'text');
			break;
		case 'userEmail':
			keywordTag.setAttribute('type', 'email');
			break;
	}
}

function init() {
	let typeTag = document.querySelector('.admin-search select[name="type"]');

	if (typeTag != null) {
		typeTag.addEventListener('change', changeTypeHandler);
	}

	addUserFunctions();
}

window.addEventListener('load', init);