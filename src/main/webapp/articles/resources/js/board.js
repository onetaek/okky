const searchForm = document.searchForm;
const mostPrevBtn = document.querySelector(".most-prev-btn");
const prevBtn = document.querySelector(".prev-btn");
const pageNumBtn = document.querySelector(".page-num-btn");
const nextBtn = document.querySelector(".next-btn");
const mostNextBtn = document.querySelector(".most-next-btn");

let pageNum = document.querySelector(".p-page-num");
let startPage = document.querySelector(".p-start-page");
let endPage = document.querySelector(".p-end-page");
let totalCount = document.querySelector(".p-total-count");

console.log(searchForm);
console.log(mostPrevBtn)
console.log(prevBtn)
console.log(pageNumBtn)
console.log(nextBtn)
console.log(mostNextBtn)
console.log(pageNum)
console.log(startPage)
console.log(endPage)

mostPrevBtn.addEventListener("click",function(){
    pageNum.value = 1;
    searchForm.submit();
});

prevBtn.addEventListener("click",function(){
    pageNum.value = startPage-1;
    searchForm.submit();
});

pageNumBtn.addEventListener("click",function(){
    let clickPageNum = document.querySelector(".page-num-value").innerHTML;
    pageNum.value = clickPageNum;
    searchForm.submit();
});

nextBtn.addEventListener("click",function(){
    pageNum.value = endPage+1;
    searchForm.submit();
});

mostNextBtn.addEventListener("click",function(){
    pageNum.value = totalCount;
    searchForm.submit();
});

