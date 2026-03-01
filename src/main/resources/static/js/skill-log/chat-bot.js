const toggle = document.getElementById("chatbotToggle");
const container = document.getElementById("chatbotContainer");
const messages = document.getElementById("chatbotMessages");
const chatInput = document.getElementById("chatbotInput");
const sendBtn = document.getElementById("chatbotSend");

// 토글 기능
toggle.addEventListener("click", (e) => {
    toggle.classList.toggle("active");
    container.classList.toggle("active");
    if (container.classList.contains("active")) {
        chatInput.focus();
    }
});

// 메시지 전송 함수
function sendMessage(text) {
    if (!text.trim()) return;

    addMessage(text, "user");
    chatInput.value = "";

    showTyping();

    setTimeout(
        () => {
            hideTyping();
            const response = getBotResponse(text);
            addMessage(response, "bot");
        },
        1000 + Math.random() * 1000,
    );
}

// 메시지 추가 함수
function addMessage(text, type) {
    const time = new Date().toLocaleTimeString("ko-KR", {
        hour: "2-digit",
        minute: "2-digit",
    });

    const avatarSvg =
        type === "bot"
            ? '<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 400 400"><defs><radialGradient id="bodyGradient" cx="50%" cy="40%" r="60%"><stop offset="0%" stop-color="#008f52"/><stop offset="100%" stop-color="#006e3f"/></radialGradient><radialGradient id="cheekGradient" cx="50%" cy="50%" r="50%"><stop offset="0%" stop-color="#ff9999"/><stop offset="100%" stop-color="#ffb3b3"/></radialGradient><radialGradient id="bellyGradient" cx="50%" cy="30%" r="70%"><stop offset="0%" stop-color="#00a85a"/><stop offset="100%" stop-color="#008f52"/></radialGradient></defs><circle cx="200" cy="200" r="180" fill="#e8f5e9" opacity="0.5"/><ellipse cx="200" cy="280" rx="90" ry="70" fill="url(#bodyGradient)"/><ellipse cx="200" cy="290" rx="55" ry="45" fill="url(#bellyGradient)"/><ellipse cx="200" cy="160" rx="100" ry="85" fill="url(#bodyGradient)"/><ellipse cx="115" cy="95" rx="25" ry="20" fill="#006e3f"/><ellipse cx="118" cy="95" rx="15" ry="12" fill="#ff9999" opacity="0.6"/><ellipse cx="285" cy="95" rx="25" ry="20" fill="#006e3f"/><ellipse cx="282" cy="95" rx="15" ry="12" fill="#ff9999" opacity="0.6"/><ellipse cx="200" cy="180" rx="70" ry="50" fill="#008f52" opacity="0.5"/><ellipse cx="200" cy="175" rx="35" ry="28" fill="#004d2b"/><ellipse cx="200" cy="172" rx="28" ry="20" fill="#003d22"/><ellipse cx="188" cy="170" rx="6" ry="5" fill="#002815"/><ellipse cx="212" cy="170" rx="6" ry="5" fill="#002815"/><ellipse cx="195" cy="165" rx="8" ry="5" fill="#005533" opacity="0.5"/><ellipse cx="155" cy="135" rx="22" ry="24" fill="#1a1a1a"/><ellipse cx="160" cy="130" rx="10" ry="12" fill="white"/><circle cx="158" cy="128" r="4" fill="white" opacity="0.8"/><ellipse cx="245" cy="135" rx="22" ry="24" fill="#1a1a1a"/><ellipse cx="250" cy="130" rx="10" ry="12" fill="white"/><circle cx="248" cy="128" r="4" fill="white" opacity="0.8"/><ellipse cx="120" cy="165" rx="20" ry="15" fill="url(#cheekGradient)" opacity="0.7"/><ellipse cx="280" cy="165" rx="20" ry="15" fill="url(#cheekGradient)" opacity="0.7"/><path d="M 180 195 Q 200 215 220 195" stroke="#003d22" stroke-width="4" fill="none" stroke-linecap="round"/><line x1="130" y1="175" x2="155" y2="180" stroke="#004d2b" stroke-width="2" stroke-linecap="round"/><line x1="128" y1="185" x2="155" y2="185" stroke="#004d2b" stroke-width="2" stroke-linecap="round"/><line x1="130" y1="195" x2="155" y2="190" stroke="#004d2b" stroke-width="2" stroke-linecap="round"/><line x1="270" y1="175" x2="245" y2="180" stroke="#004d2b" stroke-width="2" stroke-linecap="round"/><line x1="272" y1="185" x2="245" y2="185" stroke="#004d2b" stroke-width="2" stroke-linecap="round"/><line x1="270" y1="195" x2="245" y2="190" stroke="#004d2b" stroke-width="2" stroke-linecap="round"/><ellipse cx="130" cy="320" rx="30" ry="20" fill="#006e3f"/><ellipse cx="120" cy="325" rx="8" ry="6" fill="#004d2b"/><ellipse cx="135" cy="328" rx="8" ry="6" fill="#004d2b"/><ellipse cx="270" cy="320" rx="30" ry="20" fill="#006e3f"/><ellipse cx="265" cy="328" rx="8" ry="6" fill="#004d2b"/><ellipse cx="280" cy="325" rx="8" ry="6" fill="#004d2b"/><circle cx="290" cy="100" r="5" fill="#ffeb3b" opacity="0.8"/><circle cx="310" cy="120" r="3" fill="#ffeb3b" opacity="0.6"/><circle cx="100" cy="110" r="4" fill="#ffeb3b" opacity="0.7"/></svg>'
            : '<svg viewBox="0 0 24 24" fill="currentColor"><path d="M12 12C14.21 12 16 10.21 16 8C16 5.79 14.21 4 12 4C9.79 4 8 5.79 8 8C8 10.21 9.79 12 12 12ZM12 14C9.33 14 4 15.34 4 18V20H20V18C20 15.34 14.67 14 12 14Z"/></svg>';

    const messageDiv = document.createElement("div");
    messageDiv.className = "chatbot-message " + type;
    messageDiv.innerHTML =
        '<div class="chatbot-message-avatar">' +
        avatarSvg +
        "</div>" +
        "<div>" +
        '<div class="chatbot-message-content">' +
        text +
        "</div>" +
        '<div class="chatbot-message-time">' +
        time +
        "</div>" +
        "</div>";

    messages.appendChild(messageDiv);
    messages.scrollTop = messages.scrollHeight;
}

// 타이핑 인디케이터
function showTyping() {
    const typingDiv = document.createElement("div");
    typingDiv.className = "chatbot-message bot";
    typingDiv.id = "typingIndicator";
    typingDiv.innerHTML =
        '<div class="chatbot-message-avatar">' +
        '<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 400 400"><defs><radialGradient id="bodyGradient" cx="50%" cy="40%" r="60%"><stop offset="0%" stop-color="#008f52"/><stop offset="100%" stop-color="#006e3f"/></radialGradient><radialGradient id="cheekGradient" cx="50%" cy="50%" r="50%"><stop offset="0%" stop-color="#ff9999"/><stop offset="100%" stop-color="#ffb3b3"/></radialGradient><radialGradient id="bellyGradient" cx="50%" cy="30%" r="70%"><stop offset="0%" stop-color="#00a85a"/><stop offset="100%" stop-color="#008f52"/></radialGradient></defs><circle cx="200" cy="200" r="180" fill="#e8f5e9" opacity="0.5"/><ellipse cx="200" cy="280" rx="90" ry="70" fill="url(#bodyGradient)"/><ellipse cx="200" cy="290" rx="55" ry="45" fill="url(#bellyGradient)"/><ellipse cx="200" cy="160" rx="100" ry="85" fill="url(#bodyGradient)"/><ellipse cx="115" cy="95" rx="25" ry="20" fill="#006e3f"/><ellipse cx="118" cy="95" rx="15" ry="12" fill="#ff9999" opacity="0.6"/><ellipse cx="285" cy="95" rx="25" ry="20" fill="#006e3f"/><ellipse cx="282" cy="95" rx="15" ry="12" fill="#ff9999" opacity="0.6"/><ellipse cx="200" cy="180" rx="70" ry="50" fill="#008f52" opacity="0.5"/><ellipse cx="200" cy="175" rx="35" ry="28" fill="#004d2b"/><ellipse cx="200" cy="172" rx="28" ry="20" fill="#003d22"/><ellipse cx="188" cy="170" rx="6" ry="5" fill="#002815"/><ellipse cx="212" cy="170" rx="6" ry="5" fill="#002815"/><ellipse cx="195" cy="165" rx="8" ry="5" fill="#005533" opacity="0.5"/><ellipse cx="155" cy="135" rx="22" ry="24" fill="#1a1a1a"/><ellipse cx="160" cy="130" rx="10" ry="12" fill="white"/><circle cx="158" cy="128" r="4" fill="white" opacity="0.8"/><ellipse cx="245" cy="135" rx="22" ry="24" fill="#1a1a1a"/><ellipse cx="250" cy="130" rx="10" ry="12" fill="white"/><circle cx="248" cy="128" r="4" fill="white" opacity="0.8"/><ellipse cx="120" cy="165" rx="20" ry="15" fill="url(#cheekGradient)" opacity="0.7"/><ellipse cx="280" cy="165" rx="20" ry="15" fill="url(#cheekGradient)" opacity="0.7"/><path d="M 180 195 Q 200 215 220 195" stroke="#003d22" stroke-width="4" fill="none" stroke-linecap="round"/><line x1="130" y1="175" x2="155" y2="180" stroke="#004d2b" stroke-width="2" stroke-linecap="round"/><line x1="128" y1="185" x2="155" y2="185" stroke="#004d2b" stroke-width="2" stroke-linecap="round"/><line x1="130" y1="195" x2="155" y2="190" stroke="#004d2b" stroke-width="2" stroke-linecap="round"/><line x1="270" y1="175" x2="245" y2="180" stroke="#004d2b" stroke-width="2" stroke-linecap="round"/><line x1="272" y1="185" x2="245" y2="185" stroke="#004d2b" stroke-width="2" stroke-linecap="round"/><line x1="270" y1="195" x2="245" y2="190" stroke="#004d2b" stroke-width="2" stroke-linecap="round"/><ellipse cx="130" cy="320" rx="30" ry="20" fill="#006e3f"/><ellipse cx="120" cy="325" rx="8" ry="6" fill="#004d2b"/><ellipse cx="135" cy="328" rx="8" ry="6" fill="#004d2b"/><ellipse cx="270" cy="320" rx="30" ry="20" fill="#006e3f"/><ellipse cx="265" cy="328" rx="8" ry="6" fill="#004d2b"/><ellipse cx="280" cy="325" rx="8" ry="6" fill="#004d2b"/><circle cx="290" cy="100" r="5" fill="#ffeb3b" opacity="0.8"/><circle cx="310" cy="120" r="3" fill="#ffeb3b" opacity="0.6"/><circle cx="100" cy="110" r="4" fill="#ffeb3b" opacity="0.7"/></svg>' +
        "</div>" +
        '<div class="chatbot-message-content">' +
        '<div class="chatbot-typing"><span></span><span></span><span></span></div>' +
        "</div>";
    messages.appendChild(typingDiv);
    messages.scrollTop = messages.scrollHeight;
}

function hideTyping() {
    const typing = document.getElementById("typingIndicator");
    if (typing) typing.remove();
}

// 봇 응답 생성
function getBotResponse(userMessage) {
    const lowerMsg = userMessage.toLowerCase();

    // 서버할때는 이부분 api호출로 대채하면됨.
    if (lowerMsg.includes("체험") && lowerMsg.includes("신청")) {
        return '체험 신청은 다음 단계로 진행됩니다:<br><br>1. 원하는 체험 공고를 선택하세요<br>2. "신청하기" 버튼을 클릭하세요<br>3. 자기소개서를 작성해 주세요<br>4. 제출 후 결과를 기다려주세요!<br><br>더 궁금한 점이 있으시면 말씀해주세요 😊';
    } else if (lowerMsg.includes("자기소개서") || lowerMsg.includes("자소서")) {
        return '자기소개서 작성 팁을 알려드릴게요!<br><br>✅ 구체적인 경험을 작성하세요<br>✅ 지원 동기를 명확히 하세요<br>✅ 본인만의 강점을 어필하세요<br>✅ 맞춤법 검사를 꼭 하세요<br><br>블로그 메뉴의 "자소서 꿀팁"에서 더 많은 정보를 확인해보세요!';
    } else if (
        lowerMsg.includes("인기") ||
        lowerMsg.includes("기업") ||
        lowerMsg.includes("추천")
    ) {
        return '현재 인기 있는 체험 기업들입니다:<br><br>🏢 쿠팡 - 개발자 체험<br>🏢 네이버 - 기획자 체험<br>🏢 카카오 - 디자이너 체험<br><br>"체험 정보" 메뉴에서 더 많은 공고를 확인해보세요!';
    } else if (
        lowerMsg.includes("안녕") ||
        lowerMsg.includes("하이") ||
        lowerMsg.includes("hello")
    ) {
        return "안녕하세요! 반갑습니다 😊<br>TRY-CATCH에서 도움이 필요하신 게 있으신가요?";
    } else if (lowerMsg.includes("감사") || lowerMsg.includes("고맙습니다")) {
        return "도움이 되었다니 기쁩니다! 😄<br>다른 궁금한 점이 있으시면 언제든 물어봐주세요!";
    } else {
        return "말씀해주신 내용을 확인했습니다.<br><br>더 자세한 도움이 필요하시면 고객센터(1234-5678)로 연락해주시거나, 아래 주제에 대해 물어봐주세요:<br><br>• 체험 신청 방법<br>• 자기소개서 작성 팁<br>• 인기 기업 추천";
    }
}

// 전송 버튼 클릭
sendBtn.addEventListener("click", (e) => {
    sendMessage(chatInput.value);
});

// Enter 키 전송
chatInput.addEventListener("keypress", (e) => {
    if (e.key === "Enter") {
        sendMessage(chatInput.value);
    }
});

// Esc 키 챗봇 창 닫기
document.addEventListener("keydown", (e) => {
    if (e.key === "Escape") {
        toggle.classList.remove("active");
        container.classList.remove("active");
    }
});
