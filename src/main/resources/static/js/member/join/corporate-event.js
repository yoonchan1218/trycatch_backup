const emailInput = document.querySelector("input[name=memberEmail]")
const emailMessage = document.getElementById("email-message");
const idInput = document.querySelector("input[name=memberId]")
const idMessage = document.getElementById("id-message");
const companyNameInput = document.querySelector("input[name=corpCompanyName]")
const companyNameMessage = document.getElementById("company-name-message");
const button = document.querySelector("button[type=button]");
let emailCheck = false;
let idCheck = false;
let companyNameCheck = false;

emailInput.addEventListener("blur", (e) => {
    memberService.checkEmail(e.target.value, (isAvailable) => {
        emailCheck = isAvailable;
        emailMessage.classList.toggle("on", isAvailable);
        emailMessage.textContent = isAvailable ? "사용 가능한 이메일입니다." : "중복된 이메일입니다.";
    });
});

idInput.addEventListener("blur", (e) => {
    memberService.checkId(e.target.value, (isAvailable) => {
        idCheck = isAvailable;
        idMessage.classList.toggle("on", isAvailable);
        idMessage.textContent = isAvailable ? "사용 가능한 아이디입니다." : "중복된 아이디입니다.";
    });
});

companyNameInput.addEventListener("blur", (e) => {
    memberService.checkCompanyName(e.target.value, (isAvailable) => {
        companyNameCheck = isAvailable;
        companyNameMessage.classList.toggle("on", isAvailable);
        companyNameMessage.textContent = isAvailable ? "사용 가능한 기업명입니다." : "중복된 기업명입니다.";
    });
});

button.addEventListener("click", (e) => {
    if(emailCheck && idCheck && companyNameCheck) {
        document.joinForm.submit();
    }
});
