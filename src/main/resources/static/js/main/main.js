// 광고 배너

// ============================================
// 탭 메뉴 (MY, 체험공고, 기술블로그, QnA)
// ============================================
const secondBannerSections = document.querySelectorAll(".personal-tab-item a");
const secondBannerDiv = document.querySelectorAll(
    ".tab-cont.personal-contents-item",
);

secondBannerSections.forEach((secondBannerSection, i) => {
    const li = secondBannerSection.closest("li");

    secondBannerSection.addEventListener("click", (e) => {
        e.preventDefault();

        // 1. 모든 li에서 active 제거
        secondBannerSections.forEach((btn) => {
            btn.closest("li").classList.remove("active");
        });

        // 2. 모든 콘텐츠에서 active 제거
        secondBannerDiv.forEach((div) => {
            div.classList.remove("active");
        });

        // 3. 클릭한 li에 active 추가
        li.classList.add("active");

        // 4. 같은 인덱스의 콘텐츠에 active 추가
        secondBannerDiv[i].classList.add("active");

        // 5. 슬라이더가 있으면 업데이트
        setTimeout(() => {
            const activeSwiper =
                secondBannerDiv[i].querySelector(".swiper-container");
            if (activeSwiper && activeSwiper.swiper) {
                activeSwiper.swiper.update();
            }
        }, 100);
    });
});

// MY 슬라이더 (my-list-wrap)

const mySwiper = new Swiper(".my-list-wrap", {
    slidesPerView: 2,
    slidesPerGroup: 1,
    spaceBetween: 16,
    loop: true,
    autoplay: {
        delay: 3000,
        disableOnInteraction: false,
    },
    // 탭 전환 시 크기 재계산을 위한 옵션
    observer: true,
    observeParents: true,
});

// 체험공고 슬라이더 (instance-swipwe-6)

const smartfitSwiper = new Swiper(".instance-swipwe-6", {
    slidesPerView: 3,
    spaceBetween: 16,
    loop: true,
    autoplay: {
        delay: 3000,
        disableOnInteraction: false,
    },
    navigation: {
        nextEl: ".instance-next6",
        prevEl: ".instance-prev6",
    },
    // 탭 전환 시 크기 재계산을 위한 옵션
    observer: true,
    observeParents: true,
});

// 기술블로그 슬라이더 (instance-swipwe-3)

const blogSwiper = new Swiper(".instance-swipwe-3", {
    slidesPerView: 3,
    spaceBetween: 16,
    loop: true,
    autoplay: {
        delay: 3000,
        disableOnInteraction: false,
    },
    navigation: {
        nextEl: ".instance-next3",
        prevEl: ".instance-prev3",
    },
    // 탭 전환 시 크기 재계산을 위한 옵션
    observer: true,
    observeParents: true,
});

// QnA 슬라이더 (instance-swipwe-5)
const qnaSwiper = new Swiper(".instance-swipwe-5", {
    slidesPerView: 3,
    spaceBetween: 16,
    loop: true,
    autoplay: {
        delay: 3000,
        disableOnInteraction: false,
    },
    navigation: {
        nextEl: ".instance-next5",
        prevEl: ".instance-prev5",
    },
    // 탭 전환 시 크기 재계산을 위한 옵션
    observer: true,
    observeParents: true,
});

// 기업 체험 공채 슬라이더 (1000대 기업) - 무한 루프
// Swiper 대신 CSS 애니메이션 사용

const top1000Container = document.querySelector(".top1000-swiper");
const top1000Wrapper = top1000Container.querySelector(".swiper-wrapper");

// 슬라이드 복제해서 무한 루프 효과
const slides = top1000Wrapper.innerHTML;
top1000Wrapper.innerHTML = slides + slides;

// 마우스 hover 시 멈춤
top1000Container.addEventListener("mouseenter", () => {
    top1000Wrapper.style.animationPlayState = "paused";
});

top1000Container.addEventListener("mouseleave", () => {
    top1000Wrapper.style.animationPlayState = "running";
});

// 채용 바로가기 버튼

const expGoButtons = document.querySelectorAll(".button-recruit");

expGoButtons.forEach((expGoButton) => {
    expGoButton.addEventListener("click", (e) => {
        location.href = "";
    });
});
