// 알림 유형 필터
const sortSel = document.getElementById("sortSel");
if (sortSel) {
    sortSel.addEventListener("change", () => {
        const type = sortSel.value;
        document.querySelectorAll("#js-alarmFrame .alarmList").forEach((group) => {
            const items = group.querySelectorAll("li[data-type]");
            let visibleCount = 0;
            items.forEach((item) => {
                if (!type || item.dataset.type === type) {
                    item.style.display = "";
                    visibleCount++;
                } else {
                    item.style.display = "none";
                }
            });
            group.style.display = visibleCount > 0 ? "" : "none";
        });
    });
}
