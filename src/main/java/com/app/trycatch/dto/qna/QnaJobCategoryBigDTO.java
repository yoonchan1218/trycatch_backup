package com.app.trycatch.dto.qna;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class QnaJobCategoryBigDTO {
    private Long id;
    private String jobCategoryBigName;
    private String jobCategoryBigCode;
}
