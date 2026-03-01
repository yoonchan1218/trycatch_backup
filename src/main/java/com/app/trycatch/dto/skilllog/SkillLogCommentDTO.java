package com.app.trycatch.dto.skilllog;

import com.app.trycatch.audit.Period;
import com.app.trycatch.common.enumeration.file.FileContentType;
import com.app.trycatch.domain.skilllog.SkillLogCommentVO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @ToString
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class SkillLogCommentDTO{
    private Long id;
    private Long skillLogId;
    private Long skillLogCommentParentId;
    private String skillLogCommentContent;
    private String createdDatetime;
    private String updatedDatetime;

//    답글 수
    private int skillLogCommentChildCount;

//    좋아요
    private int likeCount;
    private boolean liked;

//    file
    private Long fileId;
    private String filePath;
    private String fileName;
    private String fileOriginalName;
    private String fileSize;
    private FileContentType fileContentType;

    private Long fileIdToDelete;

//    member
    private Long memberId;
    private String memberName;
    private int individualMemberLevel;

    public SkillLogCommentVO toVO() {
        return SkillLogCommentVO.builder()
                .id(id)
                .skillLogId(skillLogId)
                .memberId(memberId)
                .skillLogCommentParentId(skillLogCommentParentId)
                .skillLogCommentContent(skillLogCommentContent)
                .createdDatetime(createdDatetime)
                .updatedDatetime(updatedDatetime)
                .build();
    }
}
