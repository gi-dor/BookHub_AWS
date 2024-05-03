
function newbook() {

    $("#newbook input[name=cate]").val(1);
    $("#newbook input[name=cateKeyword]").val(1);
    $("#newbook input[name=pubDate]").val(1);
    $("#newbook input[name=page]").val(1);
    $("#newbook input[name=sort]").val(1);
    $("#newbook").trigger("submit");
}



$("#cateKeyword a ").click(function (event) {
    event.preventDefault();
    $("#newbook input[name=cate]").val(1);
    $("#newbook input[name=cateKeyword]").val($(this).attr("data-cateKeyword"));
    $("#newbook input[name=page]").val(1);
    $("#newbook input[name=sort]").val(1);
    $("#newbook").trigger("submit");
});

$("#pubDate a ").click(function (event) {
    event.preventDefault();
    $("#newbook input[name=pubDate]").val($(this).attr("data-pubDate"));
    $("#newbook input[name=page]").val(1);
    $("#newbook input[name=sort]").val(1);
    $("#newbook").trigger("submit");
});

$("#sort a ").click(function (event) {
    event.preventDefault();
    $("#newbook input[name=page]").val(1);
    $("#newbook input[name=sort]").val($(this).attr("data-sort"));
    $("#newbook").trigger("submit");
});

$(".pagination a").click(function(event) {
    event.preventDefault();
    $("#newbook input[name=page]").val($(this).attr("data-page"));
    $("#newbook").trigger("submit");
})

