package com.app.trycatch.dto.corporate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CorpTeamMemberDTO {
    private Long id;           // tbl_corp_team_member.id = tbl_member.id
    private Long corpId;
    private String memberName;
    private String memberPhone;
    private String memberEmail;
    private String corpTeamMemberStatus;   // active / inactive / wait
    private String createdDatetime;
}
