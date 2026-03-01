const unsubscribeLayout = (() => {
    const toggleCheckbox = (label) => {
        label.classList.toggle("chk");
    };

    const hidePlaceholder = (span) => {
        span.style.display = "none";
    };

    const showPlaceholder = (span, value) => {
        span.style.display = value ? "none" : "block";
    };

    const updateLnbColor = (lnbGroupAtag) => {
        if (lnbGroupAtag.classList.contains("on")) {
            lnbGroupAtag.style.color = "#7BA882";
        } else {
            lnbGroupAtag.style.color = "#000";
        }
    };

    return {toggleCheckbox, hidePlaceholder, showPlaceholder, updateLnbColor};
})();
