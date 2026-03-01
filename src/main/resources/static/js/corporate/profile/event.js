NodeList.prototype.filter = Array.prototype.filter;
NodeList.prototype.map = Array.prototype.map;

// 함수
// input 값 포맷팅 (날짜, 전화번호 등)
const formatInputValue = (value, pattern) => {
    let index = 0;
    const result = pattern
        .map((len) => {
            const result = value.slice(index, index + len);
            index += len;
            return result;
        })
        .join("-");
    return result || value;
};

// 변수
// 로고 관련
const logoUploadButton = document.getElementById("devLogoUp");
const logoUploadModal = document.querySelector(".popLogoUpload");
const logoDeleteModal = document.querySelector(".popLogoDel");
const logoUploadCloseButton = document.getElementById("devCloseLayer");
const logoDeleteCloseButton = document.getElementById("devCloseDelLayer");
const selectLogoInput = document.getElementById("devSelectLogo");
const selectLogoLabel = document.querySelector("#devSelectLogoDelegator label");
const logoUploadSubmitButton = document.getElementById("devSubmit");
const logoContainer = document.querySelector(".tbCell.tbLogo");
const logoDeleteButton = document.getElementById("devDeleteLogo");
const logoUploadLayout = document.querySelector(".tbCell.tbLogo");

// 입력 필드
const companyBasicinputs = document.querySelectorAll(".artCoBasic input");
const textareas = document.querySelectorAll("textarea");

// 기업형태
const typeSelectButton = document.getElementById("devCoType");
const typeSelectList = document.querySelector(".selTypeList");
const companyTypeInput = document.getElementById("Company_Type");
const companyTypeResultSpan = document.getElementById("Company_Name");
const nationLayer = document.getElementById("devForeignCoArea");
const nationInput = document.getElementById("devNation");
const nationSearchList = document.getElementById("devNationSearchResult");
const nationSearchContainer = document.getElementById(
    "devNationSearchContainer",
);

// 업종
const industrySelectButton = document.querySelector(".tbPart .btnSelType");
const industryCategoryList = document.querySelector(
    ".tbPart .selTypeList ul:first-child",
);
const industryPartLists = document.querySelectorAll(".tbPart .devPartList");
const industryApplyButtons = document.querySelector(".tbPart .selTypeBtn");
const industryInput = document.getElementById("Industry_Code");
const industrySmallInput = document.getElementById("Industry_Small_Code");
const industryResultSpan = document.getElementById("Industry_Name");

// 주소
const addressSearchButton = document.getElementById("devSearchAddr");
const addressSearchLayer = document.getElementById("devAddrMngWrap");
const addressLayerCloseButton = document.getElementById("devCloseAddrLayer");
const addressSearchPostCodeButton =
    document.getElementById("searchPostCodeBtn");
const addressResultSpan = document.getElementById("devAddr");
const addressInputs = document.querySelectorAll(".popAddr input[type=text]");
const addressLayer = document.getElementById("devAddrArea");
const foreignAddressLayer = document.getElementById("devForeignAddrArea");

// 연혁/개요
const historyTextareaLayer = document.querySelector(
    ".artCoHistory .mtcTxaType",
);
const overviewTextareaLayer = document.querySelector(
    ".artCoOverview .mtcTxaType",
);

// 복리후생
const moreWelfareInfoButton = document.getElementById("devWlfrInfo");
const welfareModalLayer = document.querySelector(".giWelfareModal");
const welfareModalCloseButton = document.querySelector(
    ".giWelfareModal .btnModalClose",
);
const welfareModalResultButtons = document.querySelector(
    ".giWelfareModal .regPrefWrap .buttons",
);
const welfareModalList = document.querySelectorAll(
    ".giWelfareModal .scrlBx .fieldList ul li",
);
const previewWelfareList = document.querySelector(".devPreviewList");
const welfareDirectInputSection = document.querySelector(
    ".seflAddInpWrap.devDirectInput",
);
const previewWelfareListResetButton =
    welfareDirectInputSection.nextElementSibling;
const recommendWelfareButtons = document.querySelector(".autoWlfrRegArea");
const welfareList = document.getElementById("devWlfrList");

// 기타
const submitButton = document.getElementById("devBtnSubmit");
const toTheTop = document.getElementById("btnMtcTop");
const body = document.querySelector("body");

let logoImage = ""; // 선택된 로고 이미지
let tempTypeSelectButton = document.querySelector(".devCoTypeItem.on button"); // 선택된 기업형태
let tempIndustryType = document.querySelector(".tbPart .devPartCtgr.on button"); // 선택된 업종 카테고리
let tempIndustryPartList = document.querySelector(".tbPart .devPartList.devOn"); // 선택된 업종 리스트
let tempIndustryPartItem = document.querySelector(
    ".tbPart .devPartList .devPartItem.on button",
); // 선택된 업종 항목
let tempIndustryPartItemValue = tempIndustryPartItem?.textContent;
let tempWelfareInputValues = {}; // 선택된 복리후생 (추천)
let tempWelfareModalCodes = {}; // 선택된 복리후생 (모달)
let tempScroll = 0; // 모달 열 때 스크롤 위치 저장

// 복리후생 모달 input 리스트
const welfareModalInputList = Array.from(welfareModalList).map((list) =>
    list.querySelector("input"),
);

// 기본 체크된 복리후생 값
const welfareModalBasicInputValueList = welfareModalInputList
    .filter((input) => input.checked)
    .map((input) => input.value);

// 국가 목록
const nations = "가나,가봉,가이아나,감비아..."; // 전체 국가 목록
const nationTags = nations
    .split(",")
    .map(
        (nation) =>
            `<li><a class="devNationSearchItem" style="cursor:pointer">${nation}</a></li>`,
    )
    .join("");



// 이벤트 핸들러 - 공통 Input
companyBasicinputs
    .filter((input) => !input.closest(".addrMngWrap"))
    .forEach((input) => {
        const wrap = input.closest(".elWrap");

        // 초기값이 있으면 ok 클래스 추가
        if (input.value) wrap.classList.add("ok");

        // Focus 이벤트
        input.addEventListener("focus", (e) => {
            // 숫자만 입력 가능한 필드는 하이픈 제거
            if (input.id === "devOpenDate" || input.id === "devFax") {
                input.value = input.value.replace(/[^0-9]/g, "");
            }


            // 자본금 필드 특수 처리
            if (input.classList.contains("devCapitalText")) {
                document.getElementById("devCapitalUnitA").textContent = "억";
                document.getElementById("devCapitalUnitB").textContent = "만원";
                input.closest(".inpTxItem").classList.add("ok");
                wrap.classList.add("on");

                const capitalA = document.getElementById("devCapitalUnitA");
                const capitalB = document.getElementById("devCapitalUnitB");
                if (
                    !capitalA.previousElementSibling.value &&
                    !capitalB.previousElementSibling.value
                ) {
                    wrap.classList.remove("ok");
                }
            } else {
                wrap.classList.remove("ok");
                wrap.classList.add("on");
            }

            // 다른 모달/드롭다운 닫기
            logoUploadLayout.classList.remove("on");
            industrySelectButton.closest(".elWrap").classList.remove("on");
            typeSelectButton.closest(".elWrap").classList.remove("on");
            addressSearchLayer.classList.remove("on");

            // 에러 클래스 제거
            wrap.classList.remove("error");
        });

        // Blur 이벤트 - 포맷 적용
        input.addEventListener("blur", (e) => {
            let pattern = [];

            // 설립일 포맷 (YYYY-MM-DD)
            if (input.id === "devOpenDate") {
                if (input.value.length === 6) pattern = [4, 2];
                else if (input.value.length >= 8) pattern = [4, 2, 2];
            }
            // 팩스번호 포맷
            else if (input.id === "devFax") {
                const len = input.value.length;
                if (len === 8) pattern = [4, 4];
                else if (len === 9) pattern = [2, 3, 4];
                else if (len === 10) pattern = [3, 3, 4];
                else if (len === 11) pattern = [3, 4, 4];
                else if (len >= 12) pattern = [4, 4, 4];
            }

            input.value = formatInputValue(input.value, pattern);
            wrap.classList.remove("on");
            if (input.value) wrap.classList.add("ok");
        });

        // 숫자만 입력 가능한 필드
        if (
            [
                "devOpenDate",
                "devWorkerCnt",
                "devFax",
                "devCapital_A",
                "devCapital_B",
            ].includes(input.id)
        ) {
            input.addEventListener("keyup", (e) => {
                input.value = input.value.replace(/[^0-9]/g, "");
            });
        }

        // Input 이벤트 - 글자수 표시
        input.addEventListener("input", (e) => {
            // 국가 검색
            if (input.id === "devNation") {
                if (!e.target.value) {
                    nationSearchList.innerHTML = nationTags;
                    nationSearchContainer.style.display = "block";
                } else {
                    nationSearchContainer.style.display = "none";
                }
            }
            // 글자수 제한 표시
            else if (
                input.id === "devMainItem" ||
                input.id === "devAddrForeign"
            ) {
                if (input.maxLength < input.value.length) {
                    input.value = input.value.slice(0, input.maxLength);
                }
                input.closest(".schInpType").querySelector("em").textContent =
                    input.value.length;
            }
        });
    });

// 국가 선택
nationSearchList.addEventListener("click", (e) => {
    if (e.target.tagName === "A") {
        nationInput.value = e.target.textContent;
        nationSearchContainer.closest(".elWrap").classList.add("ok");
        nationSearchContainer.style.display = "none";
    }
});

// 이벤트 핸들러 - Textarea
textareas.forEach((textarea) => {
    textarea.addEventListener("focus", (e) => {
        // 모달/드롭다운 닫기
        logoUploadLayout.classList.remove("on");
        industrySelectButton.closest(".elWrap").classList.remove("on");
        typeSelectButton.closest(".elWrap").classList.remove("on");
        addressSearchLayer.classList.remove("on");
    });
});

// 연혁 및 실적 placeholder 처리
historyTextareaLayer.addEventListener("click", (e) => {
    historyTextareaLayer.lastElementChild.focus();
    historyTextareaLayer.firstElementChild.style.display = "none";
});

// 기업개요 및 비전 placeholder 처리
overviewTextareaLayer.addEventListener("click", (e) => {
    overviewTextareaLayer.lastElementChild.focus();
    overviewTextareaLayer.firstElementChild.style.display = "none";
});

// 전역 클릭 이벤트 - placeholder 복구
document.addEventListener("click", (e) => {
    if (
        !e.target.closest(".artCoHistory .mtcTxaType") &&
        !historyTextareaLayer.lastElementChild.value
    ) {
        historyTextareaLayer.firstElementChild.style.display = "block";
    }
    if (
        !e.target.closest(".artCoOverview .mtcTxaType") &&
        !overviewTextareaLayer.lastElementChild.value
    ) {
        overviewTextareaLayer.firstElementChild.style.display = "block";
    }
});

// 이벤트 핸들러 - 로고 업로드
// 로고 업로드 모달 열기/닫기
logoUploadCloseButton.addEventListener("click", () =>
    logoUploadLayout.classList.remove("on"),
);
logoDeleteCloseButton.addEventListener("click", () =>
    logoUploadLayout.classList.remove("on"),
);

logoUploadLayout.addEventListener("click", (e) => {
    if (
        e.target.closest(".infoBtnB.infoBtnUp") ||
        e.target.classList.contains("infoBtnMod")
    ) {
        // 업로드 모달
        selectLogoInput.value = "";
        selectLogoLabel.textContent = "로고 선택";
        logoImage = "";
        logoDeleteModal.classList.remove("on");
        logoUploadModal.classList.add("on");
    } else if (e.target.classList.contains("infoBtnDel")) {
        // 삭제 모달
        logoUploadModal.classList.remove("on");
        logoDeleteModal.classList.add("on");
    } else {
        return;
    }

    logoUploadLayout.classList.add("on");
    typeSelectButton.closest(".elWrap").classList.remove("on");
});

// 로고 파일 선택
selectLogoInput.addEventListener("change", (e) => {
    const [file] = e.target.files;
    const reader = new FileReader();

    reader.readAsDataURL(file);
    reader.addEventListener("load", (e) => {
        logoImage = e.target.result;

        // 파일 형식 검사
        const validExtensions = ["gif", "jpeg", "jpg", "png"];
        const isValid = validExtensions.some((ext) => logoImage.includes(ext));

        if (!isValid) {
            logoImage = "";
            selectLogoLabel.textContent = "";
            selectLogoInput.value = "";
            alert("파일 형식이 올바르지 않습니다.");
            return;
        }

        selectLogoLabel.textContent = file.name;
    });
});

// 로고 업로드 실행 (서버 전송)
logoUploadSubmitButton.addEventListener("click", () => {
    const file = selectLogoInput.files[0];
    if (!file) {
        alert("로고를 선택해 주십시오.");
        return;
    }

    const formData = new FormData();
    formData.append("file", file);

    fetch("/corporate/logo", {
        method: "POST",
        body: formData,
    })
        .then((res) => res.json())
        .then((data) => {
            if (!data.success) {
                alert(data.message || "업로드에 실패했습니다.");
                return;
            }

            // 로고 이미지 및 버튼 생성
            const logoP = document.createElement("p");
            const buttonP = document.createElement("p");

            logoP.classList.add("logo");
            logoP.innerHTML = `<img src="${data.logoUrl}">`;

            buttonP.classList.add("btn");
            buttonP.innerHTML = `
                <button type="button" class="infoBtn infoBtnDel" id="devLogoDel"></button>
                <button type="button" class="infoBtn infoBtnMod" id="devLogoModify"></button>
            `;

            logoContainer.firstElementChild.remove();
            logoContainer.prepend(buttonP);
            logoContainer.prepend(logoP);
            logoUploadLayout.classList.remove("on");

            alert("업로드 되었습니다.");
        })
        .catch(() => alert("업로드 중 오류가 발생했습니다."));
});

// 로고 삭제 (서버 전송)
logoDeleteButton.addEventListener("click", () => {
    fetch("/corporate/logo", { method: "DELETE" })
        .then((res) => res.json())
        .then((data) => {
            if (!data.success) {
                alert(data.message || "삭제에 실패했습니다.");
                return;
            }

            const logoButton = document.createElement("button");
            logoButton.classList.add("infoBtnB", "infoBtnUp");
            logoButton.id = "devLogoUp";
            logoButton.type = "button";
            logoButton.innerHTML = "<span>로고업로드</span>";

            logoContainer.querySelector(".logo").remove();
            logoContainer.querySelector(".btn").remove();
            logoContainer.prepend(logoButton);
            logoUploadLayout.classList.remove("on");
        })
        .catch(() => alert("삭제 중 오류가 발생했습니다."));
});

// 이벤트 핸들러 - 기업형태
typeSelectButton.addEventListener("click", () => {
    const wrap = typeSelectButton.closest(".elWrap");
    wrap.classList.toggle("on");
    wrap.classList.remove("error");
});

typeSelectList.addEventListener("click", (e) => {
    if (e.target.tagName !== "BUTTON") return;

    // 이전 선택 해제
    if (tempTypeSelectButton) {
        tempTypeSelectButton.closest(".devCoTypeItem").classList.remove("on");
    }

    // 새로운 선택 적용
    e.target.closest(".devCoTypeItem").classList.add("on");
    typeSelectButton.closest(".elWrap").classList.add("ok");

    companyTypeResultSpan.textContent = e.target.textContent;
    companyTypeInput.value = e.target.closest(".devCoTypeItem").dataset.type;
    tempTypeSelectButton = e.target;

    // 외국계 기업 여부에 따라 입력 필드 변경
    const isForeign = ["6", "8", "10"].includes(companyTypeInput.value);
    nationLayer.style.display = isForeign ? "table" : "none";
    addressLayer.style.display = isForeign ? "none" : "block";
    foreignAddressLayer.style.display = isForeign ? "block" : "none";

    typeSelectButton.closest(".elWrap").classList.remove("on");
});

// 이벤트 핸들러 - 업종
industrySelectButton.addEventListener("click", () => {
    const wrap = industrySelectButton.closest(".elWrap");
    wrap.classList.toggle("on");
    wrap.classList.remove("error");
});

// 업종 카테고리 선택
industryCategoryList.addEventListener("click", (e) => {
    if (e.target.tagName !== "BUTTON") return;

    // 이전 선택 해제
    if (tempIndustryType) {
        tempIndustryType.closest(".devPartCtgr").classList.remove("on");
    }
    if (tempIndustryPartList) {
        tempIndustryPartList.style.display = "none";
    }

    // 새로운 카테고리의 세부 항목 표시
    const targetList = Array.from(industryPartLists).find(
        (list) =>
            list.dataset.partCtgrCode ===
            e.target.closest(".devPartCtgr").dataset.partCtgrCode,
    );

    if (targetList) {
        targetList.style.display = "block";
        tempIndustryPartList = targetList;
    }

    e.target.closest(".devPartCtgr").classList.add("on");
    tempIndustryType = e.target;
});

// 업종 세부 항목 선택
industryPartLists.forEach((partList) => {
    partList.addEventListener("click", (e) => {
        if (e.target.tagName !== "BUTTON") return;

        // 이전 선택 해제
        if (tempIndustryPartItem) {
            tempIndustryPartItem.closest(".devPartItem").classList.remove("on");
        }

        // 새로운 선택 적용
        e.target.closest(".devPartItem").classList.add("on");
        // 소분류 코드 저장
        industrySmallInput.value = e.target.closest(".devPartItem").dataset.part;
        // 대분류 코드 저장 (부모 devPartList의 data-part-ctgr-code)
        industryInput.value = e.target.closest(".devPartList").dataset.partCtgrCode;
        tempIndustryPartItem = e.target;
    });
});

// 업종 선택 확인/취소
industryApplyButtons.addEventListener("click", (e) => {
    if (e.target.closest(".mtcBtnBg")) {
        // 확인 버튼
        if (tempIndustryPartItem) {
            industrySmallInput.value =
                tempIndustryPartItem.closest(".devPartItem").dataset.part;
            industryInput.value =
                tempIndustryPartItem.closest(".devPartList").dataset.partCtgrCode;
            if (industrySmallInput.value) {
                tempIndustryPartItemValue = tempIndustryPartItem.textContent;
                industryResultSpan.textContent = tempIndustryPartItemValue;
                industryResultSpan.closest(".elWrap").classList.add("ok");
            }
        }
    }

    industrySelectButton.closest(".elWrap").classList.remove("on");
});

// 이벤트 핸들러 - 주소 검색
// 주소 검색 열기
addressSearchButton.addEventListener("click", () => {
    addressSearchButton.closest(".elWrap").classList.remove("error");

    // input 초기화
    addressInputs.forEach((input, index) => {
        if (index === 0 || index === 1) {
            input.style.border = "1px solid #ddd";
        }
        input.value = "";
    });

    addressSearchLayer.classList.add("on");
});

// 주소 검색 닫기
addressLayerCloseButton.addEventListener("click", () => {
    addressSearchLayer.classList.remove("on");
});

// Daum 우편번호 API
const executeDaumPostcode = () => {
    new daum.Postcode({
        oncomplete: function (data) {
            let addr = "";
            let extraAddr = "";

            // 도로명/지번 주소 선택에 따라 분기
            if (data.userSelectedType === "R") {
                addr = data.roadAddress;

                // 참고항목 조합
                if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
                    extraAddr += data.bname;
                }
                if (data.buildingName !== "" && data.apartment === "Y") {
                    extraAddr +=
                        (extraAddr !== "" ? ", " : "") + data.buildingName;
                }
                if (extraAddr !== "") {
                    extraAddr = " (" + extraAddr + ")";
                }
            } else {
                addr = data.jibunAddress;
            }

            // 우편번호와 주소 정보 입력
            document.getElementById("postcode").value = data.zonecode;
            document.getElementById("address").value = addr + extraAddr;
            document.getElementById("detailAddress").focus();
        },
    }).open();
};

// 우편번호 찾기 버튼
addressSearchPostCodeButton.addEventListener("click", () => {
    addressInputs
        .filter((input) => input.placeholder.includes("*"))
        .forEach((input) => {
            input.style.border = "1px solid #ddd";
        });
    executeDaumPostcode();
});

// 주소 확인/취소 버튼
document.querySelector(".popAddr .popFunc").addEventListener("click", (e) => {
    if (!e.target.closest("button")) return;

    if (!e.target.closest(".devCloseAddr")) {
        // 확인 버튼
        if (addressInputs[0].value && addressInputs[1].value) {
            addressResultSpan.textContent = Array.from(addressInputs)
                .filter((input) => input.value.trim() !== "")
                .map((input) => input.value)
                .join(" ");
            addressLayer.classList.add("ok");
            addressSearchLayer.classList.remove("on");
        } else {
            addressInputs
                .filter((input) => input.placeholder.includes("*"))
                .forEach((input) => {
                    input.style.border = input.value
                        ? "1px solid #ddd"
                        : "1px solid #ff3333";
                });
            alert("주소를 입력해 주십시오.");
        }
    } else {
        // 취소 버튼
        addressSearchLayer.classList.remove("on");
    }
});

// 주소 입력 시 유효성 표시
addressInputs
    .filter((input) => input.placeholder.includes("*"))
    .forEach((input, index) => {
        input.addEventListener("input", (e) => {
            if (index === 0) {
                e.target.value = e.target.value.replace(/[^0-9]/g, "");
            }
            input.style.border = input.value
                ? "1px solid #ddd"
                : "1px solid #ff3333";
        });
    });

// 이벤트 핸들러 - 복리후생
// 추천 복리후생 선택
recommendWelfareButtons.addEventListener("click", (e) => {
    if (e.target.tagName !== "BUTTON") return;

    // 중복 검사
    const welfareButtonList = Array.from(
        welfareList.querySelectorAll("li"),
    ).map((li) => li.firstElementChild);
    const isDuplicate = welfareButtonList.some(
        (btn) => btn.dataset.itemCode === e.target.dataset.itemCode,
    );

    if (isDuplicate) {
        alert("이미 선택하신 복리후생입니다.");
        return;
    }

    // 복리후생 항목 추가
    const welfareItem = document.createElement("li");
    welfareItem.innerHTML = `
        <button type="button" class="devItemDel" data-item-code="${e.target.dataset.itemCode}">
            ${e.target.textContent}
        </button>
    `;
    welfareList.appendChild(welfareItem);
    welfareList.previousElementSibling.style.display = "none";

    // 모달 input 체크 처리
    const targetInput = welfareModalInputList.find(
        (input) => input.value === e.target.dataset.itemCode,
    );
    if (targetInput) {
        targetInput.closest("span").classList.add("chk");
        targetInput.checked = true;
        tempWelfareInputValues[targetInput.value] = e.target.textContent;
    }
});

// 복리후생 항목 삭제
welfareList.addEventListener("click", (e) => {
    if (e.target.tagName !== "BUTTON") return;

    // 항목 삭제
    e.target.closest("li").remove();

    // 모달 input 체크 해제
    const targetInput = welfareModalInputList.find(
        (input) => input.value === e.target.dataset.itemCode,
    );
    if (targetInput && e.target.dataset.itemCode.length !== 6) {
        targetInput.closest("span").classList.remove("chk");
        targetInput.checked = false;
    }
    delete tempWelfareInputValues[e.target.dataset.itemCode];

    // 리스트가 비었으면 안내 문구 표시
    if (!welfareList.querySelectorAll("li").length) {
        welfareList.previousElementSibling.style.display = "block";
    }
});

// 복리후생 전체보기 모달 열기
moreWelfareInfoButton.addEventListener("click", () => {
    tempWelfareModalCodes = { ...tempWelfareInputValues };

    // 기본 체크박스 처리
    const isEmpty = !Object.keys(tempWelfareInputValues).length;

    if (isEmpty) {
        // 비어있으면 기본값으로 설정
        welfareModalInputList
            .filter((input) =>
                welfareModalBasicInputValueList.includes(input.value),
            )
            .forEach((input) => {
                input.checked = true;
                input.closest("span").classList.add("chk");
                tempWelfareModalCodes[input.value] =
                    input.nextElementSibling.textContent;
            });
    } else {
        // 선택된 항목만 체크
        welfareModalInputList.forEach((input) => {
            const isSelected = Object.keys(tempWelfareInputValues).includes(
                input.value,
            );
            input.checked = isSelected;
            input
                .closest("span")
                .classList[isSelected ? "add" : "remove"]("chk");
        });
    }

    // 미리보기 리스트 생성
    previewWelfareList.innerHTML = "";
    Object.entries(
        isEmpty ? tempWelfareModalCodes : tempWelfareInputValues,
    ).forEach(([key, value]) => {
        const item = document.createElement("li");
        item.classList.add("subItem");
        item.innerHTML = `
            <span class="inr">
                <span class="devItemText">${value}</span>
                <button type="button" class="spRegA btnItemDel" data-item-code="${key}"></button>
            </span>
        `;
        previewWelfareList.appendChild(item);
    });

    previewWelfareList.style.display = "block";

    // 모달 표시 및 스크롤 고정
    tempScroll = window.scrollY;
    body.style.cssText = `width: 100%; height: 100%; position: fixed; top: -${tempScroll}px;`;

    moreWelfareInfoButton.classList.add("isCheckButton");
    welfareModalLayer.style.cssText = "display: block; opacity: 1;";
});

// 복리후생 모달 닫기
welfareModalCloseButton.addEventListener("click", () => {
    // 변경사항 취소
    if (
        Object.keys(tempWelfareModalCodes).length !==
        Object.keys(tempWelfareInputValues).length
    ) {
        welfareModalInputList
            .filter(
                (input) =>
                    !Object.keys(tempWelfareInputValues).includes(input.value),
            )
            .forEach((input) => {
                input.checked = false;
                input.closest("span").classList.remove("chk");
            });
        tempWelfareModalCodes = { ...tempWelfareInputValues };
    }

    // 리스트 재생성
    welfareList.innerHTML = "";
    Object.entries(tempWelfareInputValues).forEach(([key, value]) => {
        const item = document.createElement("li");
        item.innerHTML = `<button type="button" class="devItemDel" data-item-code="${key}">${value}</button>`;
        welfareList.appendChild(item);
    });

    if (welfareList.querySelectorAll("li").length) {
        welfareList.previousElementSibling.style.display = "none";
    } else {
        welfareList.previousElementSibling.style.display = "block";
    }

    // 모달 닫기 및 스크롤 복원
    body.style.cssText = "";
    window.scrollTo(0, tempScroll);

    moreWelfareInfoButton.classList.remove("isCheckButton");
    welfareModalLayer.style.cssText = "display: none; opacity: 0;";
});

// 복리후생 모달 확인/취소
welfareModalResultButtons.addEventListener("click", (e) => {
    if (e.target.tagName !== "BUTTON") return;

    // 확인 버튼이면 변경사항 적용
    if (e.target.classList.contains("btnOk")) {
        tempWelfareInputValues = { ...tempWelfareModalCodes };
    } else {
        // 취소 버튼이면 변경사항 되돌리기
        welfareModalInputList
            .filter(
                (input) =>
                    !Object.keys(tempWelfareInputValues).includes(input.value),
            )
            .forEach((input) => {
                input.checked = false;
                input.closest("span").classList.remove("chk");
            });
        tempWelfareModalCodes = { ...tempWelfareInputValues };
    }

    // 리스트 재생성
    welfareList.innerHTML = "";
    Object.entries(tempWelfareModalCodes).forEach(([key, value]) => {
        const item = document.createElement("li");
        item.innerHTML = `<button type="button" class="devItemDel" data-item-code="${key}">${value}</button>`;
        welfareList.appendChild(item);
    });

    if (welfareList.querySelectorAll("li").length) {
        welfareList.previousElementSibling.style.display = "none";
    } else {
        welfareList.previousElementSibling.style.display = "block";
    }

    // 모달 닫기
    body.style.cssText = "";
    window.scrollTo(0, tempScroll);
    moreWelfareInfoButton.classList.remove("isCheckButton");
    welfareModalLayer.style.cssText = "display: none; opacity: 0;";
});

// 복리후생 체크박스 변경
welfareModalList.forEach((li) => {
    li.addEventListener("change", (e) => {
        if (e.target.tagName !== "INPUT") return;

        if (e.target.checked) {
            // 체크 시 - 미리보기에 추가
            const item = document.createElement("li");
            item.classList.add("subItem");
            item.innerHTML = `
                <span class="inr">
                    <span class="devItemText">${e.target.nextElementSibling.textContent}</span>
                    <button type="button" class="spRegA btnItemDel" data-item-code="${e.target.value}"></button>
                </span>
            `;
            previewWelfareList.appendChild(item);
            previewWelfareList.style.display = "block";

            e.target.closest("span").classList.add("chk");
            tempWelfareModalCodes[e.target.value] =
                e.target.nextElementSibling.textContent;
        } else {
            // 체크 해제 시 - 미리보기에서 제거
            const targetButton = Array.from(
                previewWelfareList.querySelectorAll("button"),
            ).find((btn) => btn.dataset.itemCode === e.target.value);

            if (targetButton) {
                targetButton.closest(".subItem").remove();
            }

            e.target.closest("span").classList.remove("chk");
            delete tempWelfareModalCodes[e.target.value];
        }
    });
});

// 복리후생 직접 입력
welfareDirectInputSection.addEventListener("focusin", (e) => {
    if (e.target.tagName === "INPUT") {
        e.target.closest(".inpTxItem").classList.add("ok");
    }
});

welfareDirectInputSection.addEventListener("focusout", (e) => {
    if (e.target.tagName === "INPUT" && !e.target.value) {
        e.target.closest(".inpTxItem").classList.remove("ok");
    }
});

welfareDirectInputSection.addEventListener("click", (e) => {
    if (!e.target.closest(".devDirectInputBtn")) return;

    const inputValue = e.target.closest(".inpTxItem").firstElementChild.value;

    if (!inputValue) {
        alert("복리후생을 입력해주세요.");
        return;
    }

    // 중복 검사
    const isDuplicate = Object.values(tempWelfareModalCodes).some(
        (value) => value === inputValue,
    );
    const isInList = welfareModalInputList.some(
        (input) => input.nextElementSibling.textContent === inputValue,
    );

    if (isDuplicate || isInList) {
        alert(
            "입력하신 복리후생은 선택항목에 있거나, 이미 선택하신 복리후생입니다.",
        );
        return;
    }

    // 랜덤 코드 생성 (6자리 이상)
    const itemCode = Math.floor(Math.random() * 900000) + 100000;

    // 미리보기에 추가
    const item = document.createElement("li");
    item.classList.add("subItem");
    item.innerHTML = `
        <span class="inr">
            <span class="devItemText">${inputValue}</span>
            <button type="button" class="spRegA btnItemDel" data-item-code="${itemCode}"></button>
        </span>
    `;
    previewWelfareList.appendChild(item);

    tempWelfareModalCodes[itemCode] = inputValue;

    // input 초기화
    e.target.closest(".inpTxItem").firstElementChild.value = "";
});

// 복리후생 미리보기 항목 삭제
previewWelfareList.addEventListener("click", (e) => {
    if (e.target.tagName !== "BUTTON") return;

    // 직접 입력이 아닌 경우 체크박스도 해제
    if (e.target.dataset.itemCode.length !== 6) {
        const targetInput = welfareModalInputList.find(
            (input) => input.value === e.target.dataset.itemCode,
        );
        if (targetInput) {
            targetInput.checked = false;
            targetInput.closest("span").classList.remove("chk");
        }
    }

    e.target.closest(".subItem").remove();
    delete tempWelfareModalCodes[e.target.dataset.itemCode];
});

// 복리후생 초기화
previewWelfareListResetButton.addEventListener("click", () => {
    // 모든 체크 해제
    welfareModalInputList.forEach((input) => {
        input.checked = false;
        input.closest("span").classList.remove("chk");
    });

    previewWelfareList.innerHTML = "";
    tempWelfareModalCodes = {};
    previewWelfareList.style.display = "none";
});

// 이벤트 핸들러 - 저장하기
submitButton.addEventListener("click", (e) => {
    e.preventDefault();

    if (!e.target.closest("#devBtnSubmit")) return;

    // 필수 입력 검사 제외 항목
    const noSettings = [
        "txtSale",
        "devCapital_A",
        "devCapital_B",
        "txtHomepage",
        "devFax",
        "devAddrForeign",
        "detailAddress",
        "searchPostCodeBtn",
        "corpCapitalHidden",
        "Industry_Small_Code",
    ];
    const domesticNoSettings = ["devNation"];
    const foreignNoSettings = [
        "postcode",
        "address",
        "detailAddress",
        "extraAddress",
    ];

    let hasError = false;

    // 필수 입력 검사
    companyBasicinputs
        .filter((input) => !noSettings.includes(input.id))
        .filter((input) => {
            const isForeign = ["6", "8", "10"].includes(companyTypeInput.value);
            return isForeign
                ? !foreignNoSettings.includes(input.id)
                : !domesticNoSettings.includes(input.id);
        })
        .forEach((input) => {
            if (!input.value) {
                console.log("유효성 검사 실패:", input.id, input.type, input.name);
                hasError = true;
                const errorTarget = input
                    .closest(".tbRow")
                    .querySelector("#devAddrArea")
                    ? input.closest(".tbRow").querySelector(".elWrap")
                    : input.closest(".elWrap");
                errorTarget.classList.add("error");
            }
        });

    if (hasError) {
        alert("필수항목을 모두 입력해 주세요.");
        window.scrollTo({ top: 0, behavior: "smooth" });
    } else {
        // 자본금 계산 (억 * 100000000 + 만원 * 10000)
        const capitalA = parseInt(document.getElementById("devCapital_A").value) || 0;
        const capitalB = parseInt(document.getElementById("devCapital_B").value) || 0;
        document.getElementById("corpCapitalHidden").value = capitalA * 100000000 + capitalB * 10000;

        // 복리후생 데이터 직렬화
        document.getElementById("devWelfareData").value = JSON.stringify(tempWelfareInputValues);

        // 폼 제출
        document.getElementById("devInfoForm").submit();
    }
});



// 페이지 로드 시 자본금 기존값 표시
(function () {
    const corpCapitalValue = parseInt(document.getElementById("corpCapitalHidden").value) || 0;
    if (corpCapitalValue > 0) {
        const capitalA = Math.floor(corpCapitalValue / 100000000);
        const capitalB = Math.floor((corpCapitalValue % 100000000) / 10000);
        if (capitalA > 0) {
            document.getElementById("devCapital_A").value = capitalA;
            document.getElementById("devCapitalUnitA").textContent = "억";
        }
        if (capitalB > 0) {
            document.getElementById("devCapital_B").value = capitalB;
            document.getElementById("devCapitalUnitB").textContent = "만원";
        }
    }
})();

// 페이지 로드 시 저장된 값 표시 (기업형태, 업종, 주소, 연혁/개요)
(function initializeSavedValues() {
    // 기업형태 표시
    const companyTypeValue = companyTypeInput.value;
    if (companyTypeValue) {
        const item = document.querySelector(`.devCoTypeItem[data-type="${companyTypeValue}"]`);
        if (item) {
            item.classList.add("on");
            companyTypeResultSpan.textContent = item.querySelector("button").textContent;
            typeSelectButton.closest(".elWrap").classList.add("ok");
            tempTypeSelectButton = item.querySelector("button");
        }
    }

    // 업종 표시
    const industryValue = industryInput.value;
    const industrySmallValue = industrySmallInput.value;
    if (industryValue && industrySmallValue) {
        const ctgr = document.querySelector(`.devPartCtgr[data-part-ctgr-code="${industryValue}"]`);
        const partList = document.querySelector(`.devPartList[data-part-ctgr-code="${industryValue}"]`);
        const partItem = document.querySelector(`.devPartItem[data-part="${industrySmallValue}"]`);

        if (ctgr) {
            ctgr.classList.add("on");
            tempIndustryType = ctgr.querySelector("button");
        }
        if (partList) {
            partList.style.display = "block";
            tempIndustryPartList = partList;
        }
        if (partItem) {
            partItem.classList.add("on");
            industryResultSpan.textContent = partItem.querySelector("button").textContent;
            industrySelectButton.closest(".elWrap").classList.add("ok");
            tempIndustryPartItem = partItem.querySelector("button");
            tempIndustryPartItemValue = tempIndustryPartItem.textContent;
        }
    }

    // 주소 표시
    const postcodeEl = document.getElementById("postcode");
    const addressEl = document.getElementById("address");
    const detailAddressEl = document.getElementById("detailAddress");
    if (postcodeEl.value && addressEl.value) {
        let addrText = addressEl.value;
        if (detailAddressEl.value) addrText += " " + detailAddressEl.value;
        addressResultSpan.textContent = addrText;
        addressLayer.classList.add("ok");
    }

    // 연혁 및 실적 placeholder 숨기기
    if (historyTextareaLayer.lastElementChild.value.trim()) {
        historyTextareaLayer.firstElementChild.style.display = "none";
    }

    // 기업개요 및 비전 placeholder 숨기기
    if (overviewTextareaLayer.lastElementChild.value.trim()) {
        overviewTextareaLayer.firstElementChild.style.display = "none";
    }
})();

// 페이지 로드 시 저장된 복리후생 초기화
(function initializeSavedWelfare() {
    if (typeof savedWelfareItems === "undefined" || !savedWelfareItems || savedWelfareItems.length === 0) {
        return;
    }

    // JS 상태 복원
    savedWelfareItems.forEach((item) => {
        tempWelfareInputValues[item.welfareCode] = item.welfareName;
    });

    // devWlfrList UI 생성
    welfareList.innerHTML = "";
    Object.entries(tempWelfareInputValues).forEach(([code, name]) => {
        const li = document.createElement("li");
        li.innerHTML = `<button type="button" class="devItemDel" data-item-code="${code}">${name}</button>`;
        welfareList.appendChild(li);
    });

    // 안내문구 숨기기
    if (welfareList.querySelectorAll("li").length) {
        welfareList.previousElementSibling.style.display = "none";
    }

    // 모달 체크박스 동기화
    welfareModalInputList.forEach((input) => {
        if (tempWelfareInputValues[input.value]) {
            input.checked = true;
            input.closest("span").classList.add("chk");
        }
    });
})();

// 이벤트 핸들러 - 맨 위로
toTheTop.addEventListener("click", () => {
    window.scrollTo({ top: 0, behavior: "smooth" });
});
