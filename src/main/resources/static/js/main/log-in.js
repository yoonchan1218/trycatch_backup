// 로그인 유지
const keepId = document.querySelector(".keep-id");
const idCheckbox = document.getElementById("lb_idSave");

idCheckbox.addEventListener("change", (e) => {
    if (idCheckbox.checked) {
        if (
            !confirm(
                "개인정보 보호를 위해 본인 기기에서만 사용하세요. 이 기능을 이용함으로써 발생하는 보안 문제의 책임은 본인에게 있습니다.",
            )
        ) {
            idCheckbox.checked = false;
        }
    }
});

// 개인회원 기업회원 바꾸기
const individualButton = document.getElementById("devMemTab").firstElementChild;
const companyButton = document.getElementById("devMemTab").lastElementChild;
const inpTxtId = document.querySelector(".inpTxt.input-id");
const kakaoLogin = document.getElementById("btnKaLogin");
const loginForm = document.getElementById("login-form");

individualButton.addEventListener("click", (e) => {
    individualButton.classList.add("on");
    companyButton.classList.remove("on");
    inpTxtId.classList.remove("corp");
    keepId.style.display = "block";
    kakaoLogin.style.display = "block";
    loginForm.action = "/main/log-in";
});

companyButton.addEventListener("click", (e) => {
    individualButton.classList.remove("on");
    companyButton.classList.add("on");
    inpTxtId.classList.add("corp");
    keepId.style.display = "none";
    kakaoLogin.style.display = "none";
    loginForm.action = "/main/corp-log-in";
});

// 로그인 실패 시 에러 메시지 표시
if (login === "fail") {
    document.querySelector(".login-input").classList.add("error");
}

