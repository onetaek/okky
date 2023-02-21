let btn_ellipsis = document.querySelector(".btn-ellipsis");
let btn_toggle = document.querySelector(".btn-toggle");
const up = window.document.querySelector('.up');
const down = window.document.querySelector('.down');

const form = window.document.getElementById('form');
const insertBtn = form['insertBtn'];
const content = form['content'];
const boardId = form['boardId'];
const articleIndex = form['articleIndex'];
const userEmail = form['userEmail'];
const userNickName = form['userNickName'];
const likeCount = window.document.querySelector('.like-cnt');
console.log(up, down);
// const replyBtn = window.document.querySelector(".replyBtn");
// const replyForm = window.document.getElementById("replyInsert");
// const replyInputs = replyForm.getElementsByTagName('input');
const container = document.querySelector('.commentContainer');

let comments = [];
console.log(comments);

selectLikeCount();

up.addEventListener('click', likeUp);
down.addEventListener('click', likeDown);
insertBtn.addEventListener('click', () => {
    const xhr = new XMLHttpRequest();
    xhr.open('post', '/comment/insert?boardId=' + boardId.value +
        "&articleIndex=" + articleIndex.value +
        "&content=" + content.value +
        "&userEmail=" + userEmail.value +
        "&userNickName=" + userNickName.value);
    xhr.send();
    xhr.onreadystatechange = () => {
        if (xhr.readyState !== XMLHttpRequest.DONE) return;
        if (xhr.status >= 200 && xhr.status < 300) {
            const responseJson = xhr.responseText;
            if (responseJson === 'success') {
                const newDiv = document.createElement("div");
                let commentContainerWrap = document.querySelector('.commentContainerWrap');
                commentContainerWrap.append(newDiv);
                newDiv.innerHTML = "<div class=\"commentContainer\">\n" +
                    "            <div class=\"replyComment\" >\n" +
                    "                <div class=\"comment_nickNameAndTime\"></div>\n" +
                    "                <div class=\"comment_content\"></div>\n" +
                    "                <span class=\"comment_delete_btn\">삭제</span>\n" +
                    "                <span class=\"replyBtn\">답글</span>\n" +
                    "            </div>\n" +
                    "            <form class=\"replyInsert\">\n" +
                    "                <input class=\"comment_group\" type=\"hidden\" value=\"\">\n" +
                    "                <input class=\"comment_sequence\" type=\"hidden\" value=\"\">\n" +
                    "                <input class=\"comment_level\" type=\"hidden\" value=\"\" >\n" +
                    "                <input class=\"comment_boardId\" type=\"hidden\" value=\"\" >\n" +
                    "                <input class=\"comment_articleIndex\" type=\"hidden\" value=\"\">\n" +
                    "                <input class=\"comment_userEmail\" type=\"hidden\" value=\"\" >\n" +
                    "\n" +
                    "                <textarea class=\"textContent\" type=\"text\" cols=\"90\" rows=\"5\" name=\"content\"></textarea>\n" +
                    "                <input class=\"submitBtn\" type=\"submit\" value=\"전송\">\n" +
                    "            </form>\n" +
                    "        </div>"
                selectCommentList();

            } else if (responseJson === 'failure') {
                alert('댓글이 정상적으로 입력되지 않았습니다. 잠시후 다시 시도해 주세요.')
            } else {
                alert('통신오류');
            }
        }
    }

})

// window.addEventListener("DOMContentLoaded", function (){
//
// });

function likeUp() {
    const xhr = new XMLHttpRequest();
    xhr.open('post', "/article/like?action=up&userEmail=" + userEmail.value + "&articleIndex=" + articleIndex.value)
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
    xhr.open('post', "/article/like?action=down&userEmail=" + userEmail.value + "&articleIndex=" + articleIndex.value)
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
    xhr.open('GET', "/article/like?articleIndex=" + articleIndex.value);
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

btn_ellipsis.addEventListener("click", () => {
    btn_toggle.classList.toggle("off");
});

function selectCommentList() {
    const xhr = new XMLHttpRequest();
    xhr.open('get', '/comments?articleIndex=' + articleIndex.value);
    xhr.send();

    xhr.onreadystatechange = () => {
        if (xhr.readyState !== XMLHttpRequest.DONE) return;

        if (xhr.status >= 200 && xhr.status < 300) {
            const responseJson = JSON.parse(xhr.responseText);

            if (responseJson['result'] !== null) {
                comments = responseJson['result'];
                console.log(comments);

                let commentContainer = document.querySelectorAll('.commentContainer');
                console.log("commentContainer", commentContainer)


                for (let i = 0; i < commentContainer.length; i++) {
                    commentContainer[i].querySelector('.replyBtn').addEventListener("click", () => {
                        commentContainer[i].querySelector('.replyInsert').classList.toggle("visible");
                        console.log(commentContainer[i].querySelector('.replyBtn'),
                            commentContainer[i].querySelector('.replyInsert'))
                    });
                    const nickNameAndTime = commentContainer[i].querySelector('.comment_nickNameAndTime');
                    nickNameAndTime.innerText = comments[i]['userNickName'] + " - " + comments[i]['createdAt'];
                    const comment_content = commentContainer[i].querySelector('.comment_content');
                    comment_content.innerText = comments[i]['content'];
                    const comment_delete_btn = commentContainer[i].querySelector('.comment_delete_btn');

                    const comment = comments[i];

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

                    comment_delete_btn.addEventListener("click", () => {
                        deleteComment(comment, i);
                    });

                    console.log(nickNameAndTime,
                        comment_content,
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

function deleteComment(comment, i) {
    const comment_index = comment['index'];
    const comment_boardId = comment['boardId'];
    const comment_articleIndex = comment['articleIndex'];
    console.log(comment_index, comment_boardId, comment_articleIndex);

    const xhr = new XMLHttpRequest();
    xhr.open('post', '/comment/delete?index=' + comment_index);
    xhr.send();

    xhr.onreadystatechange = () => {
        if (xhr.readyState !== XMLHttpRequest.DONE) return;
        if (xhr.status >= 200 && xhr.status < 300) {
            const responseJson = xhr.responseText;
            if (responseJson === 'success') {
                let commentContainer = document.querySelectorAll('.commentContainer');
                commentContainer[i].innerHTML = "";
                selectCommentList();
                // window.location.href='articleView.do?boardId='+comment_boardId+"&articleIndex="+comment_articleIndex;
            }
        }
    }

}
selectCommentList();

const formDelete = window.document.getElementById('formDelete');
function deleteArticle(boardId, articleIndex){
    if(confirm("게시글을 삭제하시 겠습니까?한번 삭제한 게시글은 복구할 수 없습니다.")){
        formDelete['boardId'].value = boardId;
        formDelete['articleIndex'].value = articleIndex;
        formDelete.action  = "/article/delete";
        formDelete.submit();
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