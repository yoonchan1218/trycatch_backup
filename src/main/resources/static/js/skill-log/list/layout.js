const skillLogLayout = (() => {
    const showList = (skillLogWithPaging, queryString) => {
        const skillLogListArea = document.querySelector(".joodJobList");
        const pageWrap = document.querySelector(".tplPagination");
        const totalSpan = document.querySelector(".stTopTitArea .total .num");
        const pageList = document.createElement("ul");
        let text = ``;

        const skillLogs = skillLogWithPaging.skillLogs;
        const criteria = skillLogWithPaging.criteria;

        skillLogListArea.innerHTML = "";
        pageWrap.innerHTML = "";

        skillLogs.forEach((skillLog) => {
            const thumbnail = skillLog.skillLogFiles[0]
                ? `/api/files/display?filePath=${skillLog.skillLogFiles[0].filePath}&fileName=${skillLog.skillLogFiles[0].fileName}`
                : "/images/none.png";

            text += `
                <li>
                    <a href="/skill-log/detail?id=${skillLog.id}">
                        <p class="thumb">
                            <img src="${thumbnail}" style="height: 100%;"/>
                        </p>
                        <dl>
                            <dt>
                                <strong>${skillLog.skillLogTitle}</strong>
                            </dt>
                            <dd class="tx">${skillLog.memberName}</dd>
                            <dd class="listSubItem">
                                <div class="tagList">`;

            skillLog.tags.forEach((tag) => {
                text += `<span class="item">#${tag.tagName}</span>`;
            })

            text += `
                                </div>
                                <span class="item dateItem">${skillLog.createdDatetime}</span>
                                <span class="item viewItem">
                                    <span class="stSpBefore stIcnView">
                                        <span class="skip">조회수</span>
                                        ${skillLog.skillLogViewCount}
                                    </span>
                                </span>
                            </dd>
                        </dl>
                    </a>
                </li>
            `;
        });
        skillLogListArea.innerHTML += text;

        // 다음/이전, 페이지 버튼
        if(criteria.startPage > 1){
            const previousButton = document.createElement("p");
            previousButton.innerHTML = `
                <a href="${criteria.startPage - 1}" class="tplBtn btnPgn btnPgnPrev paging">
                    <span class="blind">이전</span>
                    <i class="ico"></i>
                </a>
            `;
            pageWrap.appendChild(previousButton);
        }

        text = ``;
        for(let i = criteria.startPage; i <= criteria.endPage; i++){
            if(criteria.page === i){
                text += `
                    <li>
                        <span class="now">${i}</span>
                    </li>
                `;
                continue;
            }
            text += `
                <li>
                    <a href="${i}" class="paging">${i}</a>
                </li>
            `;
        }
        pageList.innerHTML = text;
        pageWrap.appendChild(pageList);

        if(criteria.endPage !== criteria.realEnd) {
            if(skillLogs.length < 1) return;

            const nextButton = document.createElement("p");
            nextButton.innerHTML = `
                <a href="${criteria.endPage + 1}" class="tplBtn btnPgnNext paging">
                    <span class="blind">다음</span>
                    <i class="ico"></i>
                </a>
            `;
            pageWrap.appendChild(nextButton);
        }
        totalSpan.textContent = criteria.total;
    }

    const showHotList = (skillLogWithPaging, queryString) => {
        const headlineWrap = document.querySelector(".headline");
        let text = ``;

        let skillLogs = skillLogWithPaging.skillLogs;

        skillLogs = skillLogs.slice(0, 5);

        skillLogs.forEach((skillLog, i) => {
            if (i === 0) {
                const thumbnail = skillLog.skillLogFiles[0]
                    ? `/api/files/display?filePath=${skillLog.skillLogFiles[0].filePath}&fileName=${skillLog.skillLogFiles[0].fileName}`
                    : "/images/none.png";

                text += `
                    <div class="headline-item image">
                        <a href="/skill-log/detail?id=${skillLog.id}">
                            <div class="thumbnail">
                                <img src="${thumbnail}" style="height: 100%;"/>
                            </div>
                            <div class="title">${skillLog.skillLogTitle}</div>
                        </a>
                    </div>
                `;
                return;
            }
            text += `
                <div class="headline-item">
                    <a href="/skill-log/detail?id=${skillLog.id}">
                        <div class="badge attention">인기</div>
                        <div class="title">${skillLog.skillLogTitle}</div>
                    </a>
                </div>
            `;
        });
        headlineWrap.innerHTML += text;
    }

    return {
        showList: showList,
        showHotList: showHotList,
    };
})();