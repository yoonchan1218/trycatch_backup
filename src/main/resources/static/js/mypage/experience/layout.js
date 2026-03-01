const experienceLayout = (() => {
    // 1. 지원취소 팝업
    const openCancelPopup = (dimmedDiv, popupApplyCancel) => {
        dimmedDiv.style.position = "fixed";
        popupApplyCancel.style.display = "block";
    };

    const closeCancelPopup = (dimmedDiv, popupApplyCancel) => {
        dimmedDiv.style.position = "relative";
        popupApplyCancel.style.display = "none";
    };

    // 2. 취소사유 드롭다운
    const toggleReasonDrop = (applyListDrop) => {
        applyListDrop.classList.toggle("visible");
    };

    const selectReason = (index, buttonText, applyListButtonSpan, applyCancelReason, applyListDrop) => {
        applyListButtonSpan.textContent = buttonText;
        applyListDrop.classList.remove("visible");
        if (index !== 3) {
            applyCancelReason.style.display = "none";
        } else {
            applyCancelReason.style.display = "block";
        }
    };

    // 3. 조회기간 버튼
    const updatePeriodActive = (selectDueButton, selectDueButtons) => {
        selectDueButtons.forEach((btn) => btn.classList.remove("on"));
        selectDueButton.classList.add("on");
    };

    // 4. 공통 드롭다운 (진행여부 등)
    const toggleCommonDrop = (dropDownDiv) => {
        // 다른 드롭다운 닫기
        document.querySelectorAll(".lyItems").forEach((div) => {
            if (div !== dropDownDiv) div.style.display = "none";
        });
        dropDownDiv.style.display = (dropDownDiv.style.display === "block" ? "none" : "block");
    };

    const selectCommonDropItem = (dropDownButton, dropDownDiv, itemText) => {
        dropDownButton.textContent = itemText;
        dropDownDiv.style.display = "none";
    };

    const closeAllDrops = () => {
        document.querySelectorAll(".lyItems").forEach((div) => {
            div.style.display = "none";
        });
    };

    // 5. 지원이력서 모달
    const openResumeModal = (resumeModalOverlay) => {
        resumeModalOverlay.classList.add("active");
        document.body.style.overflow = "hidden";
    };

    const closeResumeModal = (resumeModalOverlay) => {
        resumeModalOverlay.classList.remove("active");
        document.body.style.overflow = "";
    };

    // 6. 지원취소됨 표시 + 지원자수 감소
    const showCancelled = (cancelBtn) => {
        const tr = cancelBtn.closest("tr");
        if (tr) {
            tr.querySelectorAll(".item").forEach((item) => {
                const text = item.textContent.trim();
                if (text.startsWith("지원자수 :")) {
                    const match = text.match(/지원자수 : (\d+)\/(\d+)/);
                    if (match) {
                        const updated = Math.max(0, parseInt(match[1], 10) - 1);
                        item.textContent = `지원자수 : ${updated}/${match[2]}`;
                    }
                }
            });
        }
        const statusEl = tr ? tr.querySelector(".apply-status .item.status") : null;
        if (statusEl) statusEl.textContent = "취소";
        const td = cancelBtn.closest("td");
        if (td) {
            td.innerHTML = "<span>지원취소됨</span>";
        }
    };

    // 7. 상단 상태별 카운트 감소
    const decrementStatusCount = (applyStatus) => {
        const idMap = {
            "applied": "count-applied",
            "document_pass": "count-document-pass",
            "document_fail": "count-document-fail"
        };
        const el = document.getElementById(idMap[applyStatus]);
        if (el) {
            el.textContent = Math.max(0, parseInt(el.textContent, 10) - 1);
        }
    };

    // 8. 상태별 집계 숫자 갱신 (필터된 전체 데이터 기준)
    const updateStatusCounts = (result) => {
        const appliedEl = document.getElementById("count-applied");
        const passEl = document.getElementById("count-document-pass");
        const failEl = document.getElementById("count-document-fail");
        const activityDoneEl = document.getElementById("count-activity-done");
        if (appliedEl) appliedEl.textContent = result.appliedCount ?? 0;
        if (passEl) passEl.textContent = result.documentPassCount ?? 0;
        if (failEl) failEl.textContent = result.documentFailCount ?? 0;
        if (activityDoneEl) activityDoneEl.textContent = result.activityDoneCount ?? 0;
    };

    const renderPagination = (criteria, onPageClick) => {
        const paginationUl = document.querySelector(".tplPagination ul");
        if (!paginationUl || !criteria) return;

        const { startPage, endPage, page, realEnd } = criteria;
        let html = "";

        if (realEnd > 10 && startPage > 1) {
            html += `<li><a href="javascript:void(0);" class="dev-page-btn" data-page="${startPage - 1}">&lt;</a></li>`;
        }

        for (let i = startPage; i <= endPage; i++) {
            if (i === page) {
                html += `<li><span class="now">${i}</span></li>`;
            } else {
                html += `<li><a href="javascript:void(0);" class="dev-page-btn" data-page="${i}">${i}</a></li>`;
            }
        }

        if (realEnd > 10 && endPage < realEnd) {
            html += `<li><a href="javascript:void(0);" class="dev-page-btn" data-page="${endPage + 1}">&gt;</a></li>`;
        }

        paginationUl.innerHTML = html;

        paginationUl.querySelectorAll(".dev-page-btn").forEach(btn => {
            btn.addEventListener("click", () => {
                const targetPage = parseInt(btn.dataset.page, 10);
                if (onPageClick) onPageClick(targetPage);
            });
        });
    };

    const renderApplyList = (applies) => {
        const tbody = document.querySelector(".user-table.applymng-list tbody");
        if (!tbody) return;

        if (!applies || applies.length === 0) {
            tbody.innerHTML = `<tr><td colspan="5" style="text-align:center; padding: 30px;">체험 지원 내역이 없습니다.</td></tr>`;
            return;
        }

        tbody.innerHTML = applies.map(apply => {
            const cancelCellMap = {
                cancelled: `<span>지원취소됨</span>`,
                document_pass: `<span>마감</span>`,
                activity_done: `<span>마감</span>`
            };
            const cancelCell = cancelCellMap[apply.applyStatus] ||
                `<button class="btn btnGyBd devBtnCancel devBtnOddInfo" type="button"
                       data-idx="${apply.applyId}" data-status="${apply.applyStatus}">
                       <span>지원취소</span>
                   </button>`;

            const deadlineDiv = apply.experienceProgramDeadline
                ? `<div class="extra">(~${apply.experienceProgramDeadline})</div>`
                : "";

            return `
                <tr>
                    <td class="vertical-align-top">
                        <div class="vertical-align-middle apply-status">
                            <div class="inner">
                                <div class="item status">${apply.applyStatusLabel}</div>
                                <div class="item date">${apply.applyCreatedDatetime || "-"}</div>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="vertical-align-middle apply-board">
                            <div class="inner">
                                <div class="company">
                                    <span>${apply.corpCompanyName} : ${apply.experienceProgramLevelLabel}</span>
                                </div>
                                <div class="description">
                                    <a href="/experience/program/${apply.experienceProgramId}" target="_blank">${apply.experienceProgramTitle}</a>
                                </div>
                                <div class="meta">
                                    <div class="item applicants-field">지원분야 : ${apply.experienceProgramJob || ""}</div>
                                    <div class="item">지원자수 : ${apply.applicantCount}/${apply.experienceProgramRecruitmentCount}</div>
                                </div>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="vertical-align-middle apply-progress">
                            <div class="inner">
                                <div class="status">${apply.experienceProgramStatusLabel}</div>
                                ${deadlineDiv}
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="vertical-align-middle reading">
                            <div class="inner">
                                <div class="read-not">열람</div>
                            </div>
                        </div>
                    </td>
                    <td>${cancelCell}</td>
                </tr>`;
        }).join("");
    };

    return {
        openCancelPopup, closeCancelPopup, toggleReasonDrop, selectReason,
        updatePeriodActive, toggleCommonDrop, selectCommonDropItem, closeAllDrops,
        openResumeModal, closeResumeModal, showCancelled, decrementStatusCount,
        renderApplyList, renderPagination, updateStatusCounts
    };
})();
