const lnbGroupAtags = document.querySelectorAll(".lnbGroup a");

lnbGroupAtags.forEach((lnbGroupAtag) => {
    lnbGroupAtag.addEventListener("click", () => {
        if (lnbGroupAtag.classList.contains("on")) {
            lnbGroupAtag.style.color = "#7BA882";
        } else {
            lnbGroupAtag.style.color = "#000";
        }
    });
});

const inputAgreeCheckbox = document.querySelector("#chkLeaveAgree");
const inputAgreeCheckboxLabel = inputAgreeCheckbox.nextElementSibling;
inputAgreeCheckbox.addEventListener("click", () => {
    inputAgreeCheckboxLabel.classList.toggle("chk");
});

const leaveNameInput = document.getElementById("memberName");
const leaveNameSpan = leaveNameInput.previousElementSibling;
leaveNameInput.addEventListener("focus", () => {
    leaveNameSpan.style.display = "none";
});
leaveNameInput.addEventListener("blur", () => {
    leaveNameSpan.style.display = leaveNameInput.value ? "none" : "block";
});

const leaveForm = document.getElementById("form");
const leaveSubmitButton = document.querySelector(".btnBlue");
leaveSubmitButton.addEventListener("click", (e) => {
    e.preventDefault();

    if (!leaveNameInput.value.trim()) {
        alert("탈퇴 요청자 이름을 입력해주세요.");
        return;
    }

    if (!inputAgreeCheckbox.checked) {
        alert("탈퇴 주의사항을 확인하고 동의해 주세요.");
        return;
    }

    if (!confirm("탈퇴를 진행하시겠습니까?")) {
        return;
    }

    leaveForm.action = "/mypage/unsubscribe";
    leaveForm.method = "post";
    leaveForm.submit();
});
