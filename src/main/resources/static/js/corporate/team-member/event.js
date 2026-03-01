// 팀원 초대
const invitationButton = document.getElementById("invitation_button");
const invitationModal = document.querySelector(".company-invitation-modal");
const invitationModalCloseButton = document.querySelector(
    ".company-invitation-modal .close-button",
);
const invitationTeamMemberButton = document.querySelector(
    ".company-invitation-modal .modal-btn",
);
const invitationInput = form.invitation_mail;

// 팀원 메뉴 버튼
const teamMemberRows = document.querySelectorAll(
    ".teamWrap .mtcSchListTb table tbody tr:not(.no-team-member)",
);

// 팀원 초대 모달 열기
invitationButton.addEventListener("click", (e) => {
    invitationModal.classList.add("active");
});
// 팀원 초대 모달 닫기
invitationModalCloseButton.addEventListener("click", (e) => {
    invitationModal.classList.remove("active");
});

// 팀원 초대 - 폼 제출
invitationTeamMemberButton.addEventListener("click", async (e) => {
    if (!invitationInput.value) {
        alert("초대할 팀원의 이메일을 입력해주세요.");
        return;
    }
    // 이메일 유효성 검사
    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    if (!emailRegex.test(invitationInput.value)) {
        alert("올바른 이메일 주소를 입력해주세요.");
        return;
    }
    // 이메일 확인
    if (!confirm(invitationInput.value + " 으로 초대하시겠습니까?")) {
        return;
    }

    const response = await fetch("/corporate/team-member/invite", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: "invitation_mail=" + encodeURIComponent(invitationInput.value),
    });
    const result = await response.json();

    if (result.success) {
        alert(result.message);
        location.reload();
    } else {
        alert(result.message);
        invitationInput.value = "";
    }
});

// 팀원 내보내기
teamMemberRows.forEach((row) => {
    const moreOptionLayer = row.querySelector(".more-option");
    if (!moreOptionLayer) return;

    row.addEventListener("click", (e) => {
        if (e.target.closest(".moreOptionButton")) {
            moreOptionLayer.classList.toggle("active");
        }

        if (e.target.closest(".memberDelBtn")) {
            if (!confirm("정말로 팀원을 내보내시겠습니까?")) return;

            const memberId = e.target.closest(".memberDelBtn").dataset.memberId
                || row.dataset.memberId;

            const removeForm = document.createElement("form");
            removeForm.method = "POST";
            removeForm.action = "/corporate/team-member/remove";

            const input = document.createElement("input");
            input.type = "hidden";
            input.name = "memberId";
            input.value = memberId;

            removeForm.appendChild(input);
            document.body.appendChild(removeForm);
            removeForm.submit();
        }
    });
});

document.addEventListener("click", (e) => {
    if (!e.target.closest(".moreOptionButton") && !e.target.closest(".more-option")) {
        teamMemberRows.forEach((row) => {
            const moreOption = row.querySelector(".more-option");
            if (moreOption) moreOption.classList.remove("active");
        });
    }
});
