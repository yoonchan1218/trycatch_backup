// 다음 주소 API 연결

document.addEventListener("DOMContentLoaded", function () {
    const addressInput = document.getElementById("address");
    const detailAddressInput = document.getElementById("detailAddress");

    // 주소 입력란 클릭 시 주소 검색 팝업 실행
    if (addressInput) {
        addressInput.addEventListener("click", function () {
            execDaumPostcode();
        });
    }
});

// 다음 주소 검색 팝업 실행
function execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function (data) {
            let addr = ""; // 주소 변수
            let extraAddr = ""; // 참고항목 변수

            // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === "R") {
                // 도로명 주소 선택
                addr = data.roadAddress;
            } else {
                // 지번 주소 선택
                addr = data.jibunAddress;
            }

            // 도로명 타입일 때 참고항목을 조합한다.
            if (data.userSelectedType === "R") {
                // 법정동명이 있을 경우 추가한다. (동/로/가로 끝나는 경우)
                if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if (data.buildingName !== "" && data.apartment === "Y") {
                    extraAddr +=
                        extraAddr !== ""
                            ? ", " + data.buildingName
                            : data.buildingName;
                }
                // 참고항목이 있을 경우 괄호로 감싼다.
                if (extraAddr !== "") {
                    extraAddr = " (" + extraAddr + ")";
                }
            }

            // 주소 정보를 해당 필드에 넣는다.
            document.getElementById("address").value = addr + extraAddr;

            // hidden 필드에 주소 데이터 동기화
            document.getElementById("addressZipcode").value = data.zonecode;
            document.getElementById("addressAddress").value = addr + extraAddr;

            // 상세주소 입력란 표시 및 포커스
            const detailAddressInput = document.getElementById("detailAddress");
            detailAddressInput.style.display = "block";
            detailAddressInput.focus();
        },
    }).open({ popupKey: "popup1" });
}

// 접수기간 날짜 picker
const startInputClick = document.getElementById("start-date");
if (startInputClick) {
    startInputClick.addEventListener("click", (e) => {
        e.target.showPicker?.();
    });
}

// 접수마감일 날짜 picker
const deadlineInput = document.getElementById("deadline");
if (deadlineInput) {
    deadlineInput.addEventListener("click", (e) => {
        e.target.showPicker?.();
    });
}

// 근무요일 날짜 picker
const workdayStart = document.getElementById("workday-start");
if (workdayStart) {
    workdayStart.addEventListener("click", (e) => {
        e.target.showPicker?.();
    });
}

const workdayEnd = document.getElementById("workday-end");
if (workdayEnd) {
    workdayEnd.addEventListener("click", (e) => {
        e.target.showPicker?.();
    });
}

// 이메일 드롭다운 (요소가 있을 때만)
const emailBtn = document.querySelector(".btnSelType");
const emailDropDown = document.querySelector(".lySelOption");

if (emailBtn && emailDropDown) {
    emailBtn.addEventListener("click", (e) => {
        e.stopPropagation();
        emailDropDown.style.display = "block";
    });

    emailDropDown.addEventListener("click", (e) => {
        e.stopPropagation();
    });

    document.addEventListener("click", () => {
        emailDropDown.style.display = "none";
    });

    window.addEventListener("scroll", () => {
        emailDropDown.style.display = "none";
    });

    const insertEmailBtn = document.querySelectorAll(".selTypeList li button");
    const emailValue = document.querySelector(".devEmailDomain");

    insertEmailBtn.forEach((Btn, i) => {
        Btn.addEventListener("click", (e) => {
            emailValue.value = Btn.textContent;
            emailDropDown.style.display = "none";
        });
    });
}


