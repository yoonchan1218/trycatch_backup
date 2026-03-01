package com.app.trycatch.domain.experience;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ExperienceProgramFileVO {
    private Long id;
    private Long experienceProgramId;
}
