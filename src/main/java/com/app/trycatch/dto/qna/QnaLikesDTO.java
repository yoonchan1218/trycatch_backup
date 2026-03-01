package com.app.trycatch.dto.qna;

import com.app.trycatch.domain.qna.QnaLikesVO;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class QnaLikesDTO {
    private Long id;
    private Long memberId;
    private Long qnaId;

    public QnaLikesVO toQnaLikesVO() {
        return QnaLikesVO.builder()
                .id(id)
                .memberId(memberId)
                .qnaId(qnaId)
                .build();
    }
}
