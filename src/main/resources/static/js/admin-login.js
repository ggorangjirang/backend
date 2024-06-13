document.addEventListener("DOMContentLoaded", () => {
    console.log("Login page loaded");

    const form = document.querySelector('#login-form');
    form.addEventListener('submit', async (event) => {
        event.preventDefault();

        const email = form.querySelector('input[name="username"]').value;
        const password = form.querySelector('input[name="password"]').value;
        const messageDiv = document.querySelector('#message');

        if (!email || !password) {
            messageDiv.textContent = "이메일과 비밀번호를 입력하세요.";
            messageDiv.classList.add('has-text-danger');
            return;
        }

        try {
            const response = await fetch('/admin/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    email: email,
                    password: password
                })
            });

            const responseBody = await response.json();

            if (response.ok) {
                window.location.href = responseBody.redirectUrl || '/admin-main/admin';
            } else {
                messageDiv.textContent = responseBody.message;
                messageDiv.classList.add('has-text-danger');
            }
        } catch (error) {
            messageDiv.textContent = "로그인 과정에서 오류가 발생했습니다.";
            messageDiv.classList.add('has-text-danger');
        }
    });
});