package com.app.trycatch.dto.skilllog;

import com.app.trycatch.domain.skilllog.SkillLogCommentLikesVO;
import com.app.trycatch.domain.skilllog.SkillLogLikesVO;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class SkillLogCommentLikesDTO {
    private Long id;
    private Long memberId;
    private Long skillLogCommentId;
    private String createdDatetime;
    private String updatedDatetime;

    public SkillLogCommentLikesVO toVO() {
        return SkillLogCommentLikesVO.builder()
                .id(id)
                .memberId(memberId)
                .skillLogCommentId(skillLogCommentId)
                .createdDatetime(createdDatetime)
                .updatedDatetime(updatedDatetime)
                .build();
    }
}
