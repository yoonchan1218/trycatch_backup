// ==========================================
// 입력정보 경고
// ★ 아이디(idcheck), 비밀번호(M_Pwd), 인증번호(Certify_Num) 제거
// ==========================================
const fields = [
    {
        input: "M_Name",
        selector: ".mbr_name",
        notice: "notice_msg_name",
        regexp: /^[가-힣a-zA-Z\s]{2,12}$/,
        errorMsg: "2~12자의 한글, 영문만 입력 가능합니다.",
    },
    {
        input: "Born",
        selector: ".mbr_age",
        notice: "notice_msg_age",
        regexp: /^(19|20)\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\d|3[01])$/,
        errorMsg: "생년월일 8자리를 입력해주세요. (예: 20000131)",
    },
    {
        input: "M_Email",
        selector: ".mbr_email",
        notice: "notice_msg_mail",
        regexp: /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/,
        errorMsg: "올바른 이메일 형식을 입력해주세요.",
        skip: true,
    },
    {
        input: "M_Phone",
        selector: ".mbr_phone",
        notice: "notice_msg_phone",
        regexp: /^01[016789]-?\d{3,4}-?\d{4}$/,
        errorMsg: "올바른 휴대폰 번호를 입력해주세요.",
    }
];

// ==========================================
// 포커스 / 블러 이벤트
// ==========================================
fields.forEach(({ input, selector, notice, regexp, errorMsg, skip }) => {
    const inputEl = document.getElementById(input);
    const col1 = document.querySelector(`${selector} .col_1`);
    const col2 = document.querySelector(`${selector} .col_2`);
    const noticeEl = document.getElementById(notice);

    if (!inputEl || !col1 || !col2) return;

    // 포커스
    inputEl.addEventListener("focus", () => {
        col1.classList.add("focus");
        col2.classList.add("focus");
    });

    // 블러 (포커스 해제)
    inputEl.addEventListener("blur", () => {
        // 값 없으면 포커스 스타일 제거
        if (!inputEl.value) {
            col1.classList.remove("focus");
            col2.classList.remove("focus");
        }

        // 인증번호는 blur 검증 제외
        if (skip) return;

        // 정규식 검증
        if (regexp && noticeEl) {
            if (!inputEl.value) {
                noticeEl.innerHTML = "필수 입력 항목입니다.";
                noticeEl.classList.add("failure");
                noticeEl.style.display = "block";
            } else if (!regexp.test(inputEl.value)) {
                noticeEl.innerHTML = errorMsg;
                noticeEl.classList.add("failure");
                noticeEl.style.display = "block";
            } else {
                noticeEl.innerHTML = "";
                noticeEl.classList.remove("failure");
                noticeEl.style.display = "none";
            }
        }
    });
});

// ==========================================
// 가입하기 버튼
// ==========================================
const mbrBtnRegist = document.querySelector(".mbrBtnRegist");

mbrBtnRegist.addEventListener("click", (e) => {
    e.preventDefault();

    let isValid = true;

    // 입력 필드 검증
    fields.forEach(({ input, notice, regexp, errorMsg, skip }) => {
        if (skip) return;

        const inputEl = document.getElementById(input);
        const noticeEl = document.getElementById(notice);

        if (!inputEl || !noticeEl) return;

        if (!inputEl.value) {
            noticeEl.innerHTML = "필수 입력 항목입니다.";
            noticeEl.classList.add("failure");
            noticeEl.style.display = "block";
            isValid = false;
        } else if (!regexp.test(inputEl.value)) {
            noticeEl.innerHTML = errorMsg;
            noticeEl.classList.add("failure");
            noticeEl.style.display = "block";
            isValid = false;
        } else {
            noticeEl.innerHTML = "";
            noticeEl.classList.remove("failure");
            noticeEl.style.display = "none";
        }
    });

    // 성별 검증
    const genderChecked = document.querySelector(
        'input[name="individualMemberGender"]:checked',
    );
    const genderNotice = document.getElementById("notice_msg_gender");
    if (!genderChecked) {
        genderNotice.innerHTML = "성별을 선택해주세요.";
        genderNotice.classList.add("failure");
        genderNotice.style.display = "block";
        isValid = false;
    } else {
        genderNotice.innerHTML = "";
        genderNotice.classList.remove("failure");
        genderNotice.style.display = "none";
    }

    // 필수 약관 검증
    const ageAgree = document.getElementById("lb_chk_age");
    if (!ageAgree.checked) {
        isValid = false;
    }

    // 검증 결과
    if (!isValid) {
        alert("필수 항목을 확인해주세요.");
    } else {
        alert("환영합니다!");
        document.getElementById("frm").submit();
    }
});

// ==========================================
// 체크박스 (input 요소에 이벤트 걸기)
// ==========================================
const inputChkAll = document.getElementById("lb_chk_all");
const inputChkAge = document.getElementById("lb_chk_age");
const inputChkPrivacy = document.getElementById("lb_chk_privacyOptional");
const inputChkAdinfo = document.getElementById("lb_chk_adinfo");

const chkAll = document.querySelector(".chk_all");
const chkAge = document.querySelector(".chk_age");
const chkPrivacyOptional = document.querySelector(".chk_privacyOptional");
const chkAdinfo = document.querySelector(".chk_adinfo");

const checkBoxInputs = [inputChkAge, inputChkPrivacy, inputChkAdinfo];
const checkBoxLabels = [chkAge, chkPrivacyOptional, chkAdinfo];

// 전체 선택
inputChkAll.addEventListener("change", () => {
    const isChecked = inputChkAll.checked;

    chkAll.classList.toggle("on", isChecked);

    checkBoxInputs.forEach((input, idx) => {
        input.checked = isChecked;
        checkBoxLabels[idx].classList.toggle("on", isChecked);
    });
});

// 개별 선택
checkBoxInputs.forEach((input, idx) => {
    input.addEventListener("change", () => {
        checkBoxLabels[idx].classList.toggle("on", input.checked);

        const allChecked = checkBoxInputs.every((cb) => cb.checked);

        inputChkAll.checked = allChecked;
        chkAll.classList.toggle("on", allChecked);
    });
});

// ==========================================
// 내용보기 클릭
// ==========================================
const mbrBtnPolicies = document.querySelectorAll(".mbrBtnPolicy");

mbrBtnPolicies.forEach((btn) => {
    btn.addEventListener("click", (e) => {
        e.preventDefault();

        btn.classList.toggle("on");

        const policyTplBox = btn.closest(".row").querySelector(".policyTplBox");

        if (policyTplBox) {
            policyTplBox.style.display =
                policyTplBox.style.display === "block" ? "none" : "block";
        }
    });
});

// ==========================================
// 성별 체크시 문자 제거
// ==========================================
const boy = document.getElementById("boy");
const girl = document.getElementById("girl");
const noticeMsgGender = document.getElementById("notice_msg_gender");
const gender = [boy, girl];

gender.forEach((gender) => {
    gender.addEventListener("click", (e) => {
        if (boy.checked || girl.checked) {
            noticeMsgGender.style.display = "none";
        }
    });
});

// ==========================================
// 전화번호 하이픈 자동 포맷팅 (01012345678 → 010-1234-5678)
// ==========================================
const phoneInput = document.getElementById("M_Phone");

if (phoneInput) {
    phoneInput.addEventListener("input", () => {
        let value = phoneInput.value.replace(/[^0-9]/g, "");
        if (value.length > 11) value = value.slice(0, 11);

        if (value.length <= 3) {
            phoneInput.value = value;
        } else if (value.length <= 7) {
            phoneInput.value = value.slice(0, 3) + "-" + value.slice(3);
        } else {
            phoneInput.value = value.slice(0, 3) + "-" + value.slice(3, 7) + "-" + value.slice(7);
        }
    });
}

// ==========================================
// 카카오에서 pre-fill된 필드에 focus 스타일 적용
// ==========================================
document.addEventListener("DOMContentLoaded", () => {
    const emailInput = document.getElementById("M_Email");
    if (emailInput && emailInput.value) {
        const col1 = document.querySelector(".mbr_email .col_1");
        const col2 = document.querySelector(".mbr_email .col_2");
        if (col1) col1.classList.add("focus");
        if (col2) col2.classList.add("focus");
    }
    const nameInput = document.getElementById("M_Name");
    if (nameInput && nameInput.value) {
        const col1 = document.querySelector(".mbr_name .col_1");
        const col2 = document.querySelector(".mbr_name .col_2");
        if (col1) col1.classList.add("focus");
        if (col2) col2.classList.add("focus");
    }
});