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

    const checkCompanyName = async (corpCompanyName, callback) => {
        const response = await fetch(`/main/check-company-name?corpCompanyName=${corpCompanyName}`)
        const isAvailable = await response.text() === "true"

        if (callback) {
            callback(isAvailable);
        }
    }

    const checkBusinessNumber = async (corpBusinessNumber, callback) => {
        const response = await fetch(`/main/check-business-number?corpBusinessNumber=${corpBusinessNumber}`)
        const isAvailable = await response.text() === "true"

        if (callback) {
            callback(isAvailable);
        }
    }

    return {checkEmail: checkEmail, checkId: checkId, checkCompanyName: checkCompanyName, checkBusinessNumber: checkBusinessNumber};
})()
