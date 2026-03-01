package com.app.trycatch.dto.mypage;

import com.app.trycatch.common.enumeration.experience.ExperienceProgramStatus;
import com.app.trycatch.common.enumeration.member.Status;
import com.app.trycatch.domain.mypage.ScrapPostingVO;
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
public class ScrapPostingDTO {
    private Long id;
    private Long memberId;
    private Long experienceProgramId;
    private Status scrapStatus;
    private String createdDatetime;
    private String updatedDatetime;

    private String experienceProgramTitle;
    private String experienceProgramDeadline;
    private ExperienceProgramStatus experienceProgramStatus;
    private Long corpId;

    public ScrapPostingVO toVO() {
        return ScrapPostingVO.builder()
                .id(id)
                .memberId(memberId)
                .experienceProgramId(experienceProgramId)
                .scrapStatus(scrapStatus)
                .createdDatetime(createdDatetime)
                .updatedDatetime(updatedDatetime)
                .build();
    }
}
