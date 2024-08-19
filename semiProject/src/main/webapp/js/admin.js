/**
 * 
 */

function userInfoBtnClickHandler(event) {
	let userId = event.target.dataset.id;
	window.location.href = 'info?userId=' + userId;
}

function userInactivateBtnClickHandler(event) {
	let userId = event.target.dataset.id;
	window.location.href = 'inactivate?userId=' + userId;
}

function backButtonClickHandler() {
	window.history.back();
}

function addUserFunctions() {
	console.log('회원 관리 페이지 함수 추가');
	let btns = document.querySelectorAll(".admin-userinfo-btn");
	let backBtn = document.querySelector(".historyBack");
	let inactivateBtn = document.querySelector('.inactivateBtn')

	for (let btn of btns) {
		btn.addEventListener('click', userInfoBtnClickHandler);
	}

	backBtn.addEventListener('click', backButtonClickHandler);
	inactivateBtn.addEventListener('click', userInactivateBtnClickHandler);
}

function init() {
	addUserFunctions();
}

window.addEventListener('load', init);