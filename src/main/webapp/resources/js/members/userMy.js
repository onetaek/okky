console.log("userMy.sjp");
const form = window.document.getElementById('form');
const pwCheck = form['pwCheck'];
const oldPw = form['OldPw'];
const email = form['email'].value;
const checkBtn = document.querySelector(".btn-check");
let isChecked = pwCheck.checked;


pwCheck.addEventListener("click", () => {
    if (!isChecked) {//isChecked 가
        oldPw.removeAttribute("disabled");
        checkBtn.removeAttribute("disabled");
    } else if (isChecked) {//isChecked가 true
        oldPw.setAttribute("disabled", "disabled");
        checkBtn.setAttribute("disabled", "disabled");
    }
    isChecked = !isChecked;
});

let OLDPW = form['OldPw'].value;
console.log(OLDPW);


const NewPw = form['NewPw'];
const NewPwC = form['NewPwCheck'];
checkBtn.addEventListener("click", (e) => {
    e.preventDefault();
    // console.log("클릭!");
    console.log(form['email'].value);
    console.log(form['OldPw'].value);
    const xhr = new XMLHttpRequest();
    // const formData = new FormData();
    // formData.append('email', form['email'].value);
    // formData.append('password', form['OldPw'].value);
    xhr.open('post', '/member/pwCheck?email='+email+'&password='+oldPw.value);

    xhr.onreadystatechange = () => {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status >= 200 && xhr.status < 300) {
                console.log(xhr.responseText);
                const responseJson = xhr.responseText;
                if (responseJson === "success") {
                    alert("success")
                    oldPw.setAttribute("disabled", "disabled");
                    checkBtn.setAttribute("disabled", "disabled");
                    NewPw.removeAttribute("disabled", "disabled");
                    NewPwC.removeAttribute("disabled", "disabled");
                } else {
                    alert("failure")
                }
            } else {
                alert("서버와 통신하지 못하였습니다.")
            }
        }
    };
    xhr.send();

    // window.location.href="";
});

