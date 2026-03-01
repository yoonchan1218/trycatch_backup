const loginMenu = document.querySelector(".login");
const subMenu = document.querySelector(".subMenu");

loginMenu.addEventListener("mouseenter", (e) => {
    subMenu.style.display = "block";
});

subMenu.addEventListener("mouseleave", (e) => {
    subMenu.style.display = "none";
});

// 체험공고 버튼
const exBtns = document.querySelectorAll(".rndFilterBx li button");

exBtns.forEach((btn) => {
    btn.addEventListener("click", (e) => {
        // 먼저 모든 버튼에서 on 제거
        exBtns.forEach((b) => b.classList.remove("on"));
        // 클릭한 버튼에만 on 추가
        e.target.classList.add("on");
    });
});

const detailSearch = document.querySelector(".recruitPartBx");
const extendSrch = document.getElementById("extendSrch");
const closeSrch = document.getElementById("closeSrch");

extendSrch.addEventListener("click", (e) => {
    e.stopPropagation();
    detailSearch.classList.add("extend");
});

closeSrch.addEventListener("click", (e) => {
    e.stopPropagation();
    detailSearch.classList.remove("extend");
});

const detailButtons = document.querySelectorAll(".filterSec button");
const keywordList = document.querySelector(".keyword-list");
const srchCondition = document.querySelector(".empty-text");

// li 개수 체크해서 empty-text 표시/숨김
function checkKeywordList() {
    if (keywordList.children.length > 0) {
        srchCondition.style.display = "none";
    } else {
        srchCondition.style.display = "block";
    }
}

detailButtons.forEach((detailButton) => {
    detailButton.addEventListener("click", (e) => {
        detailButton.classList.toggle("on");
        const textContent = detailButton.textContent.trim();

        if (detailButton.classList.contains("on")) {
            keywordList.innerHTML += `
                <li class="keyword-item" data-keyword="${textContent}">
                    <button type="button" class="keyword-button">
                        ${textContent}<i class="tmIco icoClose"></i>
                    </button>
                </li>
            `;
        } else {
            const targetLi = document.querySelector(
                `.keyword-item[data-keyword="${textContent}"]`,
            );
            if (targetLi) {
                targetLi.remove();
            }
        }

        checkKeywordList(); // 추가/삭제 후 체크
    });
});

// 키워드 X버튼 클릭 시
keywordList.addEventListener("click", (e) => {
    const keywordBtn = e.target.closest(".keyword-button");
    if (keywordBtn) {
        const li = keywordBtn.closest(".keyword-item");
        const keyword = li.dataset.keyword;

        detailButtons.forEach((btn) => {
            if (btn.textContent.trim() === keyword) {
                btn.classList.remove("on");
            }
        });

        li.remove();
        checkKeywordList(); // 삭제 후 체크
    }
});

const resetSrch = document.getElementById("resetSrch");

resetSrch.addEventListener("click", (e) => {
    e.stopPropagation();

    // 모든 키워드 li 삭제
    keywordList.innerHTML = "";

    // 모든 필터 버튼 on 제거
    detailButtons.forEach((btn) => {
        btn.classList.remove("on");
    });

    // empty-text 다시 표시
    checkKeywordList();
});

// 페이지 이동시 스크롤(서버때 해야함)
const exBox = document.querySelector(".sortBx.clear");
const newVer = document.querySelectorAll(".tplPagination.newVer li");

// 공고 스크랩
const stars = document.querySelectorAll(".devAddScrap");

stars.forEach((star) => {
    star.addEventListener("click", () => {
        star.classList.toggle("tplBtnScrOff");
        star.classList.toggle("tplBtnScrOn");
        star.classList.toggle("on");
    });
});
