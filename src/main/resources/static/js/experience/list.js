(function () {
    const form = document.getElementById("filterForm");
    if (!form) {
        return;
    }

    const pageInput = document.getElementById("pageInput");
    const autoSubmitTargets = [
        document.getElementById("statusSelect"),
        document.getElementById("jobSelect"),
        document.getElementById("sortSelect"),
    ];

    autoSubmitTargets.forEach((element) => {
        if (!element) {
            return;
        }

        element.addEventListener("change", () => {
            if (pageInput) {
                pageInput.value = "1";
            }
            form.submit();
        });
    });
})();
