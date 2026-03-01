package com.app.trycatch.dto.skilllog;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class SkillLogAsideDTO {
    private Long id;
    private String memberName;
    private int individualMemberLevel;
    private int individualMemberPoint;
    private int skillLogCount;
    private int questionCount;
}
