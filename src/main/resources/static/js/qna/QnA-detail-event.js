// 게시 시간 상대 표시
function timeForToday(datetime) {
    const today = new Date();
    const date = new Date(datetime.replace(" ", "T"));
    let gap = Math.floor((today.getTime() - date.getTime()) / 1000 / 60);
    if (gap < 1) return "방금 전";
    if (gap < 60) return `${gap}분 전`;
    gap = Math.floor(gap / 60);
    if (gap < 24) return `${gap}시간 전`;
    gap = Math.floor(gap / 24);
    if (gap < 31) return `${gap}일 전`;
    gap = Math.floor(gap / 31);
    if (gap < 12) return `${gap}개월 전`;
    gap = Math.floor(gap / 12);
    return `${gap}년 전`;
}

document.querySelectorAll(".devQnaCreatedTime").forEach(el => {
    const created = new Date(el.dataset.created.replace(" ", "T"));
    const updated = new Date(el.dataset.updated.replace(" ", "T"));
    const isModified = Math.floor(created.getTime() / 1000) !== Math.floor(updated.getTime() / 1000);
    const label = isModified ? "수정" : "작성";
    el.textContent = timeForToday(el.dataset.created) + " " + label;
});

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

console.log(reportFirstReasonRadio);
// 댓글에 신고버튼
const commentReportButtons = document.querySelectorAll(
    ".btnReport.devBtnReport",
);
// 댓글에 신고버튼 누를 시 신고창 띄우기
commentReportButtons.forEach((commentReportButton, i) => {
    commentReportButton.addEventListener("click", (e) => {
        if (qstnLikeButton && qstnLikeButton.dataset.loggedIn === "false") {
            location.href = "/main/log-in";
            return;
        }
        pressReportButton.style.display = "block";
        reportFirstReasonRadio[0].checked = true;
    });
});
// 신고하기 눌렀을 때 알림띄우기(confirm 사용 시 확인,취소 뜸)
reportSubmitButton.addEventListener("click", (e) => {
    const reportSubmitMessage = confirm(
        "신고된 글은 운영자에게 전달됩니다. 신고하시겠습니까?",
    );
    if (reportSubmitMessage) {
        alert("신고 처리 완료되었습니다.");
        location.href = "/QnA-detail.html";
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
        const clicked = e.target.closest(".devBtnReport");
        if (!clicked) return;
        if (clicked.dataset.loggedIn === "false") {
            location.href = "/main/log-in";
            return;
        }
        pressReportButton.style.display = "block";
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
if (writeButton) {
    writeButton.addEventListener("click", (e) => {
        writeButtonDiv.classList.toggle("tooltip-open");
    });
}

// URL 복사 클릭

// URL 복사 버튼
const URLCopy = document.querySelector(
    ".button.button-copy-url.button-popup-component",
);

// URL 복사 눌렀을 시 클래스 추가
const URLCopyLayer = document.querySelector(".url-copy-layer");
// 닫기 버튼
const URLCopyLayerBefore = document.querySelector(".button.button-close");

// 클릭 시 "attached" 클래스 토글
URLCopy.addEventListener("click", (e) => {
    document.getElementById("lbl_url_copy").value = window.location.href;
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

if (qstnLikeButton) {
    qstnLikeButton.addEventListener("click", (e) => {
        if (qstnLikeButton.dataset.loggedIn === "false") {
            alert("로그인이 필요합니다.");
            return;
        }
        const qnaId = qstnLikeButton.dataset.qstnNo;
        fetch("/qna/like", {
            method: "POST",
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: "qnaId=" + qnaId
        })
        .then(res => res.json())
        .then(data => {
            if (data.success) {
                qstnLikeButton.classList.toggle("on");
                qstnLikeButton.querySelector("em").textContent = data.likeCount;
            } else {
                alert(data.message || "로그인이 필요합니다.");
            }
        })
        .catch(() => {
            alert("로그인이 필요합니다.");
        });
    });
}

// 게시글 삭제
const qstnDeleteButton = document.querySelector(".devQstnDeleteButton");
if (qstnDeleteButton) {
    qstnDeleteButton.addEventListener("click", (e) => {
        if (!confirm("정말로 삭제하시겠습니까?")) return;
        const qnaId = qstnDeleteButton.dataset.qstnNo;
        fetch("/qna/delete", {
            method: "POST",
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: "qnaId=" + qnaId
        })
        .then(res => res.json())
        .then(data => {
            if (data.success) {
                location.href = "/qna/list";
            } else {
                alert(data.message || "삭제에 실패했습니다.");
            }
        });
    });
}

// // 댓글 좋아요(로그인)

// 댓글에 좋아요 버튼들
const chatLikeButtonList = document.querySelectorAll(
    ".answerArea li div button.btnHeart.qnaSpB.devBtnAnswerLike",
);

// 각 버튼 눌렀을 시 "active" 토글
chatLikeButtonList.forEach((chatLike) => {
    chatLike.addEventListener("click", (e) => {
        chatLike.classList.toggle("active");
    });
});

// 대댓글(로그인)

// 댓글 버튼들
const commentReplyButtonList = document.querySelectorAll(
    ".answerArea li div button.btnCmt.devBtnComtList",
);

commentReplyButtonList.forEach((btn) => {
    btn.addEventListener("click", (e) => {
        // 내가 누른 버튼이 속한 li 찾기
        const li = btn.closest("li");
        const commentSec = li.querySelector(".commentSec");
        btn.classList.toggle("active");
        if (btn.classList.contains("active")) {
            commentSec.style.display = "block";
        } else {
            commentSec.style.display = "none";
        }

        // 닫기 버튼 눌렀을 때 닫히기.
    });
});

const commentReplyCloseButtons = document.querySelectorAll(
    ".btnCmtClose.qnaSpA.qnaBtnClose",
);

commentReplyCloseButtons.forEach((closeBtn) => {
    closeBtn.addEventListener("click", (e) => {
        // 닫기 버튼이 속한 li 찾기
        const li = closeBtn.closest("li");
        const commentSec = li.querySelector(".commentSec");
        const btn = li.querySelector(".btnCmt.devBtnComtList");

        commentSec.style.display = "none";
        btn.classList.remove("active");
    });
});

// // 북마크 등록(로그인)
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

// // 요소 선택（비로그인）
// const buttons = document.querySelectorAll(".btnBx.devComtRoot button");
// textareaList = document.querySelectorAll("textarea")

// buttons.forEach((button) => {
//     button.addEventListener("click", (e) => {
//         alert("로그인 후 이용해주세요.");
//     });
// });

// textareaList.forEach((textarea) => {
//     textarea.addEventListener("click", (e) => {
//         alert("로그인 후 이용해주세요.");
//     });
// });

// 비로그인 시 로그인 페이지로 이동(클릭 이벤트)
// function loginHref() {
//     location.href = "";
// }

// // 신고 (비로그인)
// reportActiveButton.addEventListener("click", (e) => {
//     reportButton.classList.toggle("active");
//     reportButton.addEventListener("click", (e) => {
//         alert("로그인 후 이용하실 수 있습니다.");
//     });
// });

// // 좋아요(비로그인)
// const buttonLike = document.querySelector(".icon-like.qnaSpB.devQstnLike");
// const beforeLikeCount = document.querySelector(
//     ".icon-like.qnaSpB.devQstnLike em",
// );

// buttonLike.addEventListener("click", (e) => {
//     alert("로그인 후 이용하실 수 있습니다.");
// });

// // 북마크 등록（비로그인)
// const buttonBookMark = document.querySelector(
//     ".btnBookmark.qnaSpB.devQnaDetailBookmark",
// );

// buttonBookMark.addEventListener("click", (e) => {
//     alert("로그인 후 이용하실 수 있습니다.");
// });


// 첨부파일
const addFiles = document.querySelectorAll(".reply-file");

addFiles.forEach((addFile) => {
    const writeBoxWrap = addFile.closest(".writeBoxWrap");
    const addFileButton = writeBoxWrap?.querySelector(
        ".button.icon-photo.qnaSpB",
    );

    addFileButton?.addEventListener("click", (e) => {
        addFile.click();
    });

    addFile.addEventListener("change", (e) => {
        const [file] = e.target.files;
        if (!file) return;

        if (!writeBoxWrap) return;

        // 파일을 writeBoxWrap에 저장 (submit 시 사용)
        writeBoxWrap._selectedFile = file;

        const uiPlaceholder = writeBoxWrap.querySelector(".uiPlaceholder");
        const ph1 = writeBoxWrap.querySelector(".ph_1");
        const ph2 = writeBoxWrap.querySelector(".ph_2");

        writeBoxWrap.classList.remove("case");
        if (uiPlaceholder) uiPlaceholder.classList.add("focus");
        if (ph1) ph1.style.display = "none";

        const textarea = writeBoxWrap.querySelector("textarea");
        if (ph2) {
            ph2.style.display = textarea?.value ? "none" : "block";
        }

        const reader = new FileReader();
        reader.readAsDataURL(file);

        reader.addEventListener("load", (e) => {
            const path = e.target.result;

            if (!path.includes("image")) {
                alert("이미지 파일로 올려주세요.");
                return;
            }
            const imgPathInput = writeBoxWrap.querySelector(
                "#Img_Path, [id*='Img_Path']",
            );
            if (imgPathInput) imgPathInput.value = path;

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

            div.querySelector(".remove-button")?.addEventListener(
                "click",
                () => {
                    div.remove();
                    if (imgPathInput) imgPathInput.value = "";
                    writeBoxWrap._selectedFile = null;
                },
            );
        });

        e.target.value = "";
    });
});

// 기존 첨부 이미지 삭제 버튼 (서버 렌더링된 이미지) - 이벤트 위임
document.addEventListener("click", (e) => {
    const removeBtn = e.target.closest(".attach-wrap .remove-button");
    if (!removeBtn) return;
    const attachWrap = removeBtn.closest(".attach-wrap");
    const writeBoxWrap = removeBtn.closest(".writeBoxWrap");
    if (attachWrap) attachWrap.remove();
    if (writeBoxWrap) {
        writeBoxWrap._selectedFile = null;
        writeBoxWrap._imageRemoved = true;
    }
});

// 댓글 통합
const wrappers = document.querySelectorAll(".writeBoxWrap.cmtWrite");

wrappers.forEach((wrapper) => {
    const textarea = wrapper.querySelector("textarea");

    // ✅ wrapper 안에서 직접 찾기
    const ph1 = wrapper.querySelector(".ph_1");
    const ph2 = wrapper.querySelector(".ph_2");
    const uiPlaceholder = wrapper.querySelector(".uiPlaceholder");
    const textCount = wrapper.querySelector("span.byte b");

    if (!textarea) return;

    // 클릭 시(로그인)
    wrapper.addEventListener("click", (e) => {
        wrapper.classList.remove("case");
        if (uiPlaceholder) uiPlaceholder.classList.add("focus");
        if (ph1) ph1.style.display = "none";
        if (!textarea.value && ph2) {
            ph2.style.display = "block";
        }
    });

    // 글자 수 카운트(로그인)
    textarea.addEventListener("input", (e) => {
        const totalTextCount = textarea.value.length;
        if (textCount) textCount.innerHTML = `${totalTextCount}`;

        if (totalTextCount > 1000) {
            alert("최대 1000자까지 입력 가능합니다.");
            textarea.value = textarea.value.substring(0, 1001);
            textarea.focus();
        }

        // ph2 표시/숨김
        if (ph2) {
            ph2.style.display = textarea.value ? "none" : "block";
        }
    });

    // 외부 클릭(로그인)
    document.addEventListener("click", (e) => {
        if (
            e.target.tagName !== "TEXTAREA" &&
            !textarea.value
        ) {
            wrapper.classList.add("case");
            if (uiPlaceholder) uiPlaceholder.classList.remove("focus");
            if (ph1) ph1.style.display = "block";
            if (ph2) ph2.style.display = "none";
        }
    });
});

// 댓글 달기 버튼(로그인)
const replySubmitButton = document.querySelector(".btnSbm.devBtnAnswerWrite");
// 대댓글 달기 버튼
const replyOfReplySubmitButtons = document.querySelectorAll(
    ".btnSbm.devBtnComtWrite",
);

// 대댓글 달기(로그인)
replyOfReplySubmitButtons.forEach((replyOfReplySubmitButton) => {
    replyOfReplySubmitButton.addEventListener("click", async (e) => {
        const form = replyOfReplySubmitButton.closest("form");
        const qnaId = document.querySelector("input[name=qnaId]").value;
        const parent = form.querySelector("[name=qnaCommentParent]").value;
        const content = form.querySelector("textarea.devComtWrite").value;
        if (!content.trim()) { alert("내용을 입력해주세요."); return; }
        const formData = new FormData();
        formData.append("qnaId", qnaId);
        formData.append("qnaCommentContent", content);
        formData.append("qnaCommentParent", parent);
        const writeBox = form.closest(".writeBoxWrap");
        if (writeBox && writeBox._selectedFile) {
            formData.append("file", writeBox._selectedFile);
        }
        const res = await fetch("/qna/comment/write", { method: "POST", body: formData });
        const data = await res.json();
        if (data.success === false) { alert(data.message); return; }
        location.reload();
    });
});

// 댓글 달기(로그인)
if (replySubmitButton) {
    replySubmitButton.addEventListener("click", async (e) => {
        const form = replySubmitButton.closest("form");
        const qnaId = form.querySelector("[name=qnaId]").value;
        const content = form.querySelector("textarea[name=qnaCommentContent]").value;
        if (!content.trim()) { alert("내용을 입력해주세요."); return; }
        const formData = new FormData();
        formData.append("qnaId", qnaId);
        formData.append("qnaCommentContent", content);
        const writeBox = form.closest(".writeBoxWrap");
        if (writeBox && writeBox._selectedFile) {
            formData.append("file", writeBox._selectedFile);
        }
        const res = await fetch("/qna/comment/write", { method: "POST", body: formData });
        const data = await res.json();
        if (data.success === false) { alert(data.message); return; }
        location.reload();
    });
}

// 댓글/대댓글 삭제(로그인) - 이벤트 위임
document.addEventListener("click", async (e) => {
    const btn = e.target.closest(".devAnswerDeleteButton, .devBtnComtDelete");
    if (!btn) return;
    if (!confirm("정말로 댓글을 삭제하시겠습니까?")) return;
    const id = btn.dataset.id;
    const res = await fetch(`/qna/comment/delete?id=${id}`, { method: "DELETE" });
    const data = await res.json();
    if (data.success) location.reload();
    else alert(data.message || "삭제에 실패했습니다.");
});

// 댓글 수정(로그인)
const modifyAnswerButtons = document.querySelectorAll(
    ".btnEdit.devAnswerEditButton",
);

modifyAnswerButtons.forEach((modifyAnswerButton) => {
    modifyAnswerButton.addEventListener("click", (e) => {
        // 버튼이 속한 li 찾기
        const li = modifyAnswerButton.closest("li");

        // li 안에서 요소 찾기
        const contSec = li.querySelector(".contSec.devContSection");
        const modifyContSec = li.querySelector(".contSec.modify-answer");

        // 기존 텍스트 가져오기
        const modifyText = contSec.querySelector("p.cont")?.textContent || "";

        // 수정 폼의 textarea에 텍스트 넣기
        const modifyTextarea = modifyContSec.querySelector("textarea");
        if (modifyTextarea) {
            modifyTextarea.value = modifyText;

            // 글자수 카운터 업데이트
            const byteCounter = modifyContSec.querySelector(".byte b");
            if (byteCounter) {
                byteCounter.textContent = modifyText.length;
            }
        }

        // ✅ placeholder span 숨기기
        const ph1 = modifyContSec.querySelector(".ph_1");
        const ph2 = modifyContSec.querySelector(".ph_2");
        if (ph1) ph1.style.display = "none";
        if (ph2) ph2.style.display = "none";

        // 원래 내용 숨기고, 수정 폼 표시
        contSec.style.display = "none";
        modifyContSec.style.display = "block";
    });
});
// 대댓글의 내댓글 수정(로그인)
const myComtUpdateButtons = document.querySelectorAll(
    ".btnEdit.devComtEditButton",
);

myComtUpdateButtons.forEach((myComtUpdateButton) => {
    myComtUpdateButton.addEventListener("click", (e) => {
        const li = myComtUpdateButton.closest("li");
        if (!li) return;

        const devComtSection = li.querySelector(".devComtSection");
        const modifyComt = li.querySelector(".modify-comt");
        const writeBoxWrap = modifyComt?.querySelector(".writeBoxWrap");
        const pCont = li.querySelector("p.cont");
        const textarea = modifyComt?.querySelector("textarea");
        const uiPlaceholder = modifyComt?.querySelector(".uiPlaceholder");
        const ph1 = modifyComt?.querySelector(".ph_1");
        const ph2 = modifyComt?.querySelector(".ph_2");

        // textarea에 기존 텍스트 복사
        if (textarea && pCont) {
            textarea.value = pCont.textContent.trim();
        }

        // case 클래스 제거, focus 클래스 추가
        if (writeBoxWrap) writeBoxWrap.classList.remove("case");
        if (uiPlaceholder) uiPlaceholder.classList.add("focus");
        if (ph1) ph1.style.display = "none";
        if (ph2) ph2.style.display = "none";

        // 표시 전환
        if (devComtSection) devComtSection.style.display = "none";
        if (modifyComt) modifyComt.style.display = "block";
        if (writeBoxWrap) writeBoxWrap.style.display = "block";

        // 취소 버튼
        const cancelBtn = modifyComt?.querySelector(".btnComtModifyCancel");
        cancelBtn?.addEventListener("click", (e) => {
            if (devComtSection) devComtSection.style.display = "block";
            if (modifyComt) modifyComt.style.display = "none";
        });
    });
});

// ✅ 취소 버튼 이벤트 (로그인)
const modifyCancelButtons = document.querySelectorAll(
    ".btnModifyCancel.qnaSpB",
);

modifyCancelButtons.forEach((modifyCancelButton) => {
    modifyCancelButton.addEventListener("click", (e) => {
        const li = modifyCancelButton.closest("li");
        const contSec = li.querySelector(".contSec.devContSection");
        const modifyContSec = li.querySelector(".contSec.modify-answer");

        contSec.style.display = "block";
        modifyContSec.style.display = "none";
    });
});

// ✅ 댓글/대댓글 수정 등록 - 이벤트 위임
document.addEventListener("click", async (e) => {
    const btn = e.target.closest(".devAnswerEditSubmitButton, .devComtEditSubmitButton");
    if (!btn) return;
    const id = btn.dataset.id;
    const container = btn.closest(".modify-answer, .modify-comt");
    const textarea = container ? container.querySelector("textarea") : null;
    if (!textarea) return;
    const content = textarea.value;
    if (!content.trim()) { alert("내용을 입력해주세요."); return; }
    const writeBox = container.querySelector(".writeBoxWrap");
    const formData = new FormData();
    formData.append("id", id);
    formData.append("qnaCommentContent", content);
    if (writeBox && writeBox._imageRemoved) {
        formData.append("removeFile", "true");
    }
    if (writeBox && writeBox._selectedFile) {
        formData.append("file", writeBox._selectedFile);
    }
    const res = await fetch("/qna/comment/update", { method: "PUT", body: formData });
    const data = await res.json();
    if (data.success) location.reload();
    else alert(data.message || "수정에 실패했습니다.");
});
