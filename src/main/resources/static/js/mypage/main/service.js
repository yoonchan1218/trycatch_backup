const mainService = (() => {
    const insert = async (file, callback) => {
        const formData = new FormData();
        formData.append("file", file);
        const response = await fetch("/mypage/profile-image", {
            method: "POST",
            body: formData
        });
        const imageUrl = await response.text();
        if (callback) callback(imageUrl);
    };

    const toggleScrap = async (scrapId, scrapStatus) => {
        await fetch("/mypage/scrap/toggle", {
            method: "POST",
            body: new URLSearchParams({ scrapId, scrapStatus })
        });
    };

    const addLatestWatch = async (experienceProgramId) => {
        await fetch("/mypage/latest-watch", {
            method: "POST",
            body: new URLSearchParams({ experienceProgramId })
        });
    };

    return {insert, toggleScrap, addLatestWatch};
})();
