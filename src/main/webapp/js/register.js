document.addEventListener('DOMContentLoaded', function () {
    const emailInput = document.getElementById('emailInput');
    const emailGroup = emailInput.closest('.input-group');
    const emailError = document.getElementById('emailError');
    const emailCenter = emailInput.closest('.input-center');

    const passwordInput = document.getElementById('passwordInput');
    const passwordGroup = passwordInput.closest('.input-group');
    const passwordError = document.getElementById('passwordError');
    const passwordCenter = passwordInput.closest('.input-center');

    const nameInput = document.querySelector('input[name="name"]');
    const nameGroup = nameInput.closest('.input-group');
    const nameCenter = nameInput.closest('.input-center');

    const phoneInput = document.querySelector('input[name="phone_num"]');
    const phoneGroup = phoneInput.closest('.input-group');
    const phoneCenter = phoneInput.closest('.input-center');

    function validateEmail(email) {
        const pattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return pattern.test(email);
    }

    emailInput.addEventListener('input', function () {
        const email = emailInput.value.trim();
        if (validateEmail(email)) {
            emailGroup.classList.remove('invalid');
            emailGroup.classList.add('valid');
            emailError.style.display = 'none';
            emailCenter.style.backgroundColor = '#E7F0FE';
        } else {
            emailGroup.classList.remove('valid');
            emailGroup.classList.add('invalid');
            emailError.style.display = 'block';
            emailCenter.style.backgroundColor = '';
        }
    });

    passwordInput.addEventListener('input', function () {
        const value = passwordInput.value.trim();
        if (value !== '') {
            passwordGroup.classList.remove('invalid');
            passwordGroup.classList.add('valid');
            passwordError.style.display = 'none';
            passwordCenter.style.backgroundColor = '#E7F0FE';
        } else {
            passwordGroup.classList.add('invalid');
            passwordGroup.classList.remove('valid');
            passwordError.style.display = 'block';
            passwordCenter.style.backgroundColor = '';
        }
    });

    // 이름 유효성 검사
    nameInput.addEventListener('input', function () {
        const value = nameInput.value.trim();
        if (value !== '') {
            nameGroup.classList.remove('invalid');
            nameGroup.classList.add('valid');
            nameCenter.style.backgroundColor = '#E7F0FE';
        } else {
            nameGroup.classList.add('invalid');
            nameGroup.classList.remove('valid');
            nameCenter.style.backgroundColor = '';
        }
    });

    // 전화번호 유효성 검사
    phoneInput.addEventListener('input', function () {
        const value = phoneInput.value.trim();
        if (value !== '') {
            phoneGroup.classList.remove('invalid');
            phoneGroup.classList.add('valid');
            phoneCenter.style.backgroundColor = '#E7F0FE';
        } else {
            phoneGroup.classList.add('invalid');
            phoneGroup.classList.remove('valid');
            phoneCenter.style.backgroundColor = '';
        }
    });

    // 이메일 clear 버튼
    document.querySelectorAll('.clear-email').forEach(icon => {
        icon.addEventListener('click', () => {
            const input = icon.closest('.input-group').querySelector('input');
            if (input) input.value = '';
        });
    });

    // 비밀번호 보기/숨기기
    const passwordToggleIcon = document.querySelector('.toggle-password');
    if (passwordToggleIcon) {
        passwordToggleIcon.addEventListener('click', () => {
            const isVisible = passwordInput.type === 'text';
            passwordInput.type = isVisible ? 'password' : 'text';
            passwordToggleIcon.src = isVisible
                ? `${contextPath}/images/eye-icon.png`
                : `${contextPath}/images/eye-open.png`;
        });
    }
});
