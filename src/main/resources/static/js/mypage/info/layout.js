const infoLayout = (() => {

    // 기존 생년월일을 화면에 채우기
    const showBirth = (birth) => {
        if (!birth || !birth.includes("-")) return;
        const [year, month, day] = birth.split("-");
        document.getElementById("M_Year").value  = year;
        document.getElementById("M_Month").value = String(parseInt(month));
        document.getElementById("M_Day").value   = String(parseInt(day));
    };

    // 기존 성별을 화면에 채우기
    const showGender = (gender) => {
        if (gender === "man")   document.getElementById("M_Gender0").checked = true;
        if (gender === "women") document.getElementById("M_Gender1").checked = true;
    };

    // 기존 전화번호를 화면에 채우기
    // 가입 시 하이픈 있는 형식("010-1234-5678")과 없는 형식("01012345678") 모두 처리
    const showPhone = (phone) => {
        if (!phone) return;
        let parts;
        if (phone.includes("-")) {
            // "010-1234-5678" 형식
            parts = phone.split("-");
        } else if (phone.length === 11) {
            // "01012345678" 형식 (11자리)
            parts = [phone.slice(0, 3), phone.slice(3, 7), phone.slice(7)];
        } else if (phone.length === 10) {
            // "0161234567" 형식 (10자리)
            parts = [phone.slice(0, 3), phone.slice(3, 6), phone.slice(6)];
        }
        if (!parts || parts.length < 3) return;
        document.getElementById("M_Hand_Phone1").value = parts[0];
        document.getElementById("M_Hand_Phone2").value = parts[1];
        document.getElementById("M_Hand_Phone3").value = parts[2];
    };

    // 기존 학력을 화면에 채우기
    const showEducation = (education) => {
        if (!education) return;
        const select = document.getElementById("M_Education");
        if (select) select.value = education;
    };

    // 전화번호 전체 입력 여부 확인 (중간번호, 끝번호 필수)
    const isPhoneValid = (form) => {
        return form.M_Hand_Phone2.value.trim() !== "" && form.M_Hand_Phone3.value.trim() !== "";
    };

    // 이메일 도메인 선택 처리
    const handleEmailSelect = (select, input) => {
        if (select.value !== "etc" && select.value !== "") {
            input.value    = select.value;
            input.readOnly = true;
        } else {
            input.value    = "";
            input.readOnly = false;
            input.focus();
        }
    };

    // form 안의 hidden input에 값 설정 (없으면 새로 만들어서 추가)
    const setHidden = (form, name, value) => {
        let input = form.querySelector(`input[name="${name}"]`);
        if (!input) {
            input = document.createElement("input");
            input.type = "hidden";
            input.name = name;
            form.appendChild(input);
        }
        input.value = value || "";
    };

    // 수정 버튼 클릭 시 서버 DTO 필드명에 맞게 hidden input 값 세팅
    const prepareHiddens = (form) => {
        const month  = form.M_Month.value < 10 ? "0" + form.M_Month.value : form.M_Month.value;
        const day    = form.M_Day.value   < 10 ? "0" + form.M_Day.value   : form.M_Day.value;
        const birth  = `${form.M_Year.value}-${month}-${day}`;
        const phone  = `${form.M_Hand_Phone1.value}-${form.M_Hand_Phone2.value}-${form.M_Hand_Phone3.value}`;
        const email  = `${form.Email_ID.value.trim()}@${form.Email_Addr_Text.value.trim()}`;
        const gender = form.querySelector("input[name='M_Gender']:checked")?.value === "1" ? "women" : "man";

        setHidden(form, "memberName",                form.M_Name.value.trim());
        setHidden(form, "memberEmail",               email);
        setHidden(form, "memberPhone",               phone);
        setHidden(form, "individualMemberBirth",     birth);
        setHidden(form, "individualMemberGender",    gender);
        setHidden(form, "individualMemberEducation", form.M_Education?.value || "");
    };

    return { showBirth, showGender, showPhone, showEducation, isPhoneValid, handleEmailSelect, prepareHiddens };
})();
