package com.app.trycatch.dto.qna;

import com.app.trycatch.domain.qna.QnaCommentVO;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class QnaCommentDTO {
    private Long id;
    private Long qnaId;
    private Long memberId;
    private String memberName;
    private Long qnaCommentParent;
    private String qnaCommentContent;
    private String logoFilePath;
    private String logoFileName;
    private String commentFilePath;
    private String commentFileName;
    private int replyCount;
    private String createdDatetime;
    private String updatedDatetime;

    public QnaCommentVO toQnaCommentVO() {
        return QnaCommentVO.builder()
                .id(id)
                .qnaId(qnaId)
                .memberId(memberId)
                .qnaCommentParent(qnaCommentParent)
                .qnaCommentContent(qnaCommentContent)
                .build();
    }
}

