document.addEventListener('DOMContentLoaded', () => {
    // 탭 전환 관련
    const tabs = document.querySelectorAll('.tab-btn');
    const contents = document.querySelectorAll('.tab-content');
    const indicator = document.querySelector('.tab-active-indicator');

    function moveIndicator(tab) {
        const rect = tab.getBoundingClientRect();
        const parentRect = tab.parentElement.getBoundingClientRect();

        if (indicator) {
            indicator.style.width = `${rect.width}px`;
            indicator.style.left = `${rect.left - parentRect.left}px`;
        }
    }

    tabs.forEach(tab => {
        tab.addEventListener('click', () => {
            tabs.forEach(t => t.classList.remove('active'));
            tab.classList.add('active');

            contents.forEach(c => c.style.display = 'none');
            document.getElementById('tab-' + tab.dataset.tab).style.display = 'block';

            moveIndicator(tab);
        });
    });

    const initialActive = document.querySelector('.tab-btn.active');
    if (initialActive) moveIndicator(initialActive);

    // ✳ 입력 필드 지우기 (X 아이콘)
    document.querySelectorAll('.clear-btn').forEach(btn => {
        btn.addEventListener('click', () => {
            const input = btn.previousElementSibling;
            if (input && input.tagName === 'INPUT') input.value = '';
        });
    });

    // 👁 비밀번호 보기/숨기기
    const togglePasswordBtn = document.querySelector('.toggle-password');
    const passwordInput = document.getElementById('passwordInput');
    if (togglePasswordBtn && passwordInput) {
        togglePasswordBtn.addEventListener('click', () => {
            const isVisible = passwordInput.type === 'text';
            passwordInput.type = isVisible ? 'password' : 'text';


            const eyeIcon = togglePasswordBtn.querySelector('img');
            if (eyeIcon) {
                eyeIcon.src = isVisible
                    ? '/images/eye-icon.png'   // 감추기 (닫힌 눈)
                    : '/images/eye-open.png'; // 보이기 (열린 눈)
            }
        });
    }
});
