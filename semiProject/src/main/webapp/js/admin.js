/**
 * 
 */

function userInfoBtnClickHandler(event) {
	let userId = event.target.dataset.id;
	window.location.href = 'info?userId=' + userId;
}

function backButtonClickHandler() {
	window.history.back();
}

function addUserFunctions() {
	console.log('회원 관리 페이지 함수 추가');
	let btns = document.querySelectorAll(".admin-userinfo-btn");
	let backButton = document.querySelector("#historyBack");

	for (let btn of btns) {
		btn.addEventListener('click', userInfoBtnClickHandler);
	}

	backButton.addEventListener('click', backButtonClickHandler);
}

function init() {
	addUserFunctions();
}

window.addEventListener('load', init);