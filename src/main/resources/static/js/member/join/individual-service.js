const memberService = (() => {
    const checkEmail = async (memberEmail, callback) => {
        const response = await fetch(`/main/check-email?memberEmail=${memberEmail}`)
        const isAvailable = await response.text() === "true"

        if (callback) {
            callback(isAvailable);
        }
    }

    const checkId = async (memberId, callback) => {
        const response = await fetch(`/main/check-id?memberId=${memberId}`)
        const isAvailable = await response.text() === "true"

        if (callback) {
            callback(isAvailable);
        }
    }

    return {checkEmail: checkEmail, checkId: checkId};
})()
