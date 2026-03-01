(() => {
    const applyButton = document.getElementById("applyButton");
    if (!applyButton) return;

    const defaultLabel = applyButton.textContent;
    const programId = applyButton.dataset.programId;

    applyButton.addEventListener("click", async () => {
        if (!programId) return;

        applyButton.disabled = true;
        applyButton.textContent = "지원 처리 중...";

        try {
            const response = await fetch("/api/apply", {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"
                },
                body: new URLSearchParams({ experienceProgramId: programId })
            });

            const result = await response.json().catch(() => ({}));
            if (!response.ok || !result.success) {
                throw new Error(result.message || "지원 처리에 실패했습니다.");
            }

            alert("지원이 완료되었습니다.");
            applyButton.textContent = "지원 완료";
        } catch (error) {
            alert(error.message || "지원 처리 중 오류가 발생했습니다.");
            applyButton.disabled = false;
            applyButton.textContent = defaultLabel;
        }
    });
})();
