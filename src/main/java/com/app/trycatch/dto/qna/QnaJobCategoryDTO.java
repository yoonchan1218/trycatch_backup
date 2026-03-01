package com.app.trycatch.dto.qna;

import com.app.trycatch.domain.qna.QnaJobCategoryBigVO;
import com.app.trycatch.domain.qna.QnaJobCategoryVO;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class QnaJobCategoryDTO {
    private Long id;
    private String jobCategoryName;
    private String jobCategoryCode;
    private Long jobCategoryBigId;
    private String jobCategoryBigName;
    private String jobCategoryBigCode;

    public QnaJobCategoryVO qnaJobCategoryVO(){
        return QnaJobCategoryVO.builder()
                .id(id)
                .jobCategoryName(jobCategoryName)
                .jobCategoryCode(jobCategoryCode)
                .jobCategoryBigId(jobCategoryBigId)
                .build();
    };

    public QnaJobCategoryBigVO  qnaJobCategoryBigVO(){
        return QnaJobCategoryBigVO.builder()
                .jobCategoryBigName(jobCategoryBigName)
                .jobCategoryBigCode(jobCategoryBigCode)
                .build();
    }
}
