// 신고 버튼

// 신고 열리는 버튼
const reportActiveButton = document.querySelector(".devQstnDetailMenuIcon");
// 신고창 뜨는 신고버튼
const reportButton = document.querySelector(".view-more-layer");
// 신고창
const pressReportButton = document.querySelector(".mtuLyWrap.lyQnaReport");
// 신고창 x버튼
const pressReportButtonClose = document.querySelector(
    ".butClose.mtuSpImg.devLyBtnClose",
);
// 신고창 text-area 문구
const reportFormBox = document.querySelector(
    ".qnaFormBx.qnaTxaBx.devTplSchPh span",
);
// 신고창 text-area
const reportTextArea = document.getElementById("lb_reason_8");
// 신고창 신고하기 버튼
const reportSubmitButton = document.querySelector(".btnReport.devBtnReportIns");
// 신고창 첫번째 이유(input radio)
const reportFirstReasonRadio = document.querySelectorAll(
    ".reportBx.radioCommWrap li input",
);

const reportForm = document["reportForm"];
const memberId = reportForm.memberId.value;
const skillLogId = reportForm.skillLogId.value;

let page = 1;
let nestedPage = 1;

// 댓글 목록
commentService.getList(page, skillLogId, memberId, commentLayout.showCommentList);


// 신고하기 눌렀을 때 알림띄우기(confirm 사용 시 확인,취소 뜸)
reportSubmitButton.addEventListener("click", (e) => {
    const reportSubmitMessage = confirm(
        "신고된 글은 운영자에게 전달됩니다. 신고하시겠습니까?",
    );
    if (reportSubmitMessage) {
        const skillLogCommentId = reportForm.skillLogCommentId.value;
        if(skillLogCommentId) {
            reportForm.setAttribute("action", "/report/skill-log/comment")
        }
        alert("신고 처리 완료되었습니다.");

        reportForm.submit();
    }
});
// 신고창 text-area 누를 시 문구 삭제
reportTextArea.addEventListener("click", (e) => {
    reportFormBox.style.display = "none";
    input.focus();
});

// 신고창 text-area 바깥 누를시 다시 문구 띄우기
reportTextArea.addEventListener("blur", (e) => {
    reportFormBox.style.display = "block";
});

// 신고 버튼 활성화 및 눌렀을 시 신고창 띄우기
reportActiveButton.addEventListener("click", (e) => {
    reportButton.classList.toggle("active");
    reportButton.addEventListener("click", (e) => {
        if(e.target.classList.contains("report")) {
            // 신고
            if(memberId) {
                pressReportButton.style.display = "block";
            } else {
                location.href = "/main/log-in";
            }
        } else if (e.target.classList.contains("delete")) {
            // 삭제
            if(confirm("삭제된 게시글은 복구할 수 없습니다. 정말로 삭제하시겠습니까?")) {
                location.href = `/skill-log/delete?id=${skillLogId}`;
            }
        } else {
            // 수정
            location.href = `/skill-log/update?id=${skillLogId}`;
        }
    });
    reportFirstReasonRadio.forEach((reportFirstReasonRadio) => {
        if (reportFirstReasonRadio.value === "1") {
            reportFirstReasonRadio.checked = true;
        }
    });
});

// 신고창 닫기
pressReportButtonClose.addEventListener("click", (e) => {
    pressReportButton.style.display = "none";
});

const pressReportCancelButton = document.querySelector(
    ".btnCancel.bg_white.devLyBtnClose",
);
pressReportCancelButton.addEventListener("click", (e) => {
    pressReportButton.style.display = "none";
});

// 공유하기 버튼
const shareButton = document.querySelector(".devShareBtn");
const toolDiv = document.querySelector(
    ".reaction-item .share-layer.tooltip-layer.qnaSpA",
);

// 공유하기 눌렀을 때 창띄우기
shareButton.addEventListener("click", (e) => {
    toolDiv.style.display =
        toolDiv.style.display === "block" ? "none" : "block";
});

// 작성하기 버튼
const writeButtonDiv = document.querySelector(".navi-top-area.has-tooltip");
const writeButton = document.querySelector(".navi-top-area.has-tooltip a");

// 작성하기 버튼 눌렀을 때 띄울 툴바
writeButton.addEventListener("click", (e) => {
    writeButtonDiv.classList.toggle("tooltip-open");
});

// URL 복사 클릭

// URL 복사 버튼
const URLCopy = document.querySelector(
    ".button.button-copy-url.button-popup-component",
);
const URLInput = document.getElementById("lbl_url_copy");

// URL 복사 눌렀을 시 클래스 추가
const URLCopyLayer = document.querySelector(".url-copy-layer");
// 닫기 버튼
const URLCopyLayerBefore = document.querySelector(".button.button-close");

// URL 설정
URLInput.value = window.location.href;

// 클릭 시 "attached" 클래스 토글
URLCopy.addEventListener("click", (e) => {
    URLCopyLayer.classList.toggle("attached");
});

// 닫기 버튼 누르면 "attached" 클래스 삭제
URLCopyLayerBefore.addEventListener("click", (e) => {
    URLCopyLayer.classList.remove("attached");
});

// 즐겨찾기 누를 시(로그인)
const addLike = document.querySelector(".button.button-bookmark");
addLike.addEventListener("click", (e) => {
    alert("Ctrl+D 를 이용해 이 페이지를 즐겨찾기에 추가할 수 있습니다.");
});

// 게시글(질문) 좋아요(로그인)

// 좋아요 버튼
const qstnLikeButton = document.querySelector(".devQstnLike");

// 버튼 눌렀을 때 클래스 "on" 토글
if (qstnLikeButton) {
    qstnLikeButton.addEventListener("click", (e) => {
        setTimeout(() => {
            qstnLikeButton.classList.toggle("on");
            skillLogLikeService.getCount(
                {skillLogId: skillLogId, memberId: memberId},
                skillLogLikeLayout.showCount
            );
        }, 100);
    });
}

// ################################################################################################################

// 북마크 등록(로그인)
// const buttonBookMark = document.querySelector(
//     ".btnBookmark.qnaSpB.devQnaDetailBookmark",
// );
// const bookMarkLayer = document.querySelector(
//     ".book-mark-layer.tooltip-layer.qnaSpA",
// );
//
// buttonBookMark.addEventListener("click", (e) => {
//     if (!buttonBookMark.classList.contains("on")) {
//         bookMarkLayer.style.opacity = "1";
//         setTimeout(() => {
//             bookMarkLayer.style.opacity = "0";
//         }, 975);
//     } else {
//         bookMarkLayer.style.opacity = "0";
//     }
//     buttonBookMark.classList.toggle("on");
// });

// 비로그인 시 로그인 페이지로 이동(클릭 이벤트)
function loginHref() {
    !memberId && (location.href = "/main/log-in");
}


// ============================================================
// 공통 핸들러 (댓글 / 대댓글 동일 적용)
// ============================================================

// click - 사진 버튼 & 삭제 버튼
function handleFileClick(e) {
    const target = e.target;


    if (target.closest(".button.icon-photo.qnaSpB")) {
        const btn = target.closest(".button.icon-photo.qnaSpB");
        const label = btn.closest("label") || btn.closest(".answer-util-item");
        const fileInput = label?.querySelector(".reply-file");
        if (fileInput) fileInput.click();
        return;
    }

    // 첨부 삭제 버튼
    if (target.closest(".remove-button.qnaSpB")) {
        const attachWrap = target.closest(".attach-wrap");
        if (attachWrap) attachWrap.remove();
        return;
    }
}

// change - 파일 선택 시 미리보기
function handleFileChange(e) {
    if (!e.target.classList.contains("reply-file")) return;

    const [file] = e.target.files;
    if (!file) return;

    const writeBoxWrap = e.target.closest(".writeBoxWrap");
    if (!writeBoxWrap) return;

    const uiPlaceholder = writeBoxWrap.querySelector(".uiPlaceholder");
    const ph1 = writeBoxWrap.querySelector(".ph_1");
    const ph2 = writeBoxWrap.querySelector(".ph_2");
    const textarea = writeBoxWrap.querySelector("textarea");

    writeBoxWrap.classList.remove("case");
    if (uiPlaceholder) uiPlaceholder.classList.add("focus");
    if (ph1) ph1.style.display = "none";
    if (ph2) ph2.style.display = textarea?.value ? "none" : "block";

    const reader = new FileReader();
    reader.readAsDataURL(file);

    reader.addEventListener("load", (ev) => {
        const path = ev.target.result;

        if (!path.includes("image")) {
            alert("이미지 파일로 올려주세요.");
            e.target.value = "";
            return;
        }

        const existingAttach = uiPlaceholder?.querySelector(".attach-wrap");
        if (existingAttach) existingAttach.remove();

        const div = document.createElement("div");
        div.classList.add("attach-wrap");
        div.innerHTML = `
            <div class="attach-image">
                <img src="${path}">
                <button type="button" class="remove-button qnaSpB">삭제하기</button>
            </div>
        `;
        if (uiPlaceholder) uiPlaceholder.appendChild(div);
    });
}

// input - 글자 수 카운트
function handleTextInput(e) {
    if (e.target.tagName !== "TEXTAREA") return;

    const wrapper = e.target.closest(".writeBoxWrap");
    if (!wrapper) return;

    const textCount = wrapper.querySelector("span.byte b");
    const ph2 = wrapper.querySelector(".ph_2");
    const totalTextCount = e.target.value.length;

    if (textCount) textCount.textContent = totalTextCount;

    if (totalTextCount > 1000) {
        alert("최대 1000자까지 입력 가능합니다.");
        e.target.value = e.target.value.substring(0, 1001);
        e.target.focus();
    }

    if (ph2) ph2.style.display = e.target.value ? "none" : "block";
}

// focusin - textarea placeholder 처리
function handleTextFocusin(e) {
    if (e.target.tagName !== "TEXTAREA") return;

    const wrapper = e.target.closest(".writeBoxWrap");
    if (!wrapper) return;

    const uiPlaceholder = wrapper.querySelector(".uiPlaceholder");
    const ph1 = wrapper.querySelector(".ph_1");
    const ph2 = wrapper.querySelector(".ph_2");

    wrapper.classList.remove("case");
    if (uiPlaceholder) uiPlaceholder.classList.add("focus");
    if (ph1) ph1.style.display = "none";
    if (!e.target.value && ph2) ph2.style.display = "block";
}


// ============================================================
// 메인 댓글 등록 이벤트
// ============================================================
const mainCommentWriteButton = document.querySelector(".btnSbm.devBtnAnswerWrite");

if (mainCommentWriteButton) {
    mainCommentWriteButton.addEventListener("click", async (e) => {
        const text = document.querySelector(".devTxtAreaAnswerWrite")?.value;
        const fileInput = mainCommentWriteButton.closest(".btnWrap")?.querySelector(".reply-file");

        if (!text?.trim()) {
            alert("댓글 내용을 입력해주세요.");
            return;
        }

        const formData = new FormData();
        formData.append("skillLogId", Number(skillLogId));
        formData.append("memberId", Number(memberId));
        formData.append("skillLogCommentContent", text);
        if (fileInput && fileInput.files[0]) formData.append("file", fileInput.files[0]);

        commentService.write(formData).then(() => {
            alert("댓글이 등록되었습니다.");
            document.querySelector(".devTxtAreaAnswerWrite").value = "";

            const wrapper = mainCommentWriteButton.closest(".writeBoxWrap");
            if (wrapper) {
                const byteCounter = wrapper.querySelector("span.byte b");
                if (byteCounter) byteCounter.textContent = "0";
                const attachWrap = wrapper.querySelector(".attach-wrap");
                if (attachWrap) attachWrap.remove();
            }
            // 등록 완료 후에 파일 input 초기화
            if (fileInput) fileInput.value = "";

            commentService.getList(page, skillLogId, memberId, commentLayout.showCommentList);
        });
    });
}

// ============================================================
// 메인 댓글 작성 영역 이벤트 위임 (inputBox)
// ============================================================
const inputBox = document.querySelector(".inputBox");

if (inputBox) {
    inputBox.addEventListener("click", handleFileClick);
    inputBox.addEventListener("change", handleFileChange);
    inputBox.addEventListener("input", handleTextInput);
    inputBox.addEventListener("focusin", handleTextFocusin);

    // focusout - textarea 외부 클릭 시 접기
    inputBox.addEventListener("focusout", (e) => {
        if (e.target.tagName !== "TEXTAREA") return;

        const wrapper = e.target.closest(".writeBoxWrap");
        if (!wrapper) return;

        const hasAttachment = wrapper.querySelector(".attach-wrap");
        if (e.target.value || hasAttachment) return;

        const uiPlaceholder = wrapper.querySelector(".uiPlaceholder");
        const ph1 = wrapper.querySelector(".ph_1");
        const ph2 = wrapper.querySelector(".ph_2");

        wrapper.classList.add("case");
        if (uiPlaceholder) uiPlaceholder.classList.remove("focus");
        if (ph1) ph1.style.display = "block";
        if (ph2) ph2.style.display = "none";
    });
}

// ============================================================
// 페이징 이벤트 위임
// ============================================================
const pageWrap = document.querySelector(".tplPagination.newVer");

pageWrap.addEventListener("click", (e) => {
    const target = e.target.closest(".paging");
    if (!target) return;

    e.preventDefault();
    page = Number(target.getAttribute("href"));
    commentService.getList(page, skillLogId, memberId, commentLayout.showCommentList);
});

// ============================================================
// 댓글 영역 이벤트 위임 (비동기로 생성되는 댓글/대댓글 전용)
// ============================================================
const answerArea = document.querySelector(".answerArea");

answerArea.addEventListener("click", (e) => {
    const target = e.target;

    // ----------------------------------------------------------
    // 1. 댓글 신고 버튼
    // ----------------------------------------------------------
    if (target.closest(".btnReport.devBtnReport")) {
        if (!memberId) {
            location.href = "/main/log-in";
            return;
        }
        const pressReportButton = document.querySelector(".mtuLyWrap.lyQnaReport");
        const reportFirstReasonRadio = document.querySelectorAll(".reportBx.radioCommWrap li input");
        const skillLogCommentIdInput = reportForm.skillLogCommentId;

        skillLogCommentIdInput.value = target.closest(".btnReport.devBtnReport").getAttribute("data-idf-no");

        if (pressReportButton) pressReportButton.style.display = "block";
        if (reportFirstReasonRadio.length > 0) reportFirstReasonRadio[0].checked = true;
        return;
    }

    // ----------------------------------------------------------
    // 2. 댓글 좋아요 버튼
    // ----------------------------------------------------------
    if (target.closest(".btnHeart.qnaSpB.devBtnAnswerLike")) {
        if (!memberId) {
            location.href = "/main/log-in";
            return;
        }
        const btn = target.closest(".btnHeart.qnaSpB.devBtnAnswerLike");
        const li = btn.closest("li[id^='li']");
        const skillLogCommentId = li.id.slice(2);

        commentService.getLikeCount(
            {skillLogCommentId: skillLogCommentId, memberId: memberId},
            commentLayout.showLikeCount
        );
        btn.classList.toggle("active");

        return;
    }

    // ----------------------------------------------------------
    // 3. 대댓글 열기/닫기 버튼
    // ----------------------------------------------------------
    if (target.closest(".btnCmt.devBtnComtList")) {
        const btn = target.closest(".btnCmt.devBtnComtList");
        const li = btn.closest("li[id^='li']");
        const commentId = li.id.slice(2);
        const commentSec = li.querySelector(".commentSec");

        btn.classList.toggle("active");

        if (btn.classList.contains("active")) {
            commentSec.style.display = "block";
            commentService.getNestedList(1, skillLogId, commentId, memberId, (data, mId, cId) => {
                commentLayout.showNestedCommentList(data, mId, cId, false);
            });
        } else {
            commentSec.style.display = "none";
        }
        return;
    }

    // ----------------------------------------------------------
    // 3-1. 대댓글 더보기 버튼
    // ----------------------------------------------------------
    if (target.closest(".devBtnNestedMore")) {
        const btn = target.closest(".devBtnNestedMore");
        const commentId = btn.dataset.commentId;
        const nextPage = Number(btn.dataset.page);

        commentService.getNestedList(nextPage, skillLogId, commentId, memberId, (data, mId, cId) => {
            commentLayout.showNestedCommentList(data, mId, cId, true);
        });
        return;
    }

    // ----------------------------------------------------------
    // 4. 댓글접기 버튼
    // ----------------------------------------------------------
    if (target.closest(".btnCmtClose.qnaSpA.qnaBtnClose")) {
        const li = target.closest("li");
        const commentSec = li.querySelector(".commentSec");
        const btn = li.querySelector(".btnCmt.devBtnComtList");

        commentSec.style.display = "none";
        if (btn) btn.classList.remove("active");
        return;
    }

    // ----------------------------------------------------------
    // 5. 대댓글 등록 버튼
    // ----------------------------------------------------------
    if (target.closest(".btnSbm.devBtnComtWrite")) {
        const btn = target.closest(".btnSbm.devBtnComtWrite");
        const li = btn.closest("li[id^='li']");
        const commentId = li.id.slice(2);
        const text = li.querySelector(".devComtWrite")?.value;
        const fileInput = btn.closest(".btnWrap")?.querySelector(".reply-file");

        const formData = new FormData();
        formData.append("skillLogId", Number(skillLogId));
        formData.append("memberId", Number(memberId));
        formData.append("skillLogCommentParentId", Number(commentId));
        formData.append("skillLogCommentContent", text);

        if (fileInput && fileInput.files[0]) {
            formData.append("file", fileInput.files[0]);
        }

        commentService.write(formData).then(() => {
            alert("댓글이 등록되었습니다.");
            // 등록 완료 후 파일 input 초기화
            if (fileInput) fileInput.value = "";
            commentService.getList(page, skillLogId, memberId, commentLayout.showCommentList);
        });
        return;
    }

    // ----------------------------------------------------------
    // 6. 댓글 삭제 버튼
    // ----------------------------------------------------------
    if (target.closest(".btnDelete.devAnswerDeleteButton")) {
        if (confirm("정말로 댓글을 삭제 하시겠습니까?")) {
            const commentId = target.classList[2];

            const li = target.closest("li");
            const parentLi = li.closest("li[id^='li']");
            const parentCommentId = parentLi.id.slice(2);

            commentService.remove(commentId).then(() => {
                alert("삭제되었습니다.");
                page = 1;
                commentService.getList(page, skillLogId, memberId, commentLayout.showCommentList);
                parentCommentId && commentService.getNestedList(nestedPage, skillLogId, parentCommentId, memberId, commentLayout.showNestedCommentList);
            });
        }
        return;
    }

    // ----------------------------------------------------------
    // 8. 댓글 수정 버튼
    // ----------------------------------------------------------
    if (target.closest(".btnEdit.devAnswerEditButton")) {
        const btn = target.closest(".btnEdit.devAnswerEditButton");
        const li = btn.closest("li");
        const contSec = li.querySelector(".contSec.devContSection");
        const modifyContSec = li.querySelector(".contSec.modify-answer");
        const modifyText = contSec.querySelector("p.cont")?.textContent || "";
        const modifyTextarea = modifyContSec.querySelector("textarea");

        if (modifyTextarea) {
            modifyTextarea.value = modifyText;
            const byteCounter = modifyContSec.querySelector(".byte b");
            if (byteCounter) byteCounter.textContent = modifyText.length;
        }

        const ph1 = modifyContSec.querySelector(".ph_1");
        const ph2 = modifyContSec.querySelector(".ph_2");
        if (ph1) ph1.style.display = "none";
        if (ph2) ph2.style.display = "none";

        contSec.style.display = "none";
        modifyContSec.style.display = "block";
        return;
    }

    // ----------------------------------------------------------
    // 9. 댓글 수정 취소 버튼
    // ----------------------------------------------------------
    if (target.closest(".btnModifyCancel.qnaSpB")) {
        const li = target.closest("li");
        const contSec = li.querySelector(".contSec.devContSection");
        const modifyContSec = li.querySelector(".contSec.modify-answer");

        contSec.style.display = "block";
        modifyContSec.style.display = "none";
        return;
    }

    // ----------------------------------------------------------
    // 10. 댓글 수정 등록 버튼
    // ----------------------------------------------------------
    if (target.closest(".btnSbm.devAnswerEditSubmitButton")) {
        const btn = target.closest(".btnSbm.devAnswerEditSubmitButton");
        const li = btn.closest("li[id^='li']");
        const commentId = li.id.slice(2);
        const form = btn.closest("form");
        const text = form.querySelector("textarea")?.value;
        const fileInput = form.querySelector(".reply-file");

        const formData = new FormData();
        formData.append("id", Number(commentId));
        formData.append("skillLogCommentContent", text);

        if (fileInput && fileInput.files[0]) {
            formData.append("file", fileInput.files[0]);
        }

        commentService.update(formData).then(() => {
            alert("댓글 수정이 완료되었습니다.");
            commentService.getList(page, skillLogId, memberId, commentLayout.showCommentList);
        });
        return;
    }

    // ----------------------------------------------------------
    // 11. 대댓글 수정 버튼
    // ----------------------------------------------------------
    if (target.closest(".btnEdit.devComtEditButton")) {
        const btn = target.closest(".btnEdit.devComtEditButton");
        const li = btn.closest("li");
        const devComtSection = li.querySelector(".devComtSection");
        const modifyComt = li.querySelector(".modify-comt");
        const writeBoxWrap = modifyComt?.querySelector(".writeBoxWrap");
        const pCont = li.querySelector("p.cont");
        const textarea = modifyComt?.querySelector("textarea");
        const uiPlaceholder = modifyComt?.querySelector(".uiPlaceholder");
        const ph1 = modifyComt?.querySelector(".ph_1");
        const ph2 = modifyComt?.querySelector(".ph_2");

        if (textarea && pCont) textarea.value = pCont.textContent.trim();
        if (writeBoxWrap) writeBoxWrap.classList.remove("case");
        if (uiPlaceholder) uiPlaceholder.classList.add("focus");
        if (ph1) ph1.style.display = "none";
        if (ph2) ph2.style.display = "none";

        if (devComtSection) devComtSection.style.display = "none";
        if (modifyComt) modifyComt.style.display = "block";
        if (writeBoxWrap) writeBoxWrap.style.display = "block";
        return;
    }

    // ----------------------------------------------------------
    // 12. 대댓글 수정 취소 버튼
    // ----------------------------------------------------------
    if (target.closest(".btnComtModifyCancel")) {
        const li = target.closest("li");
        const devComtSection = li.querySelector(".devComtSection");
        const modifyComt = li.querySelector(".modify-comt");

        if (devComtSection) devComtSection.style.display = "block";
        if (modifyComt) modifyComt.style.display = "none";
        return;
    }

    // ----------------------------------------------------------
    // 13. 대댓글 수정 등록 버튼
    // ----------------------------------------------------------
    if (target.closest(".btnSbm.devComtEditSubmitButton")) {
        const btn = target.closest(".btnSbm.devComtEditSubmitButton");
        const li = btn.closest("li");
        const parentLi = li.closest("li[id^='li']");
        const commentId = li.classList[0];
        const parentCommentId = parentLi.id.slice(2);
        const form = btn.closest("form");
        const text = form.querySelector("textarea")?.value;
        const fileInput = form.querySelector(".reply-file");

        const formData = new FormData();
        formData.append("id", Number(commentId));
        formData.append("skillLogCommentContent", text);

        if (fileInput && fileInput.files[0]) {
            formData.append("file", fileInput.files[0]);
        }

        commentService.update(formData).then(() => {
            alert("댓글 수정이 완료되었습니다.");
            commentService.getNestedList(nestedPage, skillLogId, parentCommentId, memberId, commentLayout.showNestedCommentList);
        });
        return;
    }

    // ----------------------------------------------------------
    // 14. 첨부파일 사진 버튼 / 삭제 버튼 (공통 핸들러)
    // ----------------------------------------------------------
    handleFileClick(e);
});

// ----------------------------------------------------------
// change 이벤트 위임 - 파일 첨부 (공통 핸들러)
// ----------------------------------------------------------
answerArea.addEventListener("change", handleFileChange);

// ----------------------------------------------------------
// input 이벤트 위임 - 글자 수 카운트 (공통 핸들러)
// ----------------------------------------------------------
answerArea.addEventListener("input", handleTextInput);

// ----------------------------------------------------------
// focusin 이벤트 위임 - textarea placeholder 처리 (공통 핸들러)
// ----------------------------------------------------------
answerArea.addEventListener("focusin", handleTextFocusin);

// ----------------------------------------------------------
// focusout - 외부 클릭 시 접기
// ----------------------------------------------------------
const wrappers = document.querySelectorAll(".writeBoxWrap.cmtWrite");
wrappers.forEach((wrapper) => {
    const textarea = wrapper.querySelector("textarea");

    const ph1 = wrapper.querySelector(".ph_1");
    const ph2 = wrapper.querySelector(".ph_2");
    const uiPlaceholder = wrapper.querySelector(".uiPlaceholder");

    if (!textarea) return;

    document.addEventListener("click", (e) => {
        const hasAttachment = wrapper.querySelector(".attach-wrap");

        if (
            e.target.tagName !== "TEXTAREA" &&
            !hasAttachment &&
            !textarea.value &&
            !e.target.classList.contains("icon-photo")
        ) {
            const emotionLayer = document.querySelector(".emotion-layer");
            if (!emotionLayer || !emotionLayer.classList.contains("open")) {
                wrapper.classList.add("case");
                if (uiPlaceholder) uiPlaceholder.classList.remove("focus");
                if (ph1) ph1.style.display = "block";
                if (ph2) ph2.style.display = "none";
            }
        }
    });
});