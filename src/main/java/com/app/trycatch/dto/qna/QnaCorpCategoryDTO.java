package com.app.trycatch.dto.qna;

import com.app.trycatch.domain.member.CorpVO;
import com.app.trycatch.domain.qna.QnaVO;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class QnaCorpCategoryDTO {
    private String corpCompanyName;
    private Long id;

    public QnaVO toQnaVO() {
        return QnaVO.builder().id(id).build();
    }

    public CorpVO toCorpVO() {
        return CorpVO.builder().corpCompanyName(corpCompanyName).build();
    }
}
