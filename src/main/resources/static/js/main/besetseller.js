function bestSeller() {

    $("#bestSeller input[name=period]").val(3);
    $("#bestSeller input[name=cateKeyword]").val(1);
    $("#bestSeller input[name=pubDate]").val(5);
    $("#bestSeller input[name=page]").val(1);
    $("#bestSeller input[name=sort]").val("1");
    $("#bestSeller").trigger("submit");
}

$("#period a ").click(function (event) {
    event.preventDefault();
    $("#bestSeller input[name=period]").val($(this).attr("data-period"));
    $("#bestSeller input[name=cateKeyword]").val(1);
    $("#bestSeller input[name=page]").val(1);
    $("#bestSeller input[name=sort]").val(1);
    $("#bestSeller").trigger("submit");
});
$("#cateKeyword a ").click(function (event) {
    event.preventDefault();
    $("#bestSeller input[name=cateKeyword]").val($(this).attr("data-cateKeyword"));
    $("#bestSeller input[name=page]").val(1);
    $("#bestSeller input[name=sort]").val(0);
    $("#bestSeller").trigger("submit");
});

$("#pubDate a ").click(function (event) {
    event.preventDefault();
    $("#bestSeller input[name=cateKeyword]").val(1);
    $("#bestSeller input[name=pubDate]").val($(this).attr("data-pubDate"));
    $("#bestSeller input[name=page]").val(1);
    $("#bestSeller input[name=sort]").val(1);
    $("#bestSeller").trigger("submit");
});

$("#sort a ").click(function (event) {
    event.preventDefault();
    $("#bestSeller input[name=page]").val(1);
    $("#bestSeller input[name=sort]").val($(this).attr("data-sort"));
    $("#bestSeller").trigger("submit");
});

$(".pagination a").click(function (event) {
    event.preventDefault();
    $("#bestSeller input[name=page]").val($(this).attr("data-page"));
    $("#bestSeller").trigger("submit");
})


// 카트에 담기
$(".btn-add-cart").click(function () {
    let len = $("#book-form :checkbox:checked").length;
    if (len === 0) {
        alert("선택된 상품이 없습니다.");
        return;
    }

    $("#book-form").attr("action", "/product/cart/add").trigger("submit");
    alert("장바구니에 담았습니다.");
});

    // 전체 선택
    $('.btn-all-choice').click(function() {
        // 전체 선택 버튼이 체크되어 있는지 확인
        const isChecked = $(this).hasClass('active');

        // 폼 안에 있는 모든 체크박스의 상태 변경
        $('#book-form input[type="checkbox"]').prop('checked', isChecked);

        // 전체 선택 버튼의 상태 변경
        $(this).toggleClass('active');
    });

    //찜에 담기
    $(".btn-add-wishlist").click(function () {
        let len = $("#book-form :checkbox:checked").length;
        if (len === 0) {
            alert("선택된 상품이 없습니다.");
            return;
        }

        $("#book-form").attr("action", "/product/wishlist/add").trigger("submit");
        alert("위시리스트에 담았습니다.");
    });

//리스트에 있는 하트(위시리스트 표시)
$(".btn-add-wishlist2").click(function (event) {
    // 기본 동작인 링크 이동을 막음
    event.preventDefault();

    // 위시리스트에 아이템이 추가되었다는 알림 표시
    alert('위시리스트에 담겼습니다.');

    // 클릭된 버튼 안에 있는 하트 아이콘에 대해 색을 변경
    $(this).find('i').addClass('fas text-danger');

    // 서버로 폼을 제출하여 위시리스트에 아이템 추가
    $.get($(this).attr('href'), function(response) {
    });
});

//리스트에 있는 장바구니
$(".btn-add-cart2").click(function (event) {
    // 기본 동작인 링크 이동을 막음
    event.preventDefault();

    alert('장바구니에 담겼습니다.');

    // 서버로 폼을 제출하여 장바구니에 아이템 추가
    $.get($(this).attr('href'), function(response) {
    });
});