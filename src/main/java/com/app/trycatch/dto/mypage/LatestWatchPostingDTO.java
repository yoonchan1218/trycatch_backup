package com.app.trycatch.dto.mypage;

import com.app.trycatch.common.enumeration.experience.ExperienceProgramStatus;
import com.app.trycatch.domain.mypage.LatestWatchPostingVO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class LatestWatchPostingDTO {
    private Long id;
    private Long memberId;
    private Long experienceProgramId;
    private String createdDatetime;

    private String experienceProgramTitle;
    private String experienceProgramDeadline;
    private ExperienceProgramStatus experienceProgramStatus;
    private Long corpId;

    public LatestWatchPostingVO toVO() {
        return LatestWatchPostingVO.builder()
                .id(id)
                .memberId(memberId)
                .experienceProgramId(experienceProgramId)
                .createdDatetime(createdDatetime)
                .build();
    }
}
