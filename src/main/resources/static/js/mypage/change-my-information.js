(function initProfileFields() {
    const birth = document.getElementById("profileBirth")?.value;
    const gender = document.getElementById("profileGender")?.value;
    const phone = document.getElementById("profilePhone")?.value;

    if (birth && birth.includes("-")) {
        const [y, m, d] = birth.split("-");
        const yEl = document.getElementById("M_Year");
        const mEl = document.getElementById("M_Month");
        const dEl = document.getElementById("M_Day");
        if (yEl) yEl.value = y;
        if (mEl) mEl.value = String(parseInt(m, 10));
        if (dEl) dEl.value = String(parseInt(d, 10));
    }

    if (gender) {
        const man = document.getElementById("M_Gender0");
        const women = document.getElementById("M_Gender1");
        if (gender === "man" && man) man.checked = true;
        if (gender === "women" && women) women.checked = true;
    }

    if (phone) {
        const parts = phone.split("-");
        if (parts.length >= 3) {
            const p1 = document.getElementById("M_Hand_Phone1");
            const p2 = document.getElementById("M_Hand_Phone2");
            const p3 = document.getElementById("M_Hand_Phone3");
            if (p1) p1.value = parts[0];
            if (p2) p2.value = parts[1];
            if (p3) p3.value = parts[2];
        }
    }
})();
function execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function (data) {
            const zonecode = data.zonecode;
            let addr = "";
            if (data.userSelectedType === "R") {
                addr = data.roadAddress;
            } else {
                addr = data.jibunAddress;
            }

            let extraAddr = "";
            if (data.userSelectedType === "R") {
                if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
                    extraAddr += data.bname;
                }
                if (data.buildingName !== "" && data.apartment === "Y") {
                    extraAddr += extraAddr !== "" ? ", " + data.buildingName : data.buildingName;
                }
                if (extraAddr !== "") {
                    extraAddr = " (" + extraAddr + ")";
                }
            }

            document.getElementById("postcheck").value = zonecode;
            document.getElementById("M_ZipCode").value = zonecode;
            document.getElementById("M_Addr_Text").value = addr + extraAddr;
            document.getElementById("M_Addr_Text1").value = "";
            document.getElementById("M_Addr_Text1").focus();
        },
    }).open();
}

document.getElementById("btnSearchAddr").addEventListener("click", execDaumPostcode);
document.getElementById("postcheck").addEventListener("click", execDaumPostcode);

(function () {
    const helpWraps = document.querySelectorAll(".mbrHelpWrap");
    helpWraps.forEach((wrap) => {
        const inputs = wrap.querySelectorAll("input, select, textarea");
        const helpLayer = wrap.querySelector(".mbrLayerHelp");
        if (!helpLayer) return;

        inputs.forEach((input) => {
            input.addEventListener("focus", () => {
                if ((input.value || "").length === 0) {
                    helpLayer.style.display = "block";
                }
            });
            input.addEventListener("blur", () => {
                if ((input.value || "").length > 0) {
                    helpLayer.style.display = "none";
                }
            });
        });
    });
})();

const emailSelect = document.getElementById("Email_Addr");
const emailInput = document.getElementById("Email_Addr_Text");

emailSelect.addEventListener("change", () => {
    const selectedValue = emailSelect.value;
    if (selectedValue !== "etc" && selectedValue !== "") {
        emailInput.value = selectedValue;
        emailInput.readOnly = true;
    } else {
        emailInput.value = "";
        emailInput.readOnly = false;
        emailInput.focus();
    }
});

function upsertHidden(form, name, value) {
    let input = form.querySelector(`input[name="${name}"]`);
    if (!input) {
        input = document.createElement("input");
        input.type = "hidden";
        input.name = name;
        form.appendChild(input);
    }
    input.value = value ?? "";
}

const modifyBtn = document.querySelector(".mbrBtnModify_1 a");
modifyBtn.addEventListener("click", (e) => {
    e.preventDefault();

    const form = document.forms["form"];
    let isValid = true;

    if (!form.M_Name.value.trim()) {
        document.getElementById("CautionResult6").style.display = "block";
        isValid = false;
    }
    if (!form.M_Year.value) {
        document.getElementById("CautionResult7").style.display = "block";
        isValid = false;
    }
    if (!form.M_Hand_Phone2.value.trim() || !form.M_Hand_Phone3.value.trim()) {
        document.getElementById("CautionResult1").style.display = "block";
        isValid = false;
    }
    if (!form.Email_ID.value.trim() || !form.Email_Addr_Text.value.trim()) {
        document.getElementById("CautionResult2").style.display = "block";
        isValid = false;
    }

    if (!isValid) return;

    const year = form.M_Year.value;
    const month = String(form.M_Month.value).padStart(2, "0");
    const day = String(form.M_Day.value).padStart(2, "0");
    const birth = `${year}-${month}-${day}`;

    const genderValue = form.querySelector("input[name='M_Gender']:checked")?.value;
    const gender = genderValue === "1" ? "women" : "man";

    const p1 = form.M_Hand_Phone1?.value || "";
    const p2 = form.M_Hand_Phone2.value || "";
    const p3 = form.M_Hand_Phone3.value || "";
    const phone = [p1, p2, p3].filter(Boolean).join("-");

    const email = `${form.Email_ID.value.trim()}@${form.Email_Addr_Text.value.trim()}`;

    upsertHidden(form, "memberName", form.M_Name.value.trim());
    upsertHidden(form, "memberEmail", email);
    upsertHidden(form, "memberPhone", phone);
    upsertHidden(form, "individualMemberBirth", birth);
    upsertHidden(form, "individualMemberGender", gender);

    form.action = "/mypage/change-my-information";
    form.method = "post";
    form.submit();
});

const cancelButton = document.querySelector(".mbrBtn.mbrBtnCancel_1 button");
cancelButton.addEventListener("click", () => {
    location.href = "/mypage/mypage";
});
