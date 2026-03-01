// 등록하기 버튼
const admitButton = document.querySelector(".btnQuestion.devQnaWriteButton");
// 제목 가져오기
const writeTitle = document.querySelector(".jkSchInp.devQnaWriteTitle");
// 내용 가져오기
const writeContent = document.querySelector(".devQnaWriteCntnt.custom-editor");

admitButton.addEventListener("click", (e) => {
    if (!writeTitle.value) {
        alert("제목을 입력해주세요");
        return;
    }
    if (!writeContent.value) {
        alert("내용을 입력해주세요");
        return;
    }
    if (!confirm("수정하시겠습니까?")) {
        return;
    }
    admitButton.closest("form").submit();
});

// 취소하기 버튼
const cancelButton = document.querySelector(
    ".btnCancel.bg_white.devQnaWriteCancelButton",
);

cancelButton.addEventListener("click", (e) => {
    if (!confirm("다른 페이지로 이동 시, 수정된 글이 저장되지 않습니다.")) {
        e.preventDefault(); //새로고침을 막아준다.
        return false;
    }
    const qnaId = document.querySelector("input[name='qnaId']").value;
    location.href = "/qna/detail?id=" + qnaId;
});

const writeButton = document.querySelector(
    ".select-tooltip.btn-question.qnaSpB",
);
const tooltipOpenDiv = document.querySelector(".navi-top-area.has-tooltip");

writeButton.addEventListener("click", (e) => {
    tooltipOpenDiv.classList.toggle("tooltip-open");
});

const btnLayerCloses = document.querySelectorAll(".btn-layer-close.qnaSpB");

btnLayerCloses.forEach((btnLayerClose) => {
    btnLayerClose.addEventListener("click", (e) => {
        ExperienceAnnouncementClick.classList.remove("on");
        ExperienceAnnouncementLayer.classList.remove("open");
        handsOnExperience.style.display =
            handsOnExperience.style.display === "block" ? "none" : "block";
    });
});

// 링크 버튼
const iconBtnLayerOpen = document.querySelector(
    ".icon-link.qnaSpB.btn-layer-open",
);
// 링크 레이어
const linkLayer = document.querySelector(".layer-box-wrap.link-layer");

// 링크 레이어 X버튼
btnLayerCloses.forEach((btnLayerClose) => {
    btnLayerClose.addEventListener("click", (e) => {
        if (linkLayer) linkLayer.classList.remove("open");
        if (iconBtnLayerOpen) iconBtnLayerOpen.classList.remove("on");
    });
});

// 링크 클릭
if (iconBtnLayerOpen) {
    iconBtnLayerOpen.addEventListener("click", (e) => {
        linkLayer.classList.toggle("open");
        iconBtnLayerOpen.classList.toggle("on");
    });
}

const urlRegex = /^(http|https):\/\/[\w\-]+(\.[\w\-]+)+[/#?]?.*$/;

const inputText = document.querySelector(
    ".jkSchInput.keywordSearch.keywordSearchLink p input[type=text]",
);
const findButton = document.querySelector(".btnSch.qnaSpB.devSearchLink");

// 로딩
const loading = document.querySelector(".loading");

// url 검사 함수 (중복 코드 방지)
function checkUrl() {
    if (urlRegex.test(inputText.value)) {
        loading.style.display = "block";
        setTimeout(() => {
            loading.style.display = "none";
            const a = document.querySelector(".ApiOpenGraphResult");
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

// 링크 추가
const textarea = document.querySelector(".addFileAndLink");

if (findButton) {
    // 버튼 클릭
    findButton.addEventListener("click", checkUrl);

    // 엔터키 입력
    inputText.addEventListener("keydown", (e) => {
        if (e.key === "Enter") {
            checkUrl();
        }
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

    attachButton.addEventListener("click", (e) => {
        const getText = document.getElementById("devAttachLinkTitle");
        const getURL = document.querySelector(
            ".jkSchInput.keywordSearch.keywordSearchLink p.inpWrap input.schInp",
        );
        if (attachButton.classList.contains("on")) {
            if (linkLayer) linkLayer.classList.remove("open");
            if (iconBtnLayerOpen) iconBtnLayerOpen.classList.remove("on");

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
        const a = document.querySelector(".ApiOpenGraphResult");
        a.innerHTML = "";
        getText.value = "";
        getURL.value = "";
    });
}

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

// 파일 누적 관리용 DataTransfer
const dataTransfer = new DataTransfer();

// 파일 선택 시 처리
photoInput.addEventListener("change", (e) => {
    const file = e.target.files[0];
    if (!file) return;

    // 이미지 파일인지 확인
    if (!file.type.startsWith("image/")) {
        alert("이미지 파일만 올릴 수 있습니다.");
        return;
    }

    dataTransfer.items.add(file);
    photoInput.files = dataTransfer.files;

    const reader = new FileReader();
    reader.readAsDataURL(file);

    reader.addEventListener("load", (v) => {
        const path = v.target.result;

        // 이미지를 화면에 추가
        textarea.innerHTML += `
            <div class="attach-wrap attach-image" data-file-name="${file.name}">
                <div class="attach-box type-image">
                    <img src="${path}" alt="첨부 이미지">
                </div>
                <button type="button" class="remove-button qnaSpB">삭제하기</button>
            </div>
        `;
    });

});

// 이미지 삭제 (이벤트 위임)
document.addEventListener("click", (e) => {
    const btn = e.target.closest(".remove-button");
    if (btn && btn.closest(".addFileAndLink")) {
        const wrap = btn.closest(".attach-wrap");
        if (wrap.classList.contains("existingImage")) {
            const fileId = wrap.dataset.fileId;
            if (fileId) {
                const hidden = document.getElementById("deletedFileIds");
                if (hidden) {
                    hidden.value = hidden.value
                        ? hidden.value + "," + fileId
                        : fileId;
                }
            }
        } else if (wrap.dataset.fileName) {
            const newDt = new DataTransfer();
            for (const f of dataTransfer.files) {
                if (f.name !== wrap.dataset.fileName) newDt.items.add(f);
            }
            dataTransfer.items.clear();
            for (const f of newDt.files) dataTransfer.items.add(f);
            photoInput.files = dataTransfer.files;
        }
        wrap.remove();
    }
});
