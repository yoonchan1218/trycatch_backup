document.addEventListener("DOMContentLoaded", () => {
    // 팁 닫기
    const tipCloseButton = document.querySelector(".my-status-top .badge .tip .tip-close");
    const tipDiv = document.querySelector(".my-status-top .badge .tip");
    if (tipCloseButton && tipDiv) {
        tipCloseButton.addEventListener("click", () => {
            mainLayout.toggleTip(tipDiv);
        });
    }

    // 스크랩 버튼
    document.addEventListener("click", (e) => {
        const scrapbutton = e.target.closest(".tplBtnTy.js-scrBtn");
        if (scrapbutton) {
            e.preventDefault();
            e.stopPropagation();
            const scrapId = scrapbutton.dataset.scrapId;
            const newStatus = scrapbutton.dataset.scrapStatus === "active" ? "inactive" : "active";
            mainLayout.toggleScrap(scrapbutton);
            mainService.toggleScrap(scrapId, newStatus);
        }
    });

    // 원픽 공고 슬라이더
    const wrapper = document.querySelector(".celebrate_my .swiper-wrapper");
    const prevBtn = document.querySelector(".celebrate-btn-prev");
    const nextBtn = document.querySelector(".celebrate-btn-next");
    const slides = document.querySelectorAll(".celebrate_my .swiper-slide");

    if (wrapper && prevBtn && nextBtn) {
        const slidesPerView = 3;
        const slideWidth = 304;
        const slideGap = 12;
        const moveDistance = (slideWidth + slideGap) * slidesPerView;
        const totalPages = Math.ceil(slides.length / slidesPerView);
        let currentPage = 0;

        nextBtn.addEventListener("click", () => {
            if (currentPage < totalPages - 1) {
                currentPage++;
                mainLayout.updateSlider(wrapper, prevBtn, nextBtn, currentPage, totalPages, moveDistance);
            }
        });

        prevBtn.addEventListener("click", () => {
            if (currentPage > 0) {
                currentPage--;
                mainLayout.updateSlider(wrapper, prevBtn, nextBtn, currentPage, totalPages, moveDistance);
            }
        });

        mainLayout.updateSlider(wrapper, prevBtn, nextBtn, currentPage, totalPages, moveDistance);
    }

    // 공고 클릭 시 최근 본 공고 저장
    document.addEventListener("click", (e) => {
        const link = e.target.closest("a[href*='/experience/program/']");
        if (link) {
            const match = link.href.match(/\/experience\/program\/(\d+)/);
            if (match) {
                mainService.addLatestWatch(match[1]);
            }
        }
    });

    // 탭 전환
    const tabLists = document.querySelectorAll(".mtuTab.devFixedTab ul li");
    const recentContent = document.getElementById("recentContent");
    const scrapContent = document.getElementById("scrapContent");
    const moreLink = document.getElementById("moreLink");

    if (tabLists.length > 0) {
        tabLists.forEach((tabList) => {
            tabList.addEventListener("click", () => {
                mainLayout.switchTab(tabList, tabLists, recentContent, scrapContent, moreLink);
            });
        });
    }

    // 프로필 모달
    const profileModal = document.getElementById("profileModal");
    const profileBtn = document.querySelector(".profile-btn.btnRegist");
    const modalClose = document.getElementById("modalClose");
    const modalCancel = document.getElementById("modalCancel");
    const modalConfirm = document.querySelector(".btn-confirm");
    const profileInput = document.getElementById("profileInput");
    const previewImg = document.getElementById("previewImg");
    const currentProfileImg = document.querySelector(".profile-img .img img");

    if (profileModal && profileBtn) {
        profileBtn.addEventListener("click", () => {
            mainLayout.showProfileModal(profileModal, previewImg, currentProfileImg);
        });

        const close = () => mainLayout.closeProfileModal(profileModal);
        if (modalClose) modalClose.addEventListener("click", close);
        if (modalCancel) modalCancel.addEventListener("click", close);
        if (modalConfirm) {
            modalConfirm.addEventListener("click", () => {
                const file = profileInput?.files[0];
                if (file) {
                    mainService.insert(file, (imageUrl) => {
                        if (imageUrl && currentProfileImg) {
                            currentProfileImg.src = imageUrl;
                        }
                        close();
                    });
                } else {
                    mainLayout.confirmProfileImage(currentProfileImg, previewImg);
                    close();
                }
            });
        }
        profileModal.addEventListener("click", (e) => {
            if (e.target === profileModal) close();
        });

        if (profileInput) {
            profileInput.addEventListener("change", (e) => {
                const file = e.target.files[0];
                if (file && previewImg) {
                    mainLayout.showProfilePreview(previewImg, file);
                }
            });
        }
    }
});
