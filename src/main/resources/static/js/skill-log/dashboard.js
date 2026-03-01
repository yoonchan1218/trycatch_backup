// 키워드 검색
const keywordBox = document.querySelector("div[type=div]");
const keyword = document.querySelector("span[type=button]");
const keywordButton = document.querySelector("button[type=image");
const input = document.getElementById("schTxt");

document.addEventListener("DOMContentLoaded", function () {
    setKeyword();
});

// 키워드 설정/저장

function setKeyword() {
    keywordBox.addEventListener("click", (e) => {
        keyword.style.display = "none";
        input.focus();
    });

    input.addEventListener("blur", (e) => {
        keyword.style.display = "block";
    });
}

// 정렬 전환
document.querySelectorAll(".tabs li").forEach((li) => {
    li.addEventListener("click", (e) => {
        // 모든 li에서 on 클래스 제거
        document.querySelectorAll(".tabs li").forEach((el) => {
            el.classList.remove("on");
        });

        // 클릭한 li에 on 클래스 추가
        e.currentTarget.classList.add("on");
    });
});

// 키워드 클릭 변환
document.querySelectorAll(".keyword button").forEach((btn) => {
    btn.addEventListener("click", (e) => {
        e.currentTarget.classList.toggle("active");
    });
});
