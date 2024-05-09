function cateList() {

    $("#cateList input[name=cateKeyword]").val(1);
    $("#cateList input[name=pubDate]").val(1);
    $("#cateList input[name=page]").val(1);
    $("#cateList input[name=sort]").val("1");
    $("#cateList").trigger("submit");
}

$("#cateKeyword a ").click(function (event) {
    event.preventDefault();
    $("#cateList input[name=cateKeyword]").val($(this).attr("data-cateKeyword"));
    $("#cateList input[name=page]").val(1);
    $("#cateList input[name=sort]").val(1);
    $("#cateList").trigger("submit");
});

$("#pubDate a ").click(function (event) {
    event.preventDefault();

    $("#cateList input[name=pubDate]").val($(this).attr("data-pubDate"));
    $("#cateList input[name=page]").val(1);
    $("#cateList input[name=sort]").val(1);
    $("#cateList").trigger("submit");
});

$("#sort a ").click(function (event) {
    event.preventDefault();
    $("#cateList input[name=page]").val(1);
    $("#cateList input[name=sort]").val($(this).attr("data-sort"));
    $("#cateList").trigger("submit");
});

$(".pagination a").click(function (event) {
    event.preventDefault();
    $("#cateList input[name=page]").val($(this).attr("data-page"));
    $("#cateList").trigger("submit");
})

$("#btn-add-cart").click(function() {
    let len = $("#book-form :checkbox:checked").length;
    if (len === 0) {
        alert("선택된 상품이 없습니다.");
        return;
    }

    $("#book-form").attr("action", "/product/cart/add").trigger("submit");
});