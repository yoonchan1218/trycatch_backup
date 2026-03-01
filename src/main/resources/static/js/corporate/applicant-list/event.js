// ── 필터 상태 관리 ──────────────────────────────────────────────
const filterState = {
    education: currentEducation || '',
    gender: currentGender || ''
};

// ── 필터 모달 토글 ──────────────────────────────────────────────
const filterBtn = document.querySelector(".btn-filter-toggle");
const filterModal = document.querySelector(".filter-modal");

filterBtn.addEventListener("click", (e) => {
    e.stopPropagation();
    filterModal.classList.toggle("active");
});

filterModal.addEventListener("click", (e) => {
    e.stopPropagation();
});

document.addEventListener("click", () => {
    filterModal.classList.remove("active");
});

// ── 필터 옵션 선택 ──────────────────────────────────────────────
const filterOptions = document.querySelectorAll(".filter-option");
const selectedFiltersBox = document.querySelector(".selected-filters");

// 초기 상태 복원 (URL 파라미터 기반)
filterOptions.forEach((btn) => {
    const filter = btn.dataset.filter;
    const value = btn.dataset.value;
    if ((filter === 'education' && value === filterState.education) ||
        (filter === 'gender' && value === filterState.gender)) {
        btn.classList.add("active");
        addFilterTag(btn.textContent, filter, value);
    }
});

filterOptions.forEach((btn) => {
    btn.addEventListener("click", () => {
        const filter = btn.dataset.filter;
        const value = btn.dataset.value;

        // 같은 그룹 내 다른 옵션 비활성화 (단일 선택)
        filterOptions.forEach((other) => {
            if (other.dataset.filter === filter && other !== btn) {
                other.classList.remove("active");
                removeFilterTag(other.dataset.filter, other.dataset.value);
            }
        });

        const isActive = btn.classList.toggle("active");

        if (isActive) {
            filterState[filter] = value;
            addFilterTag(btn.textContent, filter, value);
        } else {
            filterState[filter] = '';
            removeFilterTag(filter, value);
        }
    });
});

function addFilterTag(label, filter, value) {
    // 중복 방지
    const existing = selectedFiltersBox.querySelector(`[data-filter="${filter}"][data-value="${value}"]`);
    if (existing) return;

    const span = document.createElement("span");
    span.classList.add("selected-filter-tag");
    span.dataset.filter = filter;
    span.dataset.value = value;
    span.textContent = label;

    const delBtn = document.createElement("button");
    delBtn.type = "button";
    delBtn.className = "filter-tag-del";

    const blind = document.createElement("span");
    blind.className = "blind";
    blind.textContent = "삭제";

    delBtn.append(blind);
    span.append(delBtn);
    selectedFiltersBox.append(span);
}

function removeFilterTag(filter, value) {
    const tag = selectedFiltersBox.querySelector(`[data-filter="${filter}"][data-value="${value}"]`);
    if (tag) tag.remove();
}

// ── 필터 태그 X 버튼 (이벤트 위임) ─────────────────────────────
selectedFiltersBox.addEventListener("click", (e) => {
    const delBtn = e.target.closest(".filter-tag-del");
    if (!delBtn) return;

    const tag = delBtn.closest(".selected-filter-tag");
    const filter = tag.dataset.filter;
    const value = tag.dataset.value;

    // 필터 상태 해제
    filterState[filter] = '';

    // 모달 내 옵션 버튼 비활성화
    filterOptions.forEach((btn) => {
        if (btn.dataset.filter === filter && btn.dataset.value === value) {
            btn.classList.remove("active");
        }
    });

    tag.remove();
});

// ── 필터 모달 초기화 버튼 ───────────────────────────────────────
const modalResetBtn = document.querySelector(".btn-filter-reset");
modalResetBtn.addEventListener("click", () => {
    filterOptions.forEach((btn) => btn.classList.remove("active"));
    selectedFiltersBox.innerHTML = "";
    filterState.education = '';
    filterState.gender = '';
});

// ── 필터 검색하기 버튼 ──────────────────────────────────────────
const filterSearchBtn = document.querySelector(".btn-filter-search");
filterSearchBtn.addEventListener("click", () => {
    navigateWithFilters();
});

// ── 활성 필터 표시 영역 ─────────────────────────────────────────
const activeFiltersBar = document.getElementById("activeFilters");
const activeFilterList = activeFiltersBar ? activeFiltersBar.querySelector(".filter-list") : null;

function renderActiveFilters() {
    if (!activeFiltersBar || !activeFilterList) return;

    // 기존 태그 제거 (라벨 제외)
    activeFilterList.querySelectorAll(".filter-list__items").forEach(el => el.remove());

    let hasFilter = false;

    if (currentEducation) {
        appendActiveTag(currentEducation, 'education');
        hasFilter = true;
    }
    if (currentGender) {
        const label = currentGender === 'man' ? '남성' : '여성';
        appendActiveTag(label, 'gender');
        hasFilter = true;
    }
    if (currentKeyword) {
        appendActiveTag(currentKeyword, 'keyword');
        hasFilter = true;
    }

    activeFiltersBar.style.display = hasFilter ? 'flex' : 'none';
}

function appendActiveTag(text, filter) {
    const li = document.createElement("li");
    li.className = "filter-list__items";

    const span = document.createElement("span");
    span.className = "selected-filter-tag-bar";
    span.dataset.filter = filter;
    span.textContent = text;

    const btn = document.createElement("button");
    btn.type = "button";
    btn.className = "active-filter-del";

    const blind = document.createElement("span");
    blind.className = "blind";
    blind.textContent = "삭제";

    btn.append(blind);
    span.append(btn);
    li.append(span);
    activeFilterList.append(li);
}

// 활성 필터 X 버튼 (이벤트 위임)
if (activeFilterList) {
    activeFilterList.addEventListener("click", (e) => {
        const delBtn = e.target.closest(".active-filter-del");
        if (!delBtn) return;

        const tag = delBtn.closest(".selected-filter-tag-bar");
        const filter = tag.dataset.filter;

        // 해당 필터 해제 후 페이지 이동
        const params = new URLSearchParams();
        params.set("programId", programId);
        if (currentStatus) params.set("status", currentStatus);
        if (filter !== 'keyword' && currentKeyword) params.set("keyword", currentKeyword);
        if (filter !== 'education' && currentEducation) params.set("education", currentEducation);
        if (filter !== 'gender' && currentGender) params.set("gender", currentGender);

        location.href = "/corporate/applicant-list?" + params.toString();
    });
}

// 활성 필터 초기화 버튼
const clearFiltersBtn = document.querySelector(".btn-clear-filters");
if (clearFiltersBtn) {
    clearFiltersBtn.addEventListener("click", () => {
        const params = new URLSearchParams();
        params.set("programId", programId);
        if (currentStatus) params.set("status", currentStatus);
        location.href = "/corporate/applicant-list?" + params.toString();
    });
}

renderActiveFilters();

// ── 검색 (이름 키워드) ──────────────────────────────────────────
const searchInput = document.getElementById("searchKeyword");
const searchBtn = document.querySelector(".btn-search");

function doSearch() {
    const keyword = searchInput.value.trim();
    const params = new URLSearchParams();
    params.set("programId", programId);
    if (currentStatus) params.set("status", currentStatus);
    if (keyword) params.set("keyword", keyword);
    if (currentEducation) params.set("education", currentEducation);
    if (currentGender) params.set("gender", currentGender);
    location.href = "/corporate/applicant-list?" + params.toString();
}

searchBtn.addEventListener("click", doSearch);
searchInput.addEventListener("keydown", (e) => {
    if (e.key === "Enter") doSearch();
});

// ── 필터 적용 후 페이지 이동 ────────────────────────────────────
function navigateWithFilters() {
    const keyword = searchInput.value.trim();
    const params = new URLSearchParams();
    params.set("programId", programId);
    if (currentStatus) params.set("status", currentStatus);
    if (keyword) params.set("keyword", keyword);
    if (filterState.education) params.set("education", filterState.education);
    if (filterState.gender) params.set("gender", filterState.gender);
    location.href = "/corporate/applicant-list?" + params.toString();
}

// ── 체크박스 전체선택/해제 ──────────────────────────────────────
const checkAll = document.querySelector(".checkbox-all");
const checkItems = document.querySelectorAll(".checkbox-item");

if (checkAll) {
    checkAll.addEventListener("change", () => {
        checkItems.forEach((check) => {
            check.checked = checkAll.checked;
        });
    });
}

checkItems.forEach((check) => {
    check.addEventListener("change", () => {
        const allChecked = [...checkItems].every(c => c.checked);
        if (checkAll) checkAll.checked = allChecked;
    });
});

// ── 반려하기 버튼 ───────────────────────────────────────────────
const rejectBtn = document.querySelector(".btn-reject");
const rejectConfirmModal = document.querySelector(".modal--reject-confirm");
const rejectDoneModal = document.querySelector(".modal--reject");

if (rejectBtn) {
    rejectBtn.addEventListener("click", () => {
        const checkedIds = [...checkItems].filter(c => c.checked).map(c => Number(c.value));
        if (checkedIds.length === 0) {
            alert("반려할 지원자를 선택해주세요.");
            return;
        }
        rejectConfirmModal.classList.add("active");
    });
}

// 반려 확인 모달 - 취소
const rejectCancelBtn = document.querySelector(".modal__cancel");
if (rejectCancelBtn) {
    rejectCancelBtn.addEventListener("click", () => {
        rejectConfirmModal.classList.remove("active");
    });
}

// 반려 확인 모달 - 확인
const rejectConfirmBtn = document.querySelector(".reject-confirm");
if (rejectConfirmBtn) {
    rejectConfirmBtn.addEventListener("click", async () => {
        const checkedIds = [...checkItems].filter(c => c.checked).map(c => Number(c.value));
        rejectConfirmModal.classList.remove("active");

        const response = await fetch("/corporate/applicant/reject", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(checkedIds)
        });

        const result = await response.json();
        if (result.success) {
            rejectDoneModal.classList.add("active");
        }
    });
}

// 반려 완료 모달 - 확인 후 리로드
const rejectDoneBtn = document.querySelector(".reject-done");
if (rejectDoneBtn) {
    rejectDoneBtn.addEventListener("click", () => {
        rejectDoneModal.classList.remove("active");
        location.reload();
    });
}
