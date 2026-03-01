package com.app.trycatch.dto.skilllog;

import com.app.trycatch.common.enumeration.file.FileContentType;
import com.app.trycatch.common.enumeration.skillLog.SkillLogStatus;
import com.app.trycatch.domain.member.MemberVO;
import com.app.trycatch.domain.skilllog.SkillLogVO;
import com.app.trycatch.dto.experience.ExperienceProgramDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @ToString
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class SkillLogDTO {
//    skillLog
    private Long id;
    private String skillLogTitle;
    private String skillLogContent;
    private int skillLogViewCount;
    private SkillLogStatus skillLogStatus;
    private String createdDatetime;
    private String updatedDatetime;

//    member
    private Long memberId;
    private String memberName;

//    like
    private int likeCount;
    private boolean liked;

//    댓글 수
    private int commentCount;

//    experienceProgram
    private Long experienceProgramId;
    private String experienceProgramTitle;
    // 회사명
    private String corpCompanyName;
    // 썸네일
    private String experienceProgramFilePath;
    private String experienceProgramFileName;
    private String experienceProgramFileOriginalName;

//    tag 목록
    private List<TagDTO> tags = new ArrayList<>();
    private String[] tagIdsToDelete;

//    file 목록
    private List<SkillLogFileDTO> skillLogFiles = new ArrayList<>();
    private String[] fileIdsToDelete;

    public SkillLogVO toSkillLogVO() {
        return SkillLogVO.builder()
                .id(id)
                .memberId(memberId)
                .experienceProgramId(experienceProgramId)
                .skillLogTitle(skillLogTitle)
                .skillLogContent(skillLogContent)
                .skillLogViewCount(skillLogViewCount)
                .skillLogStatus(skillLogStatus)
                .createdDatetime(createdDatetime)
                .updatedDatetime(updatedDatetime)
                .build();
    }
}
