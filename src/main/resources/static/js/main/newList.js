<!--찜하트-->
$(document).ready(function () {
    $('#zzim').click(function () {
        var icon = $(this).find('i');
        // 현재 아이콘의 클래스에 따라 다음 클래스로 변경
        if (icon.hasClass('far')) {
            icon.removeClass('far').addClass('fas'); // 빈 하트에서 채워진 하트로 변경
        } else {
            icon.removeClass('fas').addClass('far'); // 채워진 하트에서 빈 하트로 변경
        }
    });
});


function newbook() {

    $("#newbook input[name=cate]").val(0);
    $("#newbook input[name=cateKeyword]").val(0);
    $("#newbook input[name=pubDate]").val(0);
    $("#newbook input[name=page]").val(1);
    $("#newbook input[name=sort]").val("1");
    $("#newbook").trigger("submit");
}

$(".cate a ").click(function (event) {
    event.preventDefault();
    $("#newbook input[name=cate]").val($(this).attr("data-cate"));
    $("#newbook input[name=page]").val(1);
    $("#newbook input[name=sort]").val(1);
    $("#newbook").trigger("submit");
});

$(".cateKeyword a ").click(function (event) {
    event.preventDefault();
    $("#newbook input[name=cate]").val(0);
    $("#newbook input[name=cateKeyword]").val($(this).attr("data-cateKeyword"));
    $("#newbook input[name=page]").val(1);
    $("#newbook input[name=sort]").val(1);
    $("#newbook").trigger("submit");
});

$(".pubDate a ").click(function (event) {
    event.preventDefault();
    $("#newbook input[name=cate]").val(0);
    $("#newbook input[name=cateKeyword]").val(0);
    $("#newbook input[name=pubDate]").val($(this).attr("data-pubDate"));
    $("#newbook input[name=page]").val(1);
    $("#newbook input[name=sort]").val(1);
    $("#newbook").trigger("submit");
});

$(".searchSort a ").click(function (event) {
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


