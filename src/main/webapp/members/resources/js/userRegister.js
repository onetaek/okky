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
    if (!new RegExp('^([\\da-zA-Z`~!@#$%^&*()\\-_=+\\[{\\]}\\\\|;:\'",<.>/?]{8,50})$').test(form['password'].value)) {
        Warning.show('올바른 비밀번호를 입력해 주세요.');
        form['password'].focus();
        form['password'].select();
        return false;
    }
    if (form['passwordCheck'].value === '') {
        Warning.show('비밀번호 재입력해 주세요.');
        form['passwordCheck'].focus();
        return false;
    }
    if (form['password'].value !== form['passwordCheck'].value) {
        Warning.show('입력한 비밀번호가 서로 일치하지 않습니다.');
        form['passwordCheck'].focus();
        return false;
    }


    if (form['name'].value === '') {
        Warning.show('이름을 입력해 주세요.');
        form['name'].focus();
        return false;
    }
    if (!new RegExp('^([가-힣]{2,5})$').test(form['name'].value)) {
        Warning.show('올바른 이름을 입력해 주세요.');
        form['name'].focus();
        form['name'].select();
        return false;
    }

    if (form['nickName'].value === '') {
        Warning.show('별명을 입력해 주세요.');
        form['nickName'].focus();
        return false;
    }
    if (!new RegExp('^([가-힣]{2,5})$').test(form['nickName'].value)) {
        Warning.show('올바른 별명을 입력해 주세요.');
        form['nickName'].focus();
        form['nickName'].select();
        return false;
    }

    if (form['contact'].value === '') {
        Warning.show('연락처를 입력해 주세요.');
        form['contact'].focus();
        return false;
    }
    if (!new RegExp('^(\\d{8,12})$').test(form['contact'].value)) {
        Warning.show('올바른 연락처를 입력해 주세요.');
        form['contact'].focus();
        form['contact'].select();
        return false;
    }

    if (!form['policyEmailSend'].checked) {
        Warning.show('이메일 수신동의는 필수사항입니다.');
        form['policyEmailSend'].focus();
    }



//     문자인증 유효성검사 및 전송 ajax

}