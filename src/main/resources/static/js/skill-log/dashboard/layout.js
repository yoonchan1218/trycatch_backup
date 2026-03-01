const skillLogLayout = (() => {
    const showList = ({skillLogs, criteria}) => {
        const listWrap = document.querySelector(".lyListBoard .listWrap ul");
        const pageWrap = document.querySelector(".tplPagination.newVer");
        const totalSpan = document.querySelector(".lyListBoard .headerWrap .numBx .num");
        const pageList = document.createElement("ul");
        let text = ``;

        listWrap.innerHTML = "";
        pageWrap.innerHTML = "";

        skillLogs.forEach((skillLog) => {
            text += `
                <li> `;

            if(skillLog.tags.length > 1) {
                text += `
                    <div class="topArea">
                        <div class="labelSwiper swiper-container swiper-container-horizontal">
                            <div class="labelBx swiper-wrapper">`;
                skillLog.tags.forEach((tag) => {
                    text += `
                                <!-- 링크 연결 : 클릭 시 그 태그 검색창으로 이동  -->
                                <a href="" class="label swiper-slide swiper-slide-active" target="_blank">#${tag.tagName}</a>
                    `;
                });
                text += `
                            </div>
                        </div>
                    </div>
                `;
            }

            text += `                    
                    <!-- [Dev] 방문한 링크 : visited 클래스 추가 -->
                    <div class="contArea">
                        <!-- [Dev] 이미지 포함 컨텐츠 : image 클래스 추가 -->
                        <a href="/skill-log/detail?id=${skillLog.id}" class="post-type">
                            <div class="post-title">
                                <span class="lineOne">${skillLog.skillLogTitle}</span>
                            </div>
                            <div class="post-summary">
                                <span class="lineTwo">${skillLog.skillLogContent}</span>
                            </div>
                        </a>
                        <div class="post-cell-box">
                            <span class="cell"><i class="qnaSpB icon-view-eye">조회수</i>${skillLog.skillLogViewCount}</span>
                            <button type="button" class="cell devQstnListLike" data-likecnt="0" data-qstnno="152856">
                                <i class="qnaSpB icon-like">좋아요</i>
                                <em>${skillLog.likeCount}</em>
                            </button>
                            <!-- [Dev] 신규 답변의 경우 icnNew 클래스 추가 -->
                            <span class="cell">
                                <i class="qnaSpB icon-answer-bubble">답변</i><span class="num">${skillLog.commentCount}</span>
                            </span>
                            <span class="cell">${skillLog.createdDatetime}</span>
                        </div>
                        <!--//post-cell-box-->
                        <!-- [Dev] 북마크 툴팁 show - class tooltip-open 추가 -->
                        <div class="post-util has-tooltip">
                            <div class="post-util-item">
                            </div>
                        </div>
                        <!--//post-util has-tooltip-->
                    </div>
                    <!--//contArea-->
                </li>
             `;
        });

        listWrap.innerHTML = text;
        // #######################################################################################################

        // 다음/이전, 페이지 버튼
        if(criteria.startPage > 1){
            const previousButton = document.createElement("p");
            previousButton.innerHTML = `
            <a href="${criteria.startPage - 1}" class="tplBtn btnPgn btnPgnPrev paging">
                <span class="">이전</span>
<!--                <i class="ico"></i>-->
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
                <span class="">다음</span>
<!--                <i class="ico"></i>-->
            </a>
        `;
            pageWrap.appendChild(nextButton);
        }

        totalSpan.textContent = criteria.total;
    }

    return {showList: showList};
})();



