// 키워드 검색
const keywordBox = document.querySelector("div[type=div]");
const keyword = document.querySelector("span[type=button]");
const keywordButton = document.querySelector("button[type=image]");
const input = document.getElementById("schTxt");

const writeButtonDiv = document.querySelector(".navi-top-area.has-tooltip");
const writeButton = document.querySelector(".navi-top-area.has-tooltip a");

// 페이징 처리
let page = 1;
let type = document.querySelector(".tabs.col03 li.on span").textContent;
const pageWrap = document.querySelector(".tplPagination");
let tagNames = [];

// 작성하기 버튼 눌렀을 때 띄울 툴바
writeButton.addEventListener("click", (e) => {
    writeButtonDiv.classList.toggle("tooltip-open");
});

// 블로그 게시글
skillLogService.getList(page,
    {
        type: type,
        keyword: input.value,
    }, skillLogLayout.showList);

// 블로그 인기 게시글
skillLogService.getList(page,
    {
        type: "인기",
        keyword: "",
    }, skillLogLayout.showHotList);

// 페이지 버튼
pageWrap.addEventListener("click", async (e) => {
    e.preventDefault();
    if(e.target.closest(".paging")) {
        page = e.target.closest(".paging").getAttribute("href");

        await skillLogService.getList(page,
            {
                type: type,
                keyword: input.value,
                tagNames: tagNames,
            }, skillLogLayout.showList);

        window.scrollTo({ top: 0, behavior: 'smooth' });
    }
})

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
        if(!input.value) {
            keyword.style.display = "block";
        }
    });
}

// 정렬 전환
document.querySelectorAll(".tabs li").forEach((li) => {
    li.addEventListener("click", async (e) => {
        // 모든 li에서 on 클래스 제거
        document.querySelectorAll(".tabs li").forEach((el) => {
            el.classList.remove("on");
        });

        // 클릭한 li에 on 클래스 추가
        e.currentTarget.classList.add("on");

        // 목록
        type = e.currentTarget.textContent.trim();
        page = 1;
        await skillLogService.getList(page,
            {
                type: type,
                keyword: input.value,
                tagNames: tagNames,
            }, skillLogLayout.showList);
    });
});

// 키워드 클릭 변환
document.querySelectorAll(".keyword button").forEach((btn) => {
    btn.addEventListener("click", async (e) => {
        const tagName = e.target.getAttribute("data-tag-name");
        e.currentTarget.classList.toggle("active");

        if(e.target.classList.contains("active")) {
            tagNames.push(tagName);
        } else {
            tagNames = tagNames.filter((tag) => tag !== tagName);
        }

        page = 1;
        await skillLogService.getList(page,
            {
                type: type,
                keyword: input.value,
                tagNames: tagNames,
            }, skillLogLayout.showList);
    });
});

// 검색 버튼
keywordButton.addEventListener("click", async (e) => {
    e.preventDefault();
    page = 1;
    await skillLogService.getList(page,
        {
            type: type,
            keyword: input.value,
            tagNames: tagNames,
        }, skillLogLayout.showList);
})