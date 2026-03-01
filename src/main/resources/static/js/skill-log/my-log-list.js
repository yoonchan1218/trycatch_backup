// 작성하기 버튼
const writeButtonDiv = document.querySelector(".navi-top-area.has-tooltip");
const writeButton = document.querySelector(".navi-top-area.has-tooltip a");

// 작성하기 버튼 눌렀을 때 띄울 툴바
writeButton.addEventListener("click", (e) => {
    writeButtonDiv.classList.toggle("tooltip-open");
});

// 내글목록에서 글 좋아요
const ListLike = document.querySelector(".cell.devQstnListLike");

ListLike.addEventListener("click", (e) => {
    ListLike.classList.toggle("on");
});

// 북마크 on 추가/제거
const bookark = document.querySelector(
    ".devQnaDetailBookmark.btnBookmark.qnaSpB",
);
// 레이어
const bookMarkLayer = document.querySelector(
    ".book-mark-layer.tooltip-layer.qnaSpA",
);

bookMark.addEventListener("click", (e) => {
    if (!bookMark.classList.contains("on")) {
        bookMarkLayer.style.display = "block";
        setTimeout(() => {
            bookMarkLayer.style.display = "none";
        }, 2000);
    }
    bookMark.classList.toggle("on");
});
