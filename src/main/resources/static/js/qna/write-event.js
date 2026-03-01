// 질문·글쓰기 버튼 → create-menu 토글
const createMenu = document.querySelector(".navi-top-area .create-menu");
const btnQuestion = document.querySelector(".btn-question");

btnQuestion.addEventListener("click", (e) => {
    createMenu.classList.toggle("tooltip-open");
});

//체크박스-드롭다운 연동
//체크박스와 드롭다운 매칭 (체크 해제 시 드롭다운 비활성화)
const checkboxLis = document.querySelectorAll(
    ".checkListArea .checkboxCommWrap > li",
);
const selectListArea = document.querySelector(".selectListArea");
const dropdownLis = selectListArea.querySelectorAll(":scope > ul > li");

checkboxLis.forEach((checkboxLi, index) => {
    const checkbox = checkboxLi.querySelector('input[type="checkbox"]');
    const dropdownLi = dropdownLis?.[index];
    const dropdown = dropdownLi.querySelector(".btn-select");

    if (checkbox && dropdown) {
        // 체크박스 변경 이벤트
        checkbox.addEventListener("change", () => {
            dropdown.classList.toggle("on", checkbox.checked);
        });

        // 초기 상태 동기화
        dropdown.classList.toggle("on", checkbox.checked);
    }
});

// 직무선배 체크박스(로그인)
const firstCheckButton =
    document.getElementById("lb_targetCheck2").nextElementSibling;
const jobCheckDrop = document.querySelector(
    ".btn-select.qnaSpA.devQnaWriteBizJobtypeDropDownButton.devQnaWriteBizJobtypeLayer",
);
const jobCheckDropDown = document.querySelector(
    ".layer-box-wrap.job.innerScroll",
);
const jobCheckDropDownCancelButton = document.querySelector(
    ".btn-layer-close.qnaSpB.devQnaWriteBizJobtypeLayerClose",
);
const searchDetailButton = document.querySelector(
    ".btn-direct-search.qnaSpA.devQnaWriteBizJobtypeDirectSearchButton",
);
const searchDetailButtonResult = document.querySelector(
    ".layer-box-wrap.job-directInput",
);
const jobToggleSelected = document.querySelectorAll(".tab.qnaSpA");

const tabMenuClassOnDiv = document.querySelector(".category-group");

const tabMenuCancelButton = document.querySelector(
    ".btn-layer-close.qnaSpB.devQnaWriteBizJobtypeDirectSearchLayerClose",
);

const recentSearchTab = document.querySelector(
    ".layer-cont.devRecentSearchBizJobtypeEmpty",
);

const applyJobButton = document.querySelector(
    ".btn-apply.devQnaWriteBizJobtypeApplyButton",
);

// 직무선배 버튼 이벤트(로그인)
firstCheckButton.addEventListener("click", (e) => {
    jobCheckDrop.classList.toggle("on");
});

jobCheckDrop.addEventListener("click", (e) => {
    // 다른 드롭다운 닫기
    document
        .querySelector(".layer-box-wrap.corporation.innerScroll")
        .classList.remove("open");
    // 직무선배 드롭다운 열기/닫기
    jobCheckDropDown.classList.toggle("open");
});

jobCheckDropDownCancelButton.addEventListener("click", (e) => {
    jobCheckDropDown.classList.remove("open");
});

tabMenuCancelButton.addEventListener("click", (e) => {
    jobCheckDropDown.classList.remove("open");
    searchDetailButtonResult.classList.remove("open");
});

searchDetailButton.addEventListener("click", (e) => {
    searchDetailButtonResult.classList.toggle("open");
});

const myJobToggle = document.querySelector(
    ".subtitle-wrap.has-tooltip.devQnaWriteBizJobtypeLayerTooltip",
);
if (myJobToggle) {
    const myJobButton = myJobToggle.querySelector("button");
    if (myJobButton) {
        myJobButton.addEventListener("click", (e) => {
            myJobToggle.classList.toggle("tooltip-open");
        });
    }
}


const layerContJob = document.querySelector(
    ".layer-cont.devRecentSearchBizJobtype",
);

// 전체삭제 버튼 클릭 이벤트(서버쪽 해야할 코드)
document.addEventListener("click", (e) => {
    if (e.target.closest(".btnAllDel.devQnaWriteBizJobtypeAllDeleteButton")) {
        const layerContJob = document.querySelector(
            ".layer-cont.devRecentSearchBizJobtype",
        );
        const emptySearchResult = document.querySelector(
            ".layer-cont.devRecentSearchBizJobtypeEmpty",
        );

        // searchList 안의 내용 전부 삭제!
        const searchList = layerContJob.querySelector(".searchList");
        searchList.innerHTML = "";

        layerContJob.style.display = "none";
        emptySearchResult.style.display = "block";
    }
});
// 적용 버튼
const jobApplyButton = document.querySelector(
    ".btn-apply.devQnaWriteBizJobtypeApplyButton",
);

// 변수에 담아놓고 사용(로그인)
let tempSelect = null;

jobToggleSelected.forEach((jobToggleSelected) => {
    jobToggleSelected.addEventListener("click", (e) => {
        if (tempSelect) {
            tempSelect.classList.remove("selected");
        }
        tempSelect = jobToggleSelected;
        jobToggleSelected.classList.add("selected");
    });
});



const corpSeniorDropdownDiv = document.querySelector(
    ".layer-box-wrap.corporation.innerScroll",
);
const corpSeniorDropdown = document.querySelector(
    ".btn-select.qnaSpA.devQnaWriteCompanyLayer.devQnaWriteCompanyDropDownButton ",
);

corpSeniorDropdown.addEventListener("click", (e) => {
    // 다른 드롭다운 닫기
    document
        .querySelector(".layer-box-wrap.job.innerScroll")
        .classList.remove("open");
    document
        .querySelector(".layer-box-wrap.job-directInput")
        .classList.remove("open");
    // 기업선배 드롭다운 열기
    corpSeniorDropdownDiv.classList.add("open");
});

const corpSeniorDropdownCancelButton = corpSeniorDropdownDiv.querySelector(
    ".btn-layer-close.qnaSpB.devQnaWriteCompanyLayerClose",
);
corpSeniorDropdownCancelButton.addEventListener("click", (e) => {
    corpSeniorDropdownDiv.classList.remove("open");
});

const corpInputDiv = document.querySelector(
    ".jkSchInput.keywordSearch.devQnaWriteCompanyLayerKeyworkSearchDiv ",
);
const corpInput = corpInputDiv.querySelector(
    ".schInp.devQnaWriteCompanyLayerSearchInput",
);
const corpSearchDiv = corpInputDiv.querySelector(".devQnaWriteCompanyLayerSearchDiv");
const corpSearchUl = corpSearchDiv.querySelector("ul");

// 4개 높이(약 44px * 4 = 176px)로 스크롤 제한
corpSearchUl.style.maxHeight = "176px";
corpSearchUl.style.overflowY = "auto";

corpInput.addEventListener("keyup", (e) => {
    corpInputDiv.classList.toggle("focus", !!corpInput.value);

    const keyword = corpInput.value.trim();
    if (!keyword) {
        corpSearchDiv.style.display = "none";
        corpSearchUl.innerHTML = "";
        return;
    }

    fetch(`/qna/search-company?keyword=${encodeURIComponent(keyword)}`)
        .then(res => res.json())
        .then(data => {
            corpSearchUl.innerHTML = "";
            if (data.length === 0) {
                corpSearchDiv.style.display = "none";
                return;
            }
            data.forEach(corp => {
                const lowerName = corp.corpName.toLowerCase();
                const lowerKeyword = keyword.toLowerCase();
                const idx = lowerName.indexOf(lowerKeyword);
                const before = idx !== -1 ? corp.corpName.substring(0, idx) : corp.corpName;
                const matched = idx !== -1 ? corp.corpName.substring(idx, idx + keyword.length) : "";
                const after = idx !== -1 ? corp.corpName.substring(idx + keyword.length) : "";
                const li = document.createElement("li");
                li.classList.add("devQnaWriteCompanyRecentItem");
                li.dataset.bizName = corp.corpName;
                li.innerHTML = `<button type="button" class="qnaSpB"><span>${before}</span><span style="color:#006e3f">${matched}</span><span>${after}</span></button>`;
                li.querySelector("button").addEventListener("click", () => {
                    selectCorpItem(corp.corpName);
                });
                corpSearchUl.appendChild(li);
            });
            corpSearchDiv.style.display = "block";
        });
});

function selectCorpItem(corpName) {
    if (corpTriggerBtn) corpTriggerBtn.textContent = corpName;
    document.getElementById("hiddenCompanyName").value = corpName;
    if (layerBoxCorp) layerBoxCorp.classList.remove("open");
    if (corpCheckboxForQuick) corpCheckboxForQuick.checked = true;
    if (corpTriggerBtn) corpTriggerBtn.classList.add("on");
    corpSearchDiv.style.display = "none";
    corpSearchUl.innerHTML = "";
    corpInput.value = "";
    // 최근검색 저장 및 UI 반영
    addCorpToRecentSearch(corpName);
}
// X 버튼(공용)
const btnLayerCloses = document.querySelectorAll(".btn-layer-close.qnaSpB");
// // 링크
//
// // 링크 버튼
// const iconBtnLayerOpen = document.querySelector(
//     ".icon-link.qnaSpB.btn-layer-open",
// );
// // 링크 레이어
// const linkLayer = document.querySelector(".layer-box-wrap.link-layer");
//
// // 링크 레이어 X버튼
// btnLayerCloses.forEach((btnLayerClose) => {
//     btnLayerClose.addEventListener("click", (e) => {
//         if (linkLayer) linkLayer.classList.remove("open");
//         if (iconBtnLayerOpen) iconBtnLayerOpen.classList.remove("on");
//     });
// });
//
// // 링크 클릭
// if (iconBtnLayerOpen) {
//     iconBtnLayerOpen.addEventListener("click", (e) => {
//         if (linkLayer) linkLayer.classList.toggle("open");
//         iconBtnLayerOpen.classList.toggle("on");
//     });
// }
//
// const urlRegex = /^(http|https):\/\/[\w\-]+(\.[\w\-]+)+[/#?]?.*$/;
//
// const inputText = document.querySelector(
//     ".jkSchInput.keywordSearch.keywordSearchLink p input[type=text]",
// );
// const findButton = document.querySelector(".btnSch.qnaSpB.devSearchLink");
//
// // 로딩
// const loading = document.querySelector(".loading");
//
// // url 검사 함수 (중복 코드 방지)
// function checkUrl() {
//     if (urlRegex.test(inputText.value)) {
//         loading.style.display = "block";
//         setTimeout(() => {
//             loading.style.display = "none";
//             const a = document.querySelector(".ApiOpenGraphResult");
//             a.innerHTML = `
//                 <a href="#" class="attach-box type-link">
//                     <div class="thumb-img-area">
//                         <span>링크</span>
//                     </div>
//                     <div class="link-textarea">
//                         <textarea class="corp-name" name="" id="devAttachLinkTitle" cols="20" rows="5" maxlength="51" placeholder="링크 제목을 입력하세요."></textarea>
//                     </div>
//                 </a>
//             `;
//         }, 1800);
//     } else {
//         alert("URL을 입력해주세요");
//     }
// }
//
// // 버튼 클릭
// findButton.addEventListener("click", checkUrl);
//
// // 엔터키 입력
// inputText.addEventListener("keydown", (e) => {
//     if (e.key === "Enter") {
//         checkUrl();
//     }
// });

// 링크 첨부하기 버튼 활성화

// 링크 제목 입력창
const linkTitleInput = document.querySelector(".ApiOpenGraphResult");
// 첨부하기 버튼
const attachButton = document.querySelector(".apply.attachLinkBtn");

// 입력할 때마다 체크
if (linkTitleInput) {
    linkTitleInput.addEventListener("input", (e) => {
        // 링크 제목에 들어갈 변수 담기
        if (e.target.value) {
            if (attachButton) attachButton.classList.add("on");
        } else {
            if (attachButton) attachButton.classList.remove("on");
        }
    });
}

// 링크 추가
const textarea = document.querySelector(".addFileAndLink");
if (attachButton) {
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
        if (a) a.innerHTML = "";
        if (getText) getText.value = "";
        if (getURL) getURL.value = "";

        textarea.addEventListener("click", (e) => {
            if (e.target.classList.contains("remove-button")) {
                console.log("들어옴");
                e.target.closest(".attach-wrap").remove();
            }
        });
    });
}

// 링크 삭제
const removeLink = document.querySelector(".attach-box.type-link");

// 이미지 버튼
const addPicture = document.querySelector(".icon-photo.qnaSpB.btn-layer-open");
// 숨겨진 파일 input
const photoInput = document.getElementById("photoInput");

// 파일 누적 관리용 DataTransfer
const dataTransfer = new DataTransfer();

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

    // DataTransfer에 파일 누적 후 photoInput.files 갱신
    dataTransfer.items.add(file);
    photoInput.files = dataTransfer.files;

    const reader = new FileReader();
    reader.readAsDataURL(file);

    reader.addEventListener("load", (v) => {
        const path = v.target.result;
        const fileName = file.name;

        // 이미지를 화면에 추가 (data-file-name으로 삭제 시 매칭)
        textarea.innerHTML += `
            <div class="attach-wrap attach-image" data-file-name="${fileName}">
                <div class="attach-box type-image">
                    <img src="${path}" alt="첨부 이미지">
                </div>
                <button type="button" class="remove-button qnaSpB">삭제하기</button>
            </div>
        `;
    });
});

// 이미지 삭제 (이벤트 위임) - DataTransfer에서도 제거
textarea.addEventListener("click", (e) => {
    if (e.target.classList.contains("remove-button")) {
        const wrap = e.target.closest(".attach-wrap");
        const fileName = wrap.dataset.fileName;

        // attach-image(파일 첨부)인 경우 DataTransfer에서도 제거
        if (wrap.classList.contains("attach-image") && fileName) {
            const newDt = new DataTransfer();
            for (const f of dataTransfer.files) {
                if (f.name !== fileName) {
                    newDt.items.add(f);
                }
            }
            dataTransfer.items.clear();
            for (const f of newDt.files) {
                dataTransfer.items.add(f);
            }
            photoInput.files = dataTransfer.files;
        }

        wrap.remove();
    }
});

// 드롭다운 버튼 (선택 결과 표시)
const jobSelectResult = document.querySelector(
    ".btn-select.qnaSpA.devQnaWriteBizJobtypeDropDownButton",
);

// 직무선배 체크박스 (적용 버튼용)
const jobCheckboxForApply = document.getElementById("lb_targetCheck2");

jobApplyButton.addEventListener("click", (e) => {
    const selectedRadio = document.querySelector(
        'input[name="jobCategorySmallId"]:checked',
    );

    if (selectedRadio) {
        const selectedItem = selectedRadio.closest(".devBizJobtypeItem");
        const jobName = selectedItem.dataset.bizjobtypeName;
        const jobCode = selectedItem.dataset.bizjobtypeCode;

        // 기존 코드 (드롭다운 버튼에 적용)
        jobSelectResult.dataset.bizjobtypeName = jobName;
        jobSelectResult.dataset.bizjobtypeCode = jobCode;
        jobSelectResult.textContent = jobName;

        // 폼 hidden input에 값 세팅
        document.getElementById("hiddenJobCategorySmallId").value = jobCode;
        document.getElementById("hiddenJobCategoryName").value = jobName;

        // 최근검색에 저장 및 UI 반영
        addJobToRecentSearch(jobName, jobCode);

        if (jobCheckboxForApply) {
            jobCheckboxForApply.checked = true;
        }
        jobSelectResult.classList.add("on");
        jobCheckDropDown.classList.remove("open");
        searchDetailButtonResult.classList.remove("open");
    } else {
        alert("직무를 선택해주세요");
    }
});

// ── 직무선배 최근검색 localStorage 관리 ──────────────────────────────
const JOB_RECENT_KEY = "qna_recent_job";

function getJobRecentList() {
    try {
        return JSON.parse(localStorage.getItem(JOB_RECENT_KEY)) || [];
    } catch (e) {
        return [];
    }
}

function saveJobRecentList(list) {
    localStorage.setItem(JOB_RECENT_KEY, JSON.stringify(list));
}

function addJobToRecentSearch(jobName, jobCode) {
    let list = getJobRecentList();
    // 중복 제거 (같은 code 항목 제거 후 맨 앞에 추가)
    list = list.filter(item => item.code !== String(jobCode));
    list.unshift({ name: jobName, code: String(jobCode) });
    // 최대 4개
    if (list.length > 4) list = list.slice(0, 4);
    saveJobRecentList(list);
    renderJobRecentSearch(list);
}

function renderJobRecentSearch(list) {
    const layerContJob = document.querySelector(".layer-cont.devRecentSearchBizJobtype");
    const emptyDiv = document.querySelector(".layer-cont.devRecentSearchBizJobtypeEmpty");
    const searchList = layerContJob ? layerContJob.querySelector(".searchList") : null;

    if (!layerContJob || !emptyDiv || !searchList) return;

    if (list.length === 0) {
        layerContJob.style.display = "none";
        emptyDiv.style.display = "block";
        return;
    }

    layerContJob.style.display = "block";
    emptyDiv.style.display = "none";

    searchList.innerHTML = "";
    // 4개 초과 시 스크롤 (4개 기준 높이: 약 44px * 4)
    searchList.style.maxHeight = "176px";
    searchList.style.overflowY = list.length > 4 ? "auto" : "";

    list.forEach(item => {
        const li = document.createElement("li");
        li.className = "devBizJobtypeMyItem";
        li.dataset.bizjobtypeName = item.name;
        li.dataset.bizjobtypeCode = item.code;
        li.innerHTML = `<button type="button" class="qnaSpB">${item.name}</button>`;
        searchList.appendChild(li);
    });
}

// 페이지 진입 시 직무선배 최근검색 렌더링
renderJobRecentSearch(getJobRecentList());

// 직무선배 전체삭제 → localStorage도 클리어
document.querySelector(".btnAllDel.devQnaWriteBizJobtypeAllDeleteButton")
    ?.addEventListener("click", () => {
        saveJobRecentList([]);
        // UI는 기존 전체삭제 핸들러(document.addEventListener)가 처리
    });
// 탭 메뉴 (직무/산업)
const tabMenuButtons = document.querySelectorAll(
    ".tab-box-wrap > .tab-menu > li.tab",
);
const categoryGroups = document.querySelectorAll(
    ".tab-box-wrap > .category-group",
);

tabMenuButtons.forEach((tabMenuButton, index) => {
    tabMenuButton.addEventListener("click", (e) => {
        tabMenuButtons.forEach((btn) => btn.classList.remove("selected"));
        tabMenuButton.classList.add("selected");

        categoryGroups.forEach((group, i) => {
            group.classList.toggle("on", i === index);

            if (i === index) {
                const categoryButtons = group.querySelectorAll(".tab.qnaSpA");
                categoryButtons.forEach((btn) =>
                    btn.classList.remove("selected"),
                );
                if (categoryButtons[0])
                    categoryButtons[0].classList.add("selected");

                const contLists = group.querySelectorAll(".cont-list");
                contLists.forEach((list) => {
                    list.classList.remove("attached");
                    list.style.display = "none";
                });

                const blankBox = group.querySelector(".blank-box-wrap");
                if (blankBox) blankBox.style.display = "block";

                group
                    .querySelectorAll('input[type="radio"]')
                    .forEach((r) => (r.checked = false));
            }
        });
    });
});

// 카테고리 버튼 클릭
categoryGroups.forEach((group) => {
    const categoryButtons = group.querySelectorAll(".tab.qnaSpA");
    const contLists = group.querySelectorAll(".cont-list");
    const blankBox = group.querySelector(".blank-box-wrap");
    let tempSelectRadio = null;

    categoryButtons.forEach((btn, index) => {
        btn.addEventListener("click", (e) => {
            categoryButtons.forEach((b) => b.classList.remove("selected"));
            btn.classList.add("selected");

            if (blankBox) blankBox.style.display = "none";

            contLists.forEach((list, i) => {
                list.style.display = i === index ? "block" : "none";
                list.classList.toggle("attached", i === index);
            });

            tempSelectRadio = null;
            group
                .querySelectorAll('input[type="radio"]')
                .forEach((r) => (r.checked = false));
        });
    });

    group.querySelectorAll('input[type="radio"]').forEach((radio) => {
        radio.addEventListener("change", () => {
            if (tempSelectRadio && tempSelectRadio !== radio)
                tempSelectRadio.checked = false;
            tempSelectRadio = radio;
        });
    });
});

// 나의직무 / 최근검색 바로 적용
// 직무선배
const layerBoxJob = document.querySelector(".layer-box-wrap.job.innerScroll");
// 드롭다운 트리거 버튼
const jobTriggerBtn = document.querySelector(
    ".btn-select.devQnaWriteBizJobtypeDropDownButton",
);
// 직무선배 체크박스
const jobCheckboxForQuick = document.getElementById("lb_targetCheck2");

// 이벤트 위임: 동적으로 추가되는 최근검색 항목도 처리
layerBoxJob.addEventListener("click", (e) => {
    const btn = e.target.closest(".searchList .devBizJobtypeMyItem button.qnaSpB");
    if (!btn) return;

    const item = btn.closest(".devBizJobtypeMyItem");
    const jobName = item.dataset.bizjobtypeName;
    const jobCode = item.dataset.bizjobtypeCode;

    // 드롭다운 버튼 텍스트/dataset 변경
    if (jobTriggerBtn) {
        jobTriggerBtn.textContent = jobName;
        jobTriggerBtn.dataset.bizjobtypeName = jobName;
        jobTriggerBtn.dataset.bizjobtypeCode = jobCode;
    }

    // 폼 hidden input에 값 세팅
    document.getElementById("hiddenJobCategorySmallId").value = jobCode;
    document.getElementById("hiddenJobCategoryName").value = jobName;

    // 모달 닫기
    layerBoxJob.classList.remove("open");

    // 직무선배 체크박스 체크 + 드롭다운 활성화
    if (jobCheckboxForQuick) { jobCheckboxForQuick.checked = true; }
    if (jobTriggerBtn) { jobTriggerBtn.classList.add("on"); }
});

// 기업선배 드롭다운
const layerBoxCorp = document.querySelector(
    ".layer-box-wrap.corporation.innerScroll",
);
// 최근검색 버튼들
const corpQuickSelectButtons = layerBoxCorp.querySelectorAll(
    ".searchList .qnaSpB",
);
// 드롭다운 트리거 버튼
const corpTriggerBtn = document.querySelector(
    ".btn-select.devQnaWriteCompanyDropDownButton",
);
// 기업선배 체크박스
const corpCheckboxForQuick = document.getElementById("lb_targetCheck3");

// 기업선배 최근검색: 이벤트 위임 방식으로 동적 항목도 처리
layerBoxCorp.addEventListener("click", (e) => {
    const btn = e.target.closest(".devRecentSearchCompany .searchList .devQnaWriteCompanyRecentItem button.qnaSpB");
    if (!btn) return;
    const item = btn.closest(".devQnaWriteCompanyRecentItem");
    const corpName = item ? item.dataset.bizName : btn.textContent.trim();
    selectCorpItem(corpName);
});

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
    const currentPoint = parseInt(document.getElementById("currentPoint")?.textContent || "0", 10);
    if (currentPoint < 5) {
        alert("보유 별이 부족합니다. (필요: 5개, 보유: " + currentPoint + "개)");
        return;
    }
    if (!confirm("별 5개가 소모됩니다. 등록 하시겠습니까?")) {
        return;
    }
    // 폼 제출 직전에 현재 선택된 카테고리를 hidden input에 최종 세팅
    const checkedRadio = document.querySelector('input[name="jobCategorySmallId"]:checked');
    if (checkedRadio) {
        const item = checkedRadio.closest(".devBizJobtypeItem");
        document.getElementById("hiddenJobCategorySmallId").value = item.dataset.bizjobtypeCode;
        document.getElementById("hiddenJobCategoryName").value = item.dataset.bizjobtypeName;
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
    location.href = "";
});

// ── 기업선배 최근검색 localStorage 관리 ──────────────────────────────
const CORP_RECENT_KEY = "qna_recent_corp";

function getCorpRecentList() {
    try {
        return JSON.parse(localStorage.getItem(CORP_RECENT_KEY)) || [];
    } catch (e) {
        return [];
    }
}

function saveCorpRecentList(list) {
    localStorage.setItem(CORP_RECENT_KEY, JSON.stringify(list));
}

function addCorpToRecentSearch(corpName) {
    let list = getCorpRecentList();
    // 중복 제거 (같은 이름 제거 후 맨 앞에 추가)
    list = list.filter(item => item.name !== corpName);
    list.unshift({ name: corpName });
    // 최대 4개
    if (list.length > 4) list = list.slice(0, 4);
    saveCorpRecentList(list);
    renderCorpRecentSearch(list);
}

function renderCorpRecentSearch(list) {
    const layerContCorp = document.querySelector(".layer-cont.devRecentSearchCompany");
    const emptyDiv = document.querySelector(".layer-cont.devRecentSearchCompanyEmpty");
    const searchList = layerContCorp ? layerContCorp.querySelector(".searchList") : null;

    if (!layerContCorp || !emptyDiv || !searchList) return;

    if (list.length === 0) {
        layerContCorp.style.display = "none";
        emptyDiv.style.display = "block";
        return;
    }

    layerContCorp.style.display = "block";
    emptyDiv.style.display = "none";

    searchList.innerHTML = "";
    // 4개 초과 시 스크롤 (4개 기준 높이: 약 44px * 4)
    searchList.style.maxHeight = "176px";
    searchList.style.overflowY = list.length > 4 ? "auto" : "";

    list.forEach(item => {
        const li = document.createElement("li");
        li.className = "devQnaWriteCompanyRecentItem";
        li.dataset.bizName = item.name;
        li.innerHTML = `<button type="button" class="qnaSpB">${item.name}</button>`;
        searchList.appendChild(li);
    });
}

// 페이지 진입 시 기업선배 최근검색 렌더링
renderCorpRecentSearch(getCorpRecentList());

// 기업선배 전체삭제
document.querySelector(".btnAllDel.devQnaWriteCompanyAllDeleteButton")
    ?.addEventListener("click", () => {
        saveCorpRecentList([]);
        renderCorpRecentSearch([]);
    });
