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

<!--페이지네이션-->
$(document).ready(function() {
    // 페이지네이션 요소들 선택
    let paginationItems = $('.pagination .page-item');
    let prevBtn = $('.pagination .page-prev');
    let nextBtn = $('.pagination .page-next');

    let activePage = $('.pagination .page-item.active'); // 현재 활성화된 페이지

    // 페이지 변경 함수
    function changePage(newPage) {
        activePage.removeClass('active');
        newPage.addClass('active');
        activePage = newPage;
    }

    // 페이지네이션 이벤트 처리
    paginationItems.on('click', function(e) {
        e.preventDefault(); // 기본 동작 제거
        changePage($(this)); // 클릭한 페이지를 활성화
    });

    // 이전 페이지 버튼 이벤트 처리
    prevBtn.on('click', function(e) {
        e.preventDefault(); // 기본 동작 제거

        let prevItem = activePage.prev('.page-item');

        if (prevItem.length && !prevItem.hasClass('page-prev')) {
            changePage(prevItem); // 이전 페이지를 활성화
        }
    });

    // 다음 페이지 버튼 이벤트 처리
    nextBtn.on('click', function(e) {
        e.preventDefault(); // 기본 동작 제거

        let nextItem = activePage.next('.page-item');

        if (nextItem.length && !nextItem.hasClass('page-next')) {
            changePage(nextItem); // 다음 페이지를 활성화
        }
    });
});

