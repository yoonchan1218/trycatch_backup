package com.app.trycatch.domain.qna;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class QnaJobCategorySmallVO {
    private Long id;
    private Long jobCategoryParentId;
    private String jobCategorySmallName;
    private String jobCategorySmallCode;
}
