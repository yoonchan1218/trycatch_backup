package com.app.trycatch.dto.skilllog;

import com.app.trycatch.domain.skilllog.SkillLogLikesVO;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class SkillLogLikesDTO {
    private Long id;
    private Long memberId;
    private Long skillLogId;
    private String createdDatetime;
    private String updatedDatetime;

    public SkillLogLikesVO toVO() {
        return SkillLogLikesVO.builder()
                .id(id)
                .memberId(memberId)
                .skillLogId(skillLogId)
                .createdDatetime(createdDatetime)
                .updatedDatetime(updatedDatetime)
                .build();
    }
}
