function validateForm() {
    let valid = true;

    // タイトルのバリデーション
    const title = document.getElementById('title');
    const titleError = document.getElementById('title-error');
    if (title.value.trim() === '') {
        titleError.textContent = 'タイトルを入力してください';
        valid = false;
    } else {
        titleError.textContent = '';
    }

    // 内容のバリデーション
    const content = document.getElementById('content');
    const contentError = document.getElementById('content-error');
    if (content.value.trim() === '') {
        contentError.textContent = '内容を入力してください';
        valid = false;
    } else {
        contentError.textContent = '';
	}
	
    const category = document.getElementById('category');
    const categoryError = document.getElementById('category-error');
    if (category.value.trim() === '') {
        categoryError.textContent = 'カテゴリーを選択してください';
        valid = false;
    } else {
        categoryError.textContent = '';
    }
    
    return valid
}