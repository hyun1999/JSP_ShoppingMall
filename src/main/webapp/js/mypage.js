document.addEventListener('DOMContentLoaded', function () {
    const deleteLink = document.getElementById('deleteLink');
    const deleteForm = document.getElementById('deleteForm');

    if (deleteLink && deleteForm) {
        deleteLink.addEventListener('click', function (e) {
            e.preventDefault(); // a 태그 기본 동작 막기
            if (confirm('정말 탈퇴하시겠습니까?')) {
                deleteForm.submit();
            }
        });
    }
});
