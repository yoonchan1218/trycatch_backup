document.addEventListener("DOMContentLoaded", () => {

    // 기존 회원 정보로 화면 초기화
    infoLayout.showBirth(document.getElementById("profileBirth")?.value);
    infoLayout.showGender(document.getElementById("profileGender")?.value);
    infoLayout.showPhone(document.getElementById("profilePhone")?.value);
    infoLayout.showEducation(document.getElementById("profileEducation")?.value);

    // 이메일 도메인 선택
    const emailSelect = document.getElementById("Email_Addr");
    const emailInput  = document.getElementById("Email_Addr_Text");
    if (emailSelect && emailInput) {
        emailSelect.addEventListener("change", () => {
            infoLayout.handleEmailSelect(emailSelect, emailInput);
        });
    }

    // 수정 버튼
    document.querySelector(".mbrBtnModify_1 a")?.addEventListener("click", (e) => {
        e.preventDefault();
        const form = document.forms["form"];
        if (!infoLayout.isPhoneValid(form)) {
            alert("휴대폰 번호를 모두 입력해주세요.");
            return;
        }
        infoLayout.prepareHiddens(form);
        infoService.update(form);
    });

    // 취소 버튼
    document.querySelector(".mbrBtn.mbrBtnCancel_1 button")?.addEventListener("click", () => {
        location.href = "/mypage/mypage";
    });
});
