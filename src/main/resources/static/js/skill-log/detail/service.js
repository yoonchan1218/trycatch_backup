const skillLogLikeService = (() => {
    const getCount = async ({skillLogId, memberId}, callback) => {
        const response = await fetch(`/api/skill-log/like?skillLogId=${skillLogId}&memberId=${memberId}`);
        const likeCount = await response.json();

        if(callback) {
            callback(likeCount);
        }
    }

    return {getCount: getCount};
})();