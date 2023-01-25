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

    const xhr = new XMLHttpRequest();
    xhr.open('post', `login.do?email=${form['email'].value}&password=${form['password'].value}`);
    xhr.send();
    xhr.onreadystatechange = () => {

        if (xhr.readyState !== XMLHttpRequest.DONE) return;

        if (xhr.status >=200 && xhr.status < 300) {

            const responseJson = xhr.responseText;
            console.log(responseJson);
            if (responseJson === "success") {
                alert("로그인에 성공하였습니다.");
                window.location.href ="/main/welcome.do";
            } else {
                alert("해당 계정은 휴면상태입니다.");
            }
        }
    }
}