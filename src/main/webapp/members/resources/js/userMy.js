console.log("userMy.sjp");
const form = window.document.getElementById('form');
const pwCheck = form['pwCheck'];
const oldPw = form['OldPw'];
const checkBtn = document.querySelector(".btn-check");
let isChecked = pwCheck.checked;


pwCheck.addEventListener("click",()=>{
   if(!isChecked){//isChecked 가
    oldPw.removeAttribute("disabled");
    checkBtn.removeAttribute("disabled");
   }else if(isChecked){//isChecked가 true
       oldPw.setAttribute("disabled","disabled");
       checkBtn.setAttribute("disabled","disabled");
   }
    isChecked = !isChecked;
});

checkBtn.addEventListener("click",(e)=>{
    e.preventDefault();
    // console.log("클릭!");
    const xhr = new XMLHttpRequest();
    const formData = new FormData();
    formData.append('email', form['email'].value);
    formData.append('password', form['OldPw'].value);
    xhr.open('post', 'userMyPwCheck.do');

    xhr.onreadystatechange = () => {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status >= 200 && xhr.status < 300) {
                const responseJson = JSON.parse(xhr.responseText);
                switch (responseJson){
                    case "success":
                        alert("success");
                        break;
                    default:
                        alert("failed");
                        break;
                }
            } else {
                alert("서버와 통신하지 못하였습니다.")
            }
        };
        xhr.send(formData);


        }


    // window.location.href="";
});

