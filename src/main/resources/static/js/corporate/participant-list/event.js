NodeList.prototype.filter = Array.prototype.filter;
NodeList.prototype.map = Array.prototype.map;

// 더보기 버튼
const moreOptionButtons = document.querySelectorAll(".moreOptionButton");
const moreOptions = moreOptionButtons.map(
    (button) => button.nextElementSibling,
);

// 탈락 모달
const feedbackModal = document.querySelector(".feedback-modal");
const feedbackModalCloseButton = document.querySelector(
    ".feedback-modal .close-button",
);
const feedbackModalResultButtons = document.querySelector(
    ".feedback-modal .modal-bts",
);
let targetParticipantId = null;
let rejectType = ""; // "reject" or "withdraw"

moreOptionButtons.forEach((button, index) => {
    // 더보기 메뉴
    button.addEventListener("click", (e) => {
        moreOptions[index].classList.toggle("active");
    });

    // 승급 / 탈락 / 중도탈락
    moreOptions[index].addEventListener("click", (e) => {
        const participantId = button.closest("tr").dataset.id;

        if (e.target.closest(".challengerPromoteBtn")) {
            if (confirm("승급 처리하시겠습니까?")) {
                fetch("/corporate/participant/promote", {
                    method: "POST",
                    headers: { "Content-Type": "application/x-www-form-urlencoded" },
                    body: `participantId=${participantId}`,
                })
                .then((res) => res.json())
                .then((data) => {
                    if (data.success) {
                        alert("승급 처리가 완료되었습니다.");
                        location.reload();
                    }
                });
            }

        } else if (e.target.closest(".challengerDelBtn")) {
            targetParticipantId = participantId;
            rejectType = "withdraw";
            feedbackModal.classList.add("active");
        }
    });
});

// 탈락 모달 닫기 버튼
feedbackModalCloseButton.addEventListener("click", (e) => {
    feedbackModal.classList.remove("active");
    document.getElementById("feedback").value = "";
});

// 탈락 모달 취소/처리 버튼
feedbackModalResultButtons.addEventListener("click", (e) => {
    if (e.target.classList.contains("cancel")) {
        feedbackModal.classList.remove("active");
        document.getElementById("feedback").value = "";
    } else if (e.target.classList.contains("reject")) {
        const feedbackContent = document.getElementById("feedback").value;

        if (rejectType === "reject") {
            fetch("/corporate/participant/reject", {
                method: "POST",
                headers: { "Content-Type": "application/x-www-form-urlencoded" },
                body: `participantId=${targetParticipantId}&feedback=${encodeURIComponent(feedbackContent)}`,
            })
            .then((res) => res.json())
            .then((data) => {
                if (data.success) {
                    alert("탈락 처리가 완료되었습니다.");
                    location.reload();
                }
            });
        } else if (rejectType === "withdraw") {
            fetch("/corporate/participant/withdraw", {
                method: "POST",
                headers: { "Content-Type": "application/x-www-form-urlencoded" },
                body: `participantId=${targetParticipantId}`,
            })
            .then((res) => res.json())
            .then((data) => {
                if (data.success) {
                    alert("중도 탈락 처리가 완료되었습니다.");
                    location.reload();
                }
            });
        }
    }
});

document.addEventListener("click", (e) => {
    moreOptionButtons.forEach((button, index) => {
        if (
            e.target.closest(".moreOptionButton") !== button &&
            !e.target.closest(".more-option")
        ) {
            moreOptions[index].classList.remove("active");
        }
    });
});
