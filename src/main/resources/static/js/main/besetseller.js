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
    });

