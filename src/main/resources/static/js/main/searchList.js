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


