function validateForm() {
    var isValid = true;

    // ユーザー名のヌルチェック
    var name = document.getElementById('name').value;
    var nameError = document.getElementById('name-error');
    if (!name) {
        nameError.textContent = 'ユーザー名を入力してください。';
        isValid = false;
    } else {
        nameError.textContent = '';
    }

    // パスワードのヌルチェック
    var password = document.getElementById('password').value;
    var passwordError = document.getElementById('password-error');
    if (!password) {
        passwordError.textContent = 'パスワードを入力してください。';
        isValid = false;
    } else {
        passwordError.textContent = '';
    }

    return isValid;
}

