package com.app.trycatch.dto.mypage;

import com.app.trycatch.domain.mypage.MainNotificationVO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class MyPageNotificationDTO {
    private Long id;
    private Long memberId;
    private String notificationType;
    private String notificationTitle;
    private String notificationContent;
    private boolean notificationIsRead;
    private Long qnaId;
    private Long experienceProgramId;
    private Long skillLogId;
    private String createdDatetime;
    private String updatedDatetime;

    public String getCreatedDateLabel() {
        if (createdDatetime == null) return "-";
        LocalDateTime dt = LocalDateTime.parse(createdDatetime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String date = dt.format(DateTimeFormatter.ofPattern("yy.MM.dd"));
        String day = dt.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.KOREAN);
        return date + " (" + day + ")";
    }

    public String getCreatedTimeLabel() {
        if (createdDatetime == null) return "-";
        LocalDateTime dt = LocalDateTime.parse(createdDatetime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return dt.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public String getNotificationTypeLabel() {
        if ("qna".equals(notificationType)) return "취업톡톡";
        if ("experience_apply".equals(notificationType)) return "체험지원현황";
        if ("skill_log".equals(notificationType)) return "기술블로그";
        return notificationType;
    }

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

    public MainNotificationVO toVO() {
        return MainNotificationVO.builder()
                .id(id)
                .memberId(memberId)
                .notificationType(notificationType)
                .notificationTitle(notificationTitle)
                .notificationContent(notificationContent)
                .notificationIsRead(notificationIsRead)
                .qnaId(qnaId)
                .experienceProgramId(experienceProgramId)
                .skillLogId(skillLogId)
                .build();
    }
}
