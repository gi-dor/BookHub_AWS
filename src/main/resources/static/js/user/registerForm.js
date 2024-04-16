
$(document).ready(function() {
    $('#password, #confirmPassword').on('keyup', function () {
        let password = $('#password').val();
        let confirmPassword = $('#confirmPassword').val();

        if (password !== '' && confirmPassword !== '') {
            if (password !== confirmPassword) {
                $('#passwordMatchMessage').text('비밀번호가 일치하지 않습니다.');
            } else {
                $('#passwordMatchMessage').text('비밀번호가 일치합니다.');
            }
        } else {
            $('#passwordMatchMessage').text('');
        }
    });
});