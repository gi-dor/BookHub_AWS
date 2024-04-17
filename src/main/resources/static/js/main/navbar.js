// 검색어 입력창 클릭시 변경
const searchInput = document.getElementById('searchInput');
$(searchInput).on('focus', function () {
    this.placeholder = '';
});
$(searchInput).on('blur', function () {
    this.placeholder = '검색어를 입력하세요';
});

// category버튼을 클릭할 때마다 도서 카테고리 메뉴가 보이거나 숨김
$("#btn-toggle-category").click(function () {
    $("#menu-book-category").toggleClass("d-none")
});

// 로그인, 로그아웃 상태 변경
$(document).ready(function () {
    // 로그인 상태 확인 (예시로 true 또는 false로 가정)
    let isLogIn = false;

    function updateNavbar() {
        const login = $('#loginButton');
        if (isLogIn) {
            login.text('로그아웃');
            login.attr('href', '#logout'); // 로그아웃 링크로 변경
        } else {
            login.text('로그인');
            login.attr('href', '#login'); // 로그인 링크로 변경
        }
    }
});
