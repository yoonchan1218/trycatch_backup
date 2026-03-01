document.querySelectorAll(".btn-read-notification").forEach((btn) => {
    btn.addEventListener("click", () => {
        const notificationId = btn.dataset.id;
        if (!notificationId) return;

        fetch("/mypage/notification/read?notificationId=" + notificationId, {
            method: "POST",
        })
            .then((res) => res.json())
            .then((success) => {
                if (success) {
                    const li = btn.closest("li");
                    if (li) li.classList.add("read");
                    btn.remove();
                }
            });
    });
});
