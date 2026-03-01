(function () {
    const applyButton = document.getElementById("applyButton");
    if (!applyButton) {
        return;
    }

    applyButton.addEventListener("click", async () => {
        const programId = applyButton.dataset.programId;
        if (!programId) {
            alert("프로그램 정보가 올바르지 않습니다.");
            return;
        }

        if (!confirm("해당 프로그램에 지원하시겠습니까?")) {
            return;
        }

        applyButton.disabled = true;

        try {
            const body = new URLSearchParams({ experienceProgramId: programId }).toString();
            const response = await fetch("/api/apply", {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8",
                },
                body,
            });

            const result = await response.json();
            if (result.success) {
                alert("지원이 완료되었습니다.");
                window.location.reload();
                return;
            }

            alert(result.message || "지원에 실패했습니다.");
            applyButton.disabled = false;
        } catch (error) {
            alert("요청 처리 중 오류가 발생했습니다. 잠시 후 다시 시도해주세요.");
            applyButton.disabled = false;
        }
    });
})();
