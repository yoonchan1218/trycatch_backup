// 작성하기 버튼
const writeButtonDiv = document.querySelector(".navi-top-area.has-tooltip");
const writeButton = document.querySelector(".navi-top-area.has-tooltip a");

// 작성하기 버튼 눌렀을 때 띄울 툴바
writeButton.addEventListener("click", (e) => {
    writeButtonDiv.classList.toggle("tooltip-open");
});

// 체험공고 클릭

// 체험공고 버튼
const ExperienceAnnouncementClick = document.querySelector(
    ".icon-recruit.qnaSpB.btn-layer-open",
);
// 공고 레이어
const ExperienceAnnouncementLayer = document.querySelector(".opening-layer");

// 갔다온 체험
const handsOnExperience = document.getElementById("recentRecruit");

ExperienceAnnouncementClick.addEventListener("click", (e) => {
    ExperienceAnnouncementLayer.classList.toggle("open");
    ExperienceAnnouncementClick.classList.toggle("on");
    handsOnExperience.style.display =
        handsOnExperience.style.display === "block" ? "none" : "block";
});

// 체크 개수에 따라 버튼 달라지기
NodeList.prototype.filter = Array.prototype.filter;
const applyAttachRecruitBtn = document.querySelector(".apply.attachRecruitBtn");
let resultChecked = 0;
const checkboxes = document.querySelectorAll("input[type=checkbox]");

checkboxes.forEach((checkbox) => {
    checkbox.addEventListener("change", (e) => {
        const count = checkboxes.filter((checkbox) => checkbox.checked).length;
        applyAttachRecruitBtn.classList.toggle("on", count !== 0);
        applyAttachRecruitBtn.textContent =
            count === 0 ? "첨부하기" : `체험 ${count}건 첨부하기`;
    });
});

// X 버튼(공용)
const btnLayerCloses = document.querySelectorAll(".btn-layer-close.qnaSpB");

btnLayerCloses.forEach((btnLayerClose) => {
    btnLayerClose.addEventListener("click", (e) => {
        ExperienceAnnouncementClick.classList.remove("on");
        ExperienceAnnouncementLayer.classList.remove("open");
        handsOnExperience.style.display =
            handsOnExperience.style.display === "block" ? "none" : "block";
    });
});

// 링크

// 링크 버튼
const iconBtnLayerOpen = document.querySelector(
    ".icon-link.qnaSpB.btn-layer-open",
);
// 링크 레이어
const linkLayer = document.querySelector(".layer-box-wrap.link-layer");

// 링크 레이어 X버튼
btnLayerCloses.forEach((btnLayerClose) => {
    btnLayerClose.addEventListener("click", (e) => {
        linkLayer.classList.remove("open");
        iconBtnLayerOpen.classList.remove("on");
    });
});

// 링크 클릭
iconBtnLayerOpen.addEventListener("click", (e) => {
    linkLayer.classList.toggle("open");
    iconBtnLayerOpen.classList.toggle("on");
});

const urlRegex = /^(http|https):\/\/[\w\-]+(\.[\w\-]+)+[/#?]?.*$/;

const inputText = document.querySelector(
    ".jkSchInput.keywordSearch.keywordSearchLink p input[type=text]",
);
const findButton = document.querySelector(".btnSch.qnaSpB.devSearchLink");
const a = document.querySelector(".ApiOpenGraphResult");

// 로딩
const loading = document.querySelector(".loading");

// url 검사 함수 (중복 코드 방지)
function checkUrl() {
    if (urlRegex.test(inputText.value)) {
        loading.style.display = "block";
        setTimeout(() => {
            loading.style.display = "none";
            a.innerHTML = `
                <a href="#" class="attach-box type-link">
                    <div class="thumb-img-area">
                        <span>링크</span>
                    </div>
                    <div class="link-textarea">
                        <textarea class="corp-name" name="" id="devAttachLinkTitle" cols="20" rows="5" maxlength="51" placeholder="링크 제목을 입력하세요."></textarea>
                    </div>
                </a>
            `;
        }, 1800);
    } else {
        alert("URL을 입력해주세요");
    }
}

// 버튼 클릭
findButton.addEventListener("click", checkUrl);

// 엔터키 입력
inputText.addEventListener("keydown", (e) => {
    if (e.key === "Enter") {
        checkUrl();
    }
});

const textarea = document.querySelector(".addFileAndLink");
// 삭제 이벤트 - 한 번만 등록 (이벤트 위임)
const addFileAndLink = document.querySelector(".addFileAndLink");

addFileAndLink.addEventListener("click", (e) => {
    if (e.target.classList.contains("remove-button")) {
        e.target.closest(".attach-wrap").remove();
    }
});

// 공고 첨부하기
const attachRecruitBtn = document.querySelector(".attachRecruitBtn");

attachRecruitBtn.addEventListener("click", (e) => {
    // 체크된 공고들 가져오기
    const checkedItems = document.querySelectorAll(
        '#recentRecruit .keyword-search-item input[type="checkbox"]:checked',
    );

    if (checkedItems.length === 0) {
        alert("첨부할 공고를 선택해주세요.");
        return;
    }

    checkedItems.forEach((checkbox) => {
        const gno = checkbox.dataset.gno;
        const cname = checkbox.dataset.cname;
        const title = checkbox.dataset.title;
        const logo =
            checkbox.dataset.logo ||
            "//img.jobkorea.co.kr/Images/Logo/200/l/o/logo_none_200.png";

        addFileAndLink.innerHTML += `
            <div class="attach-wrap" data-gno="${gno}">
                <a href="/Recruit/GI_Read/${gno}" target="_blank" class="attach-box">
                    <span class="thumb-img-area">
                        <img src="${logo}" alt="${cname}" 
                             onerror="this.src='//img.jobkorea.co.kr/Images/Logo/200/l/o/logo_none_200.png'">
                    </span>
                    <div class="corp-info-area qnaSpA">
                        <p class="corp-name">${cname}</p>
                        <p class="content">${title}</p>
                    </div>
                </a>
                <button type="button" class="remove-button qnaSpB">삭제하기</button>
            </div>
        `;

        // 체크 해제
        checkbox.checked = false;
    });

    // 레이어 닫기
    ExperienceAnnouncementClick.classList.remove("on");
    ExperienceAnnouncementLayer.classList.remove("open");
    handsOnExperience.style.display =
        handsOnExperience.style.display === "block" ? "none" : "block";
    applyAttachRecruitBtn.classList.remove("on");
    applyAttachRecruitBtn.textContent = "첨부하기";
});
// 링크 첨부하기 버튼 활성화

// 링크 제목 입력창
const linkTitleInput = document.querySelector(".ApiOpenGraphResult");
// 첨부하기 버튼
const attachButton = document.querySelector(".apply.attachLinkBtn");

// 입력할 때마다 체크
linkTitleInput.addEventListener("input", (e) => {
    // 링크 제목에 들어갈 변수 담기

    if (e.target.value) {
        attachButton.classList.add("on");
    } else {
        attachButton.classList.remove("on");
    }
});

// 링크 추가
attachButton.addEventListener("click", (e) => {
    const getText = document.getElementById("devAttachLinkTitle");
    const getURL = document.querySelector(
        ".jkSchInput.keywordSearch.keywordSearchLink p.inpWrap input.schInp",
    );
    if (attachButton.classList.contains("on")) {
        linkLayer.classList.remove("open");
        iconBtnLayerOpen.classList.remove("on");
        attachButton.classList.remove("on");

        textarea.innerHTML += `
            <div class="attach-wrap">
                <a href="#" class="attach-box type-link">
                    <span class="thumb-img-area">
                        <span>링크</span>
                    </span>
                <div class="corp-info-area qnaSpA">
                    <p class="content">${getText.value}</p>
                    <span class="content-url">${getURL.value}</span>
                </div>
            </a>
            <button type="button" class="remove-button qnaSpB">삭제하기</button>
            </div>
        `;
    }
    a.innerHTML = "";
    getText.value = "";
    getURL.value = "";

    textarea.addEventListener("click", (e) => {
        if (e.target.classList.contains("remove-button")) {
            e.target.closest(".attach-wrap").remove();
        }
    });
});

// 링크 삭제
const removeLink = document.querySelector(".attach-box.type-link");

// 이미지 버튼
const addPicture = document.querySelector(".icon-photo.qnaSpB.btn-layer-open");
// 숨겨진 파일 input
const photoInput = document.getElementById("photoInput");

// 버튼 클릭 → 숨겨진 input 클릭
addPicture.addEventListener("click", () => {
    photoInput.click();
});

// 파일 선택 시 처리
photoInput.addEventListener("change", (e) => {
    const file = e.target.files[0];
    if (!file) return;

    // 이미지 파일인지 확인
    if (!file.type.startsWith("image/")) {
        alert("이미지 파일만 올릴 수 있습니다.");
        return;
    }

    const reader = new FileReader();
    reader.readAsDataURL(file);

    reader.addEventListener("load", (v) => {
        const path = v.target.result;

        // 이미지를 화면에 추가
        textarea.innerHTML += `
            <div class="attach-wrap attach-image">
                <div class="attach-box type-image">
                    <img src="${path}" alt="첨부 이미지">
                </div>
                <button type="button" class="remove-button qnaSpB">삭제하기</button>
            </div>
        `;
    });

    // 같은 파일 다시 선택 가능하도록 초기화
    e.target.value = "";
});

// 이미지 삭제 (이벤트 위임)
textarea.addEventListener("click", (e) => {
    if (e.target.classList.contains("remove-button")) {
        e.target.closest(".attach-wrap").remove();
    }
});

// 태그 입력
const addTag = document.querySelector("#devTags");
const inputTag = document.querySelector(".tag-input");
const regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/;

addTag.addEventListener("keyup", (e) => {
    const tag = addTag.value;
    if (e.key === "Enter" && tag) {
        // span 태그 생성
        if (regExp.test(tag)) {
            alert("특수문자는 입력 못해요");
            addTag.value = "";
            return;
        }
        const span = document.createElement("span");
        span.className = "tagDiv";
        span.textContent = `#${tag}`;

        // input 앞에 span 삽입
        inputTag.insertBefore(span, addTag);

        // input 값 초기화
        addTag.value = "";

        // 너비 재조정
        adjustInputWidth();
    }
});

function adjustInputWidth() {
    const tags = document.querySelectorAll(".tagDiv");

    // 모든 태그의 총 너비 계산
    let tagsWidth = 0;
    tags.forEach((tag) => {
        tagsWidth += tag.offsetWidth + 10;
    });

    // input 너비 조정
    const containerWidth = inputTag.offsetWidth;
    const remainingWidth = containerWidth - tagsWidth - 60;

    addTag.style.width = Math.max(remainingWidth, 50) + "px";
}

// 태그 삭제
inputTag.addEventListener("click", (e) => {
    if (e.target.classList.contains("tagDiv")) {
        e.target.remove();
    }
});

// 등록하기 버튼
const admitButton = document.querySelector(".btnQuestion.devQnaWriteButton");
// 제목 가져오기
const writeTitle = document.querySelector(".jkSchInp.devQnaWriteTitle");
// 내용 가져오기
const writeContent = document.querySelector(".devQnaWriteCntnt.custom-editor");

admitButton.addEventListener("click", (e) => {
    if (!confirm("수정하시겠습니까?")) {
        e.preventDefault();
    }
    if (!writeTitle.value) {
        alert("제목을 입력해주세요");
        e.preventDefault();
    } else if (!writeContent.value) {
        alert("내용을 입력해주세요");
        e.preventDefault();
    } else {
        alert("수정되었습니다.");
        location.href = "";
    }
});

// 취소하기 버튼
const cancelButton = document.querySelector(
    ".btnCancel.bg_white.devQnaWriteCancelButton",
);

cancelButton.addEventListener("click", (e) => {
    if (!confirm("다른 페이지로 이동 시, 수정된 글이 저장되지 않습니다.")) {
        e.preventDefault();
        return false;
    }
    location.href = "";
});

const keywordSearch = document.querySelector(
    ".jkSchInput.keywordSearch.keywordSearchRecruit",
);
const keywordTextBox = document.getElementById("AJAX_TS_Search");

keywordTextBox.addEventListener("input", (e) => {
    keywordSearch.classList.add("focus");
    if (!keywordTextBox.value) {
        keywordSearch.classList.remove("focus");
    }
});
