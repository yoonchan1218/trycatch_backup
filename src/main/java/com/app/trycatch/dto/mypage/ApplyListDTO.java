package com.app.trycatch.dto.mypage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @NoArgsConstructor
public class ApplyListDTO {
    private Long applyId;
    private Long experienceProgramId;
    private String experienceProgramTitle;
    private String experienceProgramJob;
    private String experienceProgramDeadline;
    private String experienceProgramStatus;
    private String experienceProgramLevel;
    private int experienceProgramRecruitmentCount;
    private int applicantCount;
    private String corpCompanyName;
    private String applyStatus;
    private String applyCreatedDatetime;

    public String getExperienceProgramLevelLabel() {
        if (experienceProgramLevel == null) return "";
        return experienceProgramLevel.toUpperCase() + "유형";
    }

    public String getApplyStatusLabel() {
        if (applyStatus == null) return "-";
        return switch (applyStatus) {
            case "applied" -> "심사중";
            case "document_pass" -> "참여중";
            case "document_fail" -> "심사완료";
            case "activity_done" -> "참여완료";
            case "cancelled" -> "취소";
            default -> applyStatus;
        };
    }

    public String getExperienceProgramStatusLabel() {
        if (experienceProgramStatus == null) return "-";
        return switch (experienceProgramStatus) {
            case "recruiting" -> "모집 중";
            case "closed" -> "마감";
            case "draft" -> "준비중";
            case "cancelled" -> "취소됨";
            default -> experienceProgramStatus;
        };
    }
}
