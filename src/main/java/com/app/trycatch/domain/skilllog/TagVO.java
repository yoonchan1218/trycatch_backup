package com.app.trycatch.domain.skilllog;

import com.app.trycatch.audit.Period;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class TagVO {
    private Long id;
    private String tagName;
    private Long skillLogId;
}