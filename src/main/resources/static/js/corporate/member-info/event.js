NodeList.prototype.filter = Array.prototype.filter;

// form
const form = document.getElementById("form");

// 비밀번호 변경
const passwordChangeButton = document.getElementById("devBtnChangePwd");
const passwordChangeAreas = document.querySelectorAll(".devPwdChangeArea");

// 도움말
const tipButton = document.querySelector(".mtcBtnTip.devDescLy");
const tipArea = document.querySelector(".lyInfoHelp.devAgreeLayer");

// input on ok error 클래스 추가/삭제
const textInputList = document.querySelectorAll(".inpDefault.devAnimPh");

// 새로운 비밀번호 input error 메세지
const newPasswordInput = textInputList[0];
const newPasswordCheckInput = textInputList[1];

// 전화번호
const phoneInput = textInputList[2];

// 이메일
const emailInput = textInputList[3];

// 숨김 버튼
const passwordInputHideButton = document.getElementById("lb_sf_on");
const passwordInputHideButtonLabel = document.getElementById("devTogglePwd");

// 개인정보 수집 및 이용 동의
const privacyChoiceInput = document.getElementById("devPrivacyChoice");
const privacyChoiceInputLabel = privacyChoiceInput.nextElementSibling;
const moreContentButton = privacyChoiceInputLabel.firstElementChild;
const privacyChoiceTerms = document.querySelector(".terms-box");

// 저장하기
const submitButton = document.getElementById("devBtnSubmit");

// 회원탈퇴
const leaveCheckButton = document.querySelector(".infoBtnLeave");
const leaveModal = document.querySelector(".company-certify-modal");
const leaveModalCloseButton = document.querySelector(
    ".company-certify-modal .close-button",
);
const leaveButton = document.querySelector(".company-certify-modal .modal-btn");
const leaveAgreeInput = document.getElementById("devLeaveAgree");

// input value 유효성 검사
const isValidPassword = (value) => {
    const regex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*(),.?":{}|<>]).{8,16}$/;
    return regex.test(value);
};
const hasKorean = (value) => {
    return /[ㄱ-ㅎㅏ-ㅣ가-힣]/.test(value);
};

// 전화번호 format 함수
const formatPhoneNumber = (value) => {
    const numbers = value.replace(/[^0-9]/g, "");

    if (numbers.startsWith("02")) {
        // 서울 (02)
        if (numbers.length <= 9) {
            return numbers.replace(/(\d{2})(\d{3})(\d{1,4})/, "$1-$2-$3");
        } else {
            return numbers.replace(/(\d{2})(\d{4})(\d{4})/, "$1-$2-$3");
        }
    } else {
        // 그 외 (010, 031 등)
        if (numbers.length <= 10) {
            return numbers.replace(/(\d{3})(\d{3})(\d{1,4})/, "$1-$2-$3");
        } else {
            return numbers.replace(/(\d{3})(\d{4})(\d{4})/, "$1-$2-$3");
        }
    }
};

// 전화번호 유효성 검사
const isValidPhone = (value) => {
    const numbers = value.replace(/[^0-9]/g, "");

    // 02: 9~10자리, 그 외: 10~11자리
    if (numbers.startsWith("02")) {
        return /^02\d{7,8}$/.test(numbers);
    } else {
        return /^0\d{9,10}$/.test(numbers);
    }
};

// 저장하기 검사
const checkValues = (inputList) => {
    let check = false;
    let inputValueCheck = [];

    inputList.forEach((input) => {
        console.log(input);

        const inputWrap = input.closest(".elWrap");

        if (!input.value) {
            inputWrap.classList.add("error");
            check = true;

            input === newPasswordInput &&
                (inputWrap.lastElementChild.innerHTML = `<p id="Password_errorBx">사용 불가한 비밀번호입니다.</p>`);

            console.log(inputWrap);
        }
    });

    if (check) {
        alert("모든 정보를 입력해 주세요.");
    } else {
        if (newPasswordInput.value || newPasswordCheckInput.value) {
            newPasswordInput.value !== newPasswordCheckInput.value &&
                inputValueCheck.push(newPasswordCheckInput);
        }
        !isValidPhone(phoneInput.value) && inputValueCheck.push(phoneInput);
        !isValidEmail(emailInput.value) && inputValueCheck.push(emailInput);
    }

    if (!inputValueCheck.length && !check) {
        form.submit();
    } else {
        inputValueCheck.forEach((input) => {
            input.closest(".elWrap").classList.add("error");
        });
    }
};

// 이메일 유효성 검사
const isValidEmail = (value) => {
    return /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(value);
};

passwordChangeButton.addEventListener("click", (e) => {
    // 비밀번호 변경 버튼 toggle
    passwordChangeAreas.forEach((area) => {
        area.style.display = area.style.display === "table" ? "none" : "table";
    });
});

tipButton.addEventListener("click", (e) => {
    const tipBox = tipArea.lastElementChild;
    tipArea.classList.toggle("on");
    tipBox.style.display = tipBox.style.display === "none" ? "block" : "none";
});

textInputList.forEach((input) => {
    const inputWrap = input.closest(".elWrap");

    input.addEventListener("focus", (e) => {
        // 새로운 비밀번호 error 클래스 추가/삭제
        if (newPasswordInput.value) {
            inputWrap.classList.remove("ok");
            inputWrap.classList.remove("on");
            inputWrap.classList.add("error");
            return;
        }

        if (phoneInput.value) {
            phoneInput.value = phoneInput.value.replace(/[^0-9]/g, "");
        }

        inputWrap.classList.remove("error");
        inputWrap.classList.remove("ok");
        inputWrap.classList.add("on");
    });

    input.addEventListener("blur", (e) => {
        inputWrap.classList.remove("on");
        inputWrap.classList.remove("error");

        if (input.value) {
            inputWrap.classList.add("ok");
        }

        if (phoneInput.value) {
            phoneInput.value = formatPhoneNumber(phoneInput.value);
        }
    });

    // input error 메세지
    input.addEventListener("input", (e) => {
        const inputWrap = input.closest(".elWrap");
        const errorBox = inputWrap.lastElementChild;

        if (input === newPasswordInput) {
            if (newPasswordInput.value) {
                inputWrap.classList.remove("ok");
                inputWrap.classList.remove("on");
                inputWrap.classList.add("error");
            }

            if (inputWrap.classList.contains("error")) {
                if (hasKorean(newPasswordInput.value)) {
                    errorBox.innerHTML = `
                        <p><strong class="invalid">8~16자의 영문, 숫자, 특수문자만 사용 가능합니다. (공백, 한글 사용불가)</strong></p>
                        `;
                } else if (!isValidPassword(newPasswordInput.value)) {
                    errorBox.innerHTML = `
                        <p><strong class="invalid">8~16자의 영문, 숫자, 특수문자 조합으로 입력해 주세요.</strong></p>
                        `;
                } else {
                    errorBox.innerHTML = `
                    <p id="Password_errorBx">사용할 수 있는 비밀번호입니다.</p>
                `;
                }
            }
        } else {
            inputWrap.classList.remove("error");
            inputWrap.classList.add("on");
        }
    });
});

passwordInputHideButton.addEventListener("click", (e) => {
    passwordInputHideButtonLabel.classList.toggle("chk");

    const condition = passwordInputHideButtonLabel.classList.contains("chk");

    passwordInputHideButtonLabel.textContent = condition ? "표시" : "숨김";

    // 새 비밀번호 input
    textInputList
        .filter((_, index) => index < 2)
        .forEach((input) => {
            input.type = condition ? "text" : "password";
        });
});

phoneInput.addEventListener("input", (e) => {
    phoneInput.value > 11 && (phoneInput.value = phoneInput.value.slice(0, 11));
    phoneInput.value = phoneInput.value.replace(/[^0-9]/g, "");
});

privacyChoiceInput.addEventListener("change", (e) => {
    privacyChoiceInput.value = privacyChoiceInput.checked ? true : false;
});
moreContentButton.addEventListener("click", (e) => {
    moreContentButton.classList.toggle("on");

    const condition = moreContentButton.classList.contains("on");

    moreContentButton.textContent = condition ? "내용닫기" : "내용보기";
    privacyChoiceTerms.style.display = condition ? "block" : "none";
});

submitButton.addEventListener("click", (e) => {
    e.preventDefault();

    const textInputListWithoutPW = textInputList.filter(
        (input) => !input.id.includes("devNewPwd"),
    );
    const target =
        newPasswordInput.value || newPasswordCheckInput.value
            ? textInputList
            : textInputListWithoutPW;

    console.log(target);
    checkValues(target);
});

// 회원탈퇴 버튼
leaveCheckButton.addEventListener("click", (e) => {
    leaveModal.classList.add("active");
});
leaveModalCloseButton.addEventListener("click", (e) => {
    leaveModal.classList.remove("active");
});
leaveButton.addEventListener("click", (e) => {
    e.preventDefault();

    leaveAgreeInput.checked
        ? (location.href = e.target.href)
        : alert("탈퇴를 진행하시려면 동의해주시기 바랍니다.");
});
