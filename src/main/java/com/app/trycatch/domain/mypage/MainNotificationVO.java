package com.app.trycatch.domain.mypage;

import com.app.trycatch.audit.Period;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
@Getter
@ToString(callSuper = true)
@SuperBuilder
public class MainNotificationVO extends Period {
    private Long id;
    private Long memberId;
    private String notificationType;
    private String notificationTitle;
    private String notificationContent;
    private boolean notificationIsRead;
    private Long qnaId;
    private Long experienceProgramId;
    private Long skillLogId;
}
