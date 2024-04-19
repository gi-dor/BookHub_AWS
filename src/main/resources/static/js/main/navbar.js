// 검색어 입력창 클릭시 변경
const searchInput = document.getElementById('searchInput');

searchInput.addEventListener('focus', function() {
    this.placeholder = '';
});

searchInput.addEventListener('blur', function() {
    this.placeholder = '검색어를 입력하세요.';
});


// category버튼을 클릭할 때마다 도서 카테고리 메뉴가 보이거나 숨김
$("#btn-toggle-category").click(function() {
    $("#menu-book-category").toggleClass("d-none")
});

