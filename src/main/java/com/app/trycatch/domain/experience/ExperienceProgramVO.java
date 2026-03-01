package com.app.trycatch.domain.experience;

import com.app.trycatch.audit.Period;
import com.app.trycatch.common.enumeration.experience.ExperienceProgramStatus;
import com.app.trycatch.dto.experience.AddressProgramDTO;
import com.app.trycatch.dto.experience.ApplyDTO;
import com.app.trycatch.dto.experience.ChallengerDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(of="id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class ExperienceProgramVO extends Period {
    private Long id;
    private Long corpId;
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
}
