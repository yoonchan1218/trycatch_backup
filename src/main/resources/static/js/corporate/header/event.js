// 공통
// sub-menu-modal
const subMenuButton = document.querySelector(".button-all-menu");
const subMenu = document.querySelector(".all-menu-modal");

// sub-nav
const subNavLis = document.querySelectorAll(".left-menu-list li");

// 기업 로그인 시
// notification-menu
const bell = document.querySelector(".bell");
const notificationMenu = document.querySelector(".notification-menu");
const noNotification = document.querySelector(".no-notification");
const notificationContainer = document.querySelector(
    ".list-notification-container",
);
const notificationCheck = true;

// top-menu-modal
const topMenuModal = document.querySelector(".top-menu-modal");
const corporateName = document.querySelector(".top-menu-modal-button");

// 비로그인 시 로그인 페이지로 이동 (로그인 상태는 서버에서 체크하므로 기본 동작 유지)
function checkLogin() {
    // 서버 Controller에서 세션 체크 후 리다이렉트 처리
}

// 공지사항
const noticeBanner = document.querySelector(".notice-box .swiper-wrapper");
let noticeCount = 0;

if (noticeBanner) {
    noticeBanner.style.height = "260px";
}
//
//

// 공통
// sub-menu-modal
subMenuButton.addEventListener("click", (e) => {
    subMenuButton.classList.toggle("active");
    subMenu.classList.toggle("active");
});

// sub-nav
subNavLis.forEach((li) => {
    li.addEventListener("mouseenter", (e) => {
        e.target.classList.add("active");
    });
    li.addEventListener("mouseleave", (e) => {
        e.target.classList.remove("active");
    });
});

// 기업 로그인 시
if (bell) {
    // notification-menu
    bell.addEventListener("click", (e) => {
        notificationMenu.classList.toggle("active");

        notificationContainer.classList.remove("active");
        noNotification.classList.remove("active");

        (notificationCheck ? notificationContainer : noNotification).classList.add(
            "active",
        );

        // 빨간 점 숨김 + 읽음 처리 API 호출
        const alarmDot = document.getElementById("js-corpAlarmDot");
        if (alarmDot) {
            alarmDot.style.display = "none";
            fetch("/api/corp-alarm/read", { method: "PUT" });
        }
    });
}

// top-menu-modal
if (corporateName) {
    corporateName.addEventListener("click", (e) => {
        topMenuModal.classList.toggle("active");
    });
}

document.addEventListener("click", (e) => {
    if (
        notificationMenu &&
        !e.target.closest(".notification-menu") &&
        !e.target.classList.contains("bell")
    ) {
        notificationMenu.classList.remove("active");
    }

    if (
        topMenuModal &&
        !e.target.closest(".top-menu-modal") &&
        !e.target.classList.contains("top-menu-modal-button")
    ) {
        topMenuModal.classList.remove("active");
    }
});
//

// 공지사항
if (noticeBanner) {
    setInterval(() => {
        noticeCount++;
        noticeBanner.style.transform = `translate(0, -${52 * noticeCount}px)`;
        noticeBanner.style.transition = `transform 0.3s`;

        if (noticeCount === 4) {
            setTimeout(() => {
                noticeBanner.style.transform = `translate(0px)`;
                noticeBanner.style.transition = `transform 0s`;
            }, 300);
            noticeCount = 0;
        }
    }, 3500);
}
