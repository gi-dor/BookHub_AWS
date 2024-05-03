
function cateList() {

    $("#cateList input[name=cateKeyword]").val(0);
    $("#cateList input[name=pubDate]").val(0);
    $("#cateList input[name=page]").val(1);
    $("#cateList input[name=sort]").val("0");
    $("#cateList").trigger("submit");
}

$("#cateKeyword a ").click(function (event) {
    event.preventDefault();
    $("#cateList input[name=cateKeyword]").val($(this).attr("data-cateKeyword"));
    $("#cateList input[name=page]").val(1);
    $("#cateList input[name=sort]").val(0);
    $("#cateList").trigger("submit");
});

$("#pubDate a ").click(function (event) {
    event.preventDefault();
    $("#cateList input[name=cateKeyword]").val(0);
    $("#cateList input[name=pubDate]").val($(this).attr("data-pubDate"));
    $("#cateList input[name=page]").val(1);
    $("#cateList input[name=sort]").val(0);
    $("#cateList").trigger("submit");
});

$("#sort a ").click(function (event) {
    event.preventDefault();
    $("#cateList input[name=page]").val(1);
    $("#cateList input[name=sort]").val($(this).attr("data-sort"));
    $("#cateList").trigger("submit");
});

$(".pagination a").click(function(event) {
    event.preventDefault();
    $("#cateList input[name=page]").val($(this).attr("data-page"));
    $("#cateList").trigger("submit");
})


