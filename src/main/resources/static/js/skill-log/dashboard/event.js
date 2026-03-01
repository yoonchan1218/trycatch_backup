// 키워드 검색
// const keywordBox = document.querySelector("div[type=div]");
// const keyword = document.querySelector("span[type=button]");
// const keywordButton = document.querySelector("button[type=image]");
// const input = document.getElementById("schTxt");

// console.log(keywordBox);
// console.log(keyword);
// console.log(keywordButton);
// console.log(input);

// document.addEventListener("DOMContentLoaded", function () {
//     setKeyword();
// });
const select = document.querySelector(".devSortList");
const pageWrap = document.querySelector(".tplPagination.newVer");

let page = 1;
let type = select.value;
const memberId = document.querySelector("input[name=memberId]").value;




if(!memberId) {
    location.href = "/skill-log/list";
}

skillLogService.getList(page, type, memberId, skillLogLayout.showList);

// 키워드 설정/저장

// function setKeyword() {
// keywordBox.addEventListener("click", (e) => {
//     keyword.style.display = "none";
//     input.focus();
// });
//
// input.addEventListener("blur", (e) => {
//     keyword.style.display = "block";
// });
// }

// 정렬 전환
// document.querySelectorAll(".tabs li").forEach((li) => {
//     li.addEventListener("click", (e) => {
//         // 모든 li에서 on 클래스 제거
//         document.querySelectorAll(".tabs li").forEach((el) => {
//             el.classList.remove("on");
//         });
//
//         // 클릭한 li에 on 클래스 추가
//         e.currentTarget.classList.add("on");
//     });
// });

// 키워드 클릭 변환
// document.querySelectorAll(".keyword button").forEach((btn) => {
//     btn.addEventListener("click", (e) => {
//         e.currentTarget.classList.toggle("active");
//     });
// });

// type 값
select.addEventListener("change", async (e) => {
    type = e.target.value;
    page = 1;
    await skillLogService.getList(page, type, memberId, skillLogLayout.showList);
})

// 페이징 처리
pageWrap.addEventListener("click", async (e) => {
    e.preventDefault();

    if(e.target.closest(".paging")) {
        page = e.target.closest(".paging").getAttribute("href");
        await skillLogService.getList(page, type, memberId, skillLogLayout.showList);

        window.scrollTo({
            top: 0,
            behavior: 'smooth'
        });
    }
});













