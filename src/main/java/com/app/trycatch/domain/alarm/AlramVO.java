package com.app.trycatch.domain.alarm;

import com.app.trycatch.audit.Period;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class AlramVO extends Period {
    private Long id;
    private Long memberId;
    private String notificationType;
    private String notificationTitle;
    private String notificationContent;
    private Boolean notificationIsRead;
    private Long qnaId;
    private Long experienceProgramId;
    private Long skillLogId;
}
