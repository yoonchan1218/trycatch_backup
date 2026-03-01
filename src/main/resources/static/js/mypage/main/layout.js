const mainLayout = (() => {
    const toggleTip = (tipDiv) => {
        tipDiv.classList.add("close");
    };

    const toggleScrap = (btn) => {
        btn.classList.toggle("on");
        btn.classList.toggle("tplBtnScrOff");
        btn.classList.toggle("tplBtnScrOn");
        btn.dataset.scrapStatus = btn.dataset.scrapStatus === "active" ? "inactive" : "active";
    };

    const updateSlider = (wrapper, prevBtn, nextBtn, currentPage, totalPages, moveDistance) => {
        const translateX = -currentPage * moveDistance;
        wrapper.style.transform = `translate3d(${translateX}px, 0px, 0px)`;
        wrapper.style.transitionDuration = "300ms";
        prevBtn.classList.toggle("swiper-button-disabled", currentPage === 0);
        nextBtn.classList.toggle("swiper-button-disabled", currentPage === totalPages - 1);
    };

    const switchTab = (tabList, tabLists, recentContent, scrapContent, moreLink) => {
        tabLists.forEach((tab) => tab.classList.remove("on"));
        tabList.classList.add("on");

        const flag = tabList.getAttribute("data-flag");
        if (flag === "SC") {
            if (recentContent) recentContent.style.display = "none";
            if (scrapContent) scrapContent.style.display = "block";
            if (moreLink) moreLink.textContent = "더보기";
        } else {
            if (scrapContent) scrapContent.style.display = "none";
            if (recentContent) recentContent.style.display = "block";
            if (moreLink) moreLink.textContent = "더보기";
        }
    };

    const showProfileModal = (profileModal, previewImg, currentProfileImg) => {
        if (previewImg && currentProfileImg) {
            previewImg.src = currentProfileImg.src || "/images/default_photo.png";
        }
        profileModal.classList.add("show");
    };

    const closeProfileModal = (profileModal) => {
        profileModal.classList.remove("show");
    };

    const showProfilePreview = (previewImg, file) => {
        const reader = new FileReader();
        reader.onload = (e) => {
            previewImg.src = e.target.result;
        };
        reader.readAsDataURL(file);
    };

    const confirmProfileImage = (currentProfileImg, previewImg) => {
        if (previewImg && currentProfileImg) {
            currentProfileImg.src = previewImg.src;
        }
    };

    return {
        toggleTip, toggleScrap, updateSlider, switchTab,
        showProfileModal, closeProfileModal, showProfilePreview, confirmProfileImage
    };
})();
