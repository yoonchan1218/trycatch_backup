package com.app.trycatch.dto.qna;

import com.app.trycatch.domain.qna.QnaJobCategorySmallVO;
import com.app.trycatch.domain.qna.QnaJobCategoryVO;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class QnaJobCategorySmallDTO {
    private Long id;
    private Long jobCategoryParentId;
    private String jobCategorySmallName;
    private String jobCategorySmallCode;
    private String jobCategoryName;
    private String jobCategoryCode;

    public QnaJobCategorySmallVO toQnaJobCategorySmallVO() {
        return QnaJobCategorySmallVO.builder()
                .id(id)
                .jobCategoryParentId(jobCategoryParentId)
                .jobCategorySmallName(jobCategorySmallName)
                .jobCategorySmallCode(jobCategorySmallCode)
                .build();
    }

    public QnaJobCategoryVO toQnaJobCategoryVO() {
        return QnaJobCategoryVO.builder()
                .jobCategoryCode(jobCategoryCode)
                .jobCategoryName(jobCategoryName)
                .build();
    }


}
