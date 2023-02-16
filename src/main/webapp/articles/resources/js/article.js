let btn_ellipsis = document.querySelector(".btn-ellipsis");
let btn_toggle = document.querySelector(".btn-toggle");
const up = window.document.querySelector('.up');
const down = window.document.querySelector('.down');

const form = window.document.getElementById('form');
const articleIndex = form['articleIndex'];
const userEmail = form['userEmail'];
const likeCount = window.document.querySelector('.like-cnt');
console.log(up, down);
// const replyBtn = window.document.querySelector(".replyBtn");
// const replyForm = window.document.getElementById("replyInsert");
// const replyInputs = replyForm.getElementsByTagName('input');
const container = document.querySelector('.commentContainer');

let comments = [];
console.log(comments);

selectLikeCount();
selectCommentList();

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
            // const responseJson = xhr.responseText;
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



function selectCommentList () {
    const xhr = new XMLHttpRequest();
    xhr.open('get', 'commentListView.do?articleIndex='+articleIndex.value);
    xhr.send();

    xhr.onreadystatechange = () => {
        if (xhr.readyState !== XMLHttpRequest.DONE) return;

        if (xhr.status >= 200 && xhr.status <300) {
            const responseJson = JSON.parse(xhr.responseText);

            if (responseJson['result'] !== null) {
                comments = responseJson['result'];
                console.log(comments);

                let commentContainer = document.querySelectorAll('.commentContainer');
                console.log("commentContainer",commentContainer)


                for( let i = 0 ; i < commentContainer.length; i ++){
                    commentContainer[i].querySelector('.replyBtn').addEventListener("click",()=>{
                        commentContainer[i].querySelector('.replyInsert').classList.toggle("visible");
                        console.log(commentContainer[i].querySelector('.replyBtn'),
                            commentContainer[i].querySelector('.replyInsert'))
                    });
                    const nickNameAndTime = commentContainer[i].querySelector('.comment_nickNameAndTime');
                    nickNameAndTime.innerText = comments[i]['userNickName'] + " - " + comments[i]['createdAt'];
                    const comment_content = commentContainer[i].querySelector('.comment_content');
                    comment_content.innerText = comments[i]['content'];
                    const comment_link = commentContainer[i].querySelector('.comment_link');
                    comment_link.setAttribute('href', "commentDelete.do?index="+comments[i]['index']);


                    const comment_group = commentContainer[i].querySelector('.comment_group');
                    comment_group.value = comments[i]['group'];
                    const comment_sequence = commentContainer[i].querySelector('.comment_sequence');
                    comment_sequence.value = comments[i]['sequence'];
                    const comment_level = commentContainer[i].querySelector('.comment_level');
                    comment_level.value = comments[i]['level'];
                    const comment_boardId = commentContainer[i].querySelector('.comment_boardId');
                    comment_boardId.value = comments[i]['boardId'];
                    const comment_articleIndex = commentContainer[i].querySelector('.comment_articleIndex');
                    comment_articleIndex.value = comments[i]['articleIndex'];
                    const comment_userEmail = commentContainer[i].querySelector('.comment_userEmail');
                    comment_userEmail.value = comments[i]['userEmail'];

                    console.log(nickNameAndTime,
                        comment_content,
                        comment_link,
                        comment_group,
                        comment_sequence,
                        comment_level,
                        comment_boardId,
                        comment_articleIndex,
                        comment_userEmail);


                }

            } else {
                alert("통신 오류");
            }
        }
    }
}




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