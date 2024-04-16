$('#id').on('keyup', function(event) {
    let inputValue = $(this).val();

    if (/^[^a-zA-Z0-9]*$/.test(inputValue)) {
        $(this).val("");
        alert("영어 대.소문자와 숫자만 가능합니다.");
    }
});

