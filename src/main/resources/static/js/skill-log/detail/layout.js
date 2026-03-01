const skillLogLikeLayout = (() => {
    const showCount = (likeCount) => {
        const likeCountWrap = document.querySelector(".viewContWrap .reaction-item em");
        likeCountWrap.textContent = likeCount;
    }

    return {showCount: showCount};
})();