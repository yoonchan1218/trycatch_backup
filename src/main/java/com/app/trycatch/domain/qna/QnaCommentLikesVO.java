package com.app.trycatch.domain.qna;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@ToString
@SuperBuilder
public class QnaCommentLikesVO {
    private Long id;
    private Long fkMemberId;
}
