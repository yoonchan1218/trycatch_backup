// 질문·글쓰기 툴팁 토글
const writeButton = document.querySelector(".select-tooltip.btn-question.qnaSpB");
const tooltipOpenDiv = document.querySelector(".navi-top-area.has-tooltip");

if (writeButton !== null) {
    writeButton.addEventListener("click", (e) => {
        tooltipOpenDiv.classList.toggle("tooltip-open");
    });
}

// 충전하기 버튼
const chargeButton = document.querySelector(
    ".header_button__9mwzc.header_highlight__3hMnE",
);
const body = document.querySelector("body");
const modal = document.createElement("div");

const requestPointCharge = async (payload) => {
    const response = await fetch("/point/point/charge", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(payload),
    });

    if (!response.ok) {
        return {
            success: false,
            message: "별 충전 처리 중 서버 오류가 발생했습니다.",
        };
    }

    return response.json();
};

const requestPointCancel = async (pointDetailId) => {
    const response = await fetch(`/point/point/cancel/${pointDetailId}`, {
        method: "POST",
    });

    if (!response.ok) {
        return {
            success: false,
            message: "구매취소 처리 중 서버 오류가 발생했습니다.",
        };
    }

    return response.json();
};

chargeButton.addEventListener("click", (e) => {
    body.style.overflow = body.style.overflow === "hidden" ? "" : "hidden";
    modal.innerHTML = `
        <div class="profile_popup_dimmed__3axER">
            <div
                class="profile_popup_container__1Ekcl"
                role="alertdialog"
                aria-modal="true"
                style="width: 405px"
            >
                <div class="profile_popup_header__1c0EQ">
                    <strong class="profile_popup_title__3YiOY">별 충전하기</strong>
                </div>
                <div class="profile_popup_content__mAIU7">
                    <div class="profile_popup_inner__22tcx">
                        <div class="profile_popup_area__1WUHf" style="margin-top: 0px">
                            <div class="charge_box__djnS1">
                                <label class="charge_area__1Ks2V" for="charge-coin">
                                    <div class="charge_text__3bWer">충전할 별</div>
                                    <div class="charge_text_area__H1YKl">
                                        <input
                                            type="text"
                                            class="charge_input__HK4uG"
                                            id="charge-coin"
                                            value="10"
                                        />
                                        <button
                                            class="charge_delete_button__3xtE1"
                                            type="button"
                                        >
                                            <span class="blind">금액 초기화</span>
                                        </button>
                                    </div>
                                </label>
                                <div class="charge_area__1Ks2V">
                                    <button type="button" class="charge_button__3OgjR">
                                        +5
                                    </button>
                                    <button type="button" class="charge_button__3OgjR">
                                        +10
                                    </button>
                                    <button type="button" class="charge_button__3OgjR">
                                        +50
                                    </button>
                                    <button type="button" class="charge_button__3OgjR">
                                        +100
                                    </button>
                                    <button type="button" class="charge_button__3OgjR">
                                        +500
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="profile_popup_area__1WUHf">
                            <div class="charge_total_box__zi9C8">
                                <div class="charge_text__3bWer">최종 결제금액</div>
                                <strong class="charge_price__1_H_P">1,100원</strong>
                            </div>
                        </div>
                        <div class="charge_information_area__3bRd0">
                            내용을 확인했으며 별 충전에 동의합니다.
                            <button
                                type="button"
                                aria-expanded="false"
                                class="charge_information_area_button__D0_jh"
                            >
                            </button>
                        </div>
                    </div>
                </div>
                <div class="profile_popup_footer__1KIrx profile_popup_medium__sN54E">
                    <div class="profile_popup_box__1h-lT profile_popup_full_button__HtTtv">
                        <button
                            type="button"
                            class="profile_popup_button__1QWel profile_popup_is_highlight__37n_J"
                        >
                            별 충전하기
                        </button>
                    </div>
                </div>
                <button
                    type="button"
                    class="profile_popup_top_button__CbyxM profile_popup_close__1tv6R"
                >
                    <span class="blind">팝업 닫기</span>
                </button>
            </div>
        </div>
    `;

    document.body.appendChild(modal);

    // 충전 모달
    const popupDimmed = document.querySelector(".profile_popup_dimmed__3axER");
    const chargeInput = document.getElementById("charge-coin");
    const deleteButton = document.querySelector(".charge_delete_button__3xtE1");
    const chargeButtons = document.querySelectorAll(".charge_button__3OgjR");
    const totalPrice = document.querySelector(".charge_price__1_H_P");
    const submitButton = document.querySelector(".profile_popup_button__1QWel");
    const closeButton = document.querySelector(".profile_popup_close__1tv6R");
    const infoButton = document.querySelector(
        ".charge_information_area_button__D0_jh",
    );

    function closeChargeModal() {
        popupDimmed.style.display = "none";
        body.style.overflow = "";
    }

    function closeBootpayWindow() {
        try {
            Bootpay.destroy();
        } catch (error) {
            console.warn("Bootpay 창 닫기 실패:", error);
        }
    }

    // 숫자만 추출
    function parseNumber(str) {
        return parseInt(str.replace(/[^0-9]/g, ""), 10) || 0;
    }

    // 천 단위 콤마 포맷
    function formatNumber(num) {
        return num.toLocaleString("ko-KR");
    }

    // 총액 계산 (부가세 10% 포함)
    function calculateTotal(stars) {
        return Math.floor(stars * 110);
    }

    // 총액 업데이트
    function updateTotal() {
        const stars = parseNumber(chargeInput.value);
        const total = calculateTotal(stars);
        totalPrice.textContent = formatNumber(total) + "원";
    }

    // 금액 추가 버튼 클릭
    chargeButtons.forEach((button) => {
        button.addEventListener("click", (e) => {
            const text = e.target.textContent.trim();
            let result = 0;

            if (text === "+5") result = 5;
            else if (text === "+10") result = 10;
            else if (text === "+50") result = 50;
            else if (text === "+100") result = 100;
            else if (text === "+500") result = 500;

            const current = parseNumber(chargeInput.value);
            chargeInput.value = formatNumber(current + result);
            updateTotal();
        });
    });


    // 입력 초기화
    deleteButton.addEventListener("click", (e) => {
        chargeInput.value = "";
        updateTotal();
        chargeInput.focus();
    });

    // 입력 이벤트 처리
    chargeInput.addEventListener("input", (e) => {
        const num = parseNumber(e.target.value);
        if (num > 0) {
            e.target.value = formatNumber(num);
        } else {
            e.target.value = "";
        }
        updateTotal();
    });

    // 팝업 닫기
    closeButton.addEventListener("click", (e) => {
        closeChargeModal();
    });

    // 딤드 영역 클릭 시 닫기
    popupDimmed.addEventListener("click", (e) => {
        if (e.target === popupDimmed) {
            closeChargeModal();
        }
    });

    // 안내보기 토글
    infoButton.addEventListener("click", (e) => {
        const isExpanded = e.target.getAttribute("aria-expanded") === "true";
        e.target.setAttribute("aria-expanded", !isExpanded);
        // 안내 상세 내용 토글 로직 추가 가능
    });

    const pay = async () => {
        const pointAmount = parseNumber(chargeInput.value);
        const price = parseInt(
            totalPrice.textContent.replace(/[^0-9]/g, ""),
            10,
        );

        // NaN 체크
        if (isNaN(price) || price <= 0) {
            alert("충전할 별 개수를 입력해주세요");
            return;
        } else if (price < 1100) {
            alert("별 충전은 10개 이상부터 가능합니다.");
            return;
        }
        const orderId = `POINT-${Date.now()}-${Math.floor(Math.random() * 100000)}`;
        submitButton.disabled = true;

        try {
            const response = await Bootpay.requestPayment({
                application_id: "697868f4fc55d934885c2420",
                price: price,
                order_name: "별",
                order_id: orderId,
                pg: "라이트페이",
                // method: "계좌이체",
                tax_free: 0,
                user: {
                    id: "회원아이디",
                    username: "회원이름",
                    phone: "01000000000",
                    email: "test@test.com",
                },
                items: [
                    {
                        id: "item_id",
                        name: "테스트아이템",
                        qty: 1,
                        price: price,
                    },
                ],
                extra: {
                    open_type: "iframe",
                    card_quota: "0,2,3",
                    escrow: false,
                },
            });
            switch (response.event) {
                case "issued":
                    // 가상계좌 입금 완료 처리
                    break;
                case "done":
                    {
                        const result = await requestPointCharge({
                            pointAmount: pointAmount,
                            paymentAmount: price,
                            paymentOrderId: response.order_id || orderId,
                            paymentReceiptId: response.receipt_id || "",
                        });

                        closeBootpayWindow();

                        if (!result.success) {
                            alert(result.message || "결제는 완료되었지만 별 충전에 실패했습니다.");
                            return;
                        }

                        alert(result.message || "별이 충전되었습니다.");
                        closeChargeModal();
                        window.location.reload();
                    }
                    break;
                case "confirm": //payload.extra.separately_confirmed = true; 일 경우 승인 전 해당 이벤트가 호출됨
                    console.log(response.receipt_id);
                    const confirmedData = await Bootpay.confirm(); //결제를 승인한다
                    if (confirmedData.event === "done") {
                        const result = await requestPointCharge({
                            pointAmount: pointAmount,
                            paymentAmount: price,
                            paymentOrderId: confirmedData.order_id || response.order_id || orderId,
                            paymentReceiptId: confirmedData.receipt_id || response.receipt_id || "",
                        });

                        closeBootpayWindow();

                        if (!result.success) {
                            alert(result.message || "결제는 완료되었지만 별 충전에 실패했습니다.");
                            return;
                        }

                        alert(result.message || "별이 충전되었습니다.");
                        closeChargeModal();
                        window.location.reload();
                    }
                    break;
            }
        } catch (e) {
            // 결제 진행중 오류 발생
            // e.error_code - 부트페이 오류 코드
            // e.pg_error_code - PG 오류 코드
            // e.message - 오류 내용
            console.log(e.message);
            switch (e?.event) {
                case "cancel":
                    // 사용자가 결제창을 닫을때 호출
                    console.log(e.message);
                    alert("결제가 취소되었습니다. 다시 시도해주세요.");
                    break;
                case "error":
                    // 결제 승인 중 오류 발생시 호출
                    console.log(e.error_code);
                    alert("결제 처리 중 오류가 발생했습니다.");
                    break;
                default:
                    alert("결제/충전 처리 중 오류가 발생했습니다.");
                    break;
            }
        } finally {
            submitButton.disabled = false;
        }
    };

    // 별 충전하기 버튼
    submitButton.addEventListener("click", (e) => {
        pay();
    });

    // 초기 총액 계산
    updateTotal();
});

// 구매취소
document.addEventListener("click", async (e) => {
    const cancelButton = e.target.closest(".table_button__299bn");
    if (!cancelButton) {
        return;
    }

    const pointDetailId = parseInt(cancelButton.dataset.pointDetailId, 10);
    if (!pointDetailId) {
        alert("취소할 결제 정보를 찾을 수 없습니다.");
        return;
    }

    if (!confirm("결제를 취소하시겠습니까?")) {
        return;
    }

    try {
        const result = await requestPointCancel(pointDetailId);
        alert(result.message || "구매취소 요청이 처리되었습니다.");

        if (result.success) {
            window.location.reload();
        }
    } catch (error) {
        alert("구매취소 처리 중 오류가 발생했습니다.");
    }
});
