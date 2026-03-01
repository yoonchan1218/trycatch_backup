// 변수 선언
// 서비스 Q&A 모달
//      다음/이전 버튼
const serviceSwiperWrapper = document.querySelector(
    ".service-modal .swiper-wrapper",
);
const serviceNextButton = document.querySelector(
    ".service-modal .swiper-button-next",
);
const servicePrevButton = document.querySelector(
    ".service-modal .swiper-button-prev",
);
let serviceCount = 0;

//      닫기 버튼
const serviceModal = document.querySelector(".service-modal");
const serviceCloseButton = document.querySelector(
    ".service-modal .close-button",
);
//

// 서비스 선택
const serviceButtonList = document.querySelectorAll(".service-list li button");
//

// 홍보 세션
// 다음/이전 버튼
const recruitNextButton = document.querySelector(
    ".recruit-section .swiper-button-next",
);
const recruitPrevButton = document.querySelector(
    ".recruit-section .swiper-button-prev",
);
const recruitSwiperWrapper = document.querySelector(
    ".recruit-section .swiper-wrapper",
);
let recruitCount = 0;

// 진행 중인 프로그램
const programDatas = document.querySelectorAll(
    ".recruit-section .data-box ul li",
);
//

// 이벤트
// 서비스 Q&A 모달
if (serviceSwiperWrapper) {
    serviceSwiperWrapper.style.width = `${8 * 730}px`;

    //      이전/다음 버튼
    [serviceNextButton, servicePrevButton].forEach((button) => {
        button.addEventListener("click", (e) => {
            servicePrevButton.classList.remove("swiper-button-disabled");
            serviceNextButton.classList.remove("swiper-button-disabled");

            if (button === serviceNextButton) {
                serviceCount++;
                serviceSwiperWrapper.style.transform = `translate(-${730 * serviceCount}px)`;

                if (serviceCount === 7) {
                    serviceNextButton.classList.add("swiper-button-disabled");
                }
            } else {
                serviceCount--;
                serviceSwiperWrapper.style.transform = `translate(-${730 * serviceCount}px)`;

                if (serviceCount === 0) {
                    servicePrevButton.classList.add("swiper-button-disabled");
                }
            }
        });
    });

    //      닫기 버튼
    if (serviceCloseButton) {
        serviceCloseButton.addEventListener("click", (e) => {
            serviceModal.classList.remove("active");
        });
    }
}
//

// 서비스 선택
serviceButtonList.forEach((button, i) => {
    button.addEventListener("click", (e) => {
        serviceCount = i;

        servicePrevButton.classList.remove("swiper-button-disabled");
        serviceNextButton.classList.remove("swiper-button-disabled");

        if (serviceCount === 0) {
            servicePrevButton.classList.add("swiper-button-disabled");
        } else if (serviceCount === 7) {
            serviceNextButton.classList.add("swiper-button-disabled");
        }

        serviceSwiperWrapper.style.transform = `translate(-${serviceCount * 730}px)`;

        serviceModal.classList.add("active");
    });
});
//

// 홍보 세션
if (recruitSwiperWrapper) {
    recruitSwiperWrapper.style.width = "1787.5px";
    recruitSwiperWrapper.style.transition = "transform 0.3s";

    // 다음/이전 버튼
    [recruitPrevButton, recruitNextButton].forEach((button) => {
        button.addEventListener("click", (e) => {
            recruitNextButton.classList.remove("swiper-button-disabled");
            recruitPrevButton.classList.remove("swiper-button-disabled");

            if (button === recruitNextButton) {
                recruitCount++;

                recruitSwiperWrapper.style.transform = `translate(-${357.5 * recruitCount - (recruitCount === 5 && 214.5)}px)`;

                if (recruitCount === 5) {
                    recruitNextButton.classList.add("swiper-button-disabled");
                }
            } else {
                recruitCount--;
                recruitSwiperWrapper.style.transform = `translate(-${357.5 * recruitCount}px)`;

                if (recruitCount === 0) {
                    recruitPrevButton.classList.add("swiper-button-disabled");
                }
            }
        });
    });
}
//

// 진행 중인 프로그램
programDatas.forEach((data, i) => {
    if (i === 0 || i === 1) {
        data.style.marginTop = "7.8px";
    }

    data.style.transition = "transform 0.3s";

    data.addEventListener("mouseenter", (e) => {
        data.style.transform = "translateY(-8px)";
    });

    data.addEventListener("mouseleave", (e) => {
        data.style.transform = "translateY(0px)";
    });
});
//
