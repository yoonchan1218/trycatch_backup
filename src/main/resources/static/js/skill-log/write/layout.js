const skillLogLayout = (()=>{
    const showExperienceProgramLogs = (experienceProgramLogsWithPaging) => {
        const keywordSearchArea = document.querySelector("#recentRecruit .keyword-search-area");
        const moreButton = document.querySelector(".search-more-button");

        const experienceProgramLogs = experienceProgramLogsWithPaging.experienceProgramLogs;
        const criteria = experienceProgramLogsWithPaging.criteria;

        if(criteria.page === 1) {
            const items = document.querySelectorAll(".keyword-search-item");
            items.forEach((item) => item.remove());
        }

        experienceProgramLogs.forEach((log) => {
            const item = document.createElement("div");
            let text = "";
            const thumbnail = log.experienceProgramFiles[0] ?
                `/api/files/display?filePath=${log.experienceProgramFiles[0].filePath}&fileName=${log.experienceProgramFiles[0].fileName}` : "";

            item.classList.add("keyword-search-item");
            text += `
                <div class="checkboxCommWrap">
                    <input
                        type="checkbox"
                        class="skip"
                        id="recent-item-check_${log.id}"
                        data-gno="${log.id}"
                        data-cname="${log.corpCompanyName}"
                        data-title="${log.experienceProgramTitle}"
                        data-thumbnail="${thumbnail}"
                        style="display: none;"
                        name="experienceProgramId"
                        value="${log.id}"
                    />
                    <label class="qnaSpB" for="recent-item-check_${log.id}"></label>
                </div>
                <a href="" target="_blank">
                    <p class="companyName">
                        <span class="text_on">${log.corpCompanyName}</span>
                        ${log.experienceProgramTitle}
                    </p>
                </a>
            `;
            item.innerHTML = text;

            keywordSearchArea.appendChild(item);
        });

        moreButton.style.display = criteria.hasMore ? "block" : "none";
        moreButton.disabled = !criteria.hasMore;

        return criteria;
    }

    return {showExperienceProgramLogs:showExperienceProgramLogs};
})();