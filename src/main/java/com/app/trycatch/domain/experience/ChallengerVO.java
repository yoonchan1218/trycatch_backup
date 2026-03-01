package com.app.trycatch.domain.experience;

import com.app.trycatch.audit.Period;
import com.app.trycatch.common.enumeration.experience.ApplyStatus;
import com.app.trycatch.common.enumeration.experience.ChallengerStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(of="id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class ChallengerVO extends Period {
    private Long id;
    private Long applyId;
    private ChallengerStatus challengerStatus;
}
