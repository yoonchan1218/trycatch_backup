// 자세히 알고싶어요 화살표
const listShow = document.querySelectorAll(".list_show");
const readmeOpen = document.querySelectorAll(".readme_open");
const contentsBox = document.querySelectorAll(".contents_box");

listShow.forEach((show, idx) => {
    show.addEventListener("click", (e) => {
        // 이 안에 코드가 있어야 클릭할 때 실행됨!
        readmeOpen[idx].dataset.states =
            readmeOpen[idx].dataset.states === "close" ? "open" : "close";
        contentsBox[idx].classList.toggle("active");
    });
});

// 체험공고 스크랩
const scrapButtons = document.querySelectorAll(".rIcon.devAddScrap.devRecommend");

scrapButtons.forEach((btn) => {
    btn.addEventListener("click", (e) => {
        e.preventDefault();
        e.stopPropagation();

        const programId = btn.dataset.programId;

        const formData = new FormData();
        formData.append("experienceProgramId", programId);

        fetch("/mypage/scrap", {
            method: "POST",
            body: formData,
        })
            .then((response) => response.json())
            .then((result) => {
                if (result) {
                    btn.classList.toggle("str_off");
                    btn.classList.toggle("str_on");
                }
            })
            .catch((error) => {
                console.error("스크랩 처리 중 오류:", error);
            });
    });
});
