const Warning = {
    getElement: () => document.getElementById('warning'),
    hide: () => Warning.getElement().classList.remove('visible'),
    show: (text) => {
        Warning.getElement().innerText = text;
        Warning.getElement().classList.add('visible');
    }
};

const form = window.document.getElementById('form');
console.log(form)

form.onsubmit = e => {
    e.preventDefault();

    if (form['email'].value === '') {
        Warning.show('이메일을 입력해 주세요.');
        form['email'].focus();
        return false;
    }

    if (!new RegExp('^(?=.{7,50})([\\da-zA-Z_.]{4,})@([\\da-z\\-]{2,}\\.)?([\\da-z\\-]{2,})\\.([a-z]{2,10})(\\.[a-z]{2})?$').test(form['email'].value)) {
        Warning.show('올바른 이메일을 입력해 주세요.');
        form['email'].focus();
        form['email'].select();
        return false;
    }

    if (form['password'].value === '') {
        Warning.show('비밀번호를 입력해 주세요.');
        form['password'].focus();
        return false;
    }

    if (!new RegExp('^([\\da-zA-Z`~!@#$%^&*()\\-_=+\\[{\\]}\\\\|;:\'\",<.>/?]{8,50})$').test(form['password'].value)) {
        Warning.show('올바른 비밀번호를 입력해 주세요.');
        form['password'].focus();
        form['password'].select();
        return false;
    }





//     ajax
}