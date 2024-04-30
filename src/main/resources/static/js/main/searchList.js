

//판매량순, 출시일순, 평점순, 리뷰순
    function submitExtra() {

    $("#form-extra-search input[name=page]").val(1);
    $("#form-extra-search input[name=sort]").val("sales");
    $("#form-extra-search").trigger("submit");
}
    $(".searchSort a ").click(function (event) {
    event.preventDefault();
    $("#form-extra-search input[name=page]").val(1);
    $("#form-extra-search input[name=sort]").val($(this).attr("data-sort"));
    $("#form-extra-search").trigger("submit");
});

    $(".pagination a").click(function(event) {
    event.preventDefault();
    $("#form-extra-search input[name=page]").val($(this).attr("data-page"));
    $("#form-extra-search").trigger("submit");
})


