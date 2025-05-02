document.addEventListener('DOMContentLoaded', function () {
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

    // 이메일 입력창의 X 버튼 (clear-icon)
    document.querySelectorAll('.clear-icon').forEach(icon => {
        if (icon.classList.contains('clear-email')) {
            icon.addEventListener('click', () => {
                const input = icon.closest('.input-group').querySelector('input');
                if (input) input.value = '';
            });
        }
    });

    // 비밀번호 보기/숨기기
    const passwordToggleIcon = document.querySelector('.toggle-password');
    const passwordInput = document.getElementById('passwordInput');

    if (passwordToggleIcon && passwordInput) {
        passwordToggleIcon.addEventListener('click', () => {
            const isVisible = passwordInput.type === 'text';
            passwordInput.type = isVisible ? 'password' : 'text';

            if (isVisible) {
                passwordToggleIcon.src = `${contextPath}/images/eye-icon.png`;
            } else {
                passwordToggleIcon.src = `${contextPath}/images/eye-open.png`;
            }
        });
    }

    // 이메일 검증
    const emailInput = document.getElementById('emailInput');
    const emailGroup = emailInput.closest('.input-group');
    const emailError = document.getElementById('emailError');
    const emailCenter = emailInput.closest('.input-center');

    function validateEmail(email) {
        const pattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return pattern.test(email);
    }

    emailInput.addEventListener('focus', function () {
        const email = emailInput.value.trim();
        if (email === '' || !validateEmail(email)) {
            emailGroup.classList.add('invalid');
            emailError.style.display = 'block';
        }
    });

    emailInput.addEventListener('blur', function () {
        emailGroup.classList.remove('invalid');
        emailError.style.display = 'none';
    });

    emailInput.addEventListener('input', function () {
        const email = emailInput.value.trim();
        if (email !== '' && validateEmail(email)) {
            emailGroup.classList.remove('invalid');
            emailGroup.classList.add('valid');
            emailError.style.display = 'none';
            emailCenter.style.backgroundColor = '#E7F0FE';
        } else {
            emailGroup.classList.remove('valid');
            emailCenter.style.backgroundColor = '';
            if (document.activeElement === emailInput) {
                emailGroup.classList.add('invalid');
                emailError.style.display = 'block';
            }
        }
    });

    // 비밀번호 검증
    const passwordGroup = passwordInput.closest('.input-group');
    const passwordError = document.getElementById('passwordError');
    const passwordCenter = passwordInput.closest('.input-center');

    passwordInput.addEventListener('focus', function () {
        const password = passwordInput.value.trim();
        if (password === '') {
            passwordGroup.classList.add('invalid');
            passwordError.style.display = 'block';
        }
    });

    passwordInput.addEventListener('blur', function () {
        passwordGroup.classList.remove('invalid');
        passwordError.style.display = 'none';
    });

    passwordInput.addEventListener('input', function () {
        const password = passwordInput.value.trim();
        if (password !== '') {
            passwordGroup.classList.remove('invalid');
            passwordGroup.classList.add('valid');
            passwordError.style.display = 'none';
            passwordCenter.style.backgroundColor = '#E7F0FE';
        } else {
            passwordCenter.style.backgroundColor = '';
            if (document.activeElement === passwordInput) {
                passwordGroup.classList.add('invalid');
                passwordError.style.display = 'block';
            }
        }
    });
});
