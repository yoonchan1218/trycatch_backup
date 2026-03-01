package com.app.trycatch.domain.corporate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CorpInviteVO {
    private Long id;
    private Long corpId;
    private String inviteEmail;
    private String inviteCode;
    private String inviteStatus;
}
