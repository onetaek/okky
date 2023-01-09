const form = window.document.getElementById('form');
console.log(form)
form.onsubmit = e => {
    e.preventDefault();
    if (form['email'].value === '') {
        alert('이메일을 입력해 주세요.');
        return false;
    }
}