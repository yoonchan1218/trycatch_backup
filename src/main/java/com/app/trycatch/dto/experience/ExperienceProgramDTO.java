package com.app.trycatch.dto.experience;

import com.app.trycatch.common.enumeration.experience.ExperienceProgramStatus;
import com.app.trycatch.domain.experience.ExperienceProgramVO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class ExperienceProgramDTO {
    private Long id;
    private String experienceProgramTitle;
    private String experienceProgramDescription;
    private String experienceProgramLevel;
    private int experienceProgramRecruitmentCount;
    private String experienceProgramWorkDays;
    private String experienceProgramWorkHours;
    private String experienceProgramDeadline;
    private ExperienceProgramStatus experienceProgramStatus;
    private int experienceProgramViewCount;
    private String experienceProgramJob;
    private String createdDatetime;
    private String updatedDatetime;

    private Long corpId;
    private String corpCompanyName;
    private String corpCeoName;
    private Long corpKindSmallId;
    private String corpCompanyType;

    private List<AddressProgramDTO> addresses = new ArrayList<>();
    private List<ApplyDTO> applies = new ArrayList<>();
    private List<ExperienceProgramFileDTO> experienceProgramFiles = new ArrayList<>();

    /** HTML 태그를 제거한 순수 텍스트 반환 (카드 목록용) */
    public String getDescriptionText() {
        if (experienceProgramDescription == null) return "";
        return experienceProgramDescription.replaceAll("<[^>]*>", "").trim();
    }

    public ExperienceProgramVO toVO() {
        return ExperienceProgramVO.builder()
                .id(id)
                .corpId(corpId)
                .experienceProgramTitle(experienceProgramTitle)
                .experienceProgramDescription(experienceProgramDescription)
                .experienceProgramLevel(experienceProgramLevel)
                .experienceProgramRecruitmentCount(experienceProgramRecruitmentCount)
                .experienceProgramWorkDays(experienceProgramWorkDays)
                .experienceProgramWorkHours(experienceProgramWorkHours)
                .experienceProgramDeadline(experienceProgramDeadline)
                .experienceProgramStatus(experienceProgramStatus)
                .experienceProgramViewCount(experienceProgramViewCount)
                .experienceProgramJob(experienceProgramJob)
                .createdDatetime(createdDatetime)
                .updatedDatetime(updatedDatetime)
                .build();
    }
}
