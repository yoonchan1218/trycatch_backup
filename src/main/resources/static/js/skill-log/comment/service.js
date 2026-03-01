const commentService = (() => {

    // 추가
    const write = async (formData) => {
        await fetch("/api/skill-log/comments/write", {
            method: "POST",
            body: formData
        });
    }

    // 댓글 목록
    const getList = async (page, skillLogId, memberId, callback) => {
        const response = await fetch(`/api/skill-log/comments/comment-list/${page}?id=${skillLogId}&memberId=${memberId}`);
        const comments = await response.json();

        if(callback){
            callback(comments, memberId);
        }
    }
    // 대댓글 목록
    const getNestedList = async (page, skillLogId, commentId, memberId, callback) => {
        const response = await fetch(`/api/skill-log/comments/nested-comment-list/${page}?skillLogId=${skillLogId}&commentId=${commentId}&memberId=${memberId}`);
        const comments = await response.json();

        if(callback){
            callback(comments, memberId, commentId);
        }
    }

    // 수정
    const update = async (formData) => {
        await fetch(`/api/skill-log/comments/${formData.get("id")}`, {
            method: "PUT",
            body: formData
        })
    }

    // 삭제
    const remove = async (id) => {
        await fetch(`/api/skill-log/comments/${id}`, {
            method: "DELETE"
        });
    }

    // 좋아요 개수
    const getLikeCount = async ({skillLogCommentId, memberId}, callback) => {
        const response = await fetch(`/api/skill-log/comments/like?skillLogCommentId=${skillLogCommentId}&memberId=${memberId}`);
        const likeCount = await response.json();

        if(callback) {
            callback(likeCount, skillLogCommentId);
        }
    }

    return {
        write: write,
        getList: getList,
        getNestedList: getNestedList,
        update: update,
        remove: remove,
        getLikeCount: getLikeCount
    };
})();












