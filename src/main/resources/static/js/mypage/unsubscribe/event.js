const lnbGroupAtags = document.querySelectorAll(".lnbGroup a");
lnbGroupAtags.forEach((lnbGroupAtag) => {
    lnbGroupAtag.addEventListener("click", () => {
        unsubscribeLayout.updateLnbColor(lnbGroupAtag);
    });
});

const inputAgreeCheckbox = document.querySelector("#chkLeaveAgree");
const inputAgreeCheckboxLabel = inputAgreeCheckbox.nextElementSibling;
inputAgreeCheckbox.addEventListener("click", () => {
    unsubscribeLayout.toggleCheckbox(inputAgreeCheckboxLabel);
});

const leaveNameInput = document.getElementById("memberName");
const leaveNameSpan = leaveNameInput.previousElementSibling;
leaveNameInput.addEventListener("focus", () => {
    unsubscribeLayout.hidePlaceholder(leaveNameSpan);
});
leaveNameInput.addEventListener("blur", () => {
    unsubscribeLayout.showPlaceholder(leaveNameSpan, leaveNameInput.value);
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

    unsubscribeService.delete_(leaveForm);
});
