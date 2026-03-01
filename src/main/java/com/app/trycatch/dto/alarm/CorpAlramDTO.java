package com.app.trycatch.dto.alarm;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @NoArgsConstructor
public class CorpAlramDTO {
    private Long id;
    private Long corpId;
    private String notificationType;
    private String notificationTitle;
    private String notificationContent;
    private String notificationRelatedType;
    private Long notificationRelatedId;
    private Boolean notificationIsRead;
    private String notificationReceiveAt;
    private String createdDatetime;
    private String updatedDatetime;
}
