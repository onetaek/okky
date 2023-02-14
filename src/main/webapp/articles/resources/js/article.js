let btn_ellipsis = document.querySelector(".btn-ellipsis");
let btn_toggle = document.querySelector(".btn-toggle");
const up = window.document.querySelector('.up');
const down = window.document.querySelector('.down');

const form = window.document.getElementById('form');
const articleIndex = form['articleIndex'];
const userEmail = form['userEmail'];
const likeCount = window.document.querySelector('.like-cnt');
console.log(up, down);
const replyBtn = window.document.querySelector(".replyBtn");
const replyForm = window.document.getElementById("replyInsert");




selectLikeCount();
up.addEventListener('click', likeUp);
down.addEventListener('click', likeDown);

// window.addEventListener("DOMContentLoaded", function (){
//
// });

function likeUp () {
    const xhr = new XMLHttpRequest();
    xhr.open('post',"ArticleLikeView.do?action=up&userEmail="+userEmail.value+"&articleIndex="+articleIndex.value)
    xhr.send();
    xhr.onreadystatechange = () => {

        if (xhr.readyState !== XMLHttpRequest.DONE) return;
        if (xhr.status >= 200 && xhr.status < 300) {
            const responseJson = xhr.responseText;
            console.log(responseJson);
            likeCount.innerHTML = "";
            likeCount.innerText = responseJson;
            selectLikeCount();
        }
    }
}

function likeDown() {
    const xhr = new XMLHttpRequest();
    xhr.open('post',"ArticleLikeView.do?action=down&userEmail="+userEmail.value+"&articleIndex="+articleIndex.value)
    xhr.send();
    xhr.onreadystatechange = () => {

        if (xhr.readyState !== XMLHttpRequest.DONE) return;
        if (xhr.status >= 200 && xhr.status < 300) {
            const responseJson = xhr.responseText;
            console.log(responseJson);
            likeCount.innerHTML = "";
            likeCount.innerText = responseJson;
            selectLikeCount();
        }
    }
}


function selectLikeCount() {
    const xhr = new XMLHttpRequest();
    xhr.open('GET', "ArticleLikeView.do?articleIndex="+articleIndex.value);
    xhr.send();
    xhr.onreadystatechange = () => {
        if (xhr.readyState !== XMLHttpRequest.DONE) return;

        if (xhr.status >= 200 && xhr.status < 300) {
            const responseJson = xhr.responseText;
            console.log(responseJson);
            likeCount.innerHTML = "";
            likeCount.innerText = responseJson;


        }
    }
}





btn_ellipsis.addEventListener("click",()=>{
    btn_toggle.classList.toggle("off");
});

replyBtn.addEventListener("click",()=>{
    replyForm.classList.toggle("visible");
});









// console.log("test code")




// const up = window.document.querySelector(".up");
// const down = window.document.querySelector(".down");
// console.log(up);
// console.log(down);
//
// up.addEventListener("click", ()=> {
//     alert("up click");
// });
//
// down.addEventListener('click', ()=> {
//     alert("down click");
// })