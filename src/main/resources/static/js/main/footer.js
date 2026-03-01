// 공지사항 세로 슬라이드 배너(공용)
const noticeSlide = document.querySelector(".noti-slide .swiper-wrapper");

// ========== 1. 기존 상태 정리 (HTML에 남아있는 찌꺼기 제거) ==========
// 인라인 스타일 초기화
noticeSlide.style.transform = "";
noticeSlide.style.transition = "";

// 기존 duplicate 슬라이드 제거
const duplicates = noticeSlide.querySelectorAll(".swiper-slide-duplicate");
duplicates.forEach((el) => el.remove());

// 기존 클래스들 정리 (active, prev, next 등)
noticeSlide.querySelectorAll(".swiper-slide").forEach((slide) => {
    slide.classList.remove(
        "swiper-slide-active",
        "swiper-slide-prev",
        "swiper-slide-next",
        "swiper-slide-duplicate-prev",
        "swiper-slide-duplicate-next",
    );
    slide.removeAttribute("data-swiper-slide-index");
});

// ========== 2. 슬라이드 복제 ==========
// 원본 슬라이드들 가져오기
const originalSlides = noticeSlide.querySelectorAll(".swiper-slide");
const firstSlideContent = originalSlides[0].innerHTML;
const lastSlideContent = originalSlides[originalSlides.length - 1].innerHTML;

// 첫번째 공지사항을 마지막에 추가
const firstClone = document.createElement("li");
firstClone.className = "swiper-slide";
firstClone.style.height = "24px";
firstClone.innerHTML = firstSlideContent;
noticeSlide.appendChild(firstClone);

// 마지막 공지사항을 맨 앞에 추가
const lastClone = document.createElement("li");
lastClone.className = "swiper-slide";
lastClone.style.height = "24px";
lastClone.innerHTML = lastSlideContent;
noticeSlide.prepend(lastClone);

// ========== 3. 슬라이드 애니메이션 ==========
let count = 1;
const slideHeight = 24;
const totalSlides = noticeSlide.querySelectorAll(".swiper-slide").length;

// 초기 위치 설정
noticeSlide.style.transform = `translateY(-${slideHeight * count}px)`;

const autoSlide = () => {
    count++;
    noticeSlide.style.transition = "transform 0.5s";
    noticeSlide.style.transform = `translateY(-${slideHeight * count}px)`;

    // 마지막 슬라이드(복제된 첫번째)에 도달하면 처음으로 리셋
    if (count >= totalSlides - 1) {
        setTimeout(() => {
            noticeSlide.style.transition = "transform 0s";
            noticeSlide.style.transform = `translateY(-${slideHeight}px)`;
            count = 1;
        }, 500);
    }
};

let autoSlideInterval = setInterval(autoSlide, 3000);

// 마우스 올리면 멈춤
noticeSlide.addEventListener("mouseenter", () => {
    clearInterval(autoSlideInterval);
});

// 마우스 떼면 다시 시작
noticeSlide.addEventListener("mouseleave", () => {
    autoSlideInterval = setInterval(autoSlide, 3000);
});
