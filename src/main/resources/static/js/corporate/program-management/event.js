NodeList.prototype.filter = Array.prototype.filter;

// 탭 클릭 시 서버로 이동 (링크 기본 동작 유지)
// 탭의 active 상태는 서버에서 th:classappend로 처리

// 최근 등록일순 / 개수별 / 담당자별 드롭다운
const sortButtons = document.querySelectorAll(".sort-button");

sortButtons.forEach((button) => {
    const sortDropDown = button.nextElementSibling;

    button.addEventListener("click", (e) => {
        sortDropDown.classList.toggle("active");
    });

    sortDropDown.addEventListener("click", (e) => {
        if (e.target.tagName === "BUTTON") {
            if (button.classList.contains("sort1")) {
                const [previousItem] = sortDropDown
                    .querySelectorAll("button")
                    .filter((button) => button.classList.contains("active"));
                if (previousItem) previousItem.classList.remove("active");
            }

            e.target.classList.add("active");

            if (button.classList.contains("sort1")) {
                // 정렬 옵션 선택 시 서버로 전송
                const sortValue = e.target.getAttribute("data-sort");
                if (sortValue) {
                    const sortInput = document.getElementById("sortInput");
                    const pageInput = document.getElementById("pageInput");
                    if (sortInput) sortInput.value = sortValue;
                    if (pageInput) pageInput.value = "1";
                    button.textContent = e.target.textContent + "순";
                    document.getElementById("form").submit();
                    return;
                }
                button.textContent = e.target.textContent + "순";
            } else if (button.classList.contains("sort2")) {
                // 개수별 선택 시 폼 제출
                const count = e.target.getAttribute("data-count");
                if (count) {
                    const topCountInput = document.getElementById("TopCountInput");
                    const pageInput = document.getElementById("pageInput");
                    if (topCountInput) topCountInput.value = count;
                    if (pageInput) pageInput.value = "1";
                    button.textContent = e.target.textContent + "씩 보기";
                    document.getElementById("form").submit();
                    return;
                }
                button.textContent = e.target.textContent + "씩 보기";
            } else {
                button.textContent = e.target.textContent;
            }

            sortDropDown.classList.remove("active");
        }
    });
});

// 더보기
const programList = document.querySelectorAll(
    ".scSelectAppList .scSelectAppItem",
);
programList.forEach((li) => {
    const moreOptionLayer = li.querySelector(".more-option");

    li.addEventListener("click", (e) => {
        if (e.target.closest(".moreOptionButton")) {
            moreOptionLayer.classList.toggle("active");
        }
    });
});

document.addEventListener("click", (e) => {
    programList.forEach((li) => {
        const moreOptionButton = li.querySelector(".moreOptionButton");
        const moreOptionLayer = li.querySelector(".more-option");

        if (e.target.closest("li .moreOptionButton") !== moreOptionButton) {
            moreOptionLayer.classList.remove("active");
        }
    });
    sortButtons.forEach((button) => {
        if (e.target !== button) {
            button.nextElementSibling.classList.remove("active");
        }
    });
});

// 맨위로
const toTheTop = document.getElementById("btnMtcTop");

toTheTop.addEventListener("click", (e) => {
    window.scrollTo({
        top: 0,
        behavior: "smooth",
    });
});
