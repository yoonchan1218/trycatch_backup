const experienceService = (() => {
    const cancelApply = async (applyId, callback) => {
        try {
            const response = await fetch("/mypage/experience/cancel?applyId=" + applyId, {
                method: "POST",
            });
            const result = await response.json();
            if (callback) callback(result);
        } catch (e) {
            if (callback) callback(false);
        }
    };

    const filterApplyList = async (params, page, callback) => {
        try {
            const urlParams = new URLSearchParams();
            urlParams.append("page", page || 1);
            Object.entries(params).forEach(([k, v]) => {
                if (v !== null && v !== undefined && v !== "") urlParams.append(k, v);
            });
            const response = await fetch("/mypage/experience/filter?" + urlParams.toString());
            const result = await response.json();
            if (callback) callback(result);
        } catch (e) {
            if (callback) callback(false);
        }
    };

    return { cancelApply, filterApplyList };
})();
