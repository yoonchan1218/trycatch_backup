package com.app.trycatch.dto.skilllog;

import com.app.trycatch.domain.skilllog.TagVO;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class TagDTO {
    private Long id;
    private String tagName;
    private Long skillLogId;

    public TagVO toVO() {
        return TagVO.builder()
                .id(id)
                .tagName(tagName)
                .skillLogId(skillLogId)
                .build();
    }
}
