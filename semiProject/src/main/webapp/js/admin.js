/**
 * 
 */

function userInfoBtnClickHandler(event) {
	let userId = event.target.dataset.id;
	window.open('info?userId=' + userId);
}

function addUserFunctions() {
	console.log('회원 관리 페이지 함수 추가');
	let btns = document.querySelectorAll(".admin-userinfo-btn");

	for (let btn of btns) {
		btn.addEventListener('click', userInfoBtnClickHandler);
	}
}

function init() {
	addUserFunctions();
}

window.addEventListener('load', init);