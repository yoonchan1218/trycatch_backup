const smsService = (() => {
    const send = async (phone) => {
        const response = await fetch(`/api/messages/send`, {
            method: "POST",
            body: JSON.stringify({memberPhone: phone}),
            headers: {
                "Content-Type": "application/json;"
            }
        });

        const code = await response.text();
        return code;
    };

    return {send: send};
})();
