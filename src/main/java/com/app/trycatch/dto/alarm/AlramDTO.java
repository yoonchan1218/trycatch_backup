package com.app.trycatch.dto.alarm;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @NoArgsConstructor
public class AlramDTO {
    private Long id;
    private Long memberId;
    private String notificationType;
    private String notificationTitle;
    private String notificationContent;
    private Boolean notificationIsRead;
    private Long qnaId;
    private Long experienceProgramId;
    private Long skillLogId;
    private String createdDatetime;
    private String updatedDatetime;

    public String getExperienceStatusLabel() {
        if (notificationContent == null) return "-";
        return switch (notificationContent) {
            case "applied" -> "심사중";
            case "document_pass" -> "참여중";
            case "document_fail" -> "심사완료";
            case "activity_done" -> "참여완료";
            case "cancelled" -> "취소";
            default -> notificationContent;
        };
    }
}
