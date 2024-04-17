$(document).ready(function () {
    $('#heartButton').click(function () {
        var icon = $(this).find('i');
        // 현재 아이콘의 클래스에 따라 다음 클래스로 변경
        if (icon.hasClass('far')) {
            icon.removeClass('far').addClass('fas'); // 빈 하트에서 채워진 하트로 변경
        } else {
            icon.removeClass('fas').addClass('far'); // 채워진 하트에서 빈 하트로 변경
        }
    });
});