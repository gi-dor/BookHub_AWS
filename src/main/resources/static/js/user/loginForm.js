$('#id').on('keyup', function(event) {
    let inputValue = $(this).val();

    if (/^[^a-zA-Z0-9]*$/.test(inputValue)) {
        $(this).val("");
    }
});

let urlSearch = new URLSearchParams(location.search);
if (urlSearch.has("error")) {
    alert("아이디 혹은 비밀번호가 올바르지 않거나, 탈퇴처리된 사용자입니다.");
}


