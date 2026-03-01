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
public class CorpTeamMemberVO {
    private Long id;                       // tbl_member.id (팀원 회원 ID)
    private Long corpId;                   // 소속 기업 ID
    private String corpTeamMemberStatus;   // active / inactive / wait
    private String inviteCode;             // 초대 인증 코드
}
