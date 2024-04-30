
function bestSeller() {

    $("#bestSeller input[name=period]").val(0);
    $("#bestSeller input[name=cateKeyword]").val(0);
    $("#bestSeller input[name=pubDate]").val(0);
    $("#bestSeller input[name=page]").val(1);
    $("#bestSeller input[name=sort]").val("0");
    $("#bestSeller").trigger("submit");
}

$("#period a ").click(function (event) {
    event.preventDefault();
    $("#bestSeller input[name=period]").val($(this).attr("data-period"));
    $("#bestSeller input[name=cateKeyword]").val(0);
    $("#bestSeller input[name=page]").val(1);
    $("#bestSeller input[name=sort]").val(0);
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
    $("#bestSeller input[name=cateKeyword]").val(0);
    $("#bestSeller input[name=pubDate]").val($(this).attr("data-pubDate"));
    $("#bestSeller input[name=page]").val(1);
    $("#bestSeller input[name=sort]").val(0);
    $("#bestSeller").trigger("submit");
});

$("#sort a ").click(function (event) {
    event.preventDefault();
    $("#bestSeller input[name=page]").val(1);
    $("#bestSeller input[name=sort]").val($(this).attr("data-sort"));
    $("#bestSeller").trigger("submit");
});

$(".pagination a").click(function(event) {
    event.preventDefault();
    $("#bestSeller input[name=page]").val($(this).attr("data-page"));
    $("#bestSeller").trigger("submit");
})


