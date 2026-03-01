// 알림 버튼 hover
const notificationItems = document.querySelectorAll(".notification-item");

// 자세히보기 버튼
const notificationButtons = document.querySelectorAll(".notification-footer");

// // 이벤트
// 알림 버튼 hover
notificationItems.forEach((item) => {
    item.addEventListener("mouseenter", (e) => {
        item.classList.add("active");
    });
    item.addEventListener("mouseleave", (e) => {
        item.classList.remove("active");
    });
});

// 자세히보기 버튼
notificationButtons.forEach((button) => {
    const notificationBody = button.previousElementSibling;
    const notificationButtonText = button.firstElementChild.firstElementChild;

    button.addEventListener("click", (e) => {
        if (notificationBody.classList.contains("details")) {
            notificationButtonText.textContent = "자세히보기";
            notificationBody.classList.remove("details");
        } else {
            notificationButtonText.textContent = "접기";
            notificationBody.classList.add("details");
        }
    });
});
