package com.app.trycatch.domain.qna;


import com.app.trycatch.audit.Period;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper=true)
@EqualsAndHashCode(of="id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class QnaJobCategoryBigVO{
    private Long id;
    private String jobCategoryBigName;
    private String jobCategoryBigCode;
}
