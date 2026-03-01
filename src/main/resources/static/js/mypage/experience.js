// ============================================
// 1. 지원취소 팝업 관련
// ============================================
const applyCancelButtons = document.querySelectorAll(
    ".btn.btnGyBd.devBtnCancel.devBtnOddInfo",
);
const buttonClose = document.querySelector(".butClose.mtuSpImg.devLyBtnClose");
const dimmedDiv = document.querySelector(".dimmed");
const popupApplyCancel = document.querySelector(".mtuPopup.popupApplyCancel");
const buttonCancel = document.querySelector(".button.button-cancel");
const applySubmitButton = document.querySelector(
    ".button.button-ok.devBtnSubmitCancel",
);

// 모든 지원취소 버튼에 이벤트 등록
applyCancelButtons.forEach((applyCancelButton) => {
    applyCancelButton.addEventListener("click", (e) => {
        dimmedDiv.style.position = "fixed";
        popupApplyCancel.style.display = "block";
    });
});

// 닫기 버튼 (X)
buttonClose.addEventListener("click", (e) => {
    dimmedDiv.style.position = "relative";
    popupApplyCancel.style.display = "none";
});

// 취소 버튼
buttonCancel.addEventListener("click", (e) => {
    dimmedDiv.style.position = "relative";
    popupApplyCancel.style.display = "none";
});

// 확인 버튼
applySubmitButton.addEventListener("click", (e) => {
    location.href = "";
});

// ============================================
// 2. 취소사유 드롭다운 관련
// ============================================
const applyListButton = document.querySelector(".btnChoose");
const applyListDrop = applyListButton.nextElementSibling;
const applyLists = applyListDrop.querySelectorAll("li");
const applyListButtonSpan = applyListButton.firstElementChild;
const applyCancelReason = document.getElementById("applyCancelreason");
const applyCancelReasonInput = document.getElementById("Apply_Cncl_Rsn_Etc");

// 드롭다운 열기/닫기
applyListButton.addEventListener("click", (e) => {
    applyListDrop.classList.toggle("visible");
});

// 드롭다운 항목 클릭
applyLists.forEach((applyList, i) => {
    applyList.addEventListener("click", (e) => {
        // li 안의 button에서 텍스트 가져오기
        const buttonText = applyList.querySelector("button").textContent;

        if (i !== 3) {
            // 기타가 아닌 경우
            applyListButtonSpan.textContent = buttonText;
            applyCancelReason.style.display = "none";
            applyListDrop.classList.remove("visible");
        } else {
            // 기타인 경우 - 직접입력 영역 표시
            applyListButtonSpan.textContent = buttonText;
            applyCancelReason.style.display = "block";
            applyListDrop.classList.remove("visible");
        }
    });
});

// 30자 제한 (input 이벤트로 분리)
applyCancelReasonInput.addEventListener("input", (e) => {
    if (applyCancelReasonInput.value.length > 30) {
        alert("최대 30자만 입력 가능합니다");
        applyCancelReasonInput.value = applyCancelReasonInput.value.substring(
            0,
            30,
        );
    }
});

// ============================================
// 3. 조회기간 버튼 (1주일, 1개월, 2개월, 3개월)
// ============================================
const selectDueButtons = document.querySelectorAll(".formBx.clear button");
selectDueButtons.forEach((selectDueButton) => {
    selectDueButton.addEventListener("click", (e) => {
        // 모든 버튼에서 on 클래스 제거
        selectDueButtons.forEach((btn) => {
            btn.classList.remove("on");
        });
        // 클릭한 버튼에 on 클래스 추가
        selectDueButton.classList.add("on");
    });
});

// ============================================
// 4. 진행여부/열람여부/지원상태 드롭다운
// ============================================
const dropDownButtons = document.querySelectorAll(".btnMtcLySel.mtcBtnB");

dropDownButtons.forEach((dropDownButton) => {
    // 버튼의 형제 요소(nextElementSibling)가 바로 해당 드롭다운 div
    const dropDownDiv = dropDownButton.nextElementSibling;
    const dropDownItems = dropDownDiv.querySelectorAll("li a");

    // 버튼 클릭 → 열기/닫기
    dropDownButton.addEventListener("click", (e) => {
        e.stopPropagation();

        // 다른 드롭다운 모두 닫기
        document.querySelectorAll(".lyItems").forEach((div) => {
            if (div !== dropDownDiv) {
                div.style.display = "none";
            }
        });

        // 현재 드롭다운 토글
        dropDownDiv.style.display =
            dropDownDiv.style.display === "block" ? "none" : "block";
    });

    // 항목 클릭 → 텍스트 적용 + 닫기
    dropDownItems.forEach((item) => {
        item.addEventListener("click", (e) => {
            e.stopPropagation();
            dropDownButton.textContent = item.textContent;
            dropDownDiv.style.display = "none";
        });
    });
});

// 다른 곳 클릭 → 드롭다운 모두 닫기
document.addEventListener("click", () => {
    document.querySelectorAll(".lyItems").forEach((div) => {
        div.style.display = "none";
    });
});

// ============================================
// 5. 지원이력서 모달
// ============================================
const resumeButtons = document.querySelectorAll(
    ".button.button-resume.resumeTile",
);
const resumeModalOverlay = document.getElementById("resumeModalOverlay");
const resumeModalClose = document.getElementById("resumeModalClose");

// 모든 지원이력서 버튼에 이벤트 등록
resumeButtons.forEach((resumeButton) => {
    resumeButton.addEventListener("click", (e) => {
        e.preventDefault();
        resumeModalOverlay.classList.add("active");
        document.body.style.overflow = "hidden"; // 스크롤 방지
    });
});

// 닫기 버튼 클릭
resumeModalClose.addEventListener("click", () => {
    resumeModalOverlay.classList.remove("active");
    document.body.style.overflow = ""; // 스크롤 복원
});

// 오버레이 클릭 시 닫기 (모달 외부 클릭)
resumeModalOverlay.addEventListener("click", (e) => {
    if (e.target === resumeModalOverlay) {
        resumeModalOverlay.classList.remove("active");
        document.body.style.overflow = "";
    }
});

// ESC 키로 닫기
document.addEventListener("keydown", (e) => {
    if (e.key === "Escape" && resumeModalOverlay.classList.contains("active")) {
        resumeModalOverlay.classList.remove("active");
        document.body.style.overflow = "";
    }
});
