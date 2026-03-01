package com.app.trycatch.domain.qna;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class QnaJobCategoryVO {
    private Long id;
    private String jobCategoryName;
    private String jobCategoryCode;
    private Long jobCategoryBigId;
}
