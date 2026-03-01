let certCode = null;
let phoneVerified = false;

document.getElementById("btnSendCert").addEventListener("click", async (e) => {
    const phone = document.getElementById("M_Phone").value;
    certCode = await smsService.send(phone);
    const noticeMsgCert = document.getElementById("notice-msg-cert");
    noticeMsgCert.innerHTML = "인증번호가 전송되었습니다.";
    noticeMsgCert.classList.remove("failure", "success");
    noticeMsgCert.style.display = "block";
});

document.getElementById("btnCheckCert").addEventListener("click", (e) => {
    const inputCode = document.getElementById("Certify_Num").value;
    const noticeMsgCert = document.getElementById("notice-msg-cert");
    if (certCode === inputCode) {
        phoneVerified = true;
        noticeMsgCert.innerHTML = "인증이 완료되었습니다.";
        noticeMsgCert.classList.remove("failure");
        noticeMsgCert.classList.add("success");
        noticeMsgCert.style.display = "block";
    } else {
        phoneVerified = false;
        noticeMsgCert.innerHTML = "잘못된 인증번호입니다.";
        noticeMsgCert.classList.add("failure");
        noticeMsgCert.classList.remove("success");
        noticeMsgCert.style.display = "block";
    }
});
